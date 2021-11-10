package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Artist;
import Model.Disc;
import Utils.Conexion;

public class DiscDAO extends Disc {
	private final static String SELECT_by_Name_DAO = "SELECT nombre, foto, fecha, id_artista FROM disc WHERE nombre=";
	private final static String INSERTUPDATE="INSERT INTO disc (nombre, foto, fecha, id_artista) "
			+ "VALUES (?,?,?,?) "
			+ "ON DUPLICATE KEY UPDATE nombre=?, foto=?, fecha, id_artista=?";
    private final static String DELETE_by_Id = "DELETE FROM disc WHERE id = ?";
    private final static String DELETE_by_Name = "DELETE FROM disc WHERE nombre = ?";
    private final static String SELECT_All = "SELECT * FROM disc";
    private final static String SELECT_by_Id = "SELECT id, nombre, foto, fecha, id_artista FROM disc WHERE id = ?";
    private final static String SELECT_by_Name = "SELECT id, nombre, foto, fecha, id_artista FROM disc WHERE nombre = ?";
    
    /**
     * Constructor
     */
    public DiscDAO() {
        super();
    }
    
	/**
	 * Parameterized constructor
	 *
	 * @param a Disc to update
	 */
	public DiscDAO(Disc a) {
		this.setId(a.getId());
		this.setName(a.getName());
		this.setDate(a.getDate());
		this.setPhoto(a.getPhoto());
		this.setArtist(a.getArtist());
	}

	/**
	 * Constructor
	 *
	 * @param id of the Disc
	 */
	public DiscDAO(Integer id) {
		this(DiscDAO.List_Disc_By_Id(id));
	}
	
	/**
     * Constructor by the name
     * 
     * @param name of the artist
     */
	public DiscDAO(String name) {
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name_DAO + "'" + name + "'");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					this.setId(rs.getInt("id"));
					this.setName(rs.getString("nombre"));
					this.setDate(rs.getDate("fecha"));
					this.setPhoto(rs.getString("foto"));
					this.setArtist(ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista")));
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * List Disc by id
	 *
	 * @param id unique for all the Disc
	 * @return the Disc with that id
	 */
	public static Disc List_Disc_By_Id(Integer id) {
		Disc disc = new Disc();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Disc a = new Disc();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setDate(rs.getDate("fecha"));
					a.setPhoto(rs.getString("foto"));
					a.setArtist(ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista")));
					disc = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return disc;
	}

	/**
	 * List Disc by id without the artist.
	 *
	 * @param id unique for all the Disc
	 * @return the Disc with that id
	 */
	public static Disc List_Disc_By_Id_Lazy(Integer id) {
		Disc disc = new Disc();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Disc a = new Disc();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setDate(rs.getDate("fecha"));
					a.setPhoto(rs.getString("foto"));
					a.setArtist(new Artist());
					disc = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return disc;
	}

	/**
	 * List all the Discs
	 *
	 * @return All the Discs
	 */
	public static List<Disc> List_All_Disc() {
		List<Disc> Disc = new ArrayList<Disc>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Disc a = new Disc();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setDate(rs.getDate("fecha"));
					a.setPhoto(rs.getString("foto"));
					a.setArtist(ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista")));
					Disc.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Disc;
	}

	/**
	 * List Disc by name
	 *
	 * @param name unique for all the Disc
	 * @return the Disc with that name
	 */
	public static Disc List_Disc_By_Name(String name) {
		Disc Disc = new Disc();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Disc a = new Disc();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("nombre"));
					a.setDate(rs.getDate("fecha"));
					a.setPhoto(rs.getString("foto"));
					a.setArtist(ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista")));
					Disc = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Disc;
	}

	/**
	 * Create new Disc if don´t exist, or update if exist.
	 * 
	 * @return true if the Disc has been updated/insert, false if not
	 */
	public boolean insert_update() {
		boolean result = false;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.name);
				q.setDate(2, this.date);
				q.setString(3, this.photo);
				q.setInt(4, this.artist.getId());
				q.setString(5, this.name);
				q.setDate(6, this.date);
				q.setString(7, this.photo);
				q.setInt(8, this.artist.getId());
				int i = q.executeUpdate();
				if (i >= 1) {
					result = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar Disco");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a disc by id
	 *
	 * @param id unique for all the disc
	 * @return true if the disc has been removed, false if not
	 */
	public static boolean Remove_Disc_by_Id(Integer id) {
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
	 * Remove a disc by name
	 *
	 * @param name unique for all the disc
	 * @return true if the disc has been removed, false if not
	 */

	public static boolean Remove_Disc_by_Name(String name) {
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
	 * Remove a disc
	 *
	 * @return true if the disc has been removed, false if not
	 */
	public boolean remove_Disc() {
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
