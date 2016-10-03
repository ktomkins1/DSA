package lab2;
/**
 * 
 * @author Kishan
 * Interface for the Playlists
 */
public interface Playlist {
  //return the name of the playlist
  public String playlistName();
  //add the passed in song to this playlist
  public boolean addSong(Song s);
  //add the passed in song to this playlist, to the passed in index
  public boolean addSongAt(Song s, int index);
  //return the song 
  public Song getSongAt(int index);
  //return a list of songs
  public List getList();
  //remove the first instance of the song passed in
  public boolean removeSong(Song s);
  //return an int detailing the total number of songs
  public int totalSongs();
  //return a summation of the durations of each song
  public float playlistTime();
  //searches playlist, to find if any song matches 
  public boolean isSongInPlayList(String name, String artist);
  //prints out all songs that have the same artist as the string passed in
  public void songsByArtist(String name);
  //Takes all of the songs from the playlist p, and appends them to this playlist
  public boolean addSongsFrom(Playlist p);
  //Move selected song to position specified
  public boolean moveSong(Song s, int position);
  //move all songs the specified number of positions
  public boolean moveAllSongs(int positions);
  //swap songs in position1 and position2
  public boolean swapSongs(int position1, int position2);
}
