package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Conexion {
	private static Connection con;
	
	private final static String server=XMLUtil.loadDataXML().getServer();
	private final static String database=XMLUtil.loadDataXML().getDatabase();
	private final static String username=XMLUtil.loadDataXML().getUserName();
	private final static String password=XMLUtil.loadDataXML().getPassword();
	
	public static void conecta() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(server+"/"+database,username,password);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar la base de datos");
		} catch (SQLException e) {
			System.out.println("Error al conectar la base de datos");
			con=null;
		}catch (Exception e) {
			Dialog.showError("Error", "Conxion fallida", "");
		}
	}
	
	public static Connection getConexion() {
		try {
			if (con == null) {
				conecta();
			}
		} catch (Exception e) {
			Dialog.showError("Error", "Conxion fallida", "");
		}
		return con;
	}
	
	/**
	 * Metodo de CRUD en phpmyadmin.
	 * @param query frase a ejecutar en phpmyadmin (CRUD)
	 * @return devuelve una lista de frases.
	 */
	public static List<String[]> ejecutaSelect(String query){
		List<String[]> resultado=new ArrayList<String[]>();
		try {
			Statement st=con.createStatement();
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData rsmd=(ResultSetMetaData)rs.getMetaData();
			int ncolumns=rsmd.getColumnCount();
			while(rs.next()) {
				String[] fila=new String[ncolumns];
				int i=1;
				while(i<=ncolumns) {
					fila[i-1]=rs.getString(i);
				}
				resultado.add(fila);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
