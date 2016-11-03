package lab3;

/**
 *
 * @author Kevin
 */
public class Position {
  public int x;
  public int y;
  
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
  
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
  
  public boolean check_bounds(int size){
    if(x < 0 || x >= size){
      return false;
    }
    return !(y < 0 || y >= size);
  }
  
  public String print(){
    return "(" + x + "," + y + ")";
  }
}
