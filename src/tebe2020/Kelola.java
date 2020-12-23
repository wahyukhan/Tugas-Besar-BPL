package tebe2020;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class Kelola implements inKelola{
    Scanner sc = new Scanner(System.in);

    public void menu(String menu) {
        int choosen;
        do {
            System.out.println("\n\nMenu Kelola "+menu);
            System.out.println("1. Lihat Semua");
            System.out.println("2. Tambah");
            System.out.println("3. Hapus");
            System.out.println("4. Edit");
            System.out.println("5. Cari");
            System.out.println("0. Kembali");
            System.out.print("Pilihan : ");
            switch (choosen = sc.nextInt()) {
                case 1:
                	tampilkanData();
                    break;

                case 2:
                	tambahData();
                    break;

                case 3:
                	hapusData();
                    break;

                case 4:
                	editData();
                    break;

                case 5:
                	cariData();
                    break;
                    
                case 0:
                    break;
                    
                default:
                    System.out.println("Pilihan tidak ada");
                    break;

            }
            tunggu();
        } while (choosen != 0);
    }
    public Object showOne(String id){
        return null;
    }
    public LinkedList show() {
        return null;
    }
    public Object search(String key) throws SQLException {
        return null;
    }
    public void tunggu() throws NoSuchElementException {
        Scanner sc = new Scanner(System.in);
        System.out.print("======");
        sc.nextLine();
    }
}