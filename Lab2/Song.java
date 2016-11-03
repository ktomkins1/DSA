package lab2;

/**
 *
 * @author Kevin
 */
public class Song {
  private String name;
  private String artist;
  private float playtime;
  
  /** 
   * Creates a Song object (Constructor)
   * 
   * @param name
   * @param artist
   * @param playtime Playtime of the song in seconds
   */
  Song(String name, String artist, float playtime){
    this.name = name;
    this.artist = artist;
    this.playtime = playtime;
  }
  /**
   * returns the songs name, artist, and playtime in the following format:
   * song: It’s tearing up my hear, artist: N’Sync , playtime: 3:40
   * @return 
   */
  @Override public String toString(){
    return "Song: " + name + ", Artist: " + artist + ", Playtime: " + (int) playtime/60 + ":" + playtime % 60;
  }
  // – returns the playtime of the song
  public double getPlayTime(){
    return playtime;
  }
  // – returns the artist of the song
  public String getArtist(){
    return artist;
  }
  // – returns the name of the song
  public String getSongName(){
    return name;
  }
  // – returns if given Song object equals this Song object, Hint: overwrite the equals method for objects
  @Override public boolean equals(Object o) {
    if(!(o instanceof Song)){
      return false;
    }
    Song s = (Song)o;
    return s.getArtist().equalsIgnoreCase(artist) && s.getSongName().equalsIgnoreCase(name);
  }
}
