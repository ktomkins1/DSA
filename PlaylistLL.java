package lab2;

/**
 * 
 * @author Kevin
 */
public class PlaylistLL extends Playlist{
  private MyList<Song> internal;
  private String name;
  private float time;
  
  public PlaylistLL(String name){
    this.name = name;
    time = 0.0f;
    internal = new MyLinkedList<>();
  }
  
  // – returns the name of the playlist
  @Override String playlistName(){
    return name;
  }
  
  // – adds given song to the end of the playlist
  @Override boolean addSong(Song s){
    time += s.getPlayTime();
    return internal.add(s);
  }
  
  // – adds given song to given index
  @Override boolean addSongAt(Song s, int index){
    time += s.getPlayTime();
    return internal.add(index, s);
  }
  
  // – retrieves a song from a given index
  @Override Song getSongAt(Song s, int index){
    try {
      return internal.get(index);
    } catch (ListException ex) {
      return null;
    }
  }
  
  // – returns the List object of songs
  @Override MyList getList(){
    return internal;
  }
  
  // – remove Song s from the Playlist.
  @Override boolean removeSong(Song s){
    try {
      return internal.remove(s) != null;
    } catch (ListException ex) {
      return false;
    }
  }
  
  // – Returns the number of songs in the playlist.
  @Override int totalSongs(){
    return internal.size();
  }
  
  // – Computes the total duration of the Playlist
  @Override float playlistTime(){
    return time;
  }
  
  // – Searches for particular song in the playlist. If found return true
  @Override boolean isSongInPlaylist(String name, String artist){
    Song s = new Song(name, artist, 2.0f);
    return internal.contains(s);
  }
  
  // – Displays/Prints all songs by the artist if the artist is found in the playlist
  @Override void songsByArtist(String name){
    MyLinkedList<Song> songsByArtist = new MyLinkedList<>();
    Song s;
    int i;
    for(i = 1; i <= internal.size(); i++){
      try {
        s = internal.get(i);
      } catch (ListException ex) {
        s = null;
      }
      if(s != null && s.getArtist().equalsIgnoreCase(name)){
        songsByArtist.add(s);
      }
    }
    System.out.println("Songs by " + name + ":");
    for(i = 1; i <= songsByArtist.size(); i++){
      try {
        s = songsByArtist.get(i);
      } catch (ListException ex) {
        s = null;
      }
      System.out.println(i + ": " +
              s != null ? s.toString() : "Error:  Missing song entry at index " + i);
    }
  }
  
  // – Add (append) songs from Playlist p to end of this playlist.
  @Override boolean addSongsFrom(Playlist p){
    return internal.append(p.getList());
  }
  
  //: moves given song in list to stated position in list. This method throws an exception if the 
  //song is not in the list or the position value is less than 1 or greater than the list size.
  @Override boolean moveSong(Song s, int position){
    if(position > internal.size() || position < 1){
      return false;
    }
    int sIndex = internal.indexOf(s);
    return internal.swap(sIndex, position);
  }
  
  //: moves all the songs a given number of positions in the list. If the value of positions is
  //positive then the elements are shifted from left to right. If the value of positions is negative
  //then the elements are shifted from right to left.
  @Override boolean moveAllSongs(int positions){
    return internal.shift(positions);
  }
  
  //: swaps the positions of two Songs at the given positions. If the value of the positions are 
  //not in the Playlist, an exception is thrown.
  @Override boolean swapSongs(int position1, int position2){
    return internal.swap(position1, position2);
  }
}
