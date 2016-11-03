package infixCalculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import postfixCalculator.postfixCalculator;

//no spaces, has parenthesis
public class infixCalculator {
  
  public infixCalculator(){
    
  }
  
  public int calculate(String operationString){
    postfixCalculator pfc=new postfixCalculator();
    return pfc.calculate(convertToPostfix(operationString));
  }
  
  public String convertToPostfix(String operationString){
    boolean doneThisOperationYet=true;
    StringBuilder postFixExpression=new StringBuilder();
    Stack operationStack=new Stack();
    String[] holdArray=operationString.split(" ");
    for(int i=0;i<holdArray.length;i++){
      if(Operand.isNumber(holdArray[i])){
        postFixExpression.append(holdArray[i]+" ");
      }else if(holdArray[i].equals("(")){
        operationStack.push(holdArray[i]);;
      }else if(holdArray[i].equals(")")){
        while(!operationStack.peek().equals("(")){
          Object holdObject=operationStack.pop();
          postFixExpression.append(holdObject+" ");
        }
        operationStack.pop();
      }else if(Operand.isOperation(holdArray[i])){
        operationStack.push(holdArray[i]);
        doneThisOperationYet=false;
      }else if(Operand.isOperation(holdArray[i])&&!doneThisOperationYet){
        while(!Operand.compare(holdArray[i],(String) operationStack.peek())){
          Object holdObject=operationStack.pop();
          postFixExpression.append(holdObject+" ");
        }
        operationStack.push(holdArray[i]);
      }
    }
      while(operationStack.numOfItemsInStack()!=0){
        Object holdObject=operationStack.pop();
        postFixExpression.append(holdObject+" ");
      }
      
  

    return postFixExpression.toString();
  }
}