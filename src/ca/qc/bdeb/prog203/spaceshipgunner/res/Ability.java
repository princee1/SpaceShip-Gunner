/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Animable;
import ca.qc.bdeb.prog203.interfacee.Bougeable;
import ca.qc.bdeb.prog203.interfacee.Tirable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author madzo
 */
public class Ability extends Entite implements Animable, Tirable, Bougeable {

    private ArrayList<Image> listeImg = new ArrayList();
    private int animation;
    private long tempsAbActiv;
    private SpaceShip ss;

    public Ability(SpaceShip ss) {
        super(ss.x -150, ss.y-150, "images/shield3/00.png");
        this.ss = ss;
        this.tempsAbActiv = 12000;
        chargerListe();
    }

    @Override
    public void animer() {

        if (animation == 0) {
            this.setImage(listeImg.get(0));
        } else if (animation == 10) {
            this.setImage(listeImg.get(1));
        } else if (animation == 20) {
            this.setImage(listeImg.get(2));
        } else if (animation == 30) {
            this.setImage(listeImg.get(3));
        } else if (animation == 40) {
            this.setImage(listeImg.get(4));
        } else if (animation == 50) {
            this.setImage(listeImg.get(5));
        } else if (animation == 60) {
            this.setImage(listeImg.get(6));
        } else if (animation == 70) {
            this.setImage(listeImg.get(7));
        } else if (animation == 80) {
            this.setImage(listeImg.get(8));
        } else if (animation == 90) {
            this.setImage(listeImg.get(9));
        } else if (animation == 100) {
            animation = -1; // Pour remettre à 0 avec le ++
        }
        animation++;

    }

    @Override
    public void bouger() {
        this.setLocation(ss.x -100, ss.y-90);
    }

    private void chargerListe() {

        try {
            for (int i = 0; i < 10; i++) {
                String a = Integer.toString(i + 1);
                if (i < 9) {
                    listeImg.add(new Image("images/shield3/0" + a + ".png"));
                } else {
                    listeImg.add(new Image("images/shield3/" + a + ".png"));
                }
            }
        } catch (SlickException ex) {

        }

    }

}
