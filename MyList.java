package lab2;

public interface MyList {
  //Insert Object into specified index of List
  public boolean add(int index, Object o);
  //Adds Object to the end of the List
  public boolean add(Object o);
  //Removes all elements from List
  public boolean clear();
  //Returns true if the list contains specified Object
  public boolean contains(Object o);
  //Return Object at specified index
  public Object get(int index);
  //Returns index of first occurrence of the Object. Returns -1 if no instance is found
  public int indexOf(Object o);
  //Returns true if the list contains no elements
  public boolean isEmpty();
  //Remove Object at the specified index in the list
  public Object remove(int index);
  //Removes first occurrence of the specified Object
  public Object remove(Object o);
  //Replaces specified index with specified Object
  public boolean set(int index,Object element);
  //Returns the number of elements in the List
  public int size();
  //Creates a sublist of the original List, fromIndex(inclusive) till toIndex(exclusive)
  public MyList subList(int fromIndex, int toIndex);
  //Returns an array of the Objects in the list
  public Object[] toArray();
  //Swaps positions of the Objects located in the specified positions
  public boolean swap(int position1,int position2);
  //Changes positions of all elements in the List by specified positions. Positive position results in a shift from left to right
  //Negative position results in a shift from right to left
  public boolean shift(int positions);

}
