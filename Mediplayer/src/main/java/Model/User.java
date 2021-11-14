package Model;

public class User {
	protected int id;
	protected String name;
	protected String correo;
	protected String password;
	
	public User() {
		this.id = -1;
		this.name = "";
		this.correo = "";
		this.password = "";
	}
	public User(String name, String correo, String password) {
		this.id = -1;
		this.name = name;
		this.correo = correo;
		this.password = password;
	}
	public User(int id, String name, String correo, String password) {
		this.id = id;
		this.name = name;
		this.correo = correo;
		this.password = password;

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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
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
		User other = (User) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
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
		return "User [id=" + id + ", name=" + name + ", correo=" + correo + ", password=" + password + "]";
	}
	
	
	
}
