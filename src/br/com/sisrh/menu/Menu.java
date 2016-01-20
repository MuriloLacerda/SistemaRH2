/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sisrh.menu;

import br.com.sisrh.sql.modelo.Funcionario;
import br.com.sisrh.sql.modelo.FuncionarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Imaginatio
 */
public class Menu {
    FuncionarioDAO func = new FuncionarioDAO();
    Scanner input;

    public Menu() {
        this.input = new Scanner(System.in);
    }
    
    
    
    public void menu() {
        char op, v;
        do {            
            System.out.println("1. Incluir funcionario.");
            System.out.println("2. Listar funcionarios.");
            System.out.println("3. Deletar funcionario.");
            System.out.println("4. Alterar dados de um funcionario.");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opcao: ");
            
            op = input.next(".").charAt(0);
            
            switch(op) {
                case '1':
                    func.addFuncionario(setFuncionario());
                    v = validaResp();
                    if(v == 's') break;
                    else System.exit(0);
                case '2':
                    listarFuncionarios();
                    v = validaResp();
                    if(v == 's') break;
                    else System.exit(0);
                case '3':
                    System.out.print("Insira o Id do funcionario que deseja excluir: ");
                    Funcionario fd = new Funcionario();
                    fd.setId(Integer.parseInt(input.next()));
                    func.remove(fd);
                    v = validaResp();
                    if(v == 's') break;
                    else System.exit(0);
                case '4':
                    System.out.print("Insira o Id do funcionario que deseja atualizar: ");
                    Funcionario fu = new Funcionario();
                    fu.setId(Integer.parseInt(input.next()));
                    func.alteraFuncionario(updateFuncionario(fu));
                    v = validaResp();
                    if(v == 's') break;
                    else System.exit(0);
                case '5':
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida");
                    break;
                    
            }
        } while (op != '6');
    }
    
    public Funcionario setFuncionario() {
        Funcionario funcionario = new Funcionario();
        System.out.print("Insira o ID: ");
        funcionario.setId(input.nextInt());
        System.out.print("Insira o Nome: ");
        funcionario.setNome(input.next());
        System.out.print("Insira o email: ");
        funcionario.setEmail(input.next());
        System.out.print("Insira o Endereco: ");
        funcionario.setEndereco(input.next());
        System.out.print("Insira o salario: ");
        funcionario.setSalario(input.nextFloat());
        return funcionario;
    }
    
    public Funcionario updateFuncionario(Funcionario funcionario) {
        System.out.print("Insira o Nome: ");
        funcionario.setNome(input.next());
        System.out.print("Insira o email: ");
        funcionario.setEmail(input.next());
        System.out.print("Insira o Endereco: ");
        funcionario.setEndereco(input.next());
        System.out.print("Insira o salario: ");
        funcionario.setSalario(input.nextFloat());
        return funcionario;
    }
    
    public void listarFuncionarios() {
        List<Funcionario> funcs = new ArrayList<>();
        funcs = func.getLista();
        for (Funcionario func1 : funcs) {
            System.out.println("ID: " + func1.getId());
            System.out.println("Nome: " + func1.getNome());
            System.out.println("Email: " + func1.getEmail());
            System.out.println("Endereço: " + func1.getEndereco());
            System.out.println("Salario: " + func1.getSalario());
        }
    }
    
    private char validaResp() {
        char ch;
        do {            
            System.out.println("Deseja realizar outra operacao? [S ou N] ? ");
            ch = input.next(".").charAt(0);
        } while ((ch != 's' && ch != 'S') && (ch != 'n' && ch != 'N'));
        return ch;
    }
}
