package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Model.Artist;
import Model.Disc;
import Model.Gender;
import Model.List_Song;
import Model.Playlist;
import Model.Song;
import Model.Subscribe;
import Model.User;
import Model.DAO.ArtistDAO;
import Model.DAO.DiscDAO;
import Model.DAO.GenderDAO;
import Model.DAO.List_SongDAO;
import Model.DAO.PlaylistDAO;
import Model.DAO.SongDAO;
import Model.DAO.SubscribeDAO;
import Utils.Dialog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController implements Initializable{

	@FXML
	private TabPane dos;
	@FXML
	private Tab TabAdmin;
	@FXML
	private Tab TabBuscar; 

	@FXML
	private static User user;
	//-----------------------------------------------PlayList----------------------------------------------------------
	public ObservableList<Playlist> allplaylist;
	@FXML
	private TableView<Playlist> tablaallPlayList;
	@FXML
	private TableColumn<Playlist, String> cpp1;
	@FXML
	private TableColumn<Playlist,String> cpp2;
	@FXML
	private TableColumn<Playlist,String> cpp3;
	
	
	public ObservableList<Song> allcanciones;
	@FXML
	private TableView<Song> tablaallCanciones;
	@FXML
	private TableColumn<Song, String> css1;
	@FXML
	private TableColumn<Song, Integer> css2;
	@FXML
	private TableColumn<Song, String> css3;
	@FXML
	private TableColumn<Song, String> css4;
	@FXML
	private TableColumn<Song, Integer> css5;
	
	
	public ObservableList<Playlist> playlist;
	@FXML
	private TableView<Playlist> tablaPlayList;
	@FXML
	private TableColumn<Playlist, String> cp1;
	@FXML
	private TableColumn<Playlist,String> cp2;
	@FXML
	private TableColumn<Playlist,String> cp3;
	@FXML
	private TableView<Song> tablaCancionesPlaylist;
	@FXML
	private TableColumn<Song, String> csp1;
	@FXML
	private TableColumn<Song, Integer> csp2;
	@FXML
	private TableColumn<Song, String> csp3;
	@FXML
	private TableColumn<Song, String> csp4;
	@FXML
	private TableColumn<Song, Integer> csp5;
	@FXML
	private TextArea DescripcionPlaylist;
	@FXML
	private TextField NombrePlaylist;
	@FXML
	private TextField CreadorPlaylist;
	@FXML
	private Button BorrarPlaylist;
	@FXML
	private Button AñadirPlaylist;
	@FXML
	private Button CancionesPlaylist;
	@FXML
	private Button CancionesPlaylist2;
	@FXML
	private Button Reproducir;
	@FXML
	private Button PlaylistPlaylist;
	@FXML
	private Button PlaylistPlaylist2;


	//-----------------------------------------------PlayList----------------------------------------------------------

	// ---------------------------------------------Cancion------------------------------------------------------------

	public ObservableList<Song> canciones;
	@FXML
	private TableView<Song> tablaCanciones;
	@FXML
	private TableColumn<Song, String> cs1;
	@FXML
	private TableColumn<Song, Integer> cs2;
	@FXML
	private TableColumn<Song, String> cs3;
	@FXML
	private TableColumn<Song, String> cs4;
	@FXML
	private TableColumn<Song, Integer> cs5;
	@FXML
	private TextField NombreCancion;
	@FXML
	private TextField DuracionCancion;
	@FXML
	private ChoiceBox<String> DiscoCancion;
	@FXML
	private ChoiceBox<String> GeneroCancion;
	@FXML
	private Button BorrarCancion;
	@FXML
	private Button AñadirCancion;

	// ---------------------------------------------Cancion------------------------------------------------------------

	// ---------------------------------------------Artista------------------------------------------------------------
	
	public ObservableList<Artist> artistas;
	@FXML
	private TableView<Artist> tablaArtistas;
	@FXML
	private TableColumn<Artist, String> ca1;
	@FXML
	private TableColumn<Artist, String> ca2;
	@FXML
	private TextField NombreArtista;
	@FXML
	private TextField NacionalidadArtista;
	@FXML
	private TextField FotoArtista;
	@FXML
	private ImageView foartista;
	@FXML
	private Button BorrarArtista;
	@FXML
	private Button AñadirArtista;

	// ---------------------------------------------Artista------------------------------------------------------------

	// -----------------------------------------------Disco------------------------------------------------------------
	
	public ObservableList<Disc> discos;
	@FXML
	private TableView<Disc> tablaDiscos;
	@FXML
	private TableColumn<Disc, String> cd1;
	@FXML
	private TableColumn<Disc, String> cd2;
	@FXML
	private TextField NombreDisco;
	@FXML
	private DatePicker FechaDisco;
	@FXML
	private TextField FotoDisco;
	@FXML
	private ChoiceBox<String> ArtistaDisco;
	@FXML
	private ImageView fodisco;
	@FXML
	private Button BorrarDisco;
	@FXML
	private Button AñadirDisco;

	// -----------------------------------------------Disco------------------------------------------------------------

	// -----------------------------------------------Genero-----------------------------------------------------------
	
	public ObservableList<Gender> generos;
	@FXML
	private TableView<Gender> tablaGeneros;
	@FXML
	private TableColumn<Gender, String> cg1;
	@FXML
	private TableColumn<Gender, Integer> cg2;
	@FXML
	private TextField NombreGenero;
	@FXML
	private Button Borrargenero;
	@FXML
	private Button Añadirgenero;

	// -----------------------------------------------Genero----------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (!this.user.getName().equals("admin")) {
			TabAdmin.setDisable(true);   
			TabAdmin.setStyle("-tab-text-color: green;");
		}
		
		
		

	}
	public static void initController(User u) {
		user=u;
	}

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login");
    }
    
    
	//-----------------------------------------------PlayList----------------------------------------------------------
    public void initPlaylist() {
		DescripcionPlaylist.setWrapText(true);
		configuraTablaPlaylist();
	}
	//-----------------------------------------------PlayList2----------------------------------------------------------
		    public void initPlaylistBuscar() {
		    	showCancionesTab();
		    	CancionesPlaylist.setVisible(false);
		    	CancionesPlaylist2.setVisible(false);
				Reproducir.setVisible(false);

		    	PlaylistPlaylist.setVisible(true);
		    	PlaylistPlaylist2.setVisible(true);
		    	PlaylistPlaylist.setDisable(true);
		    	PlaylistPlaylist2.setDisable(true);
		    	configuraTablaallPlaylist();
		    }
		 	@FXML
		 	private void followPlylist() {
		 		Subscribe a = new Subscribe();
		 		Playlist selected=tablaallPlayList.getSelectionModel().getSelectedItem();
		 		if ( selected != null && SubscribeDAO.List_Subscribe_By_Ids(user.getId(), selected.getId()).getId()==-1) {
		 			try {
		 				a.setList(selected);
		 				a.setUser(user);
		 				SubscribeDAO b = new SubscribeDAO(a);

		 				b.insert_update();
		 				configuraTablaallPlaylist();
		 				configuraTablaPlaylist();

		 			} catch (Exception e) {
		 				Dialog.showError("Seguir Playlist", "Ha surgido un error al seguir una playlist", "");
		 				e.printStackTrace();
		 			}
		 		} else {
		 			Dialog.showWarning("Seguir Playlist", "Selecciona Playlist", "Solo puedes seguir listas que no seguias");
		 		}

		 	}
		 	@FXML
		 	private void unfollowPlylist() {
		 		Subscribe a = new Subscribe();
		 		Playlist selected=tablaallPlayList.getSelectionModel().getSelectedItem();
		 		if ( selected != null ) {
		 			try {
		 				a.setList(selected);
		 				a.setUser(user);
		 				SubscribeDAO b = new SubscribeDAO(a);

		 				b.remove_Subscribe();
		 				configuraTablaallPlaylist();
		 				configuraTablaPlaylist();
		 			} catch (Exception e) {
		 				Dialog.showError("Dejar de seguir Playlist", "Ha surgido un error al dejar de seguir una playlist", "");
		 				e.printStackTrace();
		 			}
		 		} else {
		 			Dialog.showWarning("Dejar de seguir Playlist", "Selecciona Playlist", "");
		 		}

		 	}
		    private void configuraTablaallPlaylist() {
				this.allplaylist = FXCollections.observableArrayList();
				this.allplaylist.setAll(PlaylistDAO.List_All_Playlist_Usernot(user));

				cpp1.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getName());
				});

				cpp2.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getDescription());
				});
				cpp3.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getUser().getName());
				});


				tablaallPlayList.setEditable(true);
				tablaallPlayList.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldvalue, newvalue) -> showTab(newvalue));
				tablaallPlayList.setItems(allplaylist);
		    }
		    private void showTab(Playlist c) {
				if (c != null) {
					PlaylistPlaylist.setDisable(false);
					PlaylistPlaylist2.setDisable(false);
					NombrePlaylist.setText(c.getName());
					DescripcionPlaylist.setText(c.getDescription());
					CreadorPlaylist.setText(c.getUser().getName());

				} else {
					PlaylistPlaylist.setDisable(true);
					PlaylistPlaylist2.setDisable(true);
					NombrePlaylist.setText("");
					DescripcionPlaylist.setText("");
					CreadorPlaylist.setText("");

				}
				
			}
	
    //-----------------------------------------------PlayList2----------------------------------------------------------

		    public void initPlaylistCanciones() {
		    	showCancionesTab();
		    	configuraTablaallCanciones();
			}
		    private void configuraTablaallCanciones() {
				this.allcanciones = FXCollections.observableArrayList();
				this.allcanciones.setAll(SongDAO.List_All_Songs());

				css1.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getName());
				});

				css2.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getNrepro());
				});
				css3.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getGender().getName());
				});
				css4.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getDisc().getName());
				});

				css5.setCellValueFactory(cellData -> {
					return new SimpleObjectProperty<>(cellData.getValue().getDuration());
				});

				
				tablaallCanciones.setItems(allcanciones);
		    }
		    /**
		     * Metodo para añadir cacion a una lista
		     */
		    @FXML
		 	private void addSongtoPlylist() {
		 		List_Song a = new List_Song();
		 		Playlist select= tablaPlayList.getSelectionModel().getSelectedItem();
		 		Song selected=tablaallCanciones.getSelectionModel().getSelectedItem();
		 		if ( select.getUser().getName().equals(user.getName())&& selected != null && select!=null && List_SongDAO.List_List_Song_By_Id(select.getId(), selected.getId()).getId()==-1) {
		 			try {
		 				a.setSong(selected);
		 				a.setList(select);
		 				List_SongDAO b = new List_SongDAO(a);

		 				b.insert_update();
		 				//configuraTablaPlaylist();
		 			} catch (Exception e) {
		 				Dialog.showError("Añadir cancion a playlist", "Ha surgido un error al añadirla", "");
		 				e.printStackTrace();
		 			}
		 		} else {
		 			Dialog.showWarning("Añadir cancion a playlist", "Selecciona Cancion", "No se pueden repetir las canciones");
		 		}

		 	}
		    /**
		     * Metodo para borrar una cacion de una lista
		     */
		    @FXML
		 	private void BorrarSongfromPlylist() {
		 		List_Song a = new List_Song();
		 		Playlist select= tablaPlayList.getSelectionModel().getSelectedItem();
		 		Song selected=tablaCancionesPlaylist.getSelectionModel().getSelectedItem();
		 		if ( selected != null && select!=null) {
		 			try {
		 				a.setSong(selected);
		 				a.setList(select);
		 				List_SongDAO b = new List_SongDAO(a);

		 				b.remove_List_Song();
		 				configuraTablaPlaylist();
		 			} catch (Exception e) {
		 				Dialog.showError("Eliminar cancion de una playlist", "Ha surgido un error al borrarla", "");
		 				e.printStackTrace();
		 			}
		 		} else {
		 			Dialog.showWarning("Eliminar cancion de una playlist", "Selecciona Cancion", "");
		 		}

		 	}
		    @FXML
		    public void showCancionesTab() {
				CancionesPlaylist.setVisible(true);
				BorrarPlaylist.setVisible(false);
				AñadirPlaylist.setVisible(false);
				Reproducir.setVisible(false);
				PlaylistPlaylist.setVisible(false);
				PlaylistPlaylist2.setVisible(false);
			}
		    @FXML
		    public void notshowCancionesTab() {
				PlaylistPlaylist.setVisible(false);
				PlaylistPlaylist2.setVisible(false);
				CancionesPlaylist.setVisible(false);
				Reproducir.setVisible(false);
				BorrarPlaylist.setVisible(true);
				AñadirPlaylist.setVisible(true);
			}
    @FXML
    public void ReproducirCancion() {
    	Song s=tablaCancionesPlaylist.getSelectionModel().getSelectedItem();
    	s.setNrepro(s.getNrepro()+1);
    	SongDAO save=new SongDAO(s);
    	System.out.println(save);
    	save.update();
    	//configuraTablaPlaylist();
    }
    
    /**
 	 * Método para añadir a una cancion en la base de datos, como tambien en la
 	 * ObservableList.
 	 */
 	@FXML
 	private void addPlaylist() {
 		Playlist a = new Playlist();
 		if (PlaylistDAO.List_Playlist_By_Name(a.getName()).getId() == -1 && NombrePlaylist.getText().length() >= 3 && user != null) {
 			try {
 				a.setName(NombrePlaylist.getText());
 				a.setDescription(DescripcionPlaylist.getText());
 				a.setUser(user);
 				PlaylistDAO b = new PlaylistDAO(a);

 				b.insert_update();
 				playlist.add(a);
 				configuraTablaPlaylist();
 			} catch (Exception e) {
 				Dialog.showError("Crear Playlist", "Ha surgido un error al crear una Playlist", "");
 				e.printStackTrace();
 			}
 		} else {
 			Dialog.showConfirm("Crear Playlist", "Rellene los campos", "");
 		}

 	}

 	/**
 	 * Método para borrar a una playlist de la base de datos como de la ObservableList.
 	 */
 	@FXML
 	private void BorrarPlaylist() {
 		Boolean confirmacion=Dialog.showConfirm("Confirmación", "¿Quieres borra la Playlist?", "");
 		Playlist selected = tablaPlayList.getSelectionModel().getSelectedItem();
 		if (selected != null && confirmacion && selected.getUser().getName().equals(user.getName())) {
 			try {
 				BorrarPlaylist.setDisable(false);
 				PlaylistDAO a = new PlaylistDAO(selected);
 				a.remove_Playlist();
 				configuraTablaPlaylist();

 			} catch (Exception e) {
 				BorrarCancion.setDisable(true);
 				Dialog.showError("Borrar Playlist", "Ha surgido un error al borrar una playlist", "");

 			}

 		}else {
			Dialog.showWarning("Borrar Playlist", "Borra solo playlist propias", "");

 		}
 	}
    /**
	 * Metodo para representar datos en la tabla.
	 */
    @FXML
	private void configuraTablaPlaylist() {
		this.playlist = FXCollections.observableArrayList();
		this.playlist.setAll(PlaylistDAO.List_All_Playlist_By_User(user.getId()));
		this.playlist.addAll(SubscribeDAO.List_All_Subscribe_Of_User(user.getId()));

		cp1.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getName());
		});

		cp2.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getDescription());
		});
		cp3.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getUser().getName());
		});


		tablaPlayList.setEditable(true);
		tablaPlayList.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldvalue, newvalue) -> showCancionesPlaylist(newvalue));
		tablaPlayList.setItems(playlist);

		showCancionesPlaylist(null);
		
		this.canciones = FXCollections.observableArrayList();

		csp1.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getName());
		});

		csp2.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getNrepro());
		});
		csp3.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getGender().getName());
		});
		csp4.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getDisc().getName());
		});

		csp5.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getDuration());
		});

		tablaCancionesPlaylist.setEditable(true);
		tablaCancionesPlaylist.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldvalue, newvalue) -> showRemoveSong(newvalue));
		tablaCancionesPlaylist.setItems(canciones);
		
		showRemoveSong(null);
	}
    private void showRemoveSong(Song c) {
 		Playlist select= tablaPlayList.getSelectionModel().getSelectedItem();

    	if (c != null) {
    		if (select.getUser().getName().equals(user.getName())) {
				
    			CancionesPlaylist2.setVisible(true);
			}
    		Reproducir.setVisible(true);

    	}else {
    		CancionesPlaylist2.setVisible(false);
			Reproducir.setVisible(false);


    	}
    }
	/**
	 * Muestra la informacion de una playlist
	 * @param c cliente selecionado
	 */
	private void showCancionesPlaylist(Playlist c) {
		if (c != null) {
			canciones.clear();
			List<Song> r=List_SongDAO.List_All_Songs_By_Playlist(c.getId());
			canciones.addAll(r);
			CancionesPlaylist.setDisable(false);
			NombrePlaylist.setText(c.getName());
			DescripcionPlaylist.setText(c.getDescription());
			CreadorPlaylist.setText(c.getUser().getName());
			if (c.getUser().getName().equals(user.getName())) {
				
				BorrarPlaylist.setDisable(false);
				TabBuscar.setDisable(false);
			}else {
				BorrarPlaylist.setDisable(true);
				TabBuscar.setDisable(true);

			}

		} else {
			BorrarPlaylist.setDisable(true);
			CancionesPlaylist.setDisable(true);
			NombrePlaylist.setText("");
			DescripcionPlaylist.setText("");
			CreadorPlaylist.setText("");
			TabBuscar.setDisable(true);
		}

		
	}

    //-----------------------------------------------PlayList----------------------------------------------------------

    // ---------------------------------------------Cancion------------------------------------------------------------
    public void initCanciones() {
		configuraTablaCanciones();
	}
    /**
 	 * Método para añadir a una cancion en la base de datos, como tambien en la
 	 * ObservableList.
 	 */
 	@FXML
 	private void addCancion() {
 		Song a = new Song();
 		if (SongDAO.List_Song_By_Name(a.getName()).getId() == -1 && NombreCancion.getText().length() >= 3
 				&& FotoArtista != null) {
 			try {
 				a.setName(NombreCancion.getText());
 				a.setNrepro(0);
 				a.setDuration(Integer.parseInt(DuracionCancion.getText()));
 				a.setGender(GenderDAO.List_Gender_By_Name(GeneroCancion.getValue().toString()));
 				a.setDisc(DiscDAO.List_Disc_By_Name(DiscoCancion.getValue().toString()));
 				SongDAO b = new SongDAO(a);

 				b.insert_update();
 				canciones.add(a);
 				configuraTablaCanciones();
 			} catch (Exception e) {
 				Dialog.showError("Crear Cancion", "Ha surgido un error al crear una cancion", "");
 				e.printStackTrace();
 			}
 		} else {
 			Dialog.showConfirm("Crear Cancion", "Rellene los campos", "");
 		}

 	}

 	/**
 	 * Método para borrar a una cancion de la base de datos como de la ObservableList.
 	 */
 	@FXML
 	private void BorrarCancion() {
 		Boolean confirmacion=Dialog.showConfirm("Confirmación", "¿Quieres borra la canción?", "");
 		Song selected = tablaCanciones.getSelectionModel().getSelectedItem();
 		if (selected != null && confirmacion) {
 			try {
 				BorrarCancion.setDisable(false);
 				SongDAO a = new SongDAO(selected);
 				a.remove_Song();
 				configuraTablaCanciones();

 			} catch (Exception e) {
 				BorrarCancion.setDisable(true);
 				Dialog.showError("Borrar Cancion", "Ha surgido un error al borrar una Cancion", "");

 			}

 		}
 	}
    /**
	 * Metodo para representar datos en la tabla.
	 */
	private void configuraTablaCanciones() {
		this.canciones = FXCollections.observableArrayList();
		this.canciones.setAll(SongDAO.List_All_Songs());

		cs1.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getName());
		});

		cs2.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getNrepro());
		});
		cs3.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getGender().getName());
		});
		cs4.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getDisc().getName());
		});

		cs5.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getDuration());
		});

		tablaCanciones.setEditable(true);
		tablaCanciones.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldvalue, newvalue) -> showCanciones(newvalue));
		tablaCanciones.setItems(canciones);

		showCanciones(null);

	}

	/**
	 * Muestra la informacion  de una cancion
	 * 
	 * @param c cancion selecionada
	 */
	private void showCanciones(Song c) {
		ObservableList<String> discos_nombres = FXCollections.observableArrayList();
		ObservableList<String> generos_nombres = FXCollections.observableArrayList();


		if (c != null) {
			Song r = SongDAO.List_Song_By_Name(c.getName());
			generos_nombres.clear();
			generos_nombres.add(r.getGender().getName());
			discos_nombres.clear();
			discos_nombres.add(r.getDisc().getName());

			NombreCancion.setText(r.getName());
			DuracionCancion.setText(""+r.getDuration());
			DiscoCancion.setItems(discos_nombres);
			DiscoCancion.setValue(r.getDisc().getName());
			GeneroCancion.setItems(generos_nombres);
			GeneroCancion.setValue(r.getGender().getName());
			BorrarCancion.setDisable(false);

		} else {
			generos_nombres.clear();
			generos_nombres.addAll(GenderDAO.List_All_Gender_Name());
			discos_nombres.clear();
			discos_nombres.addAll(DiscDAO.List_All_Disc_Name());

			NombreCancion.setText("");
			DuracionCancion.setText("");
			DiscoCancion.setItems(discos_nombres);
			DiscoCancion.setValue(null);
			GeneroCancion.setItems(generos_nombres);
			GeneroCancion.setValue(null);
			BorrarCancion.setDisable(true);
		}

	}
    // ---------------------------------------------Cancion------------------------------------------------------------

	// ---------------------------------------------Artista------------------------------------------------------------
    public void initArtista() {
		configuraTablaArtistas();
	}
    /**
	 * Método para añadir a un artista en la base de datos, como tambien en la
	 * ObservableList.
	 */
	@FXML
	private void addArtista() {
		Artist a = new Artist();
		if (ArtistDAO.List_Artist_By_Name(a.getName()).getId() == -1 && NombreArtista.getText().length() >= 3
				&& FotoArtista != null) {
			try {
				a.setName(NombreArtista.getText());
				a.setNationality(NacionalidadArtista.getText());
				a.setPhoto(FotoArtista.getText());
				ArtistDAO b = new ArtistDAO(a);

				b.insert_update();
				artistas.add(a);
				configuraTablaArtistas();
			} catch (Exception e) {
				Dialog.showError("Crear Artista", "Ha surgido un error al crear un nuevo disco", "");
			}
		} else {
			Dialog.showConfirm("Crear Artista", "Rellene los campos", "");
		}

	}

	/**
	 * Método para borrar a un artista de la base de datos como de la ObservableList.
	 */
	@FXML
	private void BorrarArtista() {
		Boolean confirmacion=Dialog.showConfirm("Confirmación", "¿Quieres borra el Artista?", "");
		Artist selected = tablaArtistas.getSelectionModel().getSelectedItem();
		if (selected != null && confirmacion) {
			try {
				BorrarArtista.setDisable(false);
				ArtistDAO a = new ArtistDAO(selected);
				a.remove_Artist();
				configuraTablaArtistas();

			} catch (Exception e) {
				BorrarArtista.setDisable(true);
				Dialog.showError("Borrar Artista", "Ha surgido un error al borrar un artista", "");

			}

		}
	}
    /**
	 * Metodo para representar datos en la tabla.
	 */
	private void configuraTablaArtistas() {
		this.artistas = FXCollections.observableArrayList();
		this.artistas.setAll(ArtistDAO.List_All_Artist());

		ca1.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getName());
		});

		ca2.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getNationality());
		});

		tablaArtistas.setEditable(true);
		tablaArtistas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldvalue, newvalue) -> showArtistas(newvalue));
		tablaArtistas.setItems(artistas);

		showArtistas(null);

	}

	/**
	 * Muestra la informacion de un artista
	 * 
	 * @param c artista selecionado
	 */
	private void showArtistas(Artist c) {
		if (c != null) {
			Artist r = ArtistDAO.List_Artist_By_Name(c.getName());
			NombreArtista.setText(r.getName());
			NacionalidadArtista.setText(r.getNationality());
			FotoArtista.setText(r.getPhoto());
			BorrarArtista.setDisable(false);
			try {
				Image image = new Image(App.class.getResourceAsStream(r.getPhoto()));
				foartista.setImage(image);
			} catch (Exception e) {
				Image image = new Image(App.class.getResourceAsStream("error.jpg"));
				foartista.setImage(image);
				System.out.println("");
			}

		} else {
			BorrarArtista.setDisable(true);
			NombreArtista.setText("");
			NacionalidadArtista.setText("");
			FotoArtista.setText("");
		}

	}
	// ---------------------------------------------Artista------------------------------------------------------------

	// -----------------------------------------------Genero----------------------------------------------------------
	public void initDisco() {
		configuraTablaDiscos();
	}

	/**
	 * Método para añadir a un disco en la base de datos, como tambien en la
	 * ObservableList.
	 */
	@FXML
	private void addDisco() {
		Disc a = new Disc();
		if (DiscDAO.List_Disc_By_Name(a.getName()).getId() == -1 && NombreDisco.getText().length() >= 3
				&& FechaDisco.getValue().isAfter(LocalDate.parse("1800-10-30"))) {
			try {
				Disc selected = tablaDiscos.getSelectionModel().getSelectedItem();
				a.setName(NombreDisco.getText());
				a.setDate(Date.valueOf(FechaDisco.getValue()));
				a.setPhoto(FotoDisco.getText());
				a.setArtist(selected.getArtist());
				DiscDAO b = new DiscDAO(a);

				b.insert_update();
				discos.add(a);
				configuraTablaDiscos();
			} catch (Exception e) {
				Dialog.showError("Crear Disco", "Ha surgido un error al crear un nuevo disco", "");
			}
		} else {
			Dialog.showWarning("Crear Disco", "Rellene los campos", "");
		}

	}

	/**
	 * Método para borrar a un disco de la base de datos como de la ObservableList.
	 */
	@FXML
	private void BorrarDisco() {
		Boolean confirmacion=Dialog.showConfirm("Confirmación", "¿Quieres borra el disco?", "");
		Disc selected = tablaDiscos.getSelectionModel().getSelectedItem();
		if (selected != null && confirmacion) {
			try {
				BorrarDisco.setDisable(false);
				DiscDAO a = new DiscDAO(selected);
				a.remove_Disc();
				configuraTablaDiscos();

			} catch (Exception e) {
				BorrarDisco.setDisable(true);
				Dialog.showError("Borrar Disco", "Ha surgido un error al borrar un disco", "");

			}

		}
	}

	/**
	 * Metodo para representar datos en la tabla.
	 */
	private void configuraTablaDiscos() {
		this.discos = FXCollections.observableArrayList();
		this.discos.setAll(DiscDAO.List_All_Disc());

		cd1.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getName());
		});

		cd2.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getArtist().getName());
		});

		tablaDiscos.setEditable(true);
		tablaDiscos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldvalue, newvalue) -> showDiscos(newvalue));
		tablaDiscos.setItems(discos);

		showDiscos(null);

	}

	/**
	 * Muestra la informacion de un disco
	 * 
	 * @param c disco selecionado
	 */
	private void showDiscos(Disc c) {
		ObservableList<String> artistas_nombres = FXCollections.observableArrayList();

		if (c != null) {
			artistas_nombres.clear();
			Disc r = DiscDAO.List_Disc_By_Name(c.getName());
			artistas_nombres.add(r.getArtist().getName());
			NombreDisco.setText(r.getName());
			ArtistaDisco.setItems(artistas_nombres);
			ArtistaDisco.setValue(r.getArtist().getName());
			FechaDisco.setValue(LocalDate.parse(c.getDate().toString()));
			FotoDisco.setText(r.getPhoto());
			BorrarDisco.setDisable(false);
			try {
				Image image = new Image(App.class.getResourceAsStream(r.getPhoto()));
				fodisco.setImage(image);
			} catch (Exception e) {
				Image image = new Image(App.class.getResourceAsStream("error.jpg"));
				fodisco.setImage(image);
				System.out.println("");
			}

		} else {
			artistas_nombres.clear();
			artistas_nombres.addAll(ArtistDAO.List_All_Artist_Name());
			BorrarDisco.setDisable(true);
			NombreDisco.setText("");
			ArtistaDisco.setItems(artistas_nombres);
			ArtistaDisco.setValue(null);
			FechaDisco.setValue(null);
			FotoDisco.setText("");
		}

	}
	// -----------------------------------------------Genero----------------------------------------------------------

	// -----------------------------------------------Genero----------------------------------------------------------
	public void initGeneros() {
		configuraTablaGenero();
	}

	/**
	 * Método para añadir a un genero en la base de datos, como tambien en la
	 * ObservableList.
	 */
	@FXML
	private void addGenero() {
		Gender a = new Gender();
		if (GenderDAO.List_Gender_By_Name(a.getName()).getId() == -1) {
			try {
				GenderDAO b = new GenderDAO(a);

				b.insert();
				generos.add(a);
				configuraTablaGenero();
			} catch (Exception e) {
				Dialog.showError("Crear Genero", "Ha surgido un error al crear una genero nueva", "");
			}
		} else {
			Dialog.showWarning("Crear Genero", "Rellene los campos", "");
		}

	}

	/**
	 * Método para borrar a un genero de la base de datos como de la ObservableList.
	 */
	@FXML
	private void BorrarGenero() {
		Boolean confirmacion=Dialog.showConfirm("Confirmación", "¿Quieres borra el genero?", "");
		Gender selected = tablaGeneros.getSelectionModel().getSelectedItem();
		if (selected != null && confirmacion) {
			try {
				
				Borrargenero.setDisable(false);
				GenderDAO a = new GenderDAO(selected);
				a.remove_Gender();
				configuraTablaGenero();

			} catch (Exception e) {
				Borrargenero.setDisable(true);
				Dialog.showError("Borrar Genero", "Ha surgido un error al borrar el genero", "");

			}

		}
	}

	/**
	 * Metodo para representar datos en la tabla.
	 */
	private void configuraTablaGenero() {
		this.generos = FXCollections.observableArrayList();
		this.generos.setAll(GenderDAO.List_All_Gender());

		cg1.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getName());
		});
		cg1.setCellFactory(TextFieldTableCell.forTableColumn());
		cg1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gender, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Gender, String> t) {

				Gender selected = (Gender) t.getTableView().getItems().get(t.getTablePosition().getRow());
				System.out.println(selected);
				if (GenderDAO.List_Gender_By_Name(t.getNewValue()).getId() == -1) {
					NombreGenero.setText(t.getNewValue());
					selected.setName(t.getNewValue());
					System.out.println(selected);

				} else {
					Dialog.showWarning("Genero", "Genero no valido", "");
					configuraTablaGenero();
				}
				GenderDAO cc = new GenderDAO(selected);
				cc.insert_update();
			}
		});

		cg2.setCellValueFactory(cellData -> {
			return new SimpleObjectProperty<>(cellData.getValue().getId());
		});

		tablaGeneros.setEditable(true);
		tablaGeneros.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldvalue, newvalue) -> showGeneros(newvalue));
		tablaGeneros.setItems(generos);

		showGeneros(null);

	}

	/**
	 * Muestra la informacion de un genero
	 * 
	 * @param c genero selecionado
	 */
	private void showGeneros(Gender c) {
		NombreGenero.setText("");
		if (c != null) {
			Gender r = GenderDAO.List_Gender_By_Name(c.getName());
			NombreGenero.setText(r.getName());
			Borrargenero.setDisable(false);

		} else {
			Borrargenero.setDisable(true);
		}

	}
	// -----------------------------------------------Genero----------------------------------------------------------

}
