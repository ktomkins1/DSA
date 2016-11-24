package lab3;
/**
 * Driver to test the heap
 * @author Kishan
 *
 */
public class Driver {

  public static void main(String[] args) {
    Heap h=new Heap();
    //Create Heap
    Node rootNode=h.makeHeap(0);
    h.insert(5);
    //Swap the two Nodes present currently
    h.swap(rootNode, rootNode.getLeftChild());
    //Sift the rootDown, since the is parent to 0, will sift 5 down
    h.siftDown(rootNode);
    h.insert(2);
    h.insert(3);
    h.insert(4);
    h.insert(5);
    h.insert(6);
    h.swap(rootNode.getLeftChild(), rootNode.getLeftChild().getRightChild());
    h.insert(7);
    h.insert(8);
    h.insert(9);
    h.insert(10);
    h.insert(11);
    h.insert(12);
    h.insert(13);
    h.insert(14);
    h.swap(rootNode, rootNode.getLeftChild());
    //Delete the rootNode
    h.deleteMin();

    //root node
    System.out.println(rootNode.getData()+" ");
    //1
    System.out.print(rootNode.getLeftChild().getData()+" ");
    System.out.println(rootNode.getRightChild().getData()+" ");
    //2 l
    System.out.print(rootNode.getLeftChild().getLeftChild().getData()+" ");
    System.out.print(rootNode.getLeftChild().getRightChild().getData()+" ");
    //2 r
    System.out.print(rootNode.getRightChild().getLeftChild().getData()+" ");
    System.out.println(rootNode.getRightChild().getRightChild().getData()+" ");
    //3 ll
    System.out.print(rootNode.getLeftChild().getLeftChild().getLeftChild().getData()+" ");
    System.out.print(rootNode.getLeftChild().getLeftChild().getRightChild().getData()+" ");
    //3 lr
    System.out.print(rootNode.getLeftChild().getRightChild().getLeftChild().getData()+" ");
    System.out.print(rootNode.getLeftChild().getRightChild().getRightChild().getData()+" ");
    //3 rl
    System.out.print(rootNode.getRightChild().getLeftChild().getLeftChild().getData()+" ");
    System.out.print(rootNode.getRightChild().getLeftChild().getRightChild().getData()+" ");
    //3rr
    System.out.print(rootNode.getRightChild().getRightChild().getLeftChild().getData()+" ");
    
  }

}
