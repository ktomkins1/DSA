package lab3;

/**
 *
 * @author Kevin
 */


public class Board {
  private boolean[][] board;
  private int[][] board2;
  public final int total_size;
  private int current_size = 0;
  
  public Board(int size) throws KnightBoardException{
    if(size < 4){
      throw new KnightBoardException();
    }
    total_size = size;
    board = new boolean[size][size];
    board2 = new int[size][size];
  }
  
  public boolean fill_space(int move_num, int x, int y){
    try{
      if(board[x][y]){
        return false;
      }
    }catch(ArrayIndexOutOfBoundsException aioobe){
      return false;
    }
    board[x][y] = true;
    board2[x][y] = move_num;
    current_size++;
    return true;
  }
  
  public boolean empty_space(int x, int y){
    if(!board[x][y]){
      return false;
    }
    board[x][y] = false;
    board2[x][y] = 0;
    return true;
  }
  
  public boolean is_space_empty(int x, int y){
    return !board[x][y];
  }
  
  public boolean is_board_full(){
    //return current_size == total_size*total_size;
    for(boolean[] ba : board){
      for(boolean b : ba){
        if(!b) return false;
      }
    }
    return true;
  }
  
  public void print(){
    for(boolean[] ba : board){
      for(boolean b : ba){
        System.out.print(b?"1":"0");
      }
      System.out.println();
    }
  }
  
  public void print2(){
    for(int[] ia : board2){
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
