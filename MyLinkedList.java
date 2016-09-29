package lab2;

/**
 * Creates a linked list of elements of type E
 *
 * @author Kevin
 * @param <E> The class which this list will be a collection of
 */
public class MyLinkedList<E> implements MyList<E>{
  private Node<E> root;
  private Node<E> tail;
  private int size;
  
  /**
   * Creates an empty linked list
   */
  public MyLinkedList(){
    root = null;
    size = 0;
  }
  
  private MyLinkedList(Node root, int size){
    this.root = root;
    this.size = size;
  }
  
  private Node<E> getNode(int checkedIndex){
    Node<E> node = root;
    for(int i = 1; i < checkedIndex; i++){
      node = node.next();
    }
    return node;
  }
  
  private MyLinkedList<E> getListAfterNode(Node<E> n, int indexOf){
    MyLinkedList out = new MyLinkedList<>(n, size - indexOf);
    return out;
  }
  
  private Node<E> getFirstNodeWithElement(E element){
    for(Node n = root; n.hasNext(); n = n.next()){
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
      return false;
    }
    Node<E> there;
    Node<E> next;
    if(index > 1){
      there = getNode(index - 1);
      next = there.next();
      there.setLink(new Node(element, next));
    }else{
      next = root;
      root = new Node(element, next);
    }
    return true;
  }

  /**
   * Adds an element to the list
   *
   * @param element Element to add
   * @return true if the operation succeeds
   */
  @Override public boolean add(E element) {
    Node<E> node = new Node(element, null);
    if(size > 0){
      tail.setLink(node);
    }else{
      root = node;
    }
    tail = node;
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
   * @return The element found there or null if the index is out of bounds
   */
  @Override public E get(int index){
    try {
      checkBounds(index);
    } catch (ListBoundsException ex) {
      return null;
    }
    return getNode(index).getData();
  }

  /**
   * Gets the index of the first node with a certain element
   *
   * @param element The element to search for
   * @return The index of the first element or 0 if the 
   */
  @Override public int indexOf(E element) {
    if(root == null){
      return 0;
    }
    Node<E> node = root;
    int i = 1;
    while(!node.getData().equals(element) && node.hasNext()){
      node = node.next();
      i++;
    }
    return i % size;
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
  @Override public E remove(int index) {
    try {
      checkBounds(index);
    } catch (ListBoundsException ex) {
      return null;
    }
    Node<E> preDelete = getNode(index - 1);
    E element = (E) preDelete.next().getData();
    preDelete.setLink(preDelete.next().next());
    return element;
  }

  /**
   * Removes the first instance of a certain element from the list
   *
   * @param element The element to be searched for and removed
   * @return The element which was removed, or null if it was not removed
   */
  @Override public E remove(E element) {
    E out = null;
    for(Node n = root; n.hasNext(); n = n.next()){
      out = (E) n.next().getData();
      if(out.equals(element)){
        n.setLink(n.next().next());
        break;
      }
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
   * Gets the subset list from one index in the list to another
   *
   * @param fromIndex The lower bounds of the subset
   * @param toIndex The upper bounds of the subset
   * @return The list form of the subset
   */
  @Override public MyList subList(int fromIndex, int toIndex) {
    try {
      checkBounds(fromIndex);
      checkBounds(toIndex);
      if(toIndex > fromIndex){
        throw new ListBoundsException(this, fromIndex);
      }
    } catch (ListBoundsException ex) {
      return null;
    }
    MyLinkedList<E> out = new MyLinkedList<>();
    Node<E> n = getNode(fromIndex);
    for(int i = 0; i < toIndex - fromIndex; i++){
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
    if(size < 1){
      return null;
    }
    E[] out = (E[]) new Object[size];
    int i = 0;
    for(Node<E> n = root; n.hasNext(); n = n.next()){
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
   * Shift the list by a certain amount of elements, a LS of more than the array size clears.
   *
   * @param positions The number of positions to shift.  Positive means RS, negative means LS
   * @return True
   */
  @Override public boolean shift(int positions) {
    if(positions == 0){
      return true;
    }
    if(positions > 0){
      Node<E> n = root;
      for(int i = 0; i < positions; i++){
        n = new Node<>(null, n);
      }
    }else{
      if(-positions <= size){
        root = getNode(-positions + 1);
      }else{
        root = null;
      }
    }
    return true;
  }
}