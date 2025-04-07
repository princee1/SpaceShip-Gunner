/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.main;

import ca.qc.bdeb.prog203.controlleur.Controller;
import ca.qc.bdeb.prog203.jeu.Jeu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1869155
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static final int HAUTEUR = 820, LARGEUR = 1050;

    public static void main(String[] args) {
        // TODO code application logic here

        try {
            
            Jeu jeu = new Jeu();
            AppGameContainer app = new AppGameContainer(jeu);
            app.setDisplayMode(LARGEUR, HAUTEUR, false);
            app.setShowFPS(true);
            app.setVSync(true);
            app.start();

            
        } catch (SlickException e) {
        }
        
        Controller cntrl= new Controller();

    }

}
