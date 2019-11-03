package jdbcCLI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();

		int option = 0;
		AppCLI ac = new AppCLI();
		Scanner scanner = new Scanner(System.in);
		char s1 = 0;
		do {
			System.out.println("\n------------------------------------");
			System.out.println("\nPERSONS DIRECTORY");
			System.out.println("\n------------------------------------");
			System.out.println("\n1 Add Person");
			System.out.println("\n2 Edit Person");
			System.out.println("\n3 Delete Person");
			System.out.println("\n4 Number of Counts");
			System.out.println("\n5 List all Persons");
			System.out.println("\n====================================");
			System.out.println("\nCHOOSE YOUR OPTION BETWEEN 1 AND 5: ");
			System.out.println("\n====================================");
			switch (option = scanner.nextInt()) {
			case 1:
				System.out.println("\nAdd Person!\n");
				ac.add();
				mainApp.addPerson(ac.personId, ac.firstName, ac.lastName);
				break;

			case 2:
				System.out.println("Enter the person ID wants to edit:\n");
				ac.edit();
				mainApp.editPerson(ac.personId, ac.personNewId, ac.firstName, ac.lastName);
				break;

			case 3:
				System.out.println("Enter the person ID wants to delete:");
				ac.delete();
				mainApp.deletePerson(ac.personId);
				break;

			case 4:
				mainApp.countPersons();
				break;

			case 5:
				System.out.println("View all the persons list");
				mainApp.listPersons();
			}
			System.out.println("View Main menu Y/N");
			s1 = scanner.next().charAt(0);
		} while (s1 == 'y' || s1 == 'y');
	}
	
	public void addPerson(int personId, String firstName, String lastName) {
		DB_Connection dbConnection = new DB_Connection();
		Connection connection = dbConnection.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "insert into person(id,fname,lname) values (?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, personId);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Inserted Successfully...");
			} else {
				System.out.println("Not Inserted...");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void editPerson(int id, int new_id, String fname, String lname) {
		DB_Connection dbConnection = new DB_Connection();
		Connection connection = dbConnection.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "update person set id=?,fname=?,lname=? where id=?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, new_id);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setInt(4, id);
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Successfully Updated...");
			} else {
				System.out.println("Not Updated...");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletePerson(int id) {
		DB_Connection dbConnection = new DB_Connection();
		Connection connection = dbConnection.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "delete from person where id=?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println("Deleted Successfully...");
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void countPersons() {
		DB_Connection dbConnection = new DB_Connection();
		Connection connection = dbConnection.get_connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select count(*) from person";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			int countPerson = rs.getInt(1);
			System.out.println("Total number of counts: " + countPerson);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void listPersons() {
		DB_Connection dbConnection = new DB_Connection();
		Connection connection = dbConnection.get_connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select * from person";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			System.out.println("----------------------------------------------------------");
			System.out.printf("%10s %20s %20s", "Person ID", "First Name", "Last Name");
			System.out.println();
			System.out.println("----------------------------------------------------------");
			while (rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				System.out.format("%10s %20s %20s", id, fname, lname);
				System.out.println();
			}
			System.out.println("==========================================================");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}