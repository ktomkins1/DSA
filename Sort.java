package lab5;

import avl.*;
import heap.*;

/**
 *
 * @author Kevin
 * @param <T>
 */
public class Sort <T extends Comparable> {
  int[] sorts;
  int twoFMerges;
  int bubbleSwaps;
  int insertionRotates;
  
  public Sort(){
    sorts = new int[8];
    twoFMerges = 0;
    bubbleSwaps = 0;
    insertionRotates = 0;
  }
  
  public String print(){
    int sort = 0;
    for(int i : this.sorts){
      sort += i;
    }
    return "Sorts completed: " + sort;
  }
  
  private void swap(T[] input, int i1, int i2){
    T temp = input[i1];
    input[i1] = input[i2];
    input[i2] = temp;
  }
  
  /**
   * Utility for rotating a sub array of size 3
   * 
   * @param input input array of some size > 3
   * @param left left index, (right - 2)
   * @param right right index
   */
  public void rotate3(T[] input, int left, int right){
    T temp = input[left];
    input[left] = input[right];
    input[right] = input[left + 1];
    input[left + 1] = temp;
  }
  
  /**
   * Utility for right rotating a sub array of any size
   * 
   * @param input
   * @param left
   * @param right 
   */
  public void rotate(T[] input, int left, int right){
    int len = right - left + 1;
    T temp = input[right];
    while(right > 0){
      input[right] = input[--right];
    }
    input[left] = temp;
  }
  
  private void twoFinger(T[] input, int left1, int right1, int left2, int right2){
    twoFMerges++;
    int writeIndex = 0;
    int readIndex1 = left1;
    int readIndex2 = left2;
    int subLen = ((right1 - left1 + 1) + (right2 - left2 + 1));
    Object[] temp = new Object[subLen];
    while(writeIndex < subLen){
      if(input[readIndex1].compareTo(input[readIndex2]) > 0){
        temp[writeIndex++] = input[readIndex2++];
      }else{
        temp[writeIndex++] = input[readIndex1++];
      }
      if(readIndex1 > right1){
        while(readIndex2 <= right2 && writeIndex < subLen){
          temp[writeIndex++] = input[readIndex2++];
        }
      }
      if(readIndex2 > right2){
        while(readIndex1 <= right1 && writeIndex < subLen){
          temp[writeIndex++] = input[readIndex1++];
        }
      }
    }
    int i = 0;
    for(writeIndex = left1; writeIndex <= right2; i++){
      input[writeIndex++] = (T)temp[i];
      if(writeIndex == right1 + 1){
        writeIndex = left2;
      }
    }
  }
  
  public void merge(T[] input, int len, int left, int right){
    System.out.println("Current Merge: left: " + left + ", right: " + right);
    if(len < 2){
      return;
    }
    if(len == 2){
      if(input[left].compareTo(input[right]) > 0){
        swap(input, left, right);
      }
      return;
    }
    if(len == 3){
      
      merge(input, 2, left, left + 1);
      if(input[right].compareTo(input[left + 1]) < 0){
        if(input[right].compareTo(input[left]) < 0){
          //input[right] is less than both others
          rotate3(input, left, right);
        }else{
          //input[right] fits between the other two
          swap(input, left + 1, right);
        }
      }
      return;
    }
    int lenLeft = len/2;
    int lenRight = lenLeft;
    if(lenLeft * 2 < len){
      lenRight++;
    }
    int right1 = left + lenLeft - 1;
    int left2 = left + lenLeft;
    
    merge(input, lenLeft, left, right1);
    merge(input, lenRight, left2, right);
    twoFinger(input, left, right1, left2, right);
    sorts[0]++;
  }
  
  public void quickI(T[] input, int len){
    int pivot = 0;
    int left = 1;
    int right = len - 1;
    while(right > left){
      if(input[left].compareTo(input[pivot]) > 0){
        
      }
    }
    sorts[1]++;
  }
  
  public void quickR(T[] input, int len, int left, int right){
    if(len < 3){
      if(input[left].compareTo(input[right]) > 1){
        swap(input, left, right);
      }
      return;
    }
    int pivot = left;
    int i = left + 1;
    int j = right;
    boolean cont = true;
    while(i < j){
      cont = true;
      while(cont){
        if(input[i].compareTo(input[pivot]) < 0){
          i++;
        }else{
          cont = false;
        }
      }
      cont = true;
      while(cont){
        if(input[j].compareTo(input[pivot]) > 0){
          j--;
        }else{
          cont = false;
        }
      }
      swap(input, i, j);
    }
    if(input[j].compareTo(input[pivot]) < 0){
      swap(input, j, pivot);
    }
    quickR(input, len, left, j);
    quickR(input, len, i, right);
    sorts[1]++;
  }
  
  public void insertion(T[] input, int len){
    sorts[2]++;
    for(int i = 1; i < len; i++){
      for(int j = 0; j < i; j++){
        if(input[i].compareTo(input[j]) < 0){
          //input is less than some member of the sorted section
          rotate(input, j, i);
          insertionRotates++;
        }
      }
    }
  }
  
  public void bubble(T[] input, int len){
    sorts[3]++;
    boolean swaps;
    do{
      swaps = false;
      for(int i = 1; i < len; i++){
        if(input[i - 1].compareTo(input[i]) > 0){
          swap(input, i - 1, i);
          swaps = true;
          bubbleSwaps++;
        }
      }
    }while(swaps);
  }
  
  public void bucket(T[] input, int len, int left, int right){
    BucketList bl = new BucketList();
    for(T item : input){
      bl.add(item);
    }
    bl.sortAll();
    bl.bucketsToArray(input);
    sorts[4]++;
  }
  
  public void radix(Integer[] input, int len){
    IntegerHolder[] ints = new IntegerHolder[len];
    int index = 0;
    for(Integer in : input){
      ints[index++] = new IntegerHolder(in);
    }
    boolean cont = true;
    while(cont){
      cont = false;
      Sort.generalSort(ints, len);
      for(IntegerHolder in : ints){
        in.increasePlace();
        if(in.getDigit() > 0){
          cont = true;
        }
      }
    }
    sorts[5]++;
  }
  
  public static int getDigit(Integer in, int place){
    if(place < 1){
      return Integer.MIN_VALUE;
    }
    String num = in.toString();
    if(place > num.length()){
      return 0;
    }
    char obj = num.charAt(num.length()-place);
    return Integer.parseInt("" + obj);
  }
  
  public void heap(T[] input, int len){
    Heap sortHeap = new Heap();
    for(T t : input){
      sortHeap.insert(t);
    }
    for(int i = 0; i < len; i++){
      input[i] = (T) sortHeap.findMin();
      sortHeap.deleteMin();
    }
    sorts[6]++;
  }
  
  public void tree(T[] input, int len){
    AVLTree<T> sortAVL = new AVLTree<>();
    for(T t : input){
      sortAVL = AVLTree.insert(sortAVL, t);
    }
    T temp;
    for(int i = 0; i < len; i++){
      temp = (T) sortAVL.deleteMin();
      input[i] = temp;
    }
    sorts[7]++;
  }
  
  public static void generalSort(Comparable[] input, int len){
    Sort genSorter = new Sort<Comparable>();
    genSorter.bubble(input, len);
  }
}
