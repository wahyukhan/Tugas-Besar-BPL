package tebe2020;

import java.util.Scanner;
import java.sql.*;
import java.util.Date;
import java.util.InputMismatchException;

public class User extends Login implements Kelola{
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	Scanner scn = new Scanner(System.in);
	Date date = new Date();
	Login log = new Login();
//	String usr ;
//	String pass;
	// Pilihan setting
		public void user_setting() {

			Scanner scan = new Scanner(System.in);
				
			System.out.println("\n\nPengelolaan data user");
			System.out.println("1. Edit akun");
			System.out.println("2. Hapus akun");
			System.out.println("3. Cari data");
			System.out.println("4. Lihat data");
			System.out.println("0. Kembali");
			System.out.print("Tentukan pilihanmu : ");
			Integer pilihan = scan.nextInt();	
			
			switch (pilihan) {
				case 0:
					Menu.user_pilih();
					break;
				case 1:
					editData();
					break;
			
				case 2:
					hapusData();
					break;

				case 3:
					cariData();
					break;

				case 4:
					lihatData();
					break;

				default:
					System.out.println("Pilihan tidak tersedia");
					break;
			}
			scan.close();
		}
		
	// Register data
	@Override
	public void tambahData(){
		Connect connect = new Connect();
		try {
			conn = connect.getConn();
			System.out.println("\n\n--SIGN UP--");
			System.out.print("Masukkan username : ");
			String username = scn.next();
			String str = String.format("%tF", date);
			System.out.print("Masukkan email : ");
			String email = scn.next();
			System.out.print("Masukkan password : ");
			String password = scn.next();
			       
			// Melakukan pengecekan validitas email
			if (email.contains("@")) {
				stmt = conn.createStatement();
				// Melakukan pengecekan username sudah tersedia atau belum
				String cek = "SELECT username FROM user WHERE username='"+username+"' ";
				try {
					rs = stmt.executeQuery(cek);				
					if (rs.next()) {
						System.out.println("Username yang dinputkan sudah terdaftar");
						tambahData();
					} else{					
						String query = "insert into user values ('"+username+"','"+str+"','"+email+"','"+password+"')";		
						try {
							stmt = conn.createStatement();
							if (stmt.executeUpdate(query) == 1) {
								System.out.println("Data berhasil diinputkan");
								log.login();
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
			} else{
				System.out.println("Masukkan email dengan benar");
				tambahData();
			}  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	// Mengedit email dan password akun
	@Override
	public void editData(){
		Connect connect = new Connect();
		System.out.println("\n\n--UPDATE--");
		System.out.println("1. Ubah email");
		System.out.println("2. Ubah password");
		System.out.print("Tentukan pilihanmu : ");
		try {
			Integer pilihan = scn.nextInt();
			switch (pilihan) {
				// Ubah email
				case 1:
					System.out.println("\n--Ubah Email--");
					System.out.print("Masukkan Username :");
					String name = scn.next();
					System.out.print("Masukkan Email baru :");
					String email = scn.next();
					System.out.print("Masukkan password :");
					String pwd = scn.next();

					 try{
						 conn = connect.getConn();
						 String sql = "SELECT * FROM user where username='"+name+"'";
					     stmt = conn.createStatement();
					     rs = stmt.executeQuery(sql);
					        while(rs.next()){
					            String usrname = rs.getString("username");
					            String pass = rs.getString("password");
					            if(name.equals(usrname) && pwd.equals(pass)){
					                if(email.contains("@")){	
					                	String query = "UPDATE user SET email ='"+email+"' where username='"+usrname+"'";
					                    stmt = conn.createStatement();
					                    if (stmt.executeUpdate(query) == 1) {
					                    	System.out.print("Email Telah Diganti");
											Menu.user_pilih();
										} else{
											System.out.println("Email gagal di Diganti");
											editData();
										}					                   
					                } else{
					                	System.out.println("Masukkan Email dengan Benar");
					                	}
					            } else if(name.equals("") && email.equals("") && pwd.equals("")) {
					            	System.out.println("Masukkan Data Dengan Benar");					            
					            } else{
					            	System.out.println("Username Tidak Ditemukan");	

					            	
					            }
					        }
					    }
					            catch(SQLException e)
					            {
					                e.printStackTrace();
					            }
					break;
				
				// Ubah password
				case 2:
					System.out.println("\n--Ubah password--");
					System.out.print("Masukkan Username :");
					String uname = scn.next();
					System.out.print("Masukkan password lama :");
					String passwordLama = scn.next();
					System.out.print("Masukkan password baru :");
					String newPassword = scn.next();
					System.out.print("Masukkan password baru :");
					String conPassword = scn.next();
					 try{
						 conn = connect.getConn();
						 String sql = "SELECT * FROM user where username='"+uname+"'";
					     stmt = conn.createStatement();
					     rs = stmt.executeQuery(sql);
					        while(rs.next()){
					            String usrname = rs.getString("username");
					            String passwd = rs.getString("password");
					            if(uname.equals(usrname) && passwordLama.equals(passwd)){
					                if(newPassword.equals(conPassword)){	
					                	String query = "UPDATE user SET password ='"+newPassword+"' where username='"+usrname+"'";
					                    stmt = conn.createStatement();
					                    if (stmt.executeUpdate(query) == 1) {
					                    	System.out.print("Password Telah Diganti");
											Menu.user_pilih();
										} else{
											System.out.println("Password gagal di Diganti");
											editData();
										}					                   
					                } else{
					                	System.out.println("Confirm Password Dengan Benar");
					                	}
					               	}
//					            else if(name.equals("") && passwordLama.equals("") && newPassword.equals(""))
//					            {
//					            	System.out.println("Masukkan Data Dengan Benar");					            
					             else{
					            	System.out.println("Username Tidak Ditemukan");					            
					            }
					        }
					    }
					            catch(SQLException e)
					            {
					                e.printStackTrace();
					            }
					break;
			
				default:
					System.out.println("Pilihan tidak tersedia");
					break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Pilihan tidak tersedia");
		}

	}


	// Hapus akun
	@Override
	public void hapusData(){
		Connect connect = new Connect();
		try {
		conn = connect.getConn();
		System.out.println("\n\n--HAPUS--");
		System.out.print("Apakah anda yakin untuk menghapus akun anda ?..(y/t)  ");
		String lanjut = scn.next();
		System.out.print("Konfirmasi Password :");
		String conPass = scn.next();

		if (lanjut.equals("y")) {
			String query = "DELETE FROM user WHERE password='"+conPass+"'";
			try {
				stmt = conn.createStatement();
				stmt.execute(query);
				System.out.println("Data berhasil di hapus");
				log.login();;
			} catch (SQLException e) {
				System.out.println("Data gagal di hapus");
			}
		} else{
			Menu.user_pilih();
		}
				
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
	}


	// Cari data akun atau data transaksi??
	@Override
	public void cariData(){
		Connect connect = new Connect();
		try {
			conn = connect.getConn();
			System.out.println("\n\n--CARI--");
			System.out.print("Masukkan username : ");
			String kunci = scn.next();

			String query = "SELECT*FROM user WHERE username = '"+kunci+"'";
			try {
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(query);

				while (rs.next()) {
						
					System.out.print(rs.getString("username"));
					System.out.print("\t");
					System.out.print(rs.getDate("login_terakhir"));
					System.out.print("\t");
					System.out.print(rs.getString("email"));
					System.out.print("\t");
					System.out.println(rs.getString("password"));
					user_setting();
				}
			} catch (SQLException e) {
				System.out.println("Tidak dapat mengakses database");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}


	// Lihat data akun atau data transaksi??
	@Override
	public void lihatData(){
		Connect connect = new Connect();
		try {
			conn = connect.getConn();
			System.out.println("\n\n--LIHAT--");
			String query = "SELECT*FROM user";
			try {
				stmt = conn.createStatement();				
				rs = stmt.executeQuery(query);
				while (rs.next()) {
						
					System.out.print(rs.getString("username"));
					System.out.print("\t");
					System.out.print(rs.getDate("login_terakhir"));
					System.out.print("\t");
					System.out.print(rs.getString("email"));
					System.out.print("\t");
					System.out.println(rs.getString("password"));
					
				}
				user_setting();
			} catch (SQLException e) {
				System.out.println("Tidak dapat mengakses database");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		

	}

	
}
