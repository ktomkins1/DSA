package lab3;

/**
 * Holds information about where the knight has been 
 *
 * @author Kevin
 */
public class Board {
  private int[][] board; //int array of move numbers
  public final int total_size;
  private int current_size = 0;
  
  /**
   * Constructor for board objects
   *
   * @param size The size of the board
   * @throws KnightBoardException If the board size is too small, it must be rejected.
   */
  public Board(int size) throws KnightBoardException{
    if(size < 4){
      throw new KnightBoardException();
    }
    total_size = size;
    board = new int[size][size];
  }
  
  /**
   * Fills the designated space with the number
   * of the move which landed the knight at that coordinate position
   *
   * @param move_num The number of the move which brought the knight there
   * @param x The x coordinate
   * @param y The y position
   * @return If the space was filled (true) or not (false)
   */
  public boolean fill_space(int move_num, int x, int y){
    try{
      if(board[x][y] > 0){
        //the space is already filled
        return false;
      }
    }catch(ArrayIndexOutOfBoundsException aioobe){
      //the coordinates are not on the board
      return false;
    }
    board[x][y] = move_num;
    current_size++;
    return true;
  }
  
  /**
   * Empties the space at the designated coordinates
   *
   * @param x The x coordinate
   * @param y The y coordinate
   * @return If the space was emptied (true) or not (false)
   */
  public boolean empty_space(int x, int y){
    if(board[x][y] <= 0){
      return false;
    }
    board[x][y] = 0;
    current_size--;
    return true;
  }
  
  /**
   * Returns true if the space is empty or false otherwise
   *
   * @param x The x coordinate of the space
   * @param y The y coordinate of the space
   * @return True only if the space was not already 0
   */
  public boolean is_space_empty(int x, int y){
    return board[x][y] <= 0;
  }
  
  /**
   * Returns true if the board is full, otherwise returns false
   *
   * @return True if the board is full of values (the tour is a success)
   */
  public boolean is_board_full(){
    return current_size == total_size*total_size;
  }
  
  /**
   * Prints the whole board to the console
   */
  public void print(){
    for(int[] ia : board){
      for(int i : ia){
        if(i == 0){
          System.out.print("__ ");
          continue;
        }
        if(i < 10){
          System.out.print("0");
        }
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
