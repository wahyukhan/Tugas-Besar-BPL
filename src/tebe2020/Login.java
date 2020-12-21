package tebe2020;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Login {
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pst;
	Scanner sc = new Scanner(System.in);
    Date date = new Date();
    public ArrayList<inLogin> login = new ArrayList<>();

    public ArrayList<inLogin> login() throws SQLException, NoSuchElementException {
        Integer check;
        Integer count = 0;
        do {
            System.out.print("Username \t :");
            String username = sc.nextLine();
            System.out.print("Password \t :");
            String password = sc.nextLine();
            check = updateLog(username, password);
            if(check == 0){
                count = count+1;
                resetPass(count, username);
            }else if (check == 2){
                System.out.println("Username not registered");
            }else if (check == 1){
                for (inLogin log: login
                     ) {
                    System.out.println("Selamat Datang "+ log.getUsername());
                    Menu.user_pilih();
                }
            }
        }while (check != 1);
        return login;
    }

    public int updateLog(String username, String password) throws SQLException {
    	Connect connect = new Connect();
    	conn = connect.getConn();
        String usernameDB = null;
        String passwordDB = null;
		String str = String.format("%tF", date);

        String sql = "SELECT username, password FROM user WHERE username = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        rs = pst.executeQuery();
        while (rs.next()){
			String sql1 = "UPDATE user SET login_terakhir='"+str+"' WHERE username='"+username+"'";
            usernameDB = rs.getString("username");
            passwordDB = rs.getString("password");
        }
        int complete = 0;
        if(username.equals(usernameDB)){
            if (password.equals(passwordDB)){
            	complete = 1;
                login.clear();
                inLogin log = new inLogin(usernameDB, passwordDB);
                login.add(log);
            }else{
            	complete = 0;
            }
        }else{
        	complete = 2;
        }

        pst.close();
        conn.close();
        return complete;
    }

    public void resetPass(int cek, String username){
        if (cek == 1){
            System.out.println("Password Salah");
        }else if (cek == 2){
            System.out.println("Password Salah");
        }else if (cek == 3){
            System.out.println("Password telah di Reset");
            try{
            	Connect connect = new Connect();
        		conn = connect.getConn();                           
                String passReset = random();

                String sql = "UPDATE user SET password = ? WHERE username = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, passReset);
                pst.setString(2, username);
                pst.executeUpdate();

                conn.close();
            }catch (SQLException e){
            	e.printStackTrace();
            }


        }
    }

    public String random(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
