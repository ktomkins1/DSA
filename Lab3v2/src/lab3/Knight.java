package lab3;

/**
 *
 * @author Kevin
 */


public class Knight {
  private int move_count = 1;
  private Position current_pos;
  private Board board;
  private MoveStack stack;
  
  public Knight(Board b, Position starting_pos){
    board = b;
    stack = new MoveStack();
    current_pos = starting_pos;
    board.fill_space(move_count++, current_pos.x, current_pos.y);
  }
  
  public boolean move_to(Position p){
    //System.out.println("Moving to: " + p.print());
    if(board.is_space_empty(p.x, p.y)){
      stack.push(current_pos);
      current_pos = p;
      board.fill_space(move_count++, p.x, p.y);
      return true;
    }
    //System.out.println("Moving failed.");
    return false;
  }
  
  public boolean move_back(){
    Position old_pos = current_pos;
    current_pos = stack.pop();
    if(current_pos == null){
      current_pos = old_pos;
      return false;
    }
    if(board.empty_space(old_pos.x, old_pos.y)){
      move_count--;
      //System.out.println("Reversed 1 move.");
      return true;
    }
    return false;
  }
  
  public Position get_current_pos(){
    return current_pos;
  }
}
