
	package tebe2020;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.InputMismatchException;
	import java.util.Scanner;

	public class Barang implements Kelola {
		static Connection conn;
		static Statement stmt;
		static ResultSet rs;
		Scanner scn = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		public void barang_setting() {
			Scanner scan = new Scanner(System.in);
			System.out.println("\n\nPengelolaan data user");
			System.out.println("1. Tambah Barang");
			System.out.println("2. Lihat Daftar Barang");
			System.out.println("3. Update Barang");
			System.out.println("4. Cari Barang");
			System.out.println("5. Hapus Barang");
			System.out.println("6. Re-Stock Barang");
			System.out.println("0. Kembali");
			System.out.print("Tentukan pilihanmu : ");
			Integer pilihan = scan.nextInt();	
			
			switch (pilihan) {
				case 0:
					Menu.user_pilih();
					break;
				case 1:
					tambahData();
					break;
				case 2:
					lihatData();
					break;
				case 3:
					editData();
					break;
				case 4:
					cariData();
					break;
				case 5:
					hapusData();
					break;
				default:
					System.out.println("Pilihan tidak tersedia");
					break;
			}
			scan.close();
		}
		@Override
		public void tambahData(){
			Connect connect = new Connect();
			try {
				System.out.println("\n\n--Tambah Barang--");
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
							System.out.println("Data Barang Suda Ada");
							tambahData();
						} else{					
							String query = "insert into barang values ('"+skub+"','"+brg+"','"+sb+"','"+hb+"','"+hj+"')";		
							try {
								stmt = conn.createStatement();
								if (stmt.executeUpdate(query) == 1) {
									System.out.println("Data berhasil diinputkan");
									barang_setting();
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
			System.out.println("\n\n--UPDATE--");
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
						System.out.println("\n Ubah Nama Barang");
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
						                    	barang_setting();
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
						System.out.println("\n Ubah Harga Beli");
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
											barang_setting();
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
						System.out.println("\n Ubah Harga Jual");
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
											barang_setting();
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
			Connect connect = new Connect();
			try {
			conn = connect.getConn();
			System.out.println("\n\n--HAPUS--");
			System.out.print("Masukkan SKU :");
			String key = scn.next();
			System.out.print("Hapus Barang ?..(y/t)  ");
			String confirm = scn.next();

			if (confirm.equals("y")) {
				String query = "DELETE FROM barang WHERE sku='"+key+"'";
				try {
					stmt = conn.createStatement();
					stmt.execute(query);
					System.out.println("Barang Berhasil di Hapus");
					barang_setting();
				} catch (SQLException e) {
					System.out.println("Barang Gagal di Hapus");
				}
			} else{
				barang_setting();
			}
					
			} catch (SQLException e1) {
					e1.printStackTrace();
			}
		}

		@Override
		public void cariData() {
			Connect connect = new Connect();
			try {
				conn = connect.getConn();
				System.out.println("\n\n--CARI--");
				System.out.print("Masukkan SKU : ");
				String key = scn.next();

				String query = "SELECT * FROM barang WHERE sku = '"+key+"'";
				try {
					stmt = conn.createStatement();				
					rs = stmt.executeQuery(query);
					while (rs.next()) {
							
						System.out.print(rs.getString("sku"));
						System.out.print("\t");
						System.out.print(rs.getString("nama"));
						System.out.print("\t");
						System.out.print(rs.getInt("stock"));
						System.out.print("\t");
						System.out.print(rs.getInt("harga_beli"));
						System.out.print("\t");
						System.out.println(rs.getInt("harga_jual"));
						barang_setting();
					}
				} catch (SQLException e) {
					System.out.println("Tidak dapat mengakses database");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}		
		}

		@Override
		public void lihatData() {
			Connect connect = new Connect();
			try {
				conn = connect.getConn();
				System.out.println("\n\n--LIHAT--");
				String query = "SELECT*FROM barang";
				try {
					stmt = conn.createStatement();				
					rs = stmt.executeQuery(query);
					while (rs.next()) {
							
						System.out.print(rs.getString("sku"));
						System.out.print("\t");
						System.out.print(rs.getString("nama"));
						System.out.print("\t");
						System.out.print(rs.getInt("stock"));
						System.out.print("\t");
						System.out.print(rs.getInt("harga_beli"));
						System.out.print("\t");
						System.out.println(rs.getInt("harga_jual"));

						
						
					}
					barang_setting();
				} catch (SQLException e) {
					System.out.println("Tidak dapat mengakses database");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}		
			
		}

	}

}
