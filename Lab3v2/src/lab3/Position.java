package lab3;

/**
 * A structure to hold a coordinate pair on the board.
 *
 * @author Kevin
 */
public class Position {
  public int x;
  public int y;
  
  /**
   * Creates a new position with some x and y coordinates
   *
   * @param x The x coordinate
   * @param y The y coordinate
   */
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  /**
   * Gets the list of positions from the current one which a knight could move to.
   * 
   * This function does not check if the positions are valid places on the board.
   *
   * @return An array of positions which could be moved to from here.
   */
  public Position[] move_positions(){
    Position[] out = new Position[8];
    out[0] = new Position(x - 2, y - 1);
    out[1] = new Position(x - 1, y - 2);
    out[2] = new Position(x + 2, y - 1);
    out[3] = new Position(x + 1, y - 2);
    out[4] = new Position(x - 2, y + 1);
    out[5] = new Position(x - 1, y + 2);
    out[6] = new Position(x + 2, y + 1);
    out[7] = new Position(x + 1, y + 2);
    return out;
  }
  
  /**
   * Checks the coordinates of this position against some board size.
   * 
   * Valid positions are in the range: 0 <= x, y < size
   *
   * @param size The size to check against (exclusive)
   * @return True if the position is within the bounds
   */
  public boolean check_bounds(int size){
    if(x < 0 || x >= size){
      return false;
    }
    return !(y < 0 || y >= size);
  }
  
  /**
   * Returns a string with the coordinates as a pair
   *
   * @return A string in the form (x,y)
   */
  public String print(){
    return "(" + x + "," + y + ")";
  }
}
