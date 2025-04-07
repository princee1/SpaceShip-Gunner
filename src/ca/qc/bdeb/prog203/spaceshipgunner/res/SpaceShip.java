/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Explosable;
import ca.qc.bdeb.prog203.main.Main;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Arme.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.input.KeyCode;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author madzo
 */
public class SpaceShip extends Entite implements Explosable {

    public enum Direction {
        UP,
        LEFT,
        RIGHT,
        DOWN;
    }

    private final int MAX_CAPACITTY = (int) (width * width);
    private int capacity = 0;
    private int animation = 0;

    private int score;
    private boolean plein = false;
    private static final int INITI_HP = 5, INITI_MG = 350, INITI_RK = 15;

    // private boolean moving = false;
    private boolean protection = true;
    private long tempsProtect = 3500;
    private long tempsMulti = 6000;

    private long vitesseMG = 50;

    private long lastTouch;
    private long lastMultiTime;

    private boolean shield;

    private int cargaison;
    private final float vitesse = 19;
    private int munitionMG = INITI_MG;
    private int munitionRocket = INITI_RK;
    private int hp = INITI_HP;

    private boolean multiplicateur;

    private Image img;
    private Image img1;

    private static final String imagePath = "images/destroyer.png";
    private ArrayList<Image> listeAnimExplo = new ArrayList();
    private static SpriteSheet spriteSheet;

    public SpaceShip() {
        super(Main.LARGEUR / 2 - 67, Main.HAUTEUR / 2 + 120, imagePath);

        try {
            spriteSheet = new SpriteSheet("images/bullets.png", 44, 45);
            img = new Image("images/minidestlife.png");
         //   img1 = new Image("images/shield.png");
            chargerListeEXplo();

        } catch (SlickException ex) {

        }
    }

    /*
    
     */
    public int getHp() {
        return hp;
    }

    public void broyer(int cap) {

        if ((capacity + cap) < MAX_CAPACITTY) {
            //     cmpt++;
            //   if (cmpt == 6) {
            System.out.println(MAX_CAPACITTY);
            this.capacity = capacity + cap;
            //     cmpt = 0;
            //}
        } else {
            this.plein = true;
        }
    }

    public void envoyer() {

        if (multiplicateur) {
            capacity = 2 * capacity;
        }

        this.cargaison = cargaison + capacity;
        this.capacity = 0;
        this.plein = false;

    }

    public void tirerRayGun(ArrayList<Entite> listeEnt) {

        listeEnt.add(new Arme(x + 55, y - 15, spriteSheet, Type.RG));
    }

    public void tirerRocket(ArrayList<Entite> listeEnt) {

        if (munitionRocket > 0) {
            munitionRocket--;
            listeEnt.add(new Arme(x + 55, y - 15, spriteSheet, Type.RKS));
        }
    }

    public void tirerMiniGun(ArrayList<Entite> listeEnt) {

        if (munitionMG > 0) {
            munitionMG--;
            listeEnt.add(new Arme(x + 55, y - 15, spriteSheet, Type.MG));
        }

    }

    //
    //
    public int getScore() {
        return score;
    }

    public int getCargaison() {
        return cargaison;
    }

    public boolean isPlein() {
        return plein;
    }

    public void calculScore(int score) {
        if (multiplicateur) {
            score = 2 * score;

        }
        this.score = this.score + score;
    }

    public boolean isProtect() {
        return protection;

    }

    public void setProtec(Boolean protect) {
        this.protection = protect;

    }

    public String getCapPrcent() {
        System.out.println(capacity + " - " + MAX_CAPACITTY);
        double a = (float) capacity / MAX_CAPACITTY * 100;
        String b;
        if (plein) {
            b = "100 ";
        } else {
            if (a == 0) {
                b = "0.0 ";
            } else {
                b = Double.toString(a);
            }
        }
        String e = "";
        for (int i = 0; i < 4; i++) {

            e = e + b.charAt(i);
        }
        return e;
    }

    public void gererVie(boolean protection, int mHp) {
        this.hp = this.hp - mHp;
        this.protection = protection;
    }

    public void setLastTouch(long lt) {
        this.lastTouch = lt;
    }

    public void setHp(int plusHp) {

        this.hp = plusHp;
    }

    public void rechargerMunition() {

        this.munitionMG = INITI_MG;
        this.munitionRocket = INITI_RK;

    }

    public long getLastTouch() {
        return lastTouch;
    }

    public long getVitesseMg() {
        return vitesseMG;
    }

    public long getTempsProtec() {
        return tempsProtect;
    }

    public int getMunitionRocket() {
        return munitionRocket;
    }

    public int getMunitionMG() {
        return munitionMG;
    }

    public void ravoirVie() {
        this.hp = INITI_HP;
    }

    public void setMulti(boolean multi) {
        this.multiplicateur = multi;

    }

    public boolean isMulti() {
        return multiplicateur;
    }

    public long getLastMulti() {
        return lastMultiTime;
    }

    public long getTempMult() {
        return tempsMulti;
    }

    public void setLastMulti(long lstMulT) {

        this.lastMultiTime = lstMulT;
    }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {

        this.shield = shield;
    }

    //
    //
    @Override
    public void exploser() {

        if (animation == 0) {
            this.setImage(listeAnimExplo.get(0));
            this.x = x - 50;
            this.y = y - 50;

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
            this.setImage(listeAnimExplo.get(6));
        } else if (animation == 35) {
            detruire = true;

        }
        animation++;

    }

    @Override
    public void setX(float f) {
        if (f == -1) {
            if (x > 0) {
                super.setX(x + f * vitesse);
            }
        } else if (f == 1) {
            if (x < Main.LARGEUR - width) {
                super.setX(x + f * vitesse);

            }
        }
    }

    @Override
    public void setY(float f) {
        if (f == -1) {
            if (y > 0) {
                super.setY(y + f * vitesse);
            }
        } else if (f == 1) {
            if (y < Main.HAUTEUR - height) {
                super.setY(y + f * vitesse);

            }
        }

    }

    public Image getImageLife() {

        return img;
    }

    public Image getImageProtec() {

        return img1;
    }

    public void calculerTemp() {
        if (System.currentTimeMillis() - lastMultiTime >= tempsMulti) {
            if (multiplicateur) {
                this.multiplicateur = false;
                this.lastMultiTime = System.currentTimeMillis();
            }
        }

    }

    public void modifierProtec() {
        if (System.currentTimeMillis() - lastTouch > tempsProtect) {
            if (protection) {
                this.protection = false;
            }
        }
    }

    private void chargerListeEXplo() {

        for (int i = 0; i < 7; i++) {
            try {
                String a = Integer.toString(i);
                listeAnimExplo.add(new Image("images/explosion/000" + a + ".png"));
            } catch (SlickException ex) {
            }

        }
    }

}

//    public void setDirection(Direction direction) {
//        this.direction = direction;
//    }
//    public Direction getDirection() {
//        return direction;
//    }
//  public void bouger(ArrayList<KeyCode> listeKeys) {
//    if (listeKeys.contains(KeyCode.A)) {
//      x = x - vitesse;
// }
//if (listeKeys.contains(KeyCode.S)) {
//  y = y + vitesse;
//}
//if (listeKeys.contains(KeyCode.D)) {
//  x = x + vitesse;
//}
//if (listeKeys.contains(KeyCode.W)) {
//  y = y - vitesse;
//}
    //   }
