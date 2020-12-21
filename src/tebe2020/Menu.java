package tebe2020;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	// Pilihan menu
		public static void user_pilih(){
			User user =new User();
			Barang barang = new Barang();
			Scanner scan = new Scanner(System.in);
			System.out.println("\n\nDaftar Menu ======");
			System.out.println("1. Kelola Akun");
			System.out.println("2. Kelola Data Barang");
			System.out.println("3. Transaksi");
			System.out.println("0. Exit");
			System.out.print("Pilihan : ");
			
			try {
				Integer pilihan = scan.nextInt();

				switch (pilihan) {
					case 0:
						System.out.println("Terima Kasih!");
						System.exit(0);
						break;
					case 1:
						user.user_setting();
						break;
					
					case 2:
						barang .barang_setting();
						break;	

					case 3:
						
						break;

					default:
						System.out.println("Pilihan tidak tersedia");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Pilihan tidak tersedia");
			}

			scan.close();

		}
}
