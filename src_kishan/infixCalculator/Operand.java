package infixCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Operand {
  String operand;
  
  public Operand(String operand){
    this.operand=operand;
  }
  
  public static boolean isOperation(String s){
    switch (s){
    
    case "*": return true;
    
    case "/": return true;
    
    case "+":return true;
    
    case"-": return true;
    
    case "(": return true;
    
    case ")":return true;
    
    default: return false;
    }
  } 
  
  public static String evaluate(String number1,String number2,String operation){
    int firstNumber=Integer.parseInt(number1);
    int secondNumber=Integer.parseInt(number2);
    int outputNumber=0;
    String outputString=null;
   
    if(operation.equals("+")){
      outputNumber=firstNumber+secondNumber;
    }else if(operation.equals("/")){
      outputNumber=(int) ((double)firstNumber/(double)secondNumber);
    }else if(operation.equals("-")){
      outputNumber=firstNumber-secondNumber;
    }else if(operation.equals("*")){
      outputNumber=firstNumber*secondNumber;
    }
    
    return outputString=Integer.toString(outputNumber);
   
  }
  
  public static boolean isNumber(String s){
    Pattern p = Pattern.compile("\\d+");
    Matcher m = p.matcher(s);
    return m.matches();
  }
  //true means higher or equal, to be appended
  public static boolean compare(String operation1,String operation2){
    if(turnToInt(operation1)>=turnToInt(operation2)){
      return true;
    }else{
      return false;
    }
  }
  public static int turnToInt(String operation){
    if(operation.equals("*")){
      return 2;
      
    }else if(operation.equals("/")){
      return 2;
    }else{
      return 1;
    }
  }

}
