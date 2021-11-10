package Model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	protected int id;
	protected String name;
	protected String description;
	protected User user;
	protected List<Song> songs;
	
	public Playlist() {
		this.id = -1;
		this.name = "";
		this.description = "";
		this.user = new User();
		this.songs = new ArrayList<Song>();
	}
	public Playlist(String name, User user) {
		this.id = -1;
		this.name = name;
		this.description = "";
		this.user = user;
		this.songs = new ArrayList<Song>();
	}
	public Playlist(String name, User user, List<Song> songs) {
		this.id = -1;
		this.name = name;
		this.description = "";
		this.user = user;
		this.songs = songs;
	}
	
	public Playlist(String name, String description, User user) {
		this.id = -1;
		this.name = name;
		this.description = description;
		this.user = user;
		this.songs = new ArrayList<Song>();
	}
	public Playlist(String name, String description, User user, List<Song> songs) {
		this.id = -1;
		this.name = name;
		this.description = description;
		this.user = user;
		this.songs = songs;
	}
	
	public Playlist(int id, String name, String description, User user) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.songs = new ArrayList<Song>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Playlist other = (Playlist) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", description=" + description + ", user=" + user + ", songs="
				+ songs + "]";
	}
	
	
	
}
