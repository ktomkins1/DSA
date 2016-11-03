package lab3;

/**
 * Holds some position, in relation to the moves that were previously done
 *
 * @author Kevin
 */
public class Move {
  private Move last;
  private Position here;
  
  /**
   * Creates a new move with some position
   *
   * @param here
   */
  public Move(Position here){
    this.here = here;
    last = null;
  }
  
  /**
   * Links some move to this one as its predecessor
   *
   * @param m The previous move
   */
  public void last(Move m){
    last = m;
  }
  
  /**
   * Returns the previous move
   *
   * @return The previous move
   */
  public Move last(){
    return last;
  }
  
  /**
   * Sets the current position
   *
   * @param p The position value of this move
   */
  public void here(Position p){
    here = p;
  }
  
  /**
   * Gets the position from this move
   *
   * @return This move's position
   */
  public Position here(){
    return here;
  }
}
