/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sisrh.main;

import br.com.sisrh.menu.Menu;

/**
 * 
 * @author Imaginatio
 */
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.menu();
        } catch (Exception e) {
        }
    }
}
