/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Animable;
import ca.qc.bdeb.prog203.interfacee.Bougeable;
import ca.qc.bdeb.prog203.interfacee.Collisionable;
import ca.qc.bdeb.prog203.main.Main;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Image;

/**
 *
 * @author madzo
 */
public abstract class Asteroid extends Entite implements Bougeable, Collisionable, Animable {

    protected int moinsHp;
    private static final int POS_Y = -(Main.HAUTEUR * 35 / 100);
    protected int vitesseX;
    protected int vitesseY;
    protected int animation;
    protected boolean dessiner;

    public Asteroid(String imagepath, int moinhp) {
        super(0, POS_Y, imagepath);
        iniatiliseVitesse();
        Random rnd = new Random();
        int choixPos = rnd.nextInt(Main.LARGEUR - ((int) this.width));
        this.x = choixPos;
        this.moinsHp = moinhp;
        
    }

    private void iniatiliseVitesse() {
        Random rnd = new Random();
        int facteur, choixVx = rnd.nextInt(10);
        int vitessY = (rnd.nextInt(6) + 1);
        int vitessX = (rnd.nextInt(2));
        if (choixVx < 5) {
            facteur = 1;
        } else {
            facteur = -1;
        }
        this.vitesseY = vitessY;
        this.vitesseX = vitessX * facteur;

    }

    public int getMinusPoint() {
        return moinsHp;
    }

    @Override
    public void bouger() {
        this.setLocation(x + vitesseX, y + vitesseY);
    }

//    public static int getPosY() {
//        return POS_Y;
//    }
    @Override
    public abstract void animer();
}
