package Model;

public class Subscribe {
	protected int id;
	protected User user;
	protected Playlist list;
	
	public Subscribe() {
		this.id = -1;
		this.user = new User();
		this.list = new Playlist();
	}
	public Subscribe(User user, Playlist list) {
		this.id = -1;
		this.user = user;
		this.list = list;
	}
	public Subscribe(int id, User user, Playlist list) {
		this.id = id;
		this.user = user;
		this.list = list;
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
	public Playlist getList() {
		return list;
	}
	public void setList(Playlist list) {
		this.list = list;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		Subscribe other = (Subscribe) obj;
		if (id != other.id)
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
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
		return "Subscribe [id=" + id + ", user=" + user + ", list=" + list + "]";
	}
	
	
	
}
