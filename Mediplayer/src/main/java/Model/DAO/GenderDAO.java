package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Gender;
import Utils.Conexion;

public class GenderDAO extends Gender{
	private final static String SELECT_by_Name_DAO = "SELECT nombre FROM gender WHERE nombre=";
	private final static String INSERTUPDATE="INSERT INTO gender (nombre) "
			+ "VALUES (?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?";
    private final static String DELETE_by_Id = "DELETE FROM gender WHERE id = ?";
    private final static String DELETE_by_Name = "DELETE FROM gender WHERE nombre = ?";
    private final static String SELECT_All = "SELECT * FROM gender";
    private final static String SELECT_by_Id = "SELECT id, nombre FROM gender WHERE id = ?";
    private final static String SELECT_by_Name = "SELECT id, nombre FROM gender WHERE nombre = ?";
    
    /**
     * Constructor
     */
    public GenderDAO() {
        super();
    }
    
    /**
     * Parameterized constructor
     *
     * @param a Gender to update
     */
    public GenderDAO(Gender a) {
        this.setId(a.getId());
        this.setName(a.getName());
    }
    
	/**
	 * Constructor
	 *
	 * @param id of the Gender
	 */
	public GenderDAO(Integer id) {
		this(GenderDAO.List_Gender_By_Id(id));
	}
	
	/**
     * Constructor by the name
     * 
     * @param name of the Gender
     */
	public GenderDAO(String name) {
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name_DAO + "'" + name + "'");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					this.setId(rs.getInt("id"));
					this.setName(rs.getString("nombre"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * List Genders by id
	 *
	 * @param id unique for all the Gender
	 * @return the Gender with that id
	 */
	public static Gender List_Gender_By_Id(Integer id) {
		Gender Gender = new Gender();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Gender a = new Gender();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					Gender = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Gender;
	}

	/**
	 * List all the Genders
	 *
	 * @return All the Genders
	 */
	public static List<Gender> List_All_Gender() {
		List<Gender> Gender = new ArrayList<Gender>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Gender a = new Gender();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					Gender.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Gender;
	}

	/**
	 * List Genders by name
	 *
	 * @param name unique for all the Gender
	 * @return the Gender with that name
	 */
	public static Gender List_Gender_By_Name(String name) {
		Gender Gender = new Gender();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Gender a = new Gender();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					Gender = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Gender;
	}

	/**
	 * Create new Gender if don´t exist, or update if exist.
	 * 
	 * @return true if the Gender has been updated/insert, false if not
	 */
	public int insert_update() {
		int rs = 0;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.name);
				q.setString(2, this.name);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar Genero");
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Remove a Gender by id
	 *
	 * @param id unique for all the Gender
	 * @return true if the Gender has been removed, false if not
	 */
	public static boolean Remove_Gender_by_Id(Integer id) {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, id);
				int i = ps.executeUpdate();
				if (i >= 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a Gender by name
	 *
	 * @param name unique for all the Gender
	 * @return true if the Gender has been removed, false if not
	 */

	public static boolean Remove_Gender_by_Name(String name) {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Name);
				ps.setString(1, name);
				int i = ps.executeUpdate();
				if (i >= 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a Gender
	 *
	 * @return true if the Gender has been removed, false if not
	 */
	public boolean remove_Gender() {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, this.getId());
				int i = ps.executeUpdate();
				if (i >= 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
