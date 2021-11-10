package Model;

public class Song {
	protected int id;
	protected String name;
	protected int duration;
	protected Gender gender;
	protected Disc disc;
	
	public Song() {
		this.id = -1;
		this.name = "";
		this.duration = -1;
		this.gender = new Gender();
		this.disc = new Disc();
	}
	public Song(String name, int duration, Gender gender, Disc disc) {
		this.id = -1;
		this.name = name;
		this.duration = duration;
		this.gender = gender;
		this.disc = disc;
	}
	public Song(int id, String name, int duration, Gender gender, Disc disc) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.gender = gender;
		this.disc = disc;
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Disc getDisc() {
		return disc;
	}
	public void setDisc(Disc disc) {
		this.disc = disc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disc == null) ? 0 : disc.hashCode());
		result = prime * result + duration;
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
		Song other = (Song) obj;
		if (disc == null) {
			if (other.disc != null)
				return false;
		} else if (!disc.equals(other.disc))
			return false;
		if (duration != other.duration)
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
		return "Song [id=" + id + ", name=" + name + ", duration=" + duration + ", gender=" + gender + ", disc=" + disc + "]";
	}
	
	
}
