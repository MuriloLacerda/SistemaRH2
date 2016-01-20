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
public final class Conexao {
    Scanner input = new Scanner(System.in);
    private final String urlpg = "jdbc:postgresql://localhost:5432/";
    private final String urlrh = "jdbc:postgresql://localhost:5432/rh";
    private final String user;
    private final String pw;
    
    
    public void cabecalho() {
        System.out.println("\tSistema de RH");
        System.out.println("Este programa foi criado utilizando postgreSQL v1.18.1");
        System.out.println("Usa a conexao padrao: jdbc:postgresql://localhost:5432/");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    
    public Conexao() throws SQLException {
        cabecalho();
        System.out.print("Digite o username: ");
        user = input.next();
        System.out.print("Digite a senha: ");
        pw = input.next();
        verificacao();
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
    
    
    public void criaDB() {
        String verifica = null;
        try {
            Statement stmt = getConnectionDB().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT datname FROM pg_database WHERE datname = 'rh';");
            while (rs.next())                 
                verifica = rs.getString("datname");
            if("rh".equalsIgnoreCase(verifica)) {
                System.out.println("Banco de dados 'RH' [OK!]");
                stmt.close();
                rs.close();
            }        
            else {
                stmt.executeQuery("CREATE DATABASE RH;");
                stmt.close();
                rs.close();
            }
        } catch (SQLException e) {
        }
    }
    
    public void criaTB() {
        String verifica = null;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select relname from pg_class where relname = 'funcionarios' and relkind='r';");
            while (rs.next()) {                
                verifica = rs.getString("relname");
            }
            
            if("funcionarios".equalsIgnoreCase(verifica)) {
                System.out.println("Tabela 'funcionarios' [OK!]");
                stmt.close();
            }  
            else {
                String sql = "CREATE TABLE funcionarios " +
                            "(ID INT PRIMARY KEY     NOT NULL," +
                            " NOME           VARCHAR    NOT NULL, " +
                            " EMAIL            VARCHAR     NOT NULL, " +
                            " ENDERECO        VARCHAR, " +
                            " SALARIO         REAL);";
                stmt.executeUpdate(sql);
                System.out.println("Tabela 'funcionarios' criada.");
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
        }
    }
}
