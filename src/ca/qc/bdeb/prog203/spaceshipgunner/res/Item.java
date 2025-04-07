/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Bougeable;
import ca.qc.bdeb.prog203.main.Main;
import java.util.Random;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author madzo
 */
public class Item extends Entite implements Bougeable {
    
    public enum Pouvoir {
        
        HP, MUNITION, MULITPICATEUR, INVICBLE;
    }
    
    private static final float POS_Y = -(Main.HAUTEUR * 25) / 100;
    private Pouvoir pouvoir;
    private int vitesse = 5;
    
    public Item(SpriteSheet spriteSheet) {
        super(0, 0, spriteSheet, 0, 0);
        Random rnd = new Random();
        this.x = rnd.nextInt((int) Main.LARGEUR);
        this.y = POS_Y;
        savoirPouvoir(rnd, spriteSheet);
        
    }
    
    private void savoirPouvoir(Random rnd, SpriteSheet spriteSheet) {
        int choixPouvoir = rnd.nextInt(4);
        switch (choixPouvoir) {
            case 0:
                this.pouvoir = Pouvoir.HP;
                this.setImage(spriteSheet.getSubImage(6, 3));
                break;
            case 1:
                this.pouvoir = Pouvoir.MUNITION;
                this.setImage(spriteSheet.getSubImage(5, 3));
                break;
            case 2:
                this.pouvoir = Pouvoir.MULITPICATEUR;
                this.setImage(spriteSheet.getSubImage(4, 3));
                break;
            case 3:
                this.pouvoir = Pouvoir.INVICBLE;
                this.setImage(spriteSheet.getSubImage(3, 3));
        }
    }
    
    public void itemUp(SpaceShip ss) {
        switch (this.pouvoir) {
            case HP:
                ss.ravoirVie();
                break;
            case MUNITION:
                ss.rechargerMunition();
                break;
            case MULITPICATEUR:
                ss.setMulti(true);
                ss.setLastMulti(System.currentTimeMillis());
                break;
            case INVICBLE:
                ss.setShield(true);
                break;
        }
        
    }
    
    @Override
    public void bouger() {
        this.setY(y + vitesse);
    }
    
}
