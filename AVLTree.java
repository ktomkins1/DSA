package avl;

/**
 *
 * @author Kevin
 * @param <T>
 */
public class AVLTree<T extends Comparable> {
  private Node<T> rootNode;
  private int size;
  
  public AVLTree(T rootData){
    rootNode = new Node<>(rootData, null, Node.ROOT);
  }
  
  public AVLTree(){
    rootNode = null;
  }
  
  public Node<T> getRoot(){
    return rootNode;
  }
  
  public void setRoot(T rootData){
    rootNode = new Node<>(rootData, null, Node.ROOT);
    size = rootNode.count();
    rebalanceUpward(rootNode, getMin());
  }
  
  private static Node getNodeWithData(Node node, Comparable data){
    int compare = 0;
    while(true){
      if(node == null){
        return null;
      }
      compare = data.compareTo(node.getData());
      if(compare == 0){
        return node;
      }
      if(compare < 0){
        node = node.getLeftChild();
        continue;
      }
      if(compare > 0){
        node = node.getRightChild();
        continue;
      }
    }
  }
  
  private static Node getSuccessiveNode(Node node){
    if(node == null){
      return node;
    }
    Node r = node.getRightChild();
    if(r == null){
      return r;
    }
    Node old = r;
    Node lr = r.getLeftChild();
    while(lr != null){
      old = lr;
      lr = lr.getLeftChild();
    }
    return old;
  }
  
  private Node addData(Comparable data){
    if(rootNode == null){
      rootNode = new Node(data, null, Node.ROOT);
      return rootNode;
    }
    int compare;
    Node n = rootNode;
    while(true){
      compare = data.compareTo(n.getData());
      if(compare == 0){
        return n;
      }
      if(compare < 0){
        if(n.getLeftChild() == null){
          return n.createNode(data);
        }else{
          n = n.getLeftChild();
        }
      }
      if(compare > 0){
        if(n.getRightChild() == null){
          return n.createNode(data);
        }else{
          n = n.getRightChild();
        }
      }
    }
  }
  
  private int bigger(int l, int r){
    return l > r ? l : r;
  }
  
  private int unbalanced(int l, int r){
    if(l > r + 1){
      return Node.LEFT;
    }
    if(r > l + 1){
      return Node.RIGHT;
    }
    return -1;
  }
  
  private int unbalanced(Node n){
    return unbalanced(n.findLeftHeight(), n.findRightHeight());
  }
  
  public boolean rebalanceUpward(Node n, Comparable newData){
    if(n == null){
      return true;
    }
    Node p = n.getParent();
    int bal = unbalanced(n);
    switch(bal){
      case -1:
        break;
      case Node.LEFT:
        if(newData.compareTo(n.getLeftChild().getData()) <= 0){
          //LL
          return rotateRight(n) && rebalanceUpward(p, newData);
        }else{
          //LR
          return rotateLeft(n.getLeftChild()) && rotateRight(n) && rebalanceUpward(p, newData);
        }
      case Node.RIGHT:
        if(newData.compareTo(n.getRightChild().getData()) >= 0){
          //RR
          return rotateLeft(n) && rebalanceUpward(p, newData);
        }else{
          //LR
          return rotateRight(n.getRightChild()) && rotateLeft(n) && rebalanceUpward(p, newData);
        }
    }
    return rebalanceUpward(p, newData);
  }
  
  private static boolean rotateRight(Node n){
    try{
      Node x = n.getLeftChild();
      Node p = n.getParent();
      int side = n.getSide();
      Node T = x.getRightChild();
      n.setLeftChild(T);
      x.setRightChild(n);
      x.setSide(side);
      x.setParent(p);
      return true;
    }catch(NullPointerException e){
      System.out.println("Right rotation failed.");
      return false;
    }
  }
  
  private boolean rotateLeft(Node n){  
    try{
      Node y = n.getRightChild();
      Node p = n.getParent();
      int side = n.getSide();
      Node T = y.getLeftChild();
      n.setRightChild(T);
      y.setLeftChild(n);
      y.setSide(side);
      y.setParent(p);
      return true;
    }catch(NullPointerException e){
      System.out.println("Left rotation failed.");
      return false;
    }
  }
  
  public static boolean search(BinarySearchTree bst, Comparable data){
    return getNodeWithData(bst.getRoot(), data) != null;
  }
  
  public static AVLTree insert(AVLTree tree, Comparable data){
    Node n = tree.addData(data);
    tree.rebalanceUpward(n.getParent(), data);
    tree.size++;
    return tree;
  }
  public static AVLTree delete(AVLTree tree, Comparable data){
    Node n = getNodeWithData(tree.getRoot(), data);
    tree.deleteNode(n);
    tree.rebalanceUpward(n, data);
    tree.size--;
    return tree;
  }
  
  /**
   * Deletes the given node
   * 
   * @param node Node to be deleted
   * @return true if the delete was successful
   */
  private boolean deleteNode(Node<T> node){
    if(node == null){
      return false;
    }
    int side = node.getSide();
    Node<T> parent = node.getParent();
    Node<T> left = node.getLeftChild();
    Node<T> right = node.getRightChild();
    Node<T> out;
    if(left == null || right == null){
      //one or less children
      if(left == null){
        //right is there alone
        out = right;
      }else{
        //left is there alone
        out = left;
      }
      if(out == null){
        //no children
        switch(side){
          case Node.LEFT:
            parent.setLeftChild(null);
            break;
          case Node.RIGHT:
            parent.setRightChild(null);
            break;
          case Node.ROOT:
            rootNode = null;
            break;
          default:
            return false;
        }
      }else{
        //one child - move it up
        switch(side){
          case Node.LEFT:
            parent.setLeftChild(out.setSide(side));
            break;
          case Node.RIGHT:
            parent.setRightChild(out.setSide(side));
            break;
          case Node.ROOT:
            rootNode = out;
            break;
          default:
            return false;
        }
      }
    }else{
      out = getSuccessiveNode(node);
      node.setData(out.getData());
      deleteNode(out);
    }
    return true;
  }
  
  public static String inOrder(AVLTree tree){
    return inOrder(tree.getRoot());
  }
  
  public static String inOrder(Node n){
    String out = " ";
    Node temp = n.getLeftChild();
    if(temp != null){
      out = out.concat(inOrder(temp) + " ");
    }
    out = out.concat(n.toString() + " ");
    temp = n.getRightChild();
    if(temp != null){
      out = out.concat(inOrder(temp) + " ");
    }
    return out;
  }
  
  public static int count(AVLTree tree, Comparable upper, Comparable lower){
    return count(tree.getRoot(), upper, lower);
  }
  
  private static int count(Node n, Comparable u, Comparable l){
    if(n == null){
      return 0;
    }
    int count = 0;
    int compareU = u.compareTo(n.getData());
    int compareL = l.compareTo(n.getData());
    if(compareU == 0){
      count++;
    }else{
      if(compareL == 0){
        count++;
      }
    }
    if(compareU > 0){
      //the upper limit is greater than the data here, so the RST should be included
      count += count(n.getRightChild(), u, l);
    }
    if(compareL < 0){
      //the lower limit is less than the data here, so the LST should be counted.
      count += count(n.getLeftChild(), u, l);
    }
    return count;
  }
  
  public Comparable getMin(){
    return getMinNode().getData();
  }
  
  public Comparable deleteMin(){
    Node min = getMinNode();
    Comparable out = min.getData();
    deleteNode(min);
    rebalanceUpward(min, out);
    size--;
    return out;
  }
  
  private Node getMinNode(){
    Node out = rootNode;
    Node oneBack = null;
    while(out != null){
      oneBack = out;
      out = out.getLeftChild();
    }
    return oneBack;
  }
  
  public Comparable[] getInorderArray(){
    Comparable[] out = new Comparable[size];
    lvr((T[])out, 0, rootNode);
    return out;
  }
  
  private void lvr(final T[] input, int index, final Node<T> cur){
    if(cur.hasLeftChild()){
      lvr(input, index, cur.getLeftChild());
    }
    input[index++] = cur.getData();
    if(cur.hasRightChild()){
      lvr(input, index, cur.getRightChild());
    }
  }
}
