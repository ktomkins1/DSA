package lab2;
/**
 * 
 * @author Kishan
 *
 */
public class PlayListAL extends Playlist {
 
  private String playlistName;
  
  private MyArrayList<Song> arrayListPlayList;
  
  public PlayListAL(String playlistName){
    this.playlistName=playlistName;
    arrayListPlayList=new MyArrayList<Song>();
    Song son1=new Song("dsad","das",32);
    Song son2=new Song("dsad","das",35);
    son1.equals(son2);
  }

  @Override
  public String playlistName() {
    return this.playlistName;
  }

  @Override
  public boolean addSong(Song s) {
    arrayListPlayList.add(s);
    return false;
  }

  @Override
  public boolean addSongAt(Song s, int index) {
    arrayListPlayList.add(index,s);
    return true;
  }

  @Override
  public Song getSongAt(Song s,int index) {    
    return (Song) arrayListPlayList.get(index);
  }

  @Override
  public MyList getList() {
    return this.arrayListPlayList;
    
  }

  @Override
  public boolean removeSong(Song s) {
    arrayListPlayList.remove(s);
    return true;
  }

  @Override
  public int totalSongs() {
    return arrayListPlayList.size();
  }

  @Override
  public float playlistTime(){
    float totalTime = 0;
    for(int i=0;i<arrayListPlayList.size();i++){
      totalTime+=((Song) arrayListPlayList.get(i)).getPlayTime();
    }
    return totalTime;
  }

  @Override
  public boolean isSongInPlaylist(String name, String artist) {
    if(arrayListPlayList.contains(new Song(name,artist,0))){
      return true;
    }else{
      return false;
    }
  }

  @Override
  public void songsByArtist(String name) {
    boolean searching=true;
    boolean foundSong=false;
    int i=0;
    while(searching){    
      if(((Song) arrayListPlayList.get(i)).getArtist().equals(name)){
        System.out.println(((Song) arrayListPlayList.get(i)).getSongName());
        i++;
        foundSong=true;
      }else if(i==arrayListPlayList.getNumOfObjectsInArray()-1&&!foundSong){
        System.out.println("No Songs by Artist in this playlist");
        searching=false;
      }else if(i==arrayListPlayList.getNumOfObjectsInArray()-1&&foundSong){
        
      }else{
        i++;
      }
      
    }
    
  }

  @Override
  public boolean addSongsFrom(Playlist p) {
    for(int i=0;i<p.getList().size();i++){
      if(!arrayListPlayList.contains(p.getSongAt(null, i))){
      arrayListPlayList.add(p.getSongAt(null, i));
      }
    }
    return true;
    
  }

  @Override
  public boolean moveSong(Song s, int position) {
    boolean outBoolean = false;
    if(arrayListPlayList.contains(s)){
      int i=arrayListPlayList.indexOf(s);
      if(i>position){
        arrayListPlayList.remove(i);
        arrayListPlayList.add(i,s);
        outBoolean=true;
      }else if(i<position){
        arrayListPlayList.add(i,s);
        arrayListPlayList.remove(i);
        outBoolean=true;
      }else if(i==position){}
      else{
        outBoolean=false;
      }
    }
    return outBoolean;
      
    
    
  }

  @Override
  public boolean moveAllSongs(int positions) {
    arrayListPlayList.shift(positions);
    return true;
  }

  @Override
  public boolean swapSongs(int position1, int position2) {
    arrayListPlayList.swap(position1, position2);
    return true;
  }

}
