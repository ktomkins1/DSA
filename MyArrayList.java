package lab2;

import java.util.Arrays;
/**
 * 
 * @author Kishan
 *
 * @param <T> Object passed in to represent the type of object of this ArrayList
 */
public class MyArrayList<T> implements MyList<T> {
  //Array that is the underlying structure of this class	
  private T[] a;
  //variable to hold the size of the Array
  private int sizeOfArray;
  //variable that will represent the default size of the array
  private final int startingDefaultSize=10;
  //variable to hold the number of objects in this Array based List
  private int numOfObjectsInArray;
  //variable to hold this type of list
  private MyArrayList<T> thisList;
  /**
   * Constructor in which you pass in size
   * @param size: represents size of underlying Array
   */
  public MyArrayList(int size){
    a=(T[]) new Object[size];
    this.sizeOfArray=size;
  }
  /**
   * Constructor in which nothing is passed in
   */
  public MyArrayList(){
    a= (T[]) new Object[startingDefaultSize];
    this.sizeOfArray=startingDefaultSize;
  }

  /**
   * Overridden add method
   * @param index:index at which you will add item
   * @param t:item to be added in
   */
  @Override public boolean add(int index, T t) {
    int sizeOfArrayList=size();
    T[] holdArray=Arrays.copyOfRange(a, index, numOfObjectsInArray);
    if(a.length==numOfObjectsInArray){
      a=resizeArray();
    }
    
    if(isValidIndex(index)){
      a[index]=t;
      for(int i=index;i<numOfObjectsInArray;i++){
        a[i+1]=holdArray[i-1];
        
        
      }
      numOfObjectsInArray++;
      return true;
	  
    }else{      
      throw new ListBoundsException(thisList,index);
      
      return false;
    }
  }
  /**
   * Another overridden add method
   * @param t:item to be added in
   */
  @Override public boolean add(T t) {
    int sizeOfArrayList=numOfObjectsInArray;
    if(a.length==numOfObjectsInArray){
      a=resizeArray();
    }
    this.a[numOfObjectsInArray]=t;
    numOfObjectsInArray++;
    return true;
  }
  /**
   * Overriden clear method:
   * clears all values in the Array Based List
   */
  @Override public boolean clear() {
    this.a=(T[]) new Object[a.length];
    numOfObjectsInArray=0;
    return true;
  }
  /**
   * Overriden contains method:
   * returns true if the Array Based List contains the object passed in
   * @param t:the object being searched for
   */
  @Override public boolean contains(T t) {
    boolean returnBoolean = false;
    for(int i=0;i<numOfObjectsInArray;i++){
      if(a[i].equals(t)){
        returnBoolean=true;
        break;
      }else{
        returnBoolean= false;
      }
    }
    return returnBoolean;
    
  }
  /**
   * overridden get method:
   * returns the item at the index passed in
   * @param index: index being accessed
   */
  @Override public T get(int index) {
    T outObject = null;
    if(isValidIndex(index)){
      outObject=a[index];
    }else{
      throw new ListBoundsException(thisList,index);
    }
    return outObject;
    
  }
  /**
   * overridden indexOf method:
   * returns int detailing which index the object passed in is located at
   * @param t: item being searched for
   */
  @Override public int indexOf(T t) {
    int outIndex = 0;
    for(int i=0;i<a.length;i++){
      if(t.equals(a[i])){
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
  @Override public T remove(int index) {
   if(isValidIndex(index)){
     swap(index, numOfObjectsInArray-1);
     T outObject= get(a.length);
     set(a.length,null);
     numOfObjectsInArray--;
     return outObject;
    }else{
      throw new ListBoundsException(thisList,index);
    }
    
  }
  /**
   * overridden remove method
   * removes first instance of the object passed in, and returns that object
   * @param o:the item being searched for
   */
  @Override public T remove(Object o) {
    T outObject = null;
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
    if(isValidIndex(index)){
      a[index]=(T) element;
      return true;
    }else{
      throw new ListBoundsException(thisList,index);
      return false;
    }
    
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
    if(isValidIndex(fromIndex)&&isValidIndex(toIndex)){       
      for(int i=fromIndex;i<toIndex+1;i++){
      outArray.add(this.a[i]);
      }
    }else{
      throw new ListBoundsException(thisList,index);
    }
    return outArray;
  }
  /**
   * overridden toArray method
   * will return an Array of the data present in this Array Based List
   */
  @Override public T[] toArray() {
    T[] outArray=(T[]) new  Object[numOfObjectsInArray];
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
   */
  @Override public boolean swap(int position1, int position2) {
    if(isValidIndex(position1)&&isValidIndex(position2)){
    T hold1=get(position1);
    T hold2=get(position2);
    a[position1]=hold2;
    a[position2]=hold1;
    return true;
    }else{
      throw new ListBoundsException(thisList,index);
      return false;
    }
  }
  /**
   * overridden shift method
   * will shift all values in the Array Based List over by the int passed int.
   * Will shift left for negative value, and right for positive value
   * returns true if successful
   * @param positions: number of positions to shift the data
   */
  @Override public boolean shift (int positions) {
    T[] holdArray=Arrays.copyOfRange(a, 0, numOfObjectsInArray);  
    
    int newPosition;
    if(positions>0){
      for(int i=0;i<numOfObjectsInArray;i++){
        newPosition=i+positions;
        if(!isValidIndex(newPosition)){
          newPosition-=numOfObjectsInArray;
        }
        a[newPosition]=holdArray[i];
      }
    }
    else{
      for(int i=0;i<numOfObjectsInArray;i++){
        newPosition=i+positions;
        if(newPosition<0){
          newPosition+=numOfObjectsInArray;
        }
        this.a[newPosition]=holdArray[i];
      }
    }   
    return false;
  }
  /**
   * method used to validate the index
   * @param index:index being tested for validity
   * @return true if index is valid
   */
  public boolean isValidIndex(int index){
    if(index<numOfObjectsInArray&&index>=0){
      return true;
    }
    else{
      return false;
    }
  }
  /**
   * method used to resizeArray, in the event that the underlying Array is at
   * its maximum
   * @return new Array, with the same data as the previous array, but with more
   * indices for values
   */
  public T[] resizeArray(){
    T[] outArray=(T[])new Object[(a.length)*2];
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
}