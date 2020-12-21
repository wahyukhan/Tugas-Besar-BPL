package tebe2020;

import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;

public class akses {
	
	static Scanner scn = new Scanner(System.in);
	static Date date = new Date();
	
	public static void main(String[] args) throws Exception {
		User user = new User();
		Login log = new Login();
		System.out.println("============================");
		System.out.println(date+"\n");
		System.out.println("1. Login \t 2. Daftar");
		System.out.println("============================");
		System.out.print("Pilihan : ");

		try {
			Integer pilihan = scn.nextInt();

			switch (pilihan) {
				case 1:
					log.login();

					break;
	
				case 2:
					user.tambahData();
					Menu.user_pilih();
					break;
			
				default:
					System.out.println("Pilihan tidak tersedia");
					System.exit(0);
					break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Pilihan tidak tersedia");
		}
			
	}

}
