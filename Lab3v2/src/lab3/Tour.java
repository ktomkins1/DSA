package lab3;

/**
 *
 * @author Kevin
 */


public class Tour {
  private Board board;
  private Knight knight;
  
  public Tour(Board b, Knight k){
    board = b;
    knight = k;
  }
  
  public void start_tour(){
    System.out.println(recurse() ? "Success!" : "Failure.");
    board.print2();
  }
  
  private boolean recurse(){
    /*
    int num_steps = 0;
    for(Position p : knight.get_current_pos().move_positions()){
      if(p.check_bounds(board.total_size)){
        num_steps++;
        if(!knight.move_to(p)){
          continue;
        }
        if(board.is_board_full()){
          return true;
        }
        if(recurse()){
          return true;
        }
      }
    }
    knight.move_back();
    return false;
    */
    //board.print2();
    //System.out.println();
    
    if(board.is_board_full()){
      return true;
    }
    Position[] ps = knight.get_current_pos().move_positions();
    /*
    System.out.println("CP: " + knight.get_current_pos().print());
    
    for(Position p : ps){
      System.out.print(p.print() + " ");
    }
    System.out.println();
*/
    for(Position p : ps){
      if(!p.check_bounds(board.total_size)){
        continue;
      }
      if(!knight.move_to(p)){
        continue;
      }
      if(recurse()){
        return true;
      }
      knight.move_back();
    }
    return false;
  }
}
