package lab5;

import java.util.Iterator;
import lab2.*;



public class BucketList<E extends Comparable>{
  private MyLinkedList<Holder<E>> list;
  Iterator<Holder<E>> it;
  
  public BucketList(){
    list = new MyLinkedList<Holder<E>>();
  }
  
  public void add(E item){
    it = list.iterator();
    Holder<E> h;
    int radix = 100;
    while(true){
      h = it.next();
      if(h.getRadix() > radix){
        h.add(item);
        return;
      }
      radix *= 10;
      if(!it.hasNext()){
        list.add(new Holder(radix));
      }
    }
  }
  
  public void sortAll(){
    it = list.iterator();
    Holder<E> h;
    while(it.hasNext()){
      h = it.next();
      Sort.generalSort(h.getArray(), 0);
    }
  }
  
  public void bucketsToArray(Comparable[] output){
    it = list.iterator();
    int index = 0;
    Holder<E> h;
    while(it.hasNext()){
      h = it.next();
      for(E e : h.getArray()){
        output[index++] = e;
      }
    }
  }
  
  private class Holder<E>{
    int radix;
    MyArrayList<E> al;
    
    public Holder(int radix){
      this.radix = radix;
      al = new MyArrayList<>();
    }
    
    public void add(E item){
      al.add(item);
    }
    
    public E[] getArray(){
      return al.toArray();
    }
    
    public int getRadix(){
      return radix;
    }
  }
}
