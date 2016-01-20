/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sisrh.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * @author Imaginatio
 */
public class Conexao {
    Scanner input = new Scanner(System.in);
    private final String urlpg = "jdbc:postgresql://localhost:5432/";
    private final String urlrh = "jdbc:postgresql://localhost:5432/RH";
    private final String user;
    private final String pw;
    
    

    public Conexao() {
        System.out.print("Digite o username: ");
        user = input.next();
        System.out.print("Digite a senha: ");
        pw = input.next();
    }
    
    public void criaTB() throws SQLException {
        try (Statement stmt = getConnectionDB().createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (id int, nome varchar, email varchar, endereco varchar, salario real)");
            System.out.println("Tabela criada com sucesso!");
        }
        catch (SQLException e) {
            System.out.println("Tabela [OK!]");
        }
    }
    
    public void criaDB() throws SQLException {
        String ver = null;
        try(Statement stmt = getConnectionDB().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT datname FROM pg_database WHERE datname = 'RH';");         
            while(rs.next()) {
                ver = rs.getString("datname");
            }
            if(!"rh".equalsIgnoreCase(ver)) {
                stmt.executeQuery("CREATE DATABASE RH;");
            }
            else
                System.out.println("Banco de dados [OK!]");
        }
    }
    
    public Connection getConnectionDB() {
        try {
            return DriverManager.getConnection(urlpg, user, pw);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void verificacao() throws SQLException {
        criaDB();
        criaTB();
    }
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(urlrh, user, pw);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
