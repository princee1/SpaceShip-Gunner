/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.main.Main;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author madzo
 */
public class RegAsteroid extends Asteroid {

    public enum Stade {
        GRAND, PETIT, RAMASSABLE
    }

    private static final int MOINS_HP = 1;
    private Stade stade = Stade.GRAND;
    private static String[] tabImagePath = {"images/rockGrand/rock1.png", "images/rockPetit/rocka1.png", "images/rockRams/rocks1.png"};
    private static final int IMAGE_PETIT = 1, IMAGE_RAMS = 2;
    private static final int APPRITON = 125;
    private static final int MASSE = 64 * 64;

    private ArrayList<Image> listeImg = new ArrayList();

    public RegAsteroid() {
        super(tabImagePath[0], MOINS_HP);
        this.stade = Stade.GRAND;
        chargerListeImage();
    }

    public RegAsteroid(float x, float y, float facteur, int taille, Stade stade) {
        super(tabImagePath[taille], MOINS_HP);
        Random rnd = new Random();
        this.stade = stade;
        this.x = x + (facteur * APPRITON);
        this.y = y;
        this.vitesseX = (rnd.nextInt(2) + 1) * (int) facteur;
        chargerListeImage();

    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public static int getImagePetit() {
        return IMAGE_PETIT;
    }

    public static int getImageRams() {
        return IMAGE_RAMS;

    }

    public static int getMasse() {
        return MASSE;

    }

    private void chargerListeImage() {

        try {
            switch (this.stade) {
                case GRAND:
                    for (int i = 0; i < 8; i++) {
                        String a = Integer.toString(i + 1);
                        listeImg.add(new Image("images/rockGrand/rock" + a + ".png"));
                    }
                    break;
                case PETIT:
                    for (int i = 0; i < 8; i++) {
                        String a = Integer.toString(i + 1);
                        listeImg.add(new Image("images/rockPetit/rocka" + a + ".png"));
                    }
                    break;
                case RAMASSABLE:
                    for (int i = 0; i < 8; i++) {
                        String a = Integer.toString(i + 1);
                        listeImg.add(new Image("images/rockRams/rocks" + a + ".png"));
                    }
                    break;

            }

        } catch (SlickException ex) {

        }
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
            animation = -1; // Pour remettre à 0 avec le ++
        }
        animation++;

    }
}
