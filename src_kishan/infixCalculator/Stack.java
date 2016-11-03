package infixCalculator;
import java.util.ArrayList;

public class Stack<E> {
  
  private ArrayList<E> thisArrayList;
  
  public Stack(){
    thisArrayList=new ArrayList();
  }
  
  public void push(E e){
   thisArrayList.add(e);
  }
  
  public E pop(){
    Object outObject=null;
    int lastItemInArray=thisArrayList.size()-1;
    outObject= thisArrayList.get(lastItemInArray);
    thisArrayList.remove(lastItemInArray);
    return (E) outObject;
  }
  
  public boolean isEmpty(){
    if(thisArrayList.size()==0){
      return true;
    }else{
      return false;
    }
  }
  
  public int numOfItemsInStack(){
    return thisArrayList.size();
  }
  
  public E peek(){
    return this.thisArrayList.get(numOfItemsInStack()-1);
  }
  
  
  
}
