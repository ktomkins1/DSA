package lab2;

public interface MyList {
	
  public boolean add(int index, Object o);
  
  public boolean add(Object o);
  
  public boolean clear();
  
  public boolean contains(Object o);
  
  public Object get(int index);
  
  public int indexOf(Object o);
  
  public boolean isEmpty();
  
  public Object remove(int inedx);
  
  public Object remove(Object o);
  
  public boolean set(int index,Object element);
  
  public int size();
  
  public MyList subList(int fromIndex, int toIndex);
  
  public Object[] toArray();
  
  public boolean swap(int position1,int position2);
  
  public boolean shift(int positions);

}
