package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Artist;
import Model.Playlist;
import Model.User;
import Utils.Conexion;

public class PlaylistDAO extends Playlist {
	private final static String INSERTUPDATE = "INSERT INTO list (nombre, descripcion, id_usuario) "
			+ "VALUES (?,?,?) " 
			+ "ON DUPLICATE KEY UPDATE nombre=?,descripcion=?, id_usuario=?";
	private final static String DELETE_by_Id = "DELETE FROM list WHERE id = ?";
	private final static String DELETE_by_Name = "DELETE FROM list WHERE nombre = ?";
	private final static String SELECT_All = "SELECT * FROM list";
	private final static String SELECT_All_Usernot = "SELECT * FROM list where not id_usuario=?";

	private final static String SELECT_by_Id = "SELECT id, nombre, descripcion, id_usuario FROM list WHERE id = ?";
	private final static String SELECT_by_Id_User = "SELECT id, nombre, descripcion, id_usuario FROM list WHERE id_usuario = ?";
	private final static String SELECT_by_Name = "SELECT id, nombre, descripcion, id_usuario FROM list WHERE nombre = ?";

	/**
	 * Constructor
	 */
	public PlaylistDAO() {
		super();
	}

	/**
	 * Parameterized constructor
	 *
	 * @param a Playlist to update
	 */
	public PlaylistDAO(Playlist a) {
		this.setId(a.getId());
		this.setName(a.getName());
		this.setDescription(a.getDescription());
		this.setUser(a.getUser());
		this.setSongs(a.getSongs());
	}

	/**
	 * Constructor
	 *
	 * @param id of the Playlist
	 */
	public PlaylistDAO(Integer id) {
		this(PlaylistDAO.List_Playlist_By_Id(id));
	}

	/**
	 * List Playlist by id
	 *
	 * @param id unique for all the Playlist
	 * @return the Playlist with that id
	 */
	public static Playlist List_Playlist_By_Id(Integer id) {
		Playlist Playlist = new Playlist();
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Playlist a = new Playlist();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("nombre"));
				a.setDescription(rs.getString("descripcion"));
				a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
				a.setSongs(List_SongDAO.List_All_Songs_By_Playlist(rs.getInt("id")));
				Playlist = a;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Playlist;
	}

	/**
	 * List all the Playlist by de id of the user
	 * @param id_user to search playlists
	 * @return All the Playlists of the user
	 */
	public static List<Playlist> List_All_Playlist_By_User(Integer id_user) {
		List<Playlist> Playlist = new ArrayList<Playlist>();
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(SELECT_by_Id_User);
			ps.setInt(1, id_user);
			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				Playlist a = new Playlist();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("nombre"));
				a.setDescription(rs.getString("descripcion"));
				a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
				a.setSongs(List_SongDAO.List_All_Songs_By_Playlist(rs.getInt("id")));
				Playlist.add(a);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Playlist;
	}

	/**
	 * List all the Playlist
	 *
	 * @return All the Playlist
	 */
	public static List<Playlist> List_All_Playlist() {
		List<Playlist> Playlist = new ArrayList<Playlist>();
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(SELECT_All);
			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				Playlist a = new Playlist();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("nombre"));
				a.setDescription(rs.getString("descripcion"));
				a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
				a.setSongs(List_SongDAO.List_All_Songs_By_Playlist(rs.getInt("id")));
				Playlist.add(a);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Playlist;
	}
	/**
	 * List all the Playlist except the user lists
	 *
	 * @return All the Playlist
	 */
	public static List<Playlist> List_All_Playlist_Usernot(User u) {
		List<Playlist> Playlist = new ArrayList<Playlist>();
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(SELECT_All_Usernot);
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				Playlist a = new Playlist();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("nombre"));
				a.setDescription(rs.getString("descripcion"));
				a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
				a.setSongs(List_SongDAO.List_All_Songs_By_Playlist(rs.getInt("id")));
				Playlist.add(a);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Playlist;
	}

	/**
	 * List Playlists by name
	 *
	 * @param name unique for all the Playlists
	 * @return the Playlists with that name
	 */
	public static Playlist List_Playlist_By_Name(String name) {
		Playlist Playlist = new Playlist();
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Playlist a = new Playlist();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("nombre"));
				a.setDescription(rs.getString("descripcion"));
				a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
				a.setSongs(List_SongDAO.List_All_Songs_By_Playlist(rs.getInt("id")));
				Playlist = a;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Playlist;
	}

	/**
	 * Create new Playlist if don´t exist, or update if exist.
	 * 
	 * @return true if the Playlist has been updated/insert, false if not
	 */
	public int insert_update() {
		int rs = 0;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setString(1, this.name);
				q.setString(2, this.description);
				q.setInt(3, this.user.getId());
				q.setString(4, this.name);
				q.setString(5, this.description);
				q.setInt(6, this.user.getId());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar Playlist");
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Remove a Playlist by id
	 *
	 * @param id unique for all the Playlist
	 * @return true if the Playlist has been removed, false if not
	 */
	public static boolean Remove_Playlist_by_Id(Integer id) {
		boolean result = false;
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i >= 1) {
				result = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Remove a Playlist by name
	 *
	 * @param name unique for all the Playlist
	 * @return true if the Playlist has been removed, false if not
	 */

	public static boolean Remove_Playlist_by_Name(String name) {
		boolean result = false;
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(DELETE_by_Name);
			ps.setString(1, name);
			int i = ps.executeUpdate();
			if (i >= 1) {
				result = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Remove a Playlist
	 *
	 * @return true if the Playlist has been removed, false if not
	 */
	public boolean remove_Playlist() {
		boolean result = false;
		try {
			Connection c = Conexion.getConexion();
			PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
			ps.setInt(1, this.getId());
			int i = ps.executeUpdate();
			if (i >= 1) {
				result = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
}
