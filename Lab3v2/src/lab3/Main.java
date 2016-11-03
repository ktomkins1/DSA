package lab3;

/**
 *
 * @author Kevin
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Board b;
    try {
      b = new Board(8);
    } catch (KnightBoardException ex) {
      System.out.println("Invalid board size");
      return;
    }
    Position p = new Position(5, 0);
    Knight k = new Knight(b, p);
    Tour t = new Tour(b, k);
    t.start_tour();
  }
}
