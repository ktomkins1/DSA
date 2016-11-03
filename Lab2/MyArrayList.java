package lab2;

import java.util.Arrays;
/**
 * Creates an Array Based List of element type e
 * @author Kishan
 *
 * @param <T> Object passed in to represent the type of object of this ArrayList
 */
public class MyArrayList<E> implements MyList<E> {
  //Array that is the underlying structure of this class	
  protected E[] a;
  //variable to hold the size of the Array
  private int sizeOfArray;
  //variable that will represent the default size of the array
  private final int startingDefaultSize=10;
  //variable to hold the number of objects in this Array based List
  private int numOfObjectsInArray;
  //variable to hold this type of list
  private MyArrayList<E> thisList;
  /**
   * Constructor in which you pass in size
   * @param size: represents size of underlying Array
   */
  public MyArrayList(int size){
    a=(E[]) new Object[size];
    this.sizeOfArray=size;
  }
  /**
   * Constructor in which nothing is passed in
   */
  public MyArrayList(){
    a= (E[]) new Object[startingDefaultSize];
    this.sizeOfArray=startingDefaultSize;
  }
  /**
   * Overridden add method
   * @param index:index at which you will add item
   * @param t:item to be added in
   */
 @Override public boolean add(int index, E e)  {
    int sizeOfArrayList=size();
    E[] holdArray=Arrays.copyOfRange(a, 0, numOfObjectsInArray);
    if(a.length==numOfObjectsInArray){
      a=resizeArray();
    }
    try{
      isValidIndex(index);
    } catch(ListBoundsException ex){
      if(index>numOfObjectsInArray){
        ex.getMessage();
        return false;
      }
    }    
      a[index]=e;
      for(int i=index;i<numOfObjectsInArray;i++){
        a[i+1]=holdArray[i];      
      }
      numOfObjectsInArray++;
    return true;
  }
  /**
   * Another overridden add method
   * @param t:item to be added in
   */
  @Override public boolean add(E e) {
    int sizeOfArrayList=numOfObjectsInArray;
    if(a.length==numOfObjectsInArray){
      a=resizeArray();
    }
    this.a[numOfObjectsInArray]=e;
    numOfObjectsInArray++;
    return true;
  }
  /**
   * Overriden clear method:
   * clears all values in the Array Based List
   */
  @Override public boolean clear() {
    this.a=(E[]) new Object[a.length];
    numOfObjectsInArray=0;
    return true;
  }
  /**
   * Overriden contains method:
   * returns true if the Array Based List contains the object passed in
   * @param t:the object being searched for
   */
  @Override public boolean contains(E e) {
    boolean returnBoolean = false;
    for(int i=0;i<numOfObjectsInArray;i++){
      if(((E) a[i]).equals(e)){
        returnBoolean=true;
        break;
      }else{
        returnBoolean= false;
      }
    }
    return returnBoolean;    
  }
  /**
   * method to obtain how many objects are in this array
   * @return number of objects in array
   */
  public int getNumOfObjectsInArray() {
    return numOfObjectsInArray;
  }
  /**
   * overridden get method:
   * returns the item at the index passed in
   * @param index: index being accessed
   */
  @Override public E get(int index) {
    E outObject = null;
    try{
      isValidIndex(index);
    } catch(ListBoundsException ex){
      ex.getMessage();
      return outObject;      
    }
      outObject=a[index];
    
    return outObject;
    
  }
  /**
   * overridden indexOf method:
   * returns int detailing which index the object passed in is located at
   * @param t: item being searched for
   */
  @Override public int indexOf(E e) {
    int outIndex = 0;
    for(int i=0;i<a.length;i++){
      if(e.equals(a[i])){
        outIndex=i;
        break;
      }else{
        outIndex=-1;
      }
    }
    return outIndex;    
  }
  /**
   * overridden isEmpty method
   * returns true if the Array Based List is empty
   */
  @Override public boolean isEmpty() {
    if(numOfObjectsInArray<=0){
      return true;
    }else{
      return false;
    }
   
  }
  /**
   * overridden remove method
   * removes the item at the index passed in
   * @param index: index at which item is being removed
   */
  @Override public E remove(int index) {
    E outObject2=null;
    try{
      isValidIndex(index);
    } catch(ListBoundsException ex){
      ex.getMessage();
      return outObject2;
    }   
     swap(index, numOfObjectsInArray-1);
     outObject2=get(a.length);
     set(a.length,null);
     numOfObjectsInArray--;
     return outObject2;    
  }
  /**
   * overridden remove method
   * removes first instance of the object passed in, and returns that object
   * @param o:the item being searched for
   */
  @Override public E remove(Object o) {
    E outObject = null;
    for(int i=0;i<numOfObjectsInArray;i++){
      if(a[i].equals(o)){
        outObject=a[i];
        remove(i);
        break;
      }
    }
    return outObject;
  }
  /**
   * overridden set method
   * will set the specified index to the object passed in, and return true if successful
   * @param index:index at which item will be set at
   * @param element: item to which index will be set to
   */
  @Override public boolean set(int index, Object element) {
    try{
      isValidIndex(index);
    } catch(ListBoundsException ex){
      ex.getMessage();
      return false;      
    }    
      a[index]=(E) element;
      return true;    
  }
  /**
   * overriden size method
   * returns the number of objects in this Array Based List
   */
  @Override public int size() {    
    return numOfObjectsInArray;
  }
  /**
   * overridden subList method:
   * returns a list of values starting at the fromIndex parameter to the toIndex parameter
   * @param fromIndex starting index for subList
   * @param toIndex ending index for subList
   */
  @Override public MyList subList(int fromIndex, int toIndex) {
    int sizeOfNew=1+(toIndex-fromIndex); 
    MyList outArray=new MyArrayList(sizeOfNew);
    try{
      isValidIndex(fromIndex);
      isValidIndex(toIndex);
    } catch (ListBoundsException ex){
      ex.getMessage();
      return null;
    }           
    for(int i=fromIndex;i<toIndex+1;i++){
      outArray.add(this.a[i]);
    }
    return outArray;    
  }
  /**
   * overridden toArray method
   * will return an Array of the data present in this Array Based List
   */
  @Override public E[] toArray() {
    E[] outArray=(E[]) new  Object[numOfObjectsInArray];
    for(int i=0;i<numOfObjectsInArray;i++){
      outArray[i]=a[i];
    }
    return outArray;
  }
  /**
   * overridden swap method
   * will swap object at position 1, with object at position 2. Returns true if successful
   * @param position1 :first position
   * @param position2 :second position
   * @throws ListBoundsException 
   */
  @Override public boolean swap(int position1, int position2){
    try{
      isValidIndex(position1);
      isValidIndex(position2);
    } catch(ListBoundsException ex){
      ex.getMessage();
      return false;
    }
    
    E hold1=get(position1);
    E hold2=get(position2);
    a[position1]=hold2;
    a[position2]=hold1;
    return true;
    
  }
  /**
   * overridden shift method
   * will shift all values in the Array Based List over by the int passed int.
   * Will shift left for negative value, and right for positive value
   * returns true if successful
   * @param positions: number of positions to shift the data
   */
  @Override public boolean shift (int positions) {
    E[] holdArray=Arrays.copyOfRange(a, 0, numOfObjectsInArray); 
    int newPosition;
    if(positions>0){
      boolean needsMod=false;
      for(int i=0;i<numOfObjectsInArray;i++){
        newPosition=i+positions;
        try{
          isValidIndex(newPosition);
        } catch(ListBoundsException ex){
          needsMod=true;
        }
        if(needsMod){
          int q=newPosition%numOfObjectsInArray;
          newPosition=q;
        }
        a[newPosition]=holdArray[i];
      }    
    }else if(positions==0){}
    else{
      positions=positions%numOfObjectsInArray;
        for(int i=0;i<numOfObjectsInArray;i++){
          newPosition=positions+numOfObjectsInArray+i;
          if(newPosition>=numOfObjectsInArray){
            newPosition=newPosition-numOfObjectsInArray;
          }
          a[newPosition]=holdArray[i];
        }    
      }
    return true;
  }
  /**
   * method used to validate the index
   * @param index:index being tested for validity
   * @return true if index is valid
   */
  public boolean isValidIndex(int index)throws ListBoundsException{
    if(index<numOfObjectsInArray&&index>=0){
      return true;
    }
    else{
      throw new ListBoundsException(this,index);
    }
  }
  /**
   * method used to resizeArray, in the event that the underlying Array is at
   * its maximum
   * @return new Array, with the same data as the previous array, but with more
   * indices for values
   */
  public E[] resizeArray(){
    E[] outArray=(E[])new Object[(a.length)*2];
    for(int i=0;i<this.a.length;i++){
      outArray[i]=a[i];
    }
    this.sizeOfArray=outArray.length;
    return outArray;
  }
  /**
   * method used to print out the values in this array
   * used for testing, to be removed.
   */
  public void printOutArray(){
    for(int i=0;i<numOfObjectsInArray;i++){
      System.out.println(a[i].toString());
    }
  }
  /**
   *method used to append the list entered as a parameter to this list
   * @param l List to be appended
   * @return true if successful
   */
  @Override
  public boolean append(MyList<E> l) {
    int newIndex=numOfObjectsInArray-1;
    for(int i=0;i<l.size();i++){
      
      try {
        a[i+newIndex]=l.get(i);
      } catch (ListException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }
}
