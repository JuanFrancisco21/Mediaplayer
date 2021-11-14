package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Song;
import Utils.Conexion;
import Utils.Dialog;

public class SongDAO extends Song{
	private final static String SELECT_by_Name_DAO = "SELECT nombre, nrepro, duracion, id_disco, id_genero FROM song WHERE nombre=";
	private final static String INSERTUPDATE="INSERT INTO song (nombre, nrepro, duracion, id_disco, id_genero) "
			+ "VALUES (?,?,?,?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?, nrepro=?, duracion=?, id_disco=?, id_genero=?";
    private final static String DELETE_by_Id = "DELETE FROM song WHERE id = ?";
    private final static String DELETE_by_Name = "DELETE FROM song WHERE nombre = ?";
    private final static String SELECT_All = "SELECT * FROM song";
    private final static String SELECT_by_Id = "SELECT id, nombre, nrepro, duracion, id_disco, id_genero FROM song WHERE id = ?";
    private final static String SELECT_by_Name = "SELECT id, nombre, nrepro, duracion, id_disco, id_genero FROM song WHERE nombre = ?";
    
    /**
     * Constructor
     */
    public SongDAO() {
        super();
    }
    
	/**
	 * Parameterized constructor
	 *
	 * @param a Songs to update
	 */
	public SongDAO(Song a) {
		this.setId(a.getId());
		this.setName(a.getName());
		this.setDuration(a.getDuration());
		this.setDisc(a.getDisc());
		this.setGender(a.getGender());
	}

	/**
	 * Constructor
	 *
	 * @param id of the Song
	 */
	public SongDAO(Integer id) {
		this(SongDAO.List_Song_By_Id(id));
	}
	
	  /**
     * Constructor by the name
     * 
     * @param name of the artist
     */
	public SongDAO(String name) {
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name_DAO + "'" + name + "'");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					this.setId(rs.getInt("id"));
					this.setName(rs.getString("nombre"));
					this.setNrepro(rs.getInt("nrepro"));
					this.setDuration(rs.getInt("duracion"));
					this.setDisc(DiscDAO.List_Disc_By_Id_Lazy(rs.getInt("id_disco")));
					this.setGender(GenderDAO.List_Gender_By_Id(rs.getInt("id_genero")));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * List Song by id
	 *
	 * @param id unique for all the Song
	 * @return the Song with that id
	 */
	public static Song List_Song_By_Id(Integer id) {
		Song Song = new Song();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Song a = new Song();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setNrepro(rs.getInt("nrepro"));
					a.setDuration(rs.getInt("duracion"));
					a.setDisc(DiscDAO.List_Disc_By_Id_Lazy(rs.getInt("id_disco")));
					a.setGender(GenderDAO.List_Gender_By_Id(rs.getInt("id_genero")));
					Song = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Song;
	}

	/**
	 * List Songs by id
	 *
	 * @param id unique for all the Song
	 * @return the Song with that id
	 */
	public static Song List_Songs_By_Ids(Integer id) {
		Song Song = new Song();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Song a = new Song();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setNrepro(rs.getInt("nrepro"));
					a.setDuration(rs.getInt("duracion"));
					a.setDisc(DiscDAO.List_Disc_By_Id_Lazy(rs.getInt("id_disco")));
					a.setGender(GenderDAO.List_Gender_By_Id(rs.getInt("id_genero")));
					Song = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Song;
	}

	/**
	 * List all the Songs
	 *
	 * @return All the Songs
	 */
	public static List<Song> List_All_Songs() {
		List<Song> Song = new ArrayList<Song>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Song a = new Song();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setNrepro(rs.getInt("nrepro"));
					a.setDuration(rs.getInt("duracion"));
					a.setDisc(DiscDAO.List_Disc_By_Id_Lazy(rs.getInt("id_disco")));
					a.setGender(GenderDAO.List_Gender_By_Id(rs.getInt("id_genero")));
					Song.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Song;
	}

	/**
	 * List Songs by name
	 *
	 * @param name unique for all the Songs
	 * @return the Songs with that name
	 */
	public static Song List_Song_By_Name(String name) {
		Song Song = new Song();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Song a = new Song();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setNrepro(rs.getInt("nrepro"));
					a.setDuration(rs.getInt("duracion"));
					a.setDisc(DiscDAO.List_Disc_By_Id(rs.getInt("id_disco")));
					a.setGender(GenderDAO.List_Gender_By_Id(rs.getInt("id_genero")));
					Song = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Song;
	}

	/**
	 * Create new Song if don´t exist, or update if exist.
	 * 
	 * @return true if the Song has been updated/insert, false if not
	 */
	public int insert_update() {
		int rs = 0;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.name);
				q.setInt(2, this.nrepro);
				q.setInt(3, this.duration);
				q.setInt(4, this.disc.getId());
				q.setInt(5, this.gender.getId());
				q.setString(6, this.name);
				q.setInt(7, this.nrepro);
				q.setInt(8, this.duration);
				q.setInt(9, this.disc.getId());
				q.setInt(10, this.gender.getId());
				rs = q.executeUpdate();
			} catch (SQLException e) {
 				Dialog.showError("Crear Cancion", "Ha surgido un error al crear una cancion", "");
 				e.printStackTrace();

			}catch (Exception e) {
				Dialog.showError("Crear Cancion", "Ha surgido un error al crear una cancion", "");
			}
		}
		return rs;
	}

	/**
	 * Remove a Song by id
	 *
	 * @param id unique for all the Song
	 * @return true if the Song has been removed, false if not
	 */
	public static boolean Remove_Song_by_Id(Integer id) {
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
	 * Remove a Song by name
	 *
	 * @param name unique for all the Song
	 * @return true if the Song has been removed, false if not
	 */

	public static boolean Remove_Song_by_Name(String name) {
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
	 * Remove a Song
	 *
	 * @return true if the Song has been removed, false if not
	 */
	public boolean remove_Song() {
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
