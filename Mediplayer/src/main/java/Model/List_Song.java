package Model;

public class List_Song {
	protected int id;
	protected Playlist list;
	protected Song song;
	
	public List_Song() {
		this.id = -1;
		this.list = new Playlist();
		this.song = new Song();
	}
	public List_Song(Playlist list, Song song) {
		this.id = -1;
		this.list = list;
		this.song = song;
	}
	public List_Song(int id, Playlist list, Song song) {
		this.id = id;
		this.list = list;
		this.song = song;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Playlist getList() {
		return list;
	}
	public void setList(Playlist list) {
		this.list = list;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((song == null) ? 0 : song.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		List_Song other = (List_Song) obj;
		if (id != other.id)
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "List_Song [id=" + id + ", list=" + list + ", song=" + song + "]";
	}
	
	
	
	
}
