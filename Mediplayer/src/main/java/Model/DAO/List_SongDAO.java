package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Artist;
import Model.List_Song;
import Model.Song;
import Utils.Conexion;

public class List_SongDAO extends List_Song {
	private final static String INSERTUPDATE="INSERT INTO list_Song (id, id_lista, id_cancion) "
			+ "VALUES (?,?,?) "
			+ "ON DUPLICATE KEY UPDATE id=?, id_lista=?, id_cancion=?";
    private final static String DELETE_by_Id = "DELETE FROM list_Song WHERE id = ?";
    private final static String SELECT_All = "SELECT * FROM list_Song";
    private final static String SELECT_All_By_List = "SELECT id_cancion FROM list_Song WHERE id_lista = ?";
    private final static String SELECT_by_Id = "SELECT id, id_lista, id_cancion FROM list_Song WHERE id = ?";
    
    /**
     * Constructor
     */
    public List_SongDAO() {
        super();
    }
    
    /**
     * Parameterized constructor
     *
     * @param a List_Song to update
     */
    public List_SongDAO(List_Song a) {
        this.setId(a.getId());
        this.setList(a.getList());
        this.setSong(a.getSong());
    }
    
    /**
     * Constructor
     *
     * @param  id of the List_Song
     */
    public List_SongDAO(Integer id) {
        this(List_SongDAO.List_List_Song_By_Id(id));
    }
    
    
	/**
	 * List List_Song by id
	 *
	 * @param id unique for all the List_Song
	 * @return the List_Song with that id
	 */
	public static List_Song List_List_Song_By_Id(Integer id) {
		List_Song List_Song = new List_Song();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					List_Song a = new List_Song();
					a.setId(rs.getInt("id"));
					a.setList(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
					a.setSong(SongDAO.List_Song_By_Id(rs.getInt("id_cancion")));
					List_Song = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return List_Song;
	}

	/**
	 * List all the List_Song
	 *
	 * @return All the List_Song
	 */
	public static List<List_Song> List_All_List_Song() {
		List<List_Song> List_Song = new ArrayList<List_Song>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					List_Song a = new List_Song();
					a.setId(rs.getInt("id"));
					a.setList(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
					a.setSong(SongDAO.List_Song_By_Id(rs.getInt("id_cancion")));
					List_Song.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return List_Song;
	}
	
	/**
	 * List all the Songs of a list
	 *
	 * @param id of the list 
	 * @return All the Songs
	 */

	public static List<Song> List_All_Songs_By_Playlist(Integer id_list) {
		List<Song> Songs = new ArrayList<Song>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All_By_List);
				ps.setInt(1, id_list);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Songs.add(SongDAO.List_Song_By_Id(rs.getInt("id_cancion")));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Songs;
	}


	/**
	 * Create new List_Song if don´t exist, or update if exist.
	 * 
	 * @return true if the List_Song has been updated/insert, false if not
	 */
	public boolean insert_update() {
		boolean result = false;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setInt(1, this.id);
				q.setInt(2, this.list.getId());
				q.setInt(3, this.song.getId());
				q.setInt(4, this.id);
				q.setInt(5, this.list.getId());
				q.setInt(6, this.song.getId());

				int i = q.executeUpdate();
				if (i >= 1) {
					result = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar List_Song");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a List_Song by id
	 *
	 * @param id unique for all the List_Song
	 * @return true if the List_Song has been removed, false if not
	 */
	public static boolean Remove_List_Song_by_Id(Integer id) {
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
	 * Remove a List_Song
	 *
	 * @return true if the List_Song has been removed, false if not
	 */
	public boolean remove_List_Song() {
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
