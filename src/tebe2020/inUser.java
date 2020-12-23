package tebe2020;

import java.sql.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;						

public class inUser extends Kelola{
    static Scanner sc = new Scanner(System.in);
	static Connection conn;
	static PreparedStatement prs;
	static Statement stmt;
	static ResultSet rs;
    Connect connect = new Connect();
    Date date = new Date();
    public inUser() throws SQLException {
        conn = connect.getConn();
        conn.close();
        menu("User");
    }
    @Override
    public void tambahData() {
    	Connect connect = new Connect();
		try {
			conn = connect.getConn();
			System.out.println("Daftar ======");
			System.out.print("Masukkan username : ");
			String username = sc.next();
			String str = String.format("%tF", date);
			System.out.print("Masukkan email : ");
			String email = sc.next();
			System.out.print("Masukkan password : ");
			String password = sc.next();
			       
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

    @Override
    public void editData() {
    	Connect connect = new Connect();
		System.out.println("\n\nEdit Akun ====== ");
		System.out.println("1. Email");
		System.out.println("2. Password");
		System.out.print("Pilihan : ");
		try {
			Integer pilihan = sc.nextInt();
			switch (pilihan) {
				// Ubah email
				case 1:
					System.out.println("\nEmail ======");
					System.out.print("Masukkan Username :");
					String name = sc.next();
					System.out.print("Masukkan Email Baru :");
					String email = sc.next();
					System.out.print("Masukkan Password :");
					String pwd = sc.next();
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
					System.out.println("\nPassword ======");
					System.out.print("Masukkan Username :");
					String uname = sc.next();
					System.out.print("Masukkan password lama :");
					String passwordLama = sc.next();
					System.out.print("Masukkan password baru :");
					String newPassword = sc.next();
					System.out.print("Masukkan password baru :");
					String conPassword = sc.next();
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
										} else{
											System.out.println("Password gagal di Diganti");
											editData();
										}					                   
					                } else{
					                	System.out.println("Confirm Password Dengan Benar");
					                	}
					               	}
					            else if(uname.equals("") && passwordLama.equals("") && newPassword.equals(""))
					            {
					            	System.out.println("Masukkan Data Dengan Benar");					            
					            }else{
					            	System.out.println("Username Tidak Ditemukan");					            
					            }
					        }
					    }catch(SQLException e){
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

    @Override
    public void hapusData() {
        try{
        	tampilkanData();
            System.out.println("\n\nDelete ====== ");
            System.out.print("Masukkan Username : ");
            delete(sc.next());
            System.out.print("Username berhasil Dihapus ");
        }catch (SQLException e){
            System.out.println("Kesalahan Input Data");
        }
    }

    @Override
    public void tampilkanData() {
        System.out.println("\n\nData User ======");
        try {
            LinkedList<User> listUser = show();
            System.out.print(" Username "+" Password "+" Email "+" Last Login \n");
            for (User user: listUser
            ) {
                System.out.print(user.getUsername());
                System.out.print("\t|\t");
                System.out.print(user.getPassword());
                System.out.print("\t|\t");
                System.out.print(user.getEmail());
                System.out.print("\t|\t");
                System.out.print(user.getLast_login());
                System.out.print("\n");
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cariData() {
        try {
            System.out.println("\n\nCari Data User ======");
        	sc = new Scanner(System.in);
            System.out.println("Masukkan username : ");
            User user = showOne(sc.nextLine());
            System.out.println("Username : "+user.getUsername());
            System.out.println("Password : "+user.getPassword());
            System.out.println("Email \t : "+user.getEmail());
            System.out.println("Last Login : "+user.getLast_login());
            
        }catch (NoSuchElementException e){
            System.out.println("Kesalahan dalam mencari data");
        }

    }
    @Override
    public LinkedList<User> show() {
        LinkedList<User> listUser = new LinkedList<User>();
        try{
        	conn = connect.getConn();
            stmt = conn.createStatement();
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                User user = new User(
                		rs.getString("username"),
                		rs.getString("password"),
                		rs.getString("login_terakhir"),
                		rs.getString("email")
                );
                listUser.add(user);
            }
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listUser;
    }
    public User search(String key) throws SQLException {
    	conn = connect.getConn();
        String sql = "select * from user where username LIKE ?";
        prs = conn.prepareStatement(sql);
        prs.setString(1, "%"+key+"%");
        rs = prs.executeQuery();
        User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("login_terakhir"), rs.getString("email"));
        conn.close();
        return user;
    }
    
    public int delete(String id) throws SQLException {
        int result = 0;
        conn = connect.getConn();
        String sql = "delete from user WHERE username = ?";
        prs = conn.prepareStatement(sql);
        prs.setString(1, id);
        result = prs.executeUpdate();
        return result;
    }

    public User showOne(String id){
        try{
            User user = null;
            conn = connect.getConn();
            String sql = "select * from user where username = ?";
            prs = conn.prepareStatement(sql);
            prs.setString(1, id);
            rs = prs.executeQuery();

            while (rs.next()){
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("login_terakhir"), rs.getString("email"));
            }
            return user;
        }catch (SQLException e){
            System.out.println("Kesalahan mengambil data");
            return null;
        }
    }
}