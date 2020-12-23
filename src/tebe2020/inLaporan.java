package tebe2020;

;

import java.sql.*;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class inLaporan{
    Scanner scanner;
    Connect connect = new Connect();
    Connection connection;


    public void menu(){
        scanner = new Scanner(System.in);
        int pilihan;

        try{
            do{
                System.out.println("\n\nLaporan ======");
                System.out.println("1. Bulanan");
                System.out.println("2. Harian");
                System.out.println("3. Keuntungan");
                System.out.println("0. Kembali");
                System.out.print("Pilihan : ");
                pilihan = scanner.nextInt();

                switch (pilihan){
                    case 1:
                        Bulanan();
                        break;

                    case 2:
                        Harian();
                        break;

                    case 3:
                        Keuntungan();
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Tidak terdapat pilihan");
                }
                tunggu();
            }while (pilihan!=0);

            scanner.close();
        }catch (NoSuchElementException e){
            System.out.print(e.getMessage());
        }
    }

    public void Bulanan(){
        try {
            connection = connect.getConn();
            System.out.println("\n\nLaporan Bulanan ======");
            String sql = "SELECT transaksi.noresi, transaksi.username, SUM(detail_transaksi.jumlah) as jumBarang, SUM(detail_transaksi.harga) as harga FROM detail_transaksi JOIN transaksi ON transaksi.noresi = detail_transaksi.noresi WHERE month(transaksi.tanggal) = month(current_date) GROUP by transaksi.noresi";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            System.out.println("============================");
            System.out.println("Nomor Resi | Username | Total Barang Dibeli | Total Belanja");

            while (resultSet.next()){
                int jum = resultSet.getInt("jumBarang");
                int harga = resultSet.getInt("harga");

                System.out.print(resultSet.getString("noresi"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("username"));
                System.out.print(" | ");
                System.out.print(jum);
                System.out.print(" | ");
                System.out.println(jum*harga);
            }
        }catch (NoSuchElementException | SQLException e){

        }
    }

    public void Harian(){
        try {
            scanner = new Scanner(System.in);
            Connection connection = connect.getConn();

            String tahun, bulan, tanggal, sql;
            System.out.println("\n\nLaporan Harian ======");
            System.out.println("Tanggal : ");
            tanggal = scanner.nextLine();	           
            System.out.println("Bulan : ");
            bulan = scanner.nextLine();
            System.out.println("Tahun : ");
            tahun = scanner.nextLine();
            sql = "SELECT transaksi.noresi, transaksi.username, SUM(detail_transaksi.jumlah) as jumBarang, SUM(detail_transaksi.harga) as harga FROM detail_transaksi JOIN transaksi ON transaksi.noresi = detail_transaksi.noresi WHERE transaksi.tanggal = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tahun+"/"+bulan+"/"+tanggal);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n==========================================");
            System.out.println("Nomor Resi | Username | Total Barang Dibeli | Total Belanja");

            while (resultSet.next()){
                int jum = resultSet.getInt("jumBarang");
                int harga = resultSet.getInt("harga");

                System.out.print(resultSet.getString("noresi"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("username"));
                System.out.print(" | ");
                System.out.print(jum);
                System.out.print(" | ");
                System.out.println(jum*harga);
            }

        }catch (NoSuchElementException e){
            System.out.println("Kesalahan input data");
        }catch (SQLException ex){
            System.out.println("Kesalahan pada query database");
        }
    }

    public void Keuntungan(){
        String sql;
        int jumlah;
        int hargaBeli;
        int hargaJual;
        System.out.println("\n\nLaporan Keuntungan ======");
        try{
            Statement statement;
            connection = connect.getConn();
            ResultSet resultSet;

            sql = "SELECT barang.sku, barang.nama, barang.harga_jual, barang.harga_beli, SUM(detail_transaksi.jumlah) as jumlah FROM detail_transaksi JOIN barang ON barang.sku = detail_transaksi.sku GROUP BY barang.sku";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            System.out.println("SKU | Nama Barang | Jumlah Terjual | Keuntungan");

            while(resultSet.next()){
                jumlah = resultSet.getInt("jumlah");
                hargaBeli = resultSet.getInt("harga_beli");
                hargaJual = resultSet.getInt("harga_jual");

                System.out.print(resultSet.getString("sku"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("nama"));
                System.out.print(" | ");
                System.out.print(jumlah);
                System.out.print(" | ");
                System.out.println((hargaJual*jumlah)-(hargaBeli*jumlah));
            }
        }catch (SQLException e){
            System.out.println("Kesalahan saat pengambilan data");
        }
    }
    public void tunggu() throws NoSuchElementException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tekan enter untuk melanjutkan");
        sc.nextLine();
    }

}



