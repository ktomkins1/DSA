package postfixCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import infixCalculator.Operand;

//spaces between everything
public class postfixCalculator {
  
  Stack<String> holdOperation;
  
  String[] individualPieces;
  
  Stack operationStringStack;
  
  String operationString;
  
  public postfixCalculator(){
    operationStringStack=new Stack();
    holdOperation=new Stack();
  }
  
  public int calculate(String operationString){
    int o=1;
    int i=0;
    String firstNumber;
    String secondNumber;
    individualPieces=operationString.split(" ");
    boolean evaluating=true;
    while(evaluating){
      if(i==individualPieces.length){
        evaluating=false;
        break;
      }
      if(Operand.isNumber(individualPieces[i])){
        operationStringStack.push(individualPieces[i]);
        i++;
        o++;
      }else if(Operand.isOperation(individualPieces[i])){
       
          secondNumber=(String) operationStringStack.pop();
          firstNumber=(String) operationStringStack.pop();
          operationStringStack.push(evaluate(firstNumber,secondNumber,individualPieces[i]));
          i++;
          o++;
      }
    } 
    int outNumber=Integer.parseInt((String) operationStringStack.pop());
    return outNumber;
  }

  public String evaluate(String firstNumber,String secondNumber, String operation){
    return Operand.evaluate(firstNumber,secondNumber,operation);
  }
  
  
  
  

  
  
}
