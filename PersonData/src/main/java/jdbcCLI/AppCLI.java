package jdbcCLI;

import java.util.Scanner;

public class AppCLI {
	int personId;
	int personNewId;
	String firstName;
	String lastName;

	public AppCLI() {
		personId = 0;
		personNewId=0;
		firstName = null;
		lastName = null;
	}

	//USER INPUT FOR INSERT DATA
	public void add() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Person ID:");
		personId = scanner.nextInt();
		System.out.println("Enter First Name:");
		firstName = scanner.next();
		System.out.println("Enter Last Name:");
		lastName = scanner.next();
	}

	//USER INPUT TO EDIT DATA
	public void edit() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Update Person ID:");
		personNewId = scanner.nextInt();
		System.out.println("Update First Name:");
		firstName = scanner.next();
		System.out.println("Update Last Name:");
		lastName = scanner.next();
	}

	//	//USER INPUT TO DELETE ROW
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		personId = scanner.nextInt();
	}
}
