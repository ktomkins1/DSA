package lab2;

public interface MyList<T> {
  //Insert T into specified index of List
  public boolean add(int index, T o);
  
  //Adds T to the end of the List
  public boolean add(T o);
  
  //Removes all elements from List
  public boolean clear();
  
  //Returns true if the list contains specified T
  public boolean contains(T o);
  
  //Return T at specified index
  public T get(int index) throws ListException;
  
  //Returns index of first occurrence of the T. Returns -1 if no instance is found
  public int indexOf(T o);
  
  //Returns true if the list contains no elements
  public boolean isEmpty();
  
  //Remove T at the specified index in the list
  public T remove(int index) throws ListException;
  
  //Removes first occurrence of the specified T
  public T remove(T o) throws ListException;
  
  //Replaces specified index with specified T
  public boolean set(int index,T element);
  
  //Returns the number of elements in the List
  public int size();
  
  //Creates a sublist of the original List, fromIndex(inclusive) till toIndex(exclusive)
  public MyList subList(int fromIndex, int toIndex) throws ListException;
  
  //Returns an array of the Ts in the list
  public T[] toArray();
  
  //Swaps positions of the Ts located in the specified positions
  public boolean swap(int position1,int position2);
  
  //Changes positions of all elements in the List by specified positions. Positive position results in a shift from left to right
  //Negative position results in a shift from right to left
  public boolean shift(int positions);
  
  //Appends the List by the appropriate sub list
  public boolean append(MyLinkedList ll);
  public boolean append(MyArrayList al);
  public boolean append(MyCircularLinkedList cll);
  public boolean append(MyList l);
}