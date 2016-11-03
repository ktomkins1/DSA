package lab3;

/**
 * Class to hold information about some knight on a path on some board.  
 * Finds if the board is able to be completed by the knight which has some starting point.
 *
 * @author Kevin
 */
public class Tour {
  private Board board;
  private Knight knight;
  
  /**
   * Creates a new tour with some board and knight
   *
   * @param b The board
   * @param k The knight
   */
  public Tour(Board b, Knight k){
    board = b;
    knight = k;
  }
  
  /**
   * initiates the tour.
   */
  public void start_tour(){
    System.out.println(recurse() ? "Success!" : "Failure.");
    board.print();
  }
  
  private boolean recurse(){
    if(board.is_board_full()){
      //base case.  this means that the knight was successful on their tour.
      return true;
    }
    //get positions to iterate over
    Position[] ps = knight.get_current_pos().move_positions();
    for(Position p : ps){
      //For each position
      //find if it is valid
      if(!p.check_bounds(board.total_size)){
        //if it is not valid, go to the next position
        continue;
      }
      //if it is valid, try to move to it
      if(!knight.move_to(p)){
        //if moving to it is not possible, go to the next position
        continue;
      }
      //The knight is now at the new position from the list.  Try this process again for that one.
      if(recurse()){
        //The board was successful due to the base case
        return true;
      }
      //even if the recursive step did not find a way to fill the board,
      //the knight is still at the current position in the list.
      //Move back from there to the last known position (the one from which the list was generated)
      knight.move_back();
    }
    //if all moves are exhausted, return false that the board at the current position does not work
    return false;
  }
}
