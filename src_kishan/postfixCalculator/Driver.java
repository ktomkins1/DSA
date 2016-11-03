package postfixCalculator;

import infixCalculator.infixCalculator;

public class Driver {

  public static void main(String[] args) {
    infixCalculator ifc=new infixCalculator();
    System.out.println(ifc.calculate("( 6 + 3 ) * 3"));
  }

}
