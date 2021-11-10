package Controller;

import Model.DAO.UserDAO;

public class Ejecutable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hola");
		System.out.println(UserDAO.List_User_By_Name("pepe"));
	}

}
