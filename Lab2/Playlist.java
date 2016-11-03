package lab2;

public abstract class Playlist {
  
  abstract String playlistName();
  
  abstract boolean addSong(Song s);
  
  abstract boolean addSongAt(Song s, int index);
  
  abstract Song getSongAt(Song s, int index);
  
  abstract MyList getList();
  
  abstract boolean removeSong(Song s);
  
  abstract int totalSongs();
  
  abstract float playlistTime();
  
  abstract boolean isSongInPlaylist(String name, String artist);
  
  abstract void songsByArtist(String name);
  
  abstract boolean addSongsFrom(Playlist p);
  
  abstract boolean moveSong(Song s, int position);
  
  abstract boolean moveAllSongs(int positions);
  
  abstract boolean swapSongs(int position1, int position2);
}
