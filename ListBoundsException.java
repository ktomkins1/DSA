package lab2;

/**
 *
 * @author Kevin
 */


public class ListBoundsException extends Exception{
  private final MyList list;
  private final int index;
  
  public ListBoundsException(MyList list, int index){
    this.list = list;
    this.index = index;
  }
  @Override
  public String getMessage(){
    String type;
    if(list instanceof MyLinkedList){
      type = "Linked List";
    }
    /**
    if(list instanceof MyCircularLinkedList){
      
    }
    if(list instanceof MyArrayList){
      
    }
    */
    return "Illegal bounds for list of type " + list;
  }
}
