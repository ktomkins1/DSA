package lab2;

import java.util.Iterator;

/**
 * Creates a linked list of elements of type E
 *
 * @author Kevin
 * @param <E> The class which this list will be a collection of
 */
public class MyLinkedList<E> implements MyList<E>, Iterable{
  private Node<E> root;
  private Node<E> tail;
  private int size;
  
  /**
   * Creates an empty linked list
   */
  public MyLinkedList(){
    root = null;
    tail = null;
    size = 0;
  }
  
  private MyLinkedList(Node<E> root, int size){
    this.root = root;
    this.size = size;
  }
  
  private Node<E> getNode(int checkedIndex){
    if(size < 1){
      return null;
    }
    Node<E> node = root;
    for(int i = 1; i < checkedIndex; i++){
      if(node.hasNext()){
        node = node.next();
      }
    }
    return node;
  }
  
  private MyLinkedList<E> getListAfterNode(Node<E> n, int indexOf){
    MyLinkedList<E> out = new MyLinkedList<>();
    for(; !n.equals(tail); n = n.next()){
      out.add(n.getData());
    }
    return out;
  }
  
  private Node<E> getFirstNodeWithElement(E element){
    for(Node n = root; n != null; n = n.next()){
      if(n.getData().equals(element)){
        return n;
      }
    }
    return null;
  }
  
  private void checkBounds(int index) throws ListBoundsException{
    if(index > size || index < 1){
      throw new ListBoundsException(this, index);
    }
  }
  
  public Node getHead(){
    return root;
  }
  
  public Node getTail(){
    return tail;
  }
  
  /**
   * Adds an element to the list at a certain index.
   * 
   * The element will be added such that its index will now be that which is passed in.
 The n which contains the new element will point to the n which was previously at
 that index, should that index have existed.
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
        Node<E> n = new Node<>(element, null);
        tail.setLink(n);
        tail = n;
      }else{
        there = getNode(index - 1);
        next = there.next();
        there.setLink(new Node<>(element, next));
      }
    }else{
      next = root;
      root = new Node<>(element, next);
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
    Node<E> node = new Node<>(element, null);
    if(tail == null){
      if(root == null){
        root = node;
      }
      tail = getNode(size);
    }
    if(size > 0){
      tail.setLink(node);
    }else{
      root = node;
    }
    tail = node;
    size++;
    return true;
  }

  /**
   * Clears the list
   *
   * @return True
   */
  @Override public boolean clear() {
    root = null;
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
   * @throws lab2.ListException Thrown when the index is out of bounds
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
   * Gets the index of the first n with a certain element
   *
   * @param element The element to search for
   * @return The index of the first element
   * @throws lab2.ListException if the element is not found
   */
  @Override public int indexOf(E element){
    if(root == null){
      return -1;
    }
    int i = 1;
    boolean found = false;
    if(tail.getData().equals(element)){
      return size;
    }
    for(Node<E> n = root; n.hasNext(); n = n.next()){
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
    return root == null;
  }

  /**
   * Removes an element at a certain index
   *
   * @param index The index to remove at
   * @return The element removed, or null if the index is out of bounds
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
    if(root.getData().equals(element)){
      root = root.next();
      size--;
      return out;
    }
    for(Node<E> n = root; n != null; n = n.next()){
      if(n.next().getData().equals(element)){
        out = (E) n.next().getData();
        if(n.next().equals(tail)){
          tail = n;
        }
        n.setLink(n.next().next());
        size--;
        break;
      }
    }
    if(out == null){
      throw new ListException();
    }
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
   */
  @Override public MyLinkedList<E> subList(int fromIndex, int toIndex) throws ListException{
    try {
      checkBounds(fromIndex);
      checkBounds(toIndex);
      if(toIndex < fromIndex){
        throw new ListBoundsException(this, fromIndex);
      }
    } catch (ListBoundsException ex) {
      throw new ListException();
    }
    MyLinkedList<E> out = new MyLinkedList<>();
    int outSize = toIndex - fromIndex + 1;
    Node<E> n = getNode(fromIndex);
    for(int i = 0; i < outSize; i++){
      out.add(n.getData());
      n = n.next();
    }
    return out;
  }

  /**
   * Converts the Linked List to an array of elements of type E
   *
   * @return An array of E elements
   */
  @Override public E[] toArray() {
    E[] out = (E[]) new Object[size];
    int i = 0;
    for(Node<E> n = root; n != null; n = n.next()){
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
      cShift(true, positions);
    }else{
      if(-positions > size){
        return shift(positions + size);
      }
      cShift(false, -positions);
    }
    return true;
  }
  
  private void cShift(boolean dir, int positions){
    if(tail == null){
      tail = getNode(size);
    }
    tail.setLink(root);
    if(dir){
      tail = getNode(size - positions);
    }else{
      tail = getNode(positions);
    }
    root = tail.next();
    tail.setLink(null);
  }

  private boolean append1(MyLinkedList ll) {
    if(ll.size() < 1){
      return true;
    }
    this.tail.setLink(ll.root);
    this.tail = ll.tail != null ? ll.tail : tail;
    return true;
  }

  private boolean append2(MyArrayList al) {
    for(E elem : (E[]) al.toArray()){
      add(elem);
    }
    return true;
  }

  private boolean append3(MyCircularLinkedList cll) {
    if(cll.size() < 1){
      return true;
    }
    tail.setLink(cll.getTail().next());
    tail = cll.getTail();
    tail.setLink(null);
    return true;
  }
  
  @Override public boolean append(MyList l){
    if(l instanceof MyLinkedList){
      return append1((MyLinkedList) l);
    }else if(l instanceof MyCircularLinkedList){
      return append3((MyCircularLinkedList) l);
    }else if(l instanceof MyArrayList){
      return append2((MyArrayList) l);
    }else{
      return false;
    }
  }

  @Override public Iterator iterator() {
    return new Iterator<E> () {
      private Node<E> oneBack = null;
      private Node<E> current = new Node(null, root);

      @Override public boolean hasNext() {
        return current.hasNext();
      }

      @Override public E next() {
        oneBack = current;
        current = current.next();
        E out = current.getData();
        return out;
      }

      @Override public void remove() {
        if(oneBack == null){
          return;
        }
        oneBack.setLink(current.next());
      }
    };
  }
}