package lab2;

/**
 *
 * @author Kevin
 */
public class Node<E>{
    private Node link;
    private E data;
    
    public Node(E data, Node next){
      this.link = next;
      this.data = data;
    }
    
    public boolean hasNext(){
      return link != null;
    }

    /**
     * @return the link
     */
    public Node next() {
      return link;
    }

    /**
     * @return the data
     */
    public E getData() {
      return data;
    }

    /**
     * @param link the link to set
     */
    public void setLink(Node link) {
      this.link = link;
    }

    /**
     * @param data the data to set
     */
    public void setData(E data) {
      this.data = data;
    }
  }
