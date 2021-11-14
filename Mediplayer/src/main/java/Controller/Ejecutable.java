package Controller;

import Model.Conection;
import Model.Disc;
import Model.DAO.ArtistDAO;
import Model.DAO.DiscDAO;
import Model.DAO.GenderDAO;
import Model.DAO.UserDAO;
import Utils.XMLUtil;

public class Ejecutable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(GenderDAO.List_All_Gender_Name());
	}

}
