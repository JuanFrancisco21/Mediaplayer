package Model;

import java.time.LocalDateTime;

public class Play {
	protected int id;
	protected User user;
	protected Song song;
	protected LocalDateTime date;
	
	public Play() {
		this.id = -1;
		this.user = new User();
		this.song = new Song();
		this.date = LocalDateTime.now();
	}
	public Play(User user, Song song) {
		this.id = -1;
		this.user = user;
		this.song = song;
		this.date = LocalDateTime.now();
	}
	public Play(int id, User user, Song song) {
		this.id = id;
		this.user = user;
		this.song = song;
		this.date = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((song == null) ? 0 : song.hashCode());
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
		Play other = (Play) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
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
		return "Play [id=" + id + ", user=" + user + ", song=" + song + ", date=" + date + "]";
	}
	
	
	
}
