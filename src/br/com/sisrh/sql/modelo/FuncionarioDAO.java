/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sisrh.sql.modelo;

import br.com.sisrh.sql.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Imaginatio
 */
public class FuncionarioDAO {
    private Connection c = null;
    
    public FuncionarioDAO() {
        try {
            this.c = new Conexao().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addFuncionario(Funcionario func) {
        String sql = "insert into funcionarios (id, nome, email, endereco, salario)" +
            " values (?,?,?,?,?)";
        
        try {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                stmt.setInt(1, func.getId());
                stmt.setString(2, func.getNome());
                stmt.setString(3, func.getEmail());
                stmt.setString(4, func.getEndereco());
                stmt.setFloat(5, func.getSalario());
                
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Funcionario> getLista() {
        try {
            List<Funcionario> funcs = new ArrayList<>();
            try (PreparedStatement stmt = this.c.prepareStatement("select * from funcionarios"); 
                    ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Funcionario func = new Funcionario();
                    func.setId(rs.getInt("id"));
                    func.setNome(rs.getString("nome"));
                    func.setEmail(rs.getString("email"));
                    func.setEndereco(rs.getString("endereco"));
                    func.setSalario(rs.getFloat("salario"));
                    
                    funcs.add(func);
                }
            }
            return funcs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void alteraFuncionario(Funcionario func) {
        String sql = "update funcionarios set nome=?, email=?, endereco=?, salario=? where id=?";
        try {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                stmt.setString(1, func.getNome());
                stmt.setString(2, func.getEmail());
                stmt.setString(3, func.getEndereco());
                stmt.setFloat(4, func.getSalario());
                stmt.setInt(5, func.getId());
                
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } 
    
    public void remove(Funcionario func) {
        String sql = "delete from funcionarios where id=?";
        try {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                stmt.setInt(1, func.getId());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
