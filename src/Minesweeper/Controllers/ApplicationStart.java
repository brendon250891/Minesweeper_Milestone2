/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper.Controllers;

import Minesweeper.UI.ApplicationView;

/**
 * Class that contains the main method that is the entry point to start up the application.
 * @author brendon
 */
public class ApplicationStart {
    
    public static void main(String[] args) {
        ApplicationView view = new ApplicationView();
        Controller controller = new Controller(view);
    }
}
