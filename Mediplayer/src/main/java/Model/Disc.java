package Model;

import java.sql.Date;
import java.time.LocalDate;

public class Disc {
	protected int id;
	protected String name;
	protected Date date;
	protected String photo;
	protected Artist artist;
	
	public Disc() {
		this.id = -1;
		this.name = "";
		this.date = Date.valueOf(LocalDate.now());
		this.photo = "";
		this.artist = new Artist();
	}
	
	public Disc(String name, Artist artist) {
		this.id = -1;
		this.name = name;
		this.date = Date.valueOf(LocalDate.now());
		this.photo = "";
		this.artist = artist;
	}
	public Disc(String name, String photo, Artist artist) {
		this.id = -1;
		this.name = name;
		this.date = Date.valueOf(LocalDate.now());
		this.photo = photo;
		this.artist = artist;
	}
	public Disc(int id, String name, String photo, Artist artist) {
		this.id = id;
		this.name = name;
		this.date = Date.valueOf(LocalDate.now());
		this.photo = photo;
		this.artist = artist;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Disc other = (Disc) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disc [id=" + id + ", name=" + name + ", photo=" + photo + ", id_artist=" + artist + "]";
	}
	
	
}
