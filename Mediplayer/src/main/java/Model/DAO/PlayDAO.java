package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.List_Song;
import Model.Play;
import Model.Song;
import Utils.Conexion;

public class PlayDAO extends Play {
	private final static String INSERTUPDATE="INSERT INTO play (id, id_usuario, id_cancion, fecha) "
			+ "VALUES (?,?,?,?) "
			+ "ON DUPLICATE KEY UPDATE id=?, id_usuario=?, id_cancion=?, fecha=?";
    private final static String DELETE_by_Id = "DELETE FROM play WHERE id = ?";
    private final static String SELECT_All = "SELECT * FROM play";
    private final static String SELECT_All_By_List = "SELECT id_cancion FROM play WHERE id_cancion = ?";
    private final static String SELECT_by_Id = "SELECT id, id_lista, id_cancion FROM play WHERE id = ?";
    
    /**
     * Constructor
     */
    public PlayDAO() {
        super();
    }
    
    /**
     * Parameterized constructor
     *
     * @param a Play to update
     */
    public PlayDAO(Play a) {
        this.setId(a.getId());
        this.setUser(a.getUser());
        this.setSong(a.getSong());
        this.setDate(a.getDate());
    }
    
    /**
     * Constructor
     *
     * @param  id of the Play
     */
    /*public PlayDAO(Integer id) {
        this(PlayDAO.List_Play_By_Id(id));
    }*/
    

	/**
	 * List Play by id
	 *
	 * @param id unique for all the Play
	 * @return the Play with that id
	 */
	/*public static Play List_Play_By_Id(Integer id) {
		Play Play = new Play();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Play a = new Play();
					a.setId(rs.getInt("id"));
					a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
					a.setSong(SongDAO.List_Song_By_Id(rs.getInt("id_cancion")));
					a.setDate(rs.getTimestamp("fecha"));
					Play = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Play;
	}*/

	/**
	 * List all the Play
	 *
	 * @return All the Play
	 */
	/*public static List<Play> List_All_Play() {
		List<Play> Play = new ArrayList<Play>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Play a = new Play();
					a.setId(rs.getInt("id"));
					a.setList(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
					a.setSong(SongDAO.List_Song_By_Id(rs.getInt("id_cancion")));
					Play.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Play;
	}*/
	
	/**
	 * List all the Play of a list
	 *
	 * @param id of the song 
	 * @return All the reproductions
	 */

	/*public static List<Song> List_All_Play_Of_Song(Integer id_song) {
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
	}*/


	/**
	 * Create new Play if don´t exist, or update if exist.
	 * 
	 * @return true if the Play has been updated/insert, false if not
	 */
	/*public boolean insert_update() {
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
				System.out.println("Error al guardar/insertar Reproduccion");
				e.printStackTrace();
			}
		}
		return result;
	}*/

	/**
	 * Remove a Play by id
	 *
	 * @param id unique for all the Play
	 * @return true if the Play has been removed, false if not
	 */
	/*public static boolean Remove_Play_by_Id(Integer id) {
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
	}*/


	/**
	 * Remove a Play
	 *
	 * @return true if the Play has been removed, false if not
	 */
	/*public boolean remove_Play() {
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
	}*/
	
}
