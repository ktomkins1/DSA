package lab2;

import java.util.Iterator;

/**
 *
 * @author Kevin
 */
public class MyCircularLinkedList<E> implements MyList<E>, Iterable{
  private Node<E> tail;
  private int size;
  
  /**
   * Creates an empty linked list
   */
  public MyCircularLinkedList(){
    tail = null;
    size = 0;
  }
  
  private void setTail(Node<E> tail, Node<E> root){
    this.tail = tail;
    tail.setLink(root);
  }
  
  private Node<E> getNode(int checkedIndex){
    Node<E> node = tail.next();
    if(checkedIndex == 0){
      return tail;
    }
    for(int i = 1; i < checkedIndex; i++){
      node = node.next();
    }
    return node;
  }
  
  private MyCircularLinkedList<E> getListAfterNode(Node<E> n, int indexOf){
    MyCircularLinkedList out = new MyCircularLinkedList<>();
    for(; !n.equals(tail); n = n.next()){
      out.add(n.getData());
    }
    return out;
  }
  
  private Node<E> getFirstNodeWithElement(E element){
    Node<E> n = tail.next();
    boolean contin;
    do{
      if(n.getData().equals(element)){
        return n;
      }
      contin = !n.equals(tail);
      n = n.next();
    }while(contin);
    return null;
  }
  
  private void checkBounds(int index) throws ListBoundsException{
    if(index > size || index < 1){
      throw new ListBoundsException(this, index);
    }
  }
  
  public Node getTail(){
    return tail;
  }
  
  /**
   * Adds an element to the list at a certain index.
   * 
   * The element will be added such that its index will now be that which is passed in.
   * The node which contains the new element will point to the node which was previously at
   * that index, should that index have existed.
   *
   * @param index The index at which to add
   * @param element The element to add
   * @return True if the add was successful or false if not (when the index is out of bounds)
   */
  @Override public boolean add(int index, E element){
    try {
      checkBounds(index);
    } catch (ListBoundsException ex) {
      if(index != size + 1){
        return false;
      }
    }
    Node<E> there;
    Node<E> next;
    if(index > 1){
      if(index == size + 1){
        Node<E> n = new Node<>(element, tail.next());
        tail.setLink(n);
        tail = n;
      }else{
        there = getNode(index - 1);
        next = there.next();
        there.setLink(new Node<>(element, next));
      }
    }else{
      next = tail.next();
      tail.setLink(new Node<>(element, next));
    }
    size++;
    return true;
  }

  /**
   * Adds an element to the list
   *
   * @param element Element to add
   * @return true if the operation succeeds
   */
  @Override public boolean add(E element) {
    Node<E> n = new Node<>(element, null);
    if(size == 0){
      tail = n;
      tail.setLink(tail);
    }else{
      n.setLink(tail.next());
      tail.setLink(n);
      tail = n;
    }
    size++;
    return true;
  }

  /**
   * Clears the list
   *
   * @return True
   */
  @Override public boolean clear() {
    tail = null;
    size = 0;
    return true;
  }

  /**
   * Finds whether or not the list contains an element;
   *
   * @param element
   * @return
   */
  @Override public boolean contains(E element) {
    if(size < 1){
      return false;
    }
    return getFirstNodeWithElement(element) != null;
  }

  /**
   * Gets the element at a certain index
   * 
   * @param index The element's index
   * @return The element found there
   * @throws lab2.ListException if the index is out of bounds
   */
  @Override public E get(int index) throws ListException{
    try {
      checkBounds(index);
    } catch (ListBoundsException ex) {
      throw new ListException();
    }
    return getNode(index).getData();
  }

  /**
   * Gets the index of the first node with a certain element
   *
   * @param element The element to search for
   * @return The index of the first element matching the input
   * @throws lab2.ListException if the element is not found
   */
  @Override public int indexOf(E element){
    if(isEmpty()){
      return -1;
    }
    int i = 1;
    boolean found = false;
    if(tail.getData().equals(element)){
      return size;
    }
    for(Node<E> n = tail.next(); !n.equals(tail); n = n.next()){
      if(n.getData().equals(element)){
        found = true;
        break;
      }
      i++;
    }
    return found ? i : -1;
  }

  /**
   * Returns if the list is empty or not
   *
   * @return True if the list is empty
   */
  @Override public boolean isEmpty() {
    return tail == null;
  }

  /**
   * Removes an element at a certain index
   *
   * @param index The index to remove at
   * @return The element removed
   * @throws lab2.ListException if the index is out of bounds
   */
  @Override public E remove(int index) throws ListException{
    try {
      checkBounds(index);
    } catch (ListBoundsException ex) {
      throw new ListException();
    }
    Node<E> preDelete = getNode(index - 1);
    if(index == size){
      tail = preDelete;
    }
    E element = (E) preDelete.next().getData();
    preDelete.setLink(preDelete.next().next());
    size--;
    if(element == null){
      throw new ListException();
    }
    return element;
  }

  /**
   * Removes the first instance of a certain element from the list
   *
   * @param element The element to be searched for and removed
   * @return The element which was removed, or null if it was not removed
   * @throws lab2.ListException
   */
  @Override public E remove(E element) throws ListException{
    E out = null;
    Node<E> root = tail.next();
    if(root.getData().equals(element)){
      tail.setLink(root.next());
      size--;
      return out;
    }
    for(Node<E> n = tail.next(); !n.equals(tail); n = n.next()){
      if(n.next().getData().equals(element)){
        out = (E) n.next().getData();
        if(n.next().equals(tail)){
          tail = n;
        }
        n.setLink(n.next().next());
      }
    }
    if(out == null){
      throw new ListException();
    }
    size--;
    return out;
  }

  /**
   * Sets the element to replace the data at a certain index
   *
   * @param index The index to reset
   * @param element The element to place
   * @return True if the operation succeeds, false if the index was out of bounds
   */
  @Override public boolean set(int index, E element) {
    try {
      checkBounds(index);
    } catch (ListBoundsException ex) {
      return false;
    }
    if(index == size){
      tail.setData(element);
      return true;
    }
    getNode(index).setData(element);
    return true;
  }

  /**
   * Finds the size of the list
   *
   * @return The size
   */
  @Override public int size() {
    return size;
  }

  /**
   * Gets the subset list from one index in the list to another, inclusive
   *
   * @param fromIndex The lower bounds of the subset
   * @param toIndex The upper bounds of the subset
   * @return The list form of the subset
   * @throws lab2.ListException if the indices are invalid
   */
  @Override public MyCircularLinkedList subList(int fromIndex, int toIndex) throws ListException {
    try {
      checkBounds(fromIndex);
      checkBounds(toIndex);
      if(toIndex < fromIndex){
        throw new ListBoundsException(this, fromIndex);
      }
    } catch (ListBoundsException ex) {
      throw new ListException();
    }
    Node<E> n = getNode(fromIndex - 1);
    MyCircularLinkedList<E> out = getListAfterNode(n, fromIndex - 1);
    int outSize = toIndex - fromIndex + 1;
    if(toIndex != size){
      out.setTail(out.getNode(outSize), out.tail.next());
    }
    return out;
  }

  /**
   * Converts the Linked List to an array of elements of type E
   *
   * @return An array of E elements
   */
  @Override public E[] toArray() {
    if(size < 1){
      return null;
    }
    E[] out = (E[]) new Object[size];
    int i = 0;
    for(Node<E> n = tail.next(); !n.equals(tail); n = n.next()){
      out[i++] = n.getData();
    }
    return out;
  }

  /**
   * Swaps the elements at certain positions
   * 
   * If the positions are the same, no action is taken, and true is returned.
   *
   * @param position1 The first position to swap from
   * @param position2 The second position to swap from
   * @return True or false depending on if the positions are valid
   */
  @Override public boolean swap(int position1, int position2) {
    try {
      checkBounds(position1);
      checkBounds(position2);
    } catch (ListBoundsException ex) {
      return false;
    }
    if(position1 == position2){
      return true;
    }
    Node<E> n1;
    Node<E> n2;
    if(position1 < position2){
      n1 = getNode(position1);
      n2 = getListAfterNode(n1, position1).getNode(position2 - position1);
    }else{
      n2 = getNode(position2);
      n1 = getListAfterNode(n2, position2).getNode(position1 - position2);
    }
    E data = n1.getData();
    n1.setData(n2.getData());
    n2.setData(data);
    return true;
  }

  /**
   * Shift (circular) the list by a certain amount of elements
   *
   * @param positions The number of positions to shift.  Positive means RS, negative means LS
   * @return True
   */
  @Override public boolean shift(int positions) {
    if(positions == 0){
      return true;
    }
    if(positions > 0){
      if(positions > size){
        return shift(positions - size);
      }
      tail = getNode(size - positions);
    }else{
      if(-positions > size){
        return shift(positions + size);
      }
      tail = getNode(-positions);
    }
    return true;
  }
  
  @Override public boolean append(MyList<E> l){
    if(l instanceof MyCircularLinkedList){
      for(Object elem : (MyCircularLinkedList)l){
        this.add((E) elem);
      }
    }else if(l instanceof MyLinkedList){
      for(Object elem : (MyLinkedList)l){
        this.add((E) elem);
      }
    }else{
      for(E elem : l.toArray()){
        this.add(elem);
      }
      return true;
    }
    return true;
  }

  @Override public Iterator iterator() {
    return new Iterator<E> () {
      private Node<E> oneBack = null;
      private Node<E> current = tail;
      private boolean tailGiven = false;

      @Override public boolean hasNext() {
        return current.hasNext() && !tailGiven;
      }

      @Override public E next() {
        oneBack = current;
        current = current.next();
        E out = current.getData();
        if(current.equals(tail)){
          tailGiven = true;
        }
        return out;
      }

      @Override public void remove() {
        if(oneBack == null){
          return;
        }
        if(current.equals(tail)){
          tail = oneBack;
        }
        oneBack.setLink(current.next());
      }
    };
  }
}
