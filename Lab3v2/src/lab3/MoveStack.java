package lab3;

/**
 *
 * @author Kevin
 */
public class MoveStack {
  private Move top;
  
  public MoveStack(){
    top = null;
  }
  
  public void push(Position p){
    Move m = new Move(p);
    if(top != null){
      m.last(top);
    }
    top = m;
  }
  
  public Position pop(){
    Move out = top;
    top = top.last();
    out.last(null);
    return out.here();
  }
}
