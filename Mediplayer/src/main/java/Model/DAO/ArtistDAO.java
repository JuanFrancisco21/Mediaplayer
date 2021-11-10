package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Artist;
import Utils.Conexion;

public class ArtistDAO extends Artist{
	
	private final static String SELECT_by_Name_DAO = "SELECT nombre, nacionalidad,foto FROM artist WHERE nombre=";
	private final static String INSERTUPDATE="INSERT INTO artist (nombre, nacionalidad,foto) "
			+ "VALUES (?,?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?,nacionalidad=?,foto=?";
    private final static String DELETE_by_Id = "DELETE FROM artista WHERE id = ?";
    private final static String DELETE_by_Name = "DELETE FROM artista WHERE nombre = ?";
    private final static String SELECT_All = "SELECT * FROM artista";
    private final static String SELECT_by_Id = "SELECT id, nombre, nacionalidad, foto FROM artista WHERE id = ?";
    private final static String SELECT_by_Name = "SELECT id, nombre, nacionalidad, foto FROM artista WHERE nombre = ?";
    
    /**
     * Constructor
     */
    public ArtistDAO() {
        super();
    }
    
    /**
     * Parameterized constructor
     *
     * @param a Artist to update
     */
    public ArtistDAO(Artist a) {
        this.setId(a.getId());
        this.setName(a.getName());
        this.setNationality(a.getNationality());
        this.setPhoto(a.getPhoto());
    }
    
    /**
     * Constructor
     *
     * @param  id of the artist
     */
    public ArtistDAO(Integer id) {
        this(ArtistDAO.List_Artist_By_Id(id));
    }
    
    /**
     * Constructor by the name
     * 
     * @param name of the artist
     */
	public ArtistDAO(String name) {
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name_DAO + "'" + name + "'");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					this.setId(rs.getInt("id"));
					this.setName(rs.getString("nombre"));
					this.setNationality(rs.getString("nacionalidad"));
					this.setPhoto(rs.getString("foto"));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
    
	/**
	 * List artists by id
	 *
	 * @param id unique for all the artist
	 * @return the artist with that id
	 */
	public static Artist List_Artist_By_Id(Integer id) {
		Artist artist = new Artist();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Artist a = new Artist();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setNationality(rs.getString("nacionalidad"));
					a.setPhoto(rs.getString("foto"));
					artist = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return artist;
	}

	/**
	 * List all the artist
	 *
	 * @return All the artist
	 */
	public static List<Artist> List_All_Artist() {
		List<Artist> artists = new ArrayList<Artist>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Artist a = new Artist();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setNationality(rs.getString("nacionalidad"));
					a.setPhoto(rs.getString("foto"));
					artists.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return artists;
	}

	/**
	 * List artists by name
	 *
	 * @param name unique for all the artist
	 * @return the artist with that name
	 */
	public static Artist List_Artist_By_Name(String name) {
		Artist artist = new Artist();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				Artist a = new Artist();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("nombre"));
				a.setNationality(rs.getString("nacionalidad"));
				a.setPhoto(rs.getString("foto"));
				artist = a;
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return artist;
	}

	/**
	 * Create new Artist if don´t exist, or update if exist.
	 * 
	 * @return true if the artist has been updated/insert, false if not
	 */
	public boolean insert_update() {
		boolean result = false;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.name);
				q.setString(2, this.nationality);
				q.setString(3, this.photo);
				q.setString(4, this.name);
				q.setString(5, this.nationality);
				q.setString(6, this.photo);
				int i = q.executeUpdate();
				if (i > 1) {
					result = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar Artista");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a artist by id
	 *
	 * @param id unique for all the artist
	 * @return true if the artist has been removed, false if not
	 */
	public static boolean Remove_Artist_by_Id(Integer id) {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, id);
				int i = ps.executeUpdate();
				if (i > 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a artist by name
	 *
	 * @param name unique for all the artist
	 * @return true if the artist has been removed, false if not
	 */

	public static boolean Remove_Artist_by_Name(String name) {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Name);
				ps.setString(1, name);
				int i = ps.executeUpdate();
				if (i > 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a artist
	 *
	 * @return true if the artist has been removed, false if not
	 */
	public boolean remove_Artist() {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, this.getId());
				int i = ps.executeUpdate();
				if (i > 1) {
					result = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
}
