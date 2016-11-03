package lab3;

/**
 * Holds the position of the knight on the board and moves it to other spaces
 *
 * @author Kevin
 */
public class Knight {
  private int move_count = 1;
  private Position current_pos;
  private Board board;
  private MoveStack stack;
  
  /**
   * Creates a knight on a board given by b and a starting position given by starting_pos
   *
   * @param b The board which the knight exists on
   * @param starting_pos The knights initial position from which the tour will begin
   */
  public Knight(Board b, Position starting_pos){
    board = b;
    stack = new MoveStack();
    current_pos = starting_pos;
    board.fill_space(move_count++, current_pos.x, current_pos.y);
  }
  
  /**
   * Moves the knight to a certain new position
   *
   * @param p the new position
   * @return Whether the move was successful (true) or not (false)
   */
  public boolean move_to(Position p){
    if(board.is_space_empty(p.x, p.y)){ //check that the space is available
      stack.push(current_pos);
      current_pos = p;
      return board.fill_space(move_count++, p.x, p.y); //write the next move number to the space
    }
    return false;
  }
  
  /**
   * Undo the last move
   *
   * @return Whether undoing was possible (true) or not (false)
   */
  public boolean move_back(){
    Position old_pos = current_pos; //save the current position temporarily
    current_pos = stack.pop(); // get the last position off of the stack
    if(current_pos == null){
      current_pos = old_pos; //if the stack had been empty, the move back fails
      return false;
    }
    if(board.empty_space(old_pos.x, old_pos.y)){
      //if the board is able to empty the old space, remove one move from the count
      move_count--;
      return true;
    }
    return false;
  }
  
  /**
   * Returns the current position
   *
   * @return the current position
   */
  public Position get_current_pos(){
    return current_pos;
  }
}
