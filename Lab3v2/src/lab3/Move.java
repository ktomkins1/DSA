package lab3;

/**
 *
 * @author Kevin
 */


public class Move {
  private Move last;
  private Position here;
  
  public Move(Position here){
    this.here = here;
    last = null;
  }
  
  public void last(Move m){
    last = m;
  }
  
  public Move last(){
    return last;
  }
  
  public void here(Position p){
    here = p;
  }
  
  public Position here(){
    return here;
  }
}
