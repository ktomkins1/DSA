package lab5;

/**
 *
 * @author Kevin
 */
  public class IntegerHolder implements Comparable{
    private Integer obj;
    private int digit;
    private int place;
    
    public IntegerHolder(Integer in){
      place = 1;
      obj = in;
      digit = Sort.getDigit(in, place);
    }
    
    public void increasePlace(){
      place++;
    }
    
    public int getDigit(){
      return digit;
    }

    @Override
    public int compareTo(Object o) {
      return -1*((Comparable) o).compareTo(digit);
    }
  }