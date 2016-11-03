package lab3;

import java.util.Scanner;

/**
 *
 * @author Kevin
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    //initialize tour objects
    Board b = null;
    Position p = null;
    
    //temporary housekeeping items
    int board_size = 4;
    Scanner sc = new Scanner(System.in);
    boolean cont = true;
    
    //get the board size.  Try until it is valid.
    while(cont){
      System.out.println("Please enter the size of the board as an integer."
              + "Type 0 to quit.");
      board_size = Integer.parseInt(sc.nextLine().trim());
      if(board_size == 0){
        return;
      }
      try {
        b = new Board(board_size);
        cont = false;
      } catch (KnightBoardException ex) {
        System.out.println("Invalid board size.  Please ReEnter");
      }
    }
    cont = true;
    String[] pos;
    int x;
    int y;
    while(cont){
      System.out.println("Please enter the starting position as coordinates in the form: (x,y).");
      pos = sc.nextLine().replaceAll("\\(", "").replaceAll("\\)", "").split(",");
      if(pos.length == 2){
        x = Integer.parseInt(pos[0].trim());
        y = Integer.parseInt(pos[1].trim());
        p = new Position(x, y);
        if(p.check_bounds(board_size)){
          cont = false;
        }
      }
    }
    
    //The starting values are now known, begin the tour and alert the user.
    Knight k = new Knight(b, p);
    Tour t = new Tour(b, k);
    System.out.println("The program will now attempt to find a knight's tour from position " 
            + p.print() + " on board of size " + board_size + ".");
    t.start_tour();
  }
}
