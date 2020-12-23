package tebe2020;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Integer choosen;
    ArrayList<Login> authArrayList = new ArrayList<Login>();

    public Menu(ArrayList auth) throws NoSuchElementException, SQLException {
        authArrayList = auth;

        do {
            System.out.println("\nDaftar Menu ======");
            System.out.println("1. Kelola User");
            System.out.println("2. Kelola Barang");
            System.out.println("3. Kelola Transaksi");
            System.out.println("4. User beli barang");
            System.out.println("5. Restok barang");
            System.out.println("6. Laporan");
            System.out.println("0. Exit");
            System.out.print("Pilihan : ");
            //varibel menu pilihan
            choosen = sc.nextInt();
            System.out.println("--------------------------");
            directMenu();
        } while (choosen != 0);
    }

    public void directMenu() throws SQLException {
    	


        switch (choosen){
            case 1:
                inUser user = new inUser();
                user.menu("User");
                break;

            case 2:
                inBarang barang = new inBarang();
                barang.menu("Barang");
                break;

            case 3:
                Transaksi transaksi = new Transaksi();
            	transaksi.menu("Transaksi");
                break;

            case 4:
                Proses jual = new Proses(authArrayList);
            	jual.menuUtamaJual();
                break;

            case 5:
                barang = new inBarang();
            	barang.reStockMenu();
                break;

            case 6:
                inLaporan laporan = new inLaporan();
            	laporan.menu();
                break;

            case 0:
                System.exit(0);
    			System.out.println("Terima Kasih");

            default:
                System.out.println("Tidak Ada Pilihan");
        }

    }
}
