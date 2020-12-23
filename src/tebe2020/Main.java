package tebe2020;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	public static ArrayList auth = null;
	static Date date = new Date();
    public static void main(String[] args) {
    	System.out.println("============================");
		System.out.println(date+"\n");
		System.out.println("\t L O G I N \t");
		System.out.println("============================");
        try {
            inLogin authControl = new inLogin();
            do {
                Menu menu = new Menu(authControl.login());
            }while (true);
        }catch (NoSuchElementException e){
            System.out.println("Something Wrong with Database");
        }catch (Exception ex){
            System.out.println("Exception "+ex.getMessage());
        }
    }
}
        

