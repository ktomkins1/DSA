package avl;

/**
 * Dummy class for searching within;
 *
 * @author Kevin
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable> {
  private Node<T> rootNode;
  
  public BinarySearchTree(T rootData){
    rootNode = new Node<>(rootData, null, Node.ROOT);
  }
  
  public BinarySearchTree(){
    rootNode = null;
  }
  
  public boolean addData(T data){
    return rootNode.createNode(data) != null;
  }
  
  public Node<T> getRoot(){
    return rootNode;
  }
}
