package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;
import Utils.Conexion;
import Utils.Dialog;

public class UserDAO extends User {
	private final static String SELECT_by_Name_DAO = "SELECT nombre, correo, password FROM user WHERE nombre=";
	private final static String INSERTUPDATE="INSERT INTO user (nombre, correo, password) "
			+ "VALUES (?,?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?,correo=?, password=?";
    private final static String DELETE_by_Id = "DELETE FROM user WHERE id = ?";
    private final static String DELETE_by_Name = "DELETE FROM user WHERE nombre = ?";
    private final static String SELECT_All = "SELECT * FROM user";
    private final static String SELECT_by_Id = "SELECT id, nombre, correo, password FROM user WHERE id = ?";
    private final static String SELECT_by_Name = "SELECT id, nombre, correo, password FROM user WHERE nombre = ?";
    
    /**
     * Constructor
     */
    public UserDAO() {
        super();
    }
    
    /**
     * Parameterized constructor
     *
     * @param a User to update
     */
    public UserDAO(User a) {
        this.setId(a.getId());
        this.setName(a.getName());
        this.setCorreo(a.getCorreo());
        this.setPassword(a.getPassword());
    }
    
    
	/**
	 * List Users by id
	 *
	 * @param id unique for all the User
	 * @return the User with that id
	 */
	public UserDAO(Integer id) {
		this(UserDAO.List_User_By_Id(id));
	}

	  /**
     * Constructor by the name
     * 
     * @param name of the User
     */
	public UserDAO(String name) {
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name_DAO + "'" + name + "'");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					this.setId(rs.getInt("id"));
					this.setName(rs.getString("nombre"));
					this.setCorreo(rs.getString("correo"));
					this.setPassword(rs.getString("password"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * List Users by id
	 *
	 * @param id unique for all the User
	 * @return the User with that id
	 */
	public static User List_User_By_Id(Integer id) {
		User User = new User();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					User a = new User();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setCorreo(rs.getString("correo"));
					a.setPassword(rs.getString("password"));
					User = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return User;
	}

	/**
	 * List all the Users
	 *
	 * @return All the Users
	 */
	public static List<User> List_All_User() {
		List<User> Users = new ArrayList<User>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					User a = new User();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setCorreo(rs.getString("correo"));
					a.setPassword(rs.getString("password"));
					Users.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Users;
	}

	/**
	 * List Users by name
	 *
	 * @param name unique for all the User
	 * @return the User with that name
	 */
	public static User List_User_By_Name(String name) {
		User User = new User();
		try {
			Connection c = Conexion.getConexion();
		

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					User a = new User();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setCorreo(rs.getString("correo"));
					a.setPassword(rs.getString("password"));
					User = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		} catch (Exception e) {
			Dialog.showError("Error", "Conexión erronea", "");
		}
		return User;
	}

	/**
	 * Create new User if don´t exist, or update if exist.
	 * 
	 * @return true if the User has been updated/insert, false if not
	 */
	public int insert_update() {
		int rs = 0;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.name);
				q.setString(2, this.correo);
				q.setString(3, this.password);
				q.setString(4, this.name);
				q.setString(5, this.correo);
				q.setString(6, this.password);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar Usuario");
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Remove a User by id
	 *
	 * @param id unique for all the User
	 * @return true if the User has been removed, false if not
	 */
	public static boolean Remove_User_by_Id(Integer id) {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, id);
				int i = ps.executeUpdate();
				if (i == 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a User by name
	 *
	 * @param name unique for all the User
	 * @return true if the User has been removed, false if not
	 */

	public static boolean Remove_User_by_Name(String name) {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Name);
				ps.setString(1, name);
				int i = ps.executeUpdate();
				if (i == 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a User
	 *
	 * @return true if the User has been removed, false if not
	 */
	public boolean remove_User() {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, this.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
