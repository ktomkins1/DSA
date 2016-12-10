package lab5;

/**
 *
 * @author Kevin
 */


public class Stack {
  private StackNode top;
  
  public Stack(){
    top = null;
  }
  
  public void push(Object o){
    top = new StackNode(top, o);
  }
  
  public Object pop(){
    if(top == null) return null;
    Object out = top.data();
    top = top.last();
    return out;
  }
  
  private class StackNode {
    private StackNode last;
    private Object data;

    public StackNode(StackNode l, Object o){
      last = l;
      data = o;
    }

    public StackNode last(){
      return last;
    }

    public Object data(){
      return data;
    }
  }
}
