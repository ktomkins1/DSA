package avl;

/**
 *
 * @author Kevin
 * @param <T> Type of thing
 */
public class Node<T extends Comparable> {
  public static final int ROOT = -1;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  
  private int side;
  private T data;
  private Node<T> leftNode;
  private Node<T> rightNode;
  private Node<T> parentNode;
  
  public Node(T data, Node<T> parent, int side){
    this.data = data;
    parentNode = parent;
    this.side = side;
  }
  
  private Node(T data, Node<T> parent){
    this.data = data;
    parentNode = parent;
  }
  
  public void setData(T data){
    this.data = data;
  }
  
  public Node setSide(int side){
    this.side = side;
    return this;
  }
  
  public int getSide(){
    return side;
  }
  
  public Node createNode(T data){
    int compare = data.compareTo(this.data);
    Node<T> newNode = new Node<>(data, this);
    if(compare < 0){
      if(leftNode != null){
        //throw new NodeException();
      }
      leftNode = newNode.setSide(LEFT);
    }else{
      if(compare != 0){
        if(rightNode != null){
          //throw new NodeException();
        }
        rightNode = newNode.setSide(RIGHT);
      }
    }
    return newNode;
  }
  
  public boolean hasRightChild(){
    return rightNode != null;
  }
  
  public boolean hasLeftChild(){
    return leftNode != null;
  }
  
  public Node<T> getRightChild(){
    return rightNode;
  }
  
  public void setRightChild(Node<T> n){
    rightNode = n;
    n.setSide(RIGHT);
  }
  
  public Node<T> getLeftChild(){
    return leftNode;
  }
  
  public void setLeftChild(Node<T> n){
    leftNode = n;
    n.setSide(LEFT);
  }
  
  public Node<T> getParent(){
    return parentNode;
  }
  
  public void setParent(Node<T> n){
    parentNode = n;
  }
  
  public T getData(){
    return data;
  }
  
  public int findRightHeight(){
    if(rightNode == null){
      return -1;
    }
    int right = rightNode.findRightHeight();
    int left = rightNode.findLeftHeight();
    return right > left ? right : left;
  }
  
  public int findLeftHeight(){
    if(leftNode == null){
      return -1;
    }
    int right = leftNode.findRightHeight();
    int left = leftNode.findLeftHeight();
    return right > left ? right : left;  
  }
  
  public int count(){
    return findLeftHeight() + findRightHeight();
  }
}
