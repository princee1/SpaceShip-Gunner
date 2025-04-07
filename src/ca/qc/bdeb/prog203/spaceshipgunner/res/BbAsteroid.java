/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Explosable;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author madzo
 */
public class BbAsteroid extends Asteroid implements Explosable {

    private boolean exploser = false;
    private int animation;
    private ArrayList<Image> listeAnimExplo = new ArrayList();
    private static final int MOINS_HP = 1;
    private static final String imagepath = "images/bbasteroid.png";

    public BbAsteroid() {
        super(imagepath, MOINS_HP);
        this.vitesseX = 0;
        this.vitesseY = vitesseY + 2;
        chargerListeImg();
    }

    @Override
    public void animer() {

    }

    @Override
    public void exploser() {

        if (exploser) {
            if (animation == 0) {
                this.setImage(listeAnimExplo.get(0));
                this.x = x - 170;
                this.y = y - 170;
                this.vitesseY = 0;
                this.moinsHp = 2;
            } else if (animation == 5) {
                this.setImage(listeAnimExplo.get(1));
            } else if (animation == 10) {
                this.setImage(listeAnimExplo.get(2));
            } else if (animation == 15) {
                this.setImage(listeAnimExplo.get(3));
            } else if (animation == 20) {
                this.setImage(listeAnimExplo.get(4));
            } else if (animation == 25) {
                this.setImage(listeAnimExplo.get(5));
            } else if (animation == 30) {
                this.setImage(listeAnimExplo.get(7));
            } else if (animation == 35) {
                this.setImage(listeAnimExplo.get(8));
            } else if (animation == 40) {
                this.setImage(listeAnimExplo.get(9));
            } else if (animation == 45) {
                this.setImage(listeAnimExplo.get(10));
            } else if (animation == 50) {
                this.setImage(listeAnimExplo.get(11));
            } else if (animation == 55) {
                this.setImage(listeAnimExplo.get(12));
            } else if (animation == 60) {
                this.setImage(listeAnimExplo.get(13));
            } else if (animation == 65) {
                //    this.setImage(listeAnimExplo.get(14));
            } else if (animation == 70) {
                detruire = true;
            }
            animation++;

        }
    }

    public void setExploser(boolean exploser) {
        this.exploser = exploser;
    }

    private void chargerListeImg() {

        try {
            for (int i = 0; i < 14; i++) {
                String a = Integer.toString(i + 1);
                if (i < 9) {
                    listeAnimExplo.add(new Image("images/explosion5/h_000" + a + ".png"));
                } else {
                    listeAnimExplo.add(new Image("images/explosion5/h_00" + a + ".png"));

                }
            }
        } catch (SlickException e) {
        }

    }

    public boolean getExploser() {

        return exploser;
    }

}
