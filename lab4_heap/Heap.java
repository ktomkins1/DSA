package lab3;

import java.util.ArrayList;
/**
 * My Implementation of a tree based heap
 * @author Kishan
 *
 */
public class Heap implements MyHeap {
  //Contains instance variables to hold the number of Nodes, the height, and a pointer
  //to the rootNode
  int numOfNodes=0;
  Node rootNode;
  int height=0;
  int i=1;
  @Override
  /**
   * Method to initially construct the heap
   * @param value: comparable value of which the first node will be made of
   */
  public Node makeHeap(Comparable value) {
    Node rootNode=new Node(value);
    numOfNodes++;
    this.rootNode=rootNode;
    return rootNode;
  }
  @Override
  /**
   * Method to determine if the heap is empty. Returns false if no nodes present
   */
  public boolean isEmpty() {
    if(numOfNodes>0){
      return false;
    }else{
      return true;
    }
  }
    
  /**
   * Method to insert a node into the heap. Uses the findNodeToInsertAt Method
   * @param value: The value from which the node to be inserted is made
   */
  @Override
  public boolean insert(Comparable value) {
    Node insertHere=new Node(value);
    Node inserted=findNodeToInsertAt(rootNode,insertHere,getHeight(rootNode));
    numOfNodes++;
    height=0;
    siftUp(inserted);
    return true;
   
  }
  /**
   * Method to delete the minimum value in the heap. In this case, the root Node
   */
  @Override
  public boolean deleteMin() {
    delete(rootNode);
    return true;
  }
  /**
   * Method to find the last Node that was inserted
   * @param root: the starting point for the search
   * @return The node that was inserted last
   */
  public Node findLastNode(Node root) {
    Node outNode = null;
    String binary=Integer.toBinaryString(numOfNodes);
    for(int i=1;i<binary.length();i++){
      if(Character.toString(binary.charAt(i)).equals("0")){
        root=root.getLeftChild();
      }else if(Character.toString(binary.charAt(i)).equals("1")){
        root=root.getRightChild();
      }
      outNode=root;
    }
    return outNode;
  }
  /**
   * Method to decrease the value of a node. Since we are decreasing value in this min heap,
   * we only need to sift up.
   * @param key: Node that we are decreasing the value of
   * @param updateValue: The new value that the key should take
   */  
  @Override
  public boolean decreaseKey(Node key, Comparable updateValue) {
    key.setData(updateValue);
    siftUp(key);
    return false;
  }
  /**
   * Method to delete a specific node from the tree. Find the last inserted node,
   * swaps it with the root Node, and removes all links that the last inserted node had,
   * and sifts down
   * @param del:Node to be deleted
   */
  @Override
  public boolean delete(Node del) {
    Node lastInserted=findLastNode(rootNode);
    swap(lastInserted,del);
    if(isLeftChild(lastInserted.getParent(),lastInserted)){
      lastInserted.getParent().setLeftChild(null);
      lastInserted.setParent(null);
    }else if(isRightChild(lastInserted.getParent(),lastInserted)){
      lastInserted.getParent().setRightChild(null);
      lastInserted.setParent(null);
    }
    siftDown(rootNode);
    return true;
  }
  /**
   * Method to find the successor to a specific Node
   * @param beingDeleted:Node that is being deleted, whos successor must be found
   * @return the Node that would succeed beingDeleted
   */
  public Node findSuccessor(Node beingDeleted){
    Node outNode = null;
    if(beingDeleted.getLeftChild()!=null&&beingDeleted.getRightChild()!=null){
    Node oldLeft=beingDeleted.getLeftChild();
    Node oldRight=beingDeleted.getRightChild();
    
    if(oldLeft.getData().compareTo(oldRight.getData())==-1){
      outNode=oldLeft;
    }else if(oldLeft.getData().compareTo(oldRight.getData())>=0){
      outNode=oldRight;
    }else{
      outNode=null;
    }
    }else if(beingDeleted.getRightChild()==null){
      outNode=beingDeleted.getLeftChild();
    }else if(beingDeleted.getLeftChild()==null){
      
    }
    return outNode;
  }
  /**
   * Method to combine two heaps.
   * @param heap: the heap to be merged with this heap
   * @return boolean describing whether or not union was successfull
   */
  @Override
  public boolean union(MyHeap heap) {
    while(heap.findMin()!=null){
    insert(heap.findMin());
    heap.deleteMin();
    
    }
    return true;
  }
  /**
   * Method to find the minimum value of the heap
   * @return the data present in the rootNode
   */
  @Override
  public Comparable findMin() {    
    return rootNode.getData();
  }
  /**
   * Method to siftUp. Starting with startRoot, if the startRoot is less than the
   * parent of the startRoot, the two nodes are swapped. This process is repeated until
   * the startRoot has no parent (the startRoot is the rootNode)
   * @param startRoot:Starting node for the sifting
   * @return The rootNode
   */
  public Node siftUp(Node startRoot) {  
    if(startRoot.getParent() !=  null) { 
      if(startRoot.getData().compareTo(startRoot.getParent().getData())==-1){
        swap(startRoot.getParent(),startRoot);
      }
      return siftUp(startRoot.getParent());
    }
    return startRoot;
  }
  /**
   * Method to siftDown. Starting with startRoot, each successor to the start root is found,
   * and if a successor is found, will swap the two, and repeat the process until
   * no possible successor can be found.
   * @param startRoot Root where the sifting begins
   * @return: The final sucessor the startRoot, after it has been sifted as much as possible
   */
  public Node siftDown(Node startRoot){
    Node holdNode=findSuccessor(startRoot);
    if(holdNode!=null){
      swap(startRoot,holdNode);
      return siftDown(holdNode);
    }
    return holdNode;
  }
  /**
   * Method to get the height of the tree, used in other methods
   * @param rootNode: The root node of the heap
   * @return:Integer representing the height of the tree, with 0 representing 1 level.
   */
  public int getHeight(Node rootNode){
    if(rootNode.getLeftChild()!=null){
      height++;
      return getHeight(rootNode.getLeftChild());
    }
    return height;
  }
  /**
   * Method used in the insert method, to find where a node should be inserted, and 
   * sever links
   * @param startNode:Node where we begin the search, changed in the recursive steps
   * @param beingInserted:The Node that is being inserted
   * @param currentHeight:The current height of the tree, changed in the recursive steps
   * @return The Node where the insertedNode is inserted to
   */
  public Node findNodeToInsertAt(Node startNode,Node beingInserted,int currentHeight){    
    Node outNode = null;    
      if(startNode.getLeftChild()==null){           //If No Left Child, insert at the left Child
        outNode=beingInserted;
        startNode.setLeftChild(outNode);
        outNode.setParent(startNode);
      }else if(startNode.getRightChild()==null){   //If not Right Child, insert at the right child
        outNode=beingInserted;
        startNode.setRightChild(outNode);
        outNode.setParent(startNode);
      }else{  
        //Else we need to go deeper into the tree
        int maxNumOfNodes=(int) Math.pow(2, currentHeight+1)-1;
        int numOfNodesBelow=count(startNode);
        if(maxNumOfNodes==numOfNodesBelow){
          return findNodeToInsertAt(startNode.getLeftChild(),beingInserted,currentHeight-1);
        }else if(maxNumOfNodes-numOfNodesBelow>Math.pow(2, currentHeight)/2){          
          return findNodeToInsertAt(startNode.getLeftChild(),beingInserted,currentHeight-1);
        }else if(maxNumOfNodes-numOfNodesBelow<=Math.pow(2, currentHeight)/2){          
          return findNodeToInsertAt(startNode.getRightChild(),beingInserted,currentHeight-1);
      }
    }
      return outNode;
  }
  /**
   * Method to swap two nodes-Swaps Data values
   * @param originalParent:The original Parent Node
   * @param originalChild:The original Child Node
   */
  public void swap(Node originalParent,Node originalChild){
    boolean updateRoot=false;
      
      if(originalParent.equals(rootNode)){
        updateRoot=true;
      }
      Comparable a=originalParent.getData();
      Comparable b=originalChild.getData();
      originalParent.setData(b);
      originalChild.setData(a);
    if(updateRoot){
      updateRoot(originalParent);
    }
  }
  /**
   * Method to determine if the input ChildNode is the left child of the parent node
   * @param parentNode:Parent node whos child is being tested
   * @param childNode:Child node whos position relative to parent is tested
   * @return:boolean determining if the child is a left child of parent
   */
  public boolean isLeftChild(Node parentNode,Node childNode){
    if(parentNode.getLeftChild().equals(childNode)){
      return true;
    }else if(parentNode.getRightChild().equals(childNode)){
      return false;
    }else{
      System.out.println("Got to this part in is left child");
      return false;
    }
  }
  /**
   *Method to determine if the input ChildNode is the right child of the parent node
   * @param parentNode:Parent node whos child is being tested
   * @param childNode:Child node whos position relative to parent is tested
   * @return:boolean determining if the child is a right child of parent
   */
  public boolean isRightChild(Node parentNode,Node childNode){
    if(parentNode.getRightChild().equals(childNode)){
      return true;
    }else if(parentNode.getLeftChild().equals(childNode)){
      return false;
    }else{
      System.out.println("Got to this part in is left child");
      return false;
    }
  }
  /**
   * Method to count the number of nodes below the start node in the heap
   * @param startingNode: Starting Node, whos nodes below are determined
   * @return:int representing the number of nodes below the startnode
   */
  public int count(Node startingNode) {
    int c = 1;                                      
    if ( startingNode.getRightChild() != null ){ 
      c +=count(startingNode.getRightChild());       
    }
    if ( startingNode.getLeftChild() != null ) {
      c += count(startingNode.getLeftChild());         
    }
    return c;
  }
  /**
   * Method to update the root of the heap
   * @param newRoot:The new Root
   */
  public void updateRoot(Node newRoot){
    this.rootNode=newRoot;
  }
  

}
