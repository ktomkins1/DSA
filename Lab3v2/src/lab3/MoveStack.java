package lab3;

/**
 * A stack structure to hold the moves that the knight undergoes
 *
 * @author Kevin
 */
public class MoveStack {
  private Move top;
  
  /**
   * Creates a new stack with a null top value.
   */
  public MoveStack(){
    top = null;
  }
  
  /**
   * Pushes a position onto the stack
   *
   * @param p The position (which was most recently undergone by the knight
   */
  public void push(Position p){
    Move m = new Move(p);
    if(top != null){
      m.last(top);
    }
    top = m;
  }
  
  /**
   * Gets the most recent position from the stack
   *
   * @return The top level position
   */
  public Position pop(){
    Move out = top;
    top = top.last();
    out.last(null);
    return out.here();
  }
}
