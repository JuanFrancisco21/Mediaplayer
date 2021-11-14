package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Playlist;
import Model.Subscribe;
import Utils.Conexion;

public class SubscribeDAO extends Subscribe {
	private final static String INSERTUPDATE="INSERT INTO subscribe (id_usuario, id_lista) "
			+ "VALUES (?,?) "
			+ "ON DUPLICATE KEY UPDATE  id_usuario=?, id_lista=?";
    private final static String DELETE_by_Id = "DELETE FROM subscribe WHERE id_lista = ? AND id_usuario = ?";
    private final static String SELECT_All = "SELECT * FROM subscribe";
    private final static String SELECT_All_By_List = "SELECT id_lista FROM subscribe WHERE id_usuario = ?";
    private final static String SELECT_by_Id = "SELECT id, id_lista, id_lista FROM subscribe WHERE id = ?";
    private final static String SELECT_by_Ids= "SELECT id, id_lista, id_usuario FROM subscribe WHERE id_usuario = ? AND id_lista = ?";

    /**
     * Constructor
     */
    public SubscribeDAO() {
        super();
    }
    
    /**
     * Parameterized constructor
     *
     * @param a Play to update
     */
    public SubscribeDAO(Subscribe a) {
        this.setId(a.getId());
        this.setUser(a.getUser());
        this.setList(a.getList());
    }
    
    /**
     * Constructor
     *
     * @param  id of the Subscribe
     */
    public SubscribeDAO(Integer id) {
        this(SubscribeDAO.List_Subscribe_By_Id(id));
    }
    
    
	/**
	 * List Subscribe by id
	 *
	 * @param id unique for all the Subscribe
	 * @return the Subscribe with that id
	 */
	public static Subscribe List_Subscribe_By_Id(Integer id) {
		Subscribe Subscribe = new Subscribe();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Subscribe a = new Subscribe();
					a.setId(rs.getInt("id"));
					a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
					a.setList(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
					Subscribe = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Subscribe;
	}
	/**
	 * List Subscribe by ids
	 *
	 * @param id unique for all the Subscribe
	 * @return the Subscribe with that id
	 */
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Subscribe List_Subscribe_By_Ids(Integer id_usuario,Integer id_lista) {
		Subscribe Subscribe = new Subscribe();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_by_Ids);
				ps.setInt(1, id_usuario);
				ps.setInt(2, id_lista);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Subscribe a = new Subscribe();
					a.setId(rs.getInt("id"));
					a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
					a.setList(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
					Subscribe = a;
				}
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Subscribe;
	}
	/**
	 * List all the Subscribe
	 *
	 * @return All the Subscribe
	 */
	public static List<Subscribe> List_All_Subscribe() {
		List<Subscribe> Subscribe = new ArrayList<Subscribe>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Subscribe a = new Subscribe();
					a.setId(rs.getInt("id"));
					a.setUser(UserDAO.List_User_By_Id(rs.getInt("id_usuario")));
					a.setList(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
					Subscribe.add(a);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Subscribe;
	}
	
	/**
	 * List all the Subscribe of a list
	 *
	 * @param id of the user 
	 * @return All the Subscribes
	 */

	public static List<Playlist> List_All_Subscribe_Of_User(Integer id_user) {
		List<Playlist> Subscribe = new ArrayList<Playlist>();
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(SELECT_All_By_List);
				ps.setInt(1, id_user);
				ResultSet rs = ps.executeQuery();
				while (rs != null && rs.next()) {
					Subscribe.add(PlaylistDAO.List_Playlist_By_Id(rs.getInt("id_lista")));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Subscribe;
	}


	/**
	 * Create new Subscribe if don´t exist, or update if exist.
	 * 
	 * @return true if the Subscribe has been updated/insert, false if not
	 */
	public boolean insert_update() {
		boolean result = false;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setInt(1, this.user.getId());
				q.setInt(2, this.list.getId());
				q.setInt(3, this.user.getId());
				q.setInt(4, this.list.getId());

				int i = q.executeUpdate();
				if (i >= 1) {
					result = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al guardar/insertar suscribirse");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Remove a Subscribe by id
	 *
	 * @param id unique for all the Subscribe
	 * @return true if the Subscribe has been removed, false if not
	 */
	public static boolean Remove_Subscribe_by_Id(Integer id) {
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
	 * Remove a Subscribe
	 *
	 * @return true if the Subscribe has been removed, false if not
	 */
	public boolean remove_Subscribe() {
		boolean result = false;
		Connection c = Conexion.getConexion();

		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
				ps.setInt(1, this.getList().getId());
				ps.setInt(2, this.getUser().getId());
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
