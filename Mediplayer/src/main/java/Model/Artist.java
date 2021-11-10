package Model;

public class Artist {
	protected int id;
	protected String name;
	protected String nationality;
	protected String photo;
	
	public Artist() {
		this.id = -1;
		this.name = "";
		this.nationality = "";
		this.photo = "";
	}
	public Artist(String name, String nationality) {
		this.id = -1;
		this.name = name;
		this.nationality = nationality;
		this.photo = "";
	}
	public Artist(String name, String nationality, String photo) {
		this.id = -1;
		this.name = name;
		this.nationality = nationality;
		this.photo = photo;
	}
	public Artist(int id, String name, String nationality, String photo) {
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.photo = photo;
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
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
		Artist other = (Artist) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", nationality=" + nationality + ", photo=" + photo + "]";
	}
	
	
	
	
}
