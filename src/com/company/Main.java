package com.company;

import java.sql.*;
import java.util.Scanner;
public class Main {
    static String username = "root";
    static String password = "123456789";
    static String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        vvod();
        vivodtabl();
    }
    public static void vvod(){
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            Statement add = conn.createStatement();
            System.out.println("Введите автора книги");
            String a = scan.next();
            System.out.println("Введите название книги");
            String b = scan.next();
            System.out.println("Введите количество страниц книги");
            int c = scan.nextInt();
            System.out.println("Введите язык книги");
            String d = scan.next();
            int rows = add.executeUpdate("INSERT tabl(avtor, name, obyem, language) VALUES ('"+a + "'," +"'"+b+ "'," +"'" +c+ "',"+"'"+d+"')");
            System.out.println("Запись добавлена");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
    }
    public static void vivodtabl(){
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tabl");
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String avtor = resultSet.getString(2);
                String name = resultSet.getString(3);
                int obyem = resultSet.getInt(4);
                String language = resultSet.getString(5);
                System.out.println(id + " " + avtor+ " " + name+ " " + obyem+ " " +language +"\n");
            }
        }
        catch (SQLException ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public static void vivodpolanguage(){
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            Statement statement = conn.createStatement();
            System.out.println("Введите язык книги");
            String z = scan.next();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tabl WHERE language='" +z+"'");
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String avtor = resultSet.getString(2);
                String name = resultSet.getString(3);
                int obyem = resultSet.getInt(4);
                String language = resultSet.getString(5);
                System.out.println(id + " " + avtor+ " " + name+ " " + obyem+ " " +language +"\n");
            }
        }
        catch (SQLException ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public static void udalenie(){
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            Statement statement = conn.createStatement();
            System.out.println("Введите id строчки для удаления");
            int b = scan.nextInt();
            int rows = statement.executeUpdate("DELETE FROM tabl WHERE Id ="+b);
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
    }
}
