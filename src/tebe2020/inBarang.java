package tebe2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class inBarang extends Kelola{
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	Connect connect = new Connect();
	Scanner scn = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public inBarang() throws SQLException{
        conn = connect.getConn();
        conn.close();
    }

    @Override
    public void tambahData() {
    	Connect connect = new Connect();
		try {
			System.out.println("\n\nTambah Barang ======");
			System.out.print("Nama Barang : ");
			String brg = scn.nextLine();
			System.out.print("SKU Barang : ");
			String skub = scn.next();
			System.out.print("Stock Barang : ");
			int sb = scn.nextInt();
			System.out.print("Harga Beli : ");
			int hb = scn.nextInt();
			System.out.print("Harga Jual : ");
			int hj = scn.nextInt();			       			
				// Melakukan pengecekan username sudah tersedia atau belum
				conn = connect.getConn();	
				String cek = "SELECT sku FROM barang WHERE sku='"+skub+"' ";
				stmt = conn.createStatement();
				try {
					rs = stmt.executeQuery(cek);				
					if (rs.next()) {
						System.out.println("Data Barang Sudah Ada");
						tambahData();
					} else{					
						String query = "insert into barang values ('"+skub+"','"+brg+"','"+sb+"','"+hb+"','"+hj+"')";		
						try {
							stmt = conn.createStatement();
							if (stmt.executeUpdate(query) == 1) {
								System.out.println("Data berhasil diinputkan");
							} else{
								System.out.println("Data gagal diinputkan");
							}					
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					System.out.println("Terjadi kesalahan");
				}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

    @Override
    public void editData() {
    	Connect connect = new Connect();
		System.out.println("\n\nUpdate Barang ========");
		System.out.println("1. Ubah Nama Barang");
		System.out.println("2. Ubah Harga Beli");
		System.out.println("3. Ubah Harga Jual");
		System.out.print("Tentukan pilihanmu : ");
		try {
			Integer pilihan = scn.nextInt();
			switch (pilihan) {
				// Ubah email
				case 1:									
				try {
					System.out.println("\n Ubah Nama Barang ======");
					System.out.print("Nama Barang Lama :");
					String name = br.readLine();
					System.out.print("Nama Barang baru :");
					String newname = br.readLine();
					System.out.print("Masukkan SKU :");
					String sku = scn.next();
					try{
						 conn = connect.getConn();
						 String sql = "SELECT * FROM barang where sku='"+sku+"'";
					     stmt = conn.createStatement();
					     rs = stmt.executeQuery(sql);
					        while(rs.next()){
					            String dname = rs.getString("nama");
					            String dsku = rs.getString("sku");
					            if(name.equals(dname) && sku.equals(dsku)){	
					                	String query = "UPDATE barang SET nama ='"+newname+"' where sku='"+dsku+"'";
					                    stmt = conn.createStatement();
					                    if (stmt.executeUpdate(query) == 1) {
					                    	System.out.print("Nama Barang Telah di Ubah");
										} else{
											System.out.println("Nama Barang Gagal di Ubah");
											editData();
										}					                   
					            } else if(name.equals("") && newname.equals("") && sku.equals("")) {
					            	System.out.println("Masukkan Data Dengan Benar");					            
					            } else{
					            	System.out.println("Barang Tidak Ditemukan");					
					            }
					        }
					    }
					            catch(SQLException e)
					            {
					                e.printStackTrace();
					            }
				} catch (IOException e1) {
					e1.printStackTrace();
				}										 
				break;
				
				// Ubah password
				case 2:
					System.out.println("\nUbah Harga Beli ======");
					System.out.print("Masukkan SKU :");
					String sku = scn.next();
					System.out.print("Update Harga Beli :");
					int newharga = scn.nextInt();					
					 try{
						 conn = connect.getConn();
						 if (newharga != 0) {								
								String query = "UPDATE barang SET harga_beli='"+newharga+"' WHERE sku='"+sku+"'";
				
								try {
									stmt = conn.createStatement();
									
									if (stmt.executeUpdate(query) == 1) {
										System.out.println("Harga Beli Telah di Update");
									} else{
										System.out.println("Harga Beli gagal di update");
										editData();
									}
								} catch (SQLException e) {
									System.out.println("Terjadi kesalahan");
								}
			
							} else{
								System.out.println("Masukkan Nominal");
								editData();
							}
					        
					    }catch(SQLException e){
					        e.printStackTrace();
					    }break;
				case 3:
					System.out.println("\n Ubah Harga Jual ======");
					System.out.print("Masukkan SKU :");
					String sku1 = scn.next();
					System.out.print("Update Harga Jual :");
					int newharga1 = scn.nextInt();					
					 try{
						 conn = connect.getConn();
						 if (newharga1 != 0) {								
								String query = "UPDATE barang SET harga_beli='"+newharga1+"' WHERE sku='"+sku1+"'";
				
								try {
									stmt = conn.createStatement();
									
									if (stmt.executeUpdate(query) == 1) {
										System.out.println("Harga Jual Telah di Update");
									} else{
										System.out.println("Harga Jual gagal di update");
										editData();
									}
									
								} catch (SQLException e) {
									System.out.println("Terjadi kesalahan");
								}
			
							} else{
								System.out.println("Masukkan Nominal");
								editData();
							}
					        
					    }catch(SQLException e){
					        e.printStackTrace();
					    }break;
				default:
					System.out.println("Pilihan tidak tersedia");
					break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Pilihan tidak tersedia");
		}
    }

    @Override
    public void hapusData() {
        sc = new Scanner(System.in);
        tampilkanData();

        System.out.println("\n\nDelete Barang ======");
        System.out.print("SKU barang yang akan di hapus : ");
        delete(sc.nextLine());
    }

    @Override
    public void tampilkanData() {
        sc = new Scanner(System.in);
		System.out.println("\n\nData Barang ========");
        LinkedList<Barang> listBarang = show();

        System.out.println("sku | nama | stok | Harga beli | Harga Jual");

        for (Barang barang:
             listBarang) {
            System.out.print(barang.getSku());
            System.out.print("|\t");

            System.out.print(barang.getNama());
            System.out.print("|\t");

            System.out.print(barang.getStok());
            System.out.print("|\t|");

            System.out.print(barang.getHarga_beli());
            System.out.print("|\t");

            System.out.print(barang.getHarga_jual());
            System.out.print("\n");

        }
    }
    
    public LinkedList<Barang> search(String key) throws SQLException {
        conn = connect.getConn();

        String sql = "SELECT * FROM barang WHERE nama LIKE ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%"+key+"%");

        ResultSet resultSet = statement.executeQuery();
        LinkedList<Barang> listBarang = new LinkedList<Barang>();
        
        while (resultSet.next()){
            Barang barang = new Barang(
                    resultSet.getString("sku"),
                    resultSet.getString("nama"),
                    resultSet.getInt("stock"),
                    resultSet.getInt("harga_beli"),
                    resultSet.getInt("harga_jual")
            );

            listBarang.add(barang);
        }
        conn.close();
        return listBarang;
    }
    @Override
    public void cariData() {
		System.out.println("\n\nCari Barang ========");
    	try {	
    		sc = new Scanner(System.in);
    		System.out.print("Masukkan Nama Barang : ");
    		LinkedList<Barang> listBarang = search(sc.nextLine());
    		
    		for(Barang barang : listBarang) {
    			System.out.println("SKU : "+barang.getSku());
    			System.out.println("NAMA : "+barang.getNama());
    			System.out.println("Stock : "+barang.getStok());
    			System.out.println("Harga Beli : "+barang.getHarga_beli());
    			System.out.println("Harga Jual : "+barang.getHarga_jual());
    		}
    		
    	}catch (SQLException | NoSuchElementException e) {
    		System.out.println(e.getMessage());
    	}
 
    }

    @Override
    public LinkedList show() {
          LinkedList<Barang> listBarang = new LinkedList<Barang>();

          try{
        	  conn = connect.getConn();
              Statement statement = conn.createStatement();
              String sql = "SELECT * FROM barang";

              ResultSet resultSet = statement.executeQuery(sql);

              while (resultSet.next()){
                  Barang barang = new Barang(
                          resultSet.getString("sku"),
                          resultSet.getString("nama"),
                          resultSet.getInt("stock"),
                          resultSet.getInt("harga_beli"),
                          resultSet.getInt("harga_jual")
                  );

                  listBarang.add(barang);
              }
          }catch (SQLException e){
              System.out.println(e.getMessage());
          }
        return listBarang;
    }

    public int add(Barang barang) {
        int result = 0;

        try {
        	conn = connect.getConn();
            String sql = "INSERT INTO barang (sku, nama, stock, harga_beli, harga_jual) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, barang.getSku());
            statement.setString(2, barang.getNama());
            statement.setInt(3, barang.getStok());
            statement.setInt(4, barang.getHarga_beli());
            statement.setInt(5, barang.getHarga_jual());

            result = statement.executeUpdate();
            System.out.println("Input Data Berhasil");
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

//    public int edit(String id, Barang barang) {
//        int result = 0;
//
//        try {
//            connection = connect.getConn();
//            PreparedStatement statement;
//
//            String sql = "UPDATE barang SET sku = ?, nama = ?, stock = ?, harga_jual = ?, harga_beli = ? WHERE sku = ?";
//            statement = connection.prepareStatement(sql);
//
//            statement.setString(1 ,barang.getSku());
//            statement.setString(2, barang.getNama());
//            statement.setInt(3, barang.getStok());
//            statement.setInt(4, barang.getHarga_jual());
//            statement.setInt(5, barang.getHarga_beli());
//            statement.setString(6 , id);
//
//            result = statement.executeUpdate();
//            System.out.println("Barang telah berhasil di update");
//            connection.close();
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//
//        return result;
//    }

    public int delete(String id) {
        int result = 0;

        try {
        	conn = connect.getConn();
            String sql = "DELETE FROM barang WHERE sku = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);

            result = statement.executeUpdate();
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return result;
    }


    public Barang showOne(String id) {

        try{
        	conn = connect.getConn();

            String sql = "SELECT * FROM barang WHERE sku = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            Barang barang = null;
            if (resultSet.next()){
                barang = new Barang(
                        resultSet.getString("sku"),
                        resultSet.getString("nama"),
                        resultSet.getInt("stock"),
                        resultSet.getInt("harga_beli"),
                        resultSet.getInt("harga_jual")
                );
            }
            return barang;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void reStockMenu(){
		System.out.println("\n\nRe-Stock Barang ========");
        Scanner scanner = new Scanner(System.in);
        try {
        	tampilkanData();
            System.out.print("Masukkan SKU : ");
            Barang barang = showOne(scanner.nextLine());

            System.out.println("Nama Barang   : "+barang.getNama());
            System.out.println("Stok saat ini : "+barang.getStok());

            System.out.print("Jumlah Restok : ");
            reStock(scanner.nextInt()+barang.getStok(), barang.getSku());

            System.out.println(barang.getNama()+" telah dilakukan restock");
        }catch (NullPointerException | NoSuchElementException e){
            System.out.println("Kesalahan Input");
        }catch (SQLException e){
            System.out.println("Kesalahan pada input data ke database barang");
        }
    }

    public int reStock(int sum, String sku)throws SQLException{
        int result = 0;
        Connection connection = connect.getConn();
        String sql = "UPDATE barang SET stock = ? WHERE sku = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, sum);
        statement.setString(2, sku);
        result = statement.executeUpdate();
        return result;
    }
}
