package tebe2020;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class inLogin {
	static Connection conn;
	static PreparedStatement prs;
	static Statement stmt;
	static ResultSet rs;
    Connect connect = new Connect();
    Scanner sc = new Scanner(System.in);
    public ArrayList<Login> authTemp = new ArrayList<>();

    public ArrayList login() throws SQLException, NoSuchElementException {
        Integer check;
        Integer count = 0;
        do {
            System.out.print("Username \t :");
            String username = sc.nextLine();
            System.out.print("Password \t :");
            String password = sc.nextLine();
            check = logUpdate(username, password);
            if(check == 0){
                count = count+1;
                resetPass(count, username);
            }else if (check == 2){
                System.out.println("Username Tidak Ada");
            }else if (check == 1){
                for (Login auth: authTemp
                     ) {
                    System.out.println("\n+ Selamat Datang "+ auth.getUsername()+" +");
                }
            }
        }while (check != 1);
        return authTemp;
    }

    public int logUpdate(String username, String password) throws SQLException {
        Connect connect = new Connect();
        conn = connect.getConn();
        String usernameDB = null;
        String passwordDB = null;
        String sql = "SELECT username, password FROM user WHERE username = ?";
        prs = conn.prepareStatement(sql);
        prs.setString(1, username);
        rs = prs.executeQuery();
        while (rs.next()){
            usernameDB = rs.getString("username");
            passwordDB = rs.getString("password");
        }
        int oke = 0;
        if(username.equals(usernameDB)){
           if (password.equals(passwordDB)){
                oke = 1;
                authTemp.clear();
                Login auth = new Login(usernameDB, passwordDB);
                authTemp.add(auth);
            }else{
                oke = 0;
            }
        }else{
            oke = 2;
        }
        prs.close();
        conn.close();
        return oke;
    }

    public void resetPass(int count, String username){
        if (count == 1){
            System.out.println("Password Salah");
        }else if (count == 2){
            System.out.println("Password Salah");
        }else if (count == 3){
            System.out.println("Password telah direset!!");
            try{
                Connect connect = new Connect();
                conn = connect.getConn();
                String passwordReset = randomString();
                String sql = "UPDATE user SET password = ? WHERE username = ?";
                prs = conn.prepareStatement(sql);
                prs.setString(1, passwordReset);
                prs.setString(2, username);
                prs.executeUpdate();

                conn.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String randomString(){
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
