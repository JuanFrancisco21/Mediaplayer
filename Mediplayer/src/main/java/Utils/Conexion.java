package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	
	/**
	 * Metodo de conexion con la bbdd sql.
	 */
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
	/**
	 * Chekea si existe la BBDD y si no existe la crea.
	 */
	public static void checkBBDD() {
		Connection c = Conexion.getConexion();
		try {
			Statement ps = c.createStatement();
			ResultSet rs = ps.executeQuery("SHOW DATABASES LIKE 'tienda'");
			if (rs.next())
			{
				
			}else {				
				crearbbdd();
			}
			
		} catch (SQLException e) {
			
		}
	}
	/**
	 * Crea base de datos guardada en resources.	
	 */
	public static void crearbbdd() {
		Connection c = Conexion.getConexion();
		String filePath = new File("").getAbsolutePath();

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String crear = "";

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(filePath + "\\src\\main\\resources\\Controller\\mediaplayer.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea;
            Statement ps = c.createStatement();

			while ((linea = br.readLine()) != null) {
				ps.executeUpdate(linea);
			}
			// System.out.println(crear);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
