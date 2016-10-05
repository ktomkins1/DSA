package lab2;

import java.util.Random;

/**
 *
 * @author Kevin
 */
public class PlaylistTester {
  private static Random r = new Random(1);
  public static void fill(Playlist l, int amount){
    for(int i = 0; i < amount; i++){
      l.addSong(newSong());
    }
  }
  
  private static Song newSong(){
    return new Song("t" + r.nextInt(), "a" + r.nextInt(), r.nextFloat());
  }
  
  private static int vp(int size){
    return r.nextInt(size) + 1;
  }
  
  public static long[] test(Playlist l){
    try {
      int op = r.nextInt(4) + 1;
      long time = System.nanoTime();
      int z = l.totalSongs();
      switch(op){
        case 1:
          if(!l.addSongAt(newSong(), vp(z))){
            throw new ListException();
          }
          break;
        case 2:
          l.getSongAt(null, vp(z));
          break;
        case 3:
          if(!l.swapSongs(vp(z), vp(z))){
            throw new ListException();
          }
          break;
        case 4:
          int pos = r.nextInt(1000000) + 1;
          if(r.nextInt(1) > 0){
            pos = -pos;
          }
          if(!l.moveAllSongs(pos)){
            throw new ListException();
          }
          break;
        default:
          System.out.println("default test case reached.");
          throw new ListException();
      }
      long[] out = {System.nanoTime() - time, op};
      return out;
    } catch (ListException ex) {
      long[] out = {-1, -1};
      return out;
    }
  }
}
