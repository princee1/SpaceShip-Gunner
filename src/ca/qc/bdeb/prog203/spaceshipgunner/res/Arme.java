/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Bougeable;
import ca.qc.bdeb.prog203.interfacee.Tirable;
import ca.qc.bdeb.prog203.main.Main;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author madzo
 */
public class Arme extends Entite implements Bougeable, Tirable {

    public enum Type {
        RG, MG, RKS;

    }

    private int porte = Main.HAUTEUR / 2;
    //private static String imagePath = "";
    private Type type;
    private int vitesse = 6;
    private float yDepart;
    // private static final String imagepath = "images/raygun.png";

    public Arme(float x, float y, SpriteSheet spriteSheet, Type type) {
        super(x, y, spriteSheet, 0, 0);
        this.yDepart = y;
        this.detruire = false;
        this.type = type;
        switch (this.type) {

            case RG:
                this.setImage(spriteSheet.getSubImage(1, 0));
                break;
            case MG:
                this.setImage(spriteSheet.getSubImage(0, 1));
                this.setPorte(Main.HAUTEUR / 2 - 175);
                this.vitesse = 10;
                break;
            case RKS:
                this.setImage(spriteSheet.getSubImage(4, 0));
                this.setPorte(Main.HAUTEUR / 2 + 200);
                this.vitesse = 15;
                break;

        }

    }

    @Override
    public void bouger() {
        this.setY(y - vitesse);
    }

    public void savoirDetruire() {
        if (yDepart - y >= porte) {
            detruire = true;
        }
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public Type getType() {
        return type;
    }

}
