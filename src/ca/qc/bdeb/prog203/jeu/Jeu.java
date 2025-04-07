/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.jeu;

import ca.qc.bdeb.prog203.controlleur.Controller;
import ca.qc.bdeb.prog203.interfacee.Animable;
import ca.qc.bdeb.prog203.interfacee.Bougeable;
import ca.qc.bdeb.prog203.interfacee.Collisionable;
import ca.qc.bdeb.prog203.interfacee.Explosable;
import ca.qc.bdeb.prog203.interfacee.Tirable;
import ca.qc.bdeb.prog203.main.Main;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Ability;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Asteroid;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Entite;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Arme;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Arme.Type;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Backround;
import ca.qc.bdeb.prog203.spaceshipgunner.res.BbAsteroid;
import ca.qc.bdeb.prog203.spaceshipgunner.res.Item;
import ca.qc.bdeb.prog203.spaceshipgunner.res.RegAsteroid;
import ca.qc.bdeb.prog203.spaceshipgunner.res.RegAsteroid.Stade;
import ca.qc.bdeb.prog203.spaceshipgunner.res.SpaceShip;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
//import javafx.scene.input.KeyCode;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author madzo
 */
public class Jeu extends BasicGame implements Observer {

    private GameContainer container;
    private static final String gameTitle = "S P A C E S H I P   G U N N E R ";
    private SpaceShip ssTemp;
    private SpaceShip ssTemp1;

    private static ArrayList<String> listeBckg = new ArrayList();
    private ArrayList<Entite> listeEntitePrinc = new ArrayList();
    private ArrayList<Entite> listeEntiteTemp = new ArrayList();
    private ArrayList<Backround> listeEntiteBkg = new ArrayList();

    //  private ArrayList<Input> listeKeys = new ArrayList();
    private Input input;
    // private Image imageBckd;
    private Image imageGO;

    private long startTime;
    private long lastRegAstTime;
    private long lastBbAstTime;
    private long lastShieldTime;

    private long lastMGTime1;

//    private long lastMGTime2;
    private long lastItemTime1;

    private boolean partieEnCours;
    private boolean shield = false;
    private long ajoutRegAst = 2500;
    private long ajoutBbAst = 4500;

    private long ajoutItem = 25000;

    private SpriteSheet spriteSheet;
    private SpriteSheet spriteSheet1;

    private Sound snd;

    private static final int SCORE_G = 100, SCORE_P = 25, SCORE_B = 500;
    private int anim = 0;

    Backround bkg;

    //private Music musicBackround;
    public Jeu( //  Controller controller, Modele modele
            ) {
        super(gameTitle);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.container = gc;
        this.startTime = gc.getTime();
        this.input = gc.getInput();

        intialiserBckg();
        ssTemp = new SpaceShip();
        bkg = new Backround();
//         ssTemp1 = new SpaceShip("images/ship.png");
        // listeEntitePrinc.add(new RegAsteroid());
        // listeEntiteBkg.add(new Backround(-1600 + Main.HAUTEUR));

        lastRegAstTime = gc.getTime();
        lastBbAstTime = gc.getTime();

        lastMGTime1 = gc.getTime();
        //ad         lastMGTime2 = gc.getTime();

        lastItemTime1 = gc.getTime();

//        imageBckd = new Image("images/Background-3.jpg");
        imageGO = new Image("images/gameover.jpg");
        partieEnCours = true;

        spriteSheet = new SpriteSheet("images/spaceship.png", 114, 108);
        // spriteSheet1 = new SpriteSheet("images/asteroid.png", , 52);

        //  musicBackround = new Music("images/ObservingTheStar.ogg");
        // musicBackround.loop();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        long time = gc.getTime();
        getKeys();
        traiterKeys();
        // ast.bouger();
        bkg.bouger();

//        for (int i = 0; i < listeEntiteBkg.size(); i++) {
//
//            listeEntiteBkg.get(i).bouger();
//        }
        // savoirBkg();
        //----------------------------------------- 
        calculerTempsAjoutAst();
        calculerTempsAjoutItem();
        calculerTempsShield();
        listeEntitePrinc.addAll(listeEntiteTemp);
        listeEntiteTemp.clear();
        bougerEntite();
        gererCollisions();
        gererSortieEntite();
        // modifierProtection();
        ssTemp.modifierProtec();
        ssTemp.calculerTemp();
        // calculerMulitplicateur();
        gererMissile(); //  A VOIR
        savoirFinPartie();
        savoirBkg();
        //System.gc();
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {

        for (int i = 0; i < listeEntiteBkg.size(); i++) {
            grphcs.drawImage(listeEntiteBkg.get(i).getImage(), listeEntiteBkg.get(i).getX(), listeEntiteBkg.get(i).getY());
        }
        //   grphcs.drawImage(imageBckd, -50, -50);
        grphcs.drawImage(bkg.getImage(), bkg.getX(), bkg.getY());
        for (int i = 0; i < listeEntitePrinc.size(); i++) {
            grphcs.drawImage(listeEntitePrinc.get(i).getImage(), listeEntitePrinc.get(i).getX(), listeEntitePrinc.get(i).getY());
        }
        // grphcs.drawImage(ast.getImage(), ast.getX(), ast.getY());
        grphcs.setColor(Color.white);

        grphcs.drawImage(ssTemp.getImage(), ssTemp.getX(), ssTemp.getY());

        //grphcs.drawImage(a.getImage(), a.getX(), a.getY());
//       grphcs.drawImage(ssTemp1.getImage(), ssTemp1.getX(), ssTemp1.getY());
        grphcs.drawString("SCORE : " + ssTemp.getScore(), Main.LARGEUR / 2 - 30, 20);
        grphcs.drawString("v.64", Main.LARGEUR - 45, Main.HAUTEUR - 30);
        grphcs.drawString("Cargaison envoyer ", 0, Main.HAUTEUR - 205);
        grphcs.drawString("sur Mars: " + ssTemp.getCargaison() + " kg", 0, Main.HAUTEUR - 190);
        grphcs.drawString("Capacity : " + ssTemp.getCapPrcent() + " %", 0, Main.HAUTEUR - 158);
        //grphcs.drawString("HP " + ssTemp.getHp() + " Protection " + ssTemp.isProtect(), 70, Main.HAUTEUR - 75);

        for (int i = 0; i < ssTemp.getHp(); i++) {
            grphcs.drawImage(ssTemp.getImageLife(), ssTemp.getImageLife().getWidth() * i, Main.HAUTEUR - 100);
        }
        if (ssTemp.isProtect()) {
//            grphcs.drawImage(ssTemp.getImageProtec(), Main.LARGEUR - 70, Main.HAUTEUR - 100);
        }

        grphcs.drawString("Time Elapsed: " + ((gc.getTime() - startTime) / 1000), Main.LARGEUR - 165, 20);
        //    grphcs.drawString("Ship (x): " + ssTemp.getX(), ((Main.LARGEUR * 2) / 3) + 200, 70);
        //  grphcs.drawString("Ship (y): " + ssTemp.getY(), ((Main.LARGEUR * 2) / 3) + 200, 100);
        //grphcs.drawString("Background (y): " + bkg.getY(), ((Main.LARGEUR * 2) / 3) + 100, 200);

        //grphcs.drawString("RayGun : ∞", ((Main.LARGEUR * 2) / 3) + 200, 130);
        grphcs.drawString("Rocket : " + ssTemp.getMunitionRocket(), ((Main.LARGEUR * 2) / 3) + 200, Main.HAUTEUR - 190);
        grphcs.drawString("MiniGun : " + ssTemp.getMunitionMG(), ((Main.LARGEUR * 2) / 3) + 200, Main.HAUTEUR - 158);

        if (ssTemp.isPlein()) {
            grphcs.setColor(Color.magenta);
            grphcs.drawString("APPUYER SUR [E] POUR VIDER LE VAISSEAU", Main.LARGEUR / 2 - 200, Main.HAUTEUR / 2);
        }

        if (!partieEnCours) {
            grphcs.clear();
            grphcs.setBackground(Color.black);
            grphcs.setColor(Color.cyan);
            grphcs.drawImage(imageGO, Main.LARGEUR * 16 / 100 + 50, Main.HAUTEUR * 1567 / 10000 - 75);
            grphcs.drawString("Score: " + ssTemp.getScore(), 50, Main.HAUTEUR - 200);
            grphcs.drawString("Cargaison: " + ssTemp.getCargaison(), 50, Main.HAUTEUR - 175);
            //    long tempSurvecu = ((gc.getTime() - startTime) / 1000);
            //    grphcs.drawString("Temps Survecu:  " + tempSurvecu,50, Main.HAUTEUR - 150);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
//        ssTemp.setMovin(false);
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
        if (partieEnCours && ssTemp.getHp() != 0) {

            if (Input.KEY_P == key) {
                //     this.container.setPaused(true);
                //  musicBackround.pause();
            }

            if (Input.KEY_SPACE == key) {
                ssTemp.tirerRayGun(listeEntitePrinc);
            }

            if (Input.KEY_I == key) {
                ssTemp.tirerRocket(listeEntitePrinc);
            }

            if (Input.KEY_E == key) {
                ssTemp.envoyer();
            }

//            if (Input.KEY_L == key) {
//                ssTemp1.tirerRayGun(listeEntitePrinc);
//            }
//            
        } else {
            if (!partieEnCours) {
                if (Input.KEY_R == key) {
                    try {
                        this.container.reinit();
                    } catch (SlickException ex) {
                    }
                }
            }
        }
    }

    private void getKeys() {
        //ESPACE
//        if (input.isKeyDown(Input.KEY_SPACE)) {
//            if (!listeKeys.contains(KeyCode.SPACE)) {
//                listeKeys.add(KeyCode.SPACE);
//            }
//        } else {
//            listeKeys.remove(KeyCode.SPACE);
//        }
        if (partieEnCours && ssTemp.getHp() != 0) {
            if (input.isKeyDown(Input.KEY_A)) {
                ssTemp.setX(-1);

                //if (!listeKeys.contains(Input.KEY_A)) {
                //  listeKeys.add(Input.KEY_A);
                //}
            } else {
                // listeKeys.remove(KeyCode.A);
            }

            if (input.isKeyDown(Input.KEY_S)) {
                ssTemp.setY(1);
                // if (!listeKeys.contains(KeyCode.S)) {
                //   listeKeys.add(KeyCode.S);
                // }
            } else {
                //  listeKeys.remove(KeyCode.S);
            }

            if (input.isKeyDown(Input.KEY_D)) {

                ssTemp.setX(1);

                //    if (!listeKeys.contains(KeyCode.D)) {
                //      listeKeys.add(KeyCode.D);
                // }
            } else {
                //  listeKeys.remove(KeyCode.D);
            }
            if (input.isKeyDown(Input.KEY_W)) {
                ssTemp.setY(-1);

                //if (!listeKeys.contains(KeyCode.W)) {
                //  listeKeys.add(KeyCode.W);
                //}
            } else {
                //listeKeys.remove(KeyCode.W);
            }

            if (input.isKeyDown(Input.KEY_O)) {

                if (System.currentTimeMillis() - lastMGTime1 >= ssTemp.getVitesseMg()) {

                    lastMGTime1 = System.currentTimeMillis();
                    ssTemp.tirerMiniGun(listeEntitePrinc);
                }

                //if (!listeKeys.contains(KeyCode.W)) {
                //  listeKeys.add(KeyCode.W);
                //}
            } else {
                //listeKeys.remove(KeyCode.W);
            }

//            
//            if (input.isKeyDown(Input.KEY_LEFT)) {
//                ssTemp1.setX(-1);
//            }
//            if (input.isKeyDown(Input.KEY_RIGHT)) {
//                ssTemp1.setX(1);
//
//            }
//            if (input.isKeyDown(Input.KEY_DOWN)) {
//                ssTemp1.setY(1);
//
//            }
//            if (input.isKeyDown(Input.KEY_UP)) {
//                ssTemp1.setY(-1);
//
//            }
//
//            if (input.isKeyDown(Input.KEY_K)) {
//
//                if (System.currentTimeMillis() - lastMGTime2 >= 50) {
//
//                    lastMGTime2 = System.currentTimeMillis();
//                    ssTemp1.tirerMiniGun(listeEntitePrinc);
//                }
//
//                //if (!listeKeys.contains(KeyCode.W)) {
//                //  listeKeys.add(KeyCode.W);
//                //}
//            }
        }
    }

    private void traiterKeys() {

        // Bouger le joueur en fonction des 4 directions
        // ssTemp.bouger(listeKeys); // Bouger le joueur tiendra compte de la liste
        //   if (listeKeys.contains(KeyCode.SPACE))
        {
            //tirerBalle(); // Méthode qui va ajouter un projectile tiré à l’écran
        }
    }

    private void intialiserBckg() {
        listeBckg.add("Background-1.png");
        listeBckg.add("Background-3.png");
        listeBckg.add("Background-4.png");

    }

    private void gererCollisions() {

        ArrayList<Entite> listeTemp = new ArrayList();
        int mHp;
        boolean a = false;
        for (Entite b1 : listeEntitePrinc) {
            for (Entite b2 : listeEntitePrinc) {
                //  if (b1 != b2) { // 2 entités différentes
                if (b1 instanceof Collisionable) {
                    if (b2 instanceof Tirable) {
                        if (b1.getRectangle().intersects(b2.getRectangle())) {
                            if (b1 instanceof Asteroid) {
                                //trycatch
                                if (b1 instanceof RegAsteroid) {
                                    gererAstMissile((RegAsteroid) b1);
                                }
                                if (b1 instanceof BbAsteroid) {
                                    if (b2 instanceof Arme) {
                                        ssTemp.calculScore(SCORE_B / 2);
                                        if (((Arme) b2).getType() != Type.RKS) {
                                            ((BbAsteroid) b1).setExploser(true);

                                        } else {
                                            if (!((BbAsteroid) b1).getExploser()) {
                                                ((BbAsteroid) b1).setDetruire(true);
                                            }
                                        }
                                    }
                                    if (b2 instanceof Ability) {
                                        ((BbAsteroid) b1).setExploser(false);
                                        ((BbAsteroid) b1).setDetruire(true);
                                    }
                                }
                                savoirDetruire((Asteroid) b1, listeTemp); //
                            } // peut faire un else if
                            else {
                                listeTemp.add((Entite) b1);     // C’est aussi une Entite
                            }
                            if (b2 instanceof Arme) {
                                if (((Arme) b2).getType() != Type.RKS) {

                                    listeTemp.add((Entite) b2);
                                }

                            } else if (b2 instanceof Ability) {
                                if (b2.getDetruire()) {
                                    listeTemp.add((Entite) b2);
                                }
                            } else {
                                listeTemp.add((Entite) b2);
                            }

                        }
                    }

                    if (b1 instanceof BbAsteroid) {
                        savoirDetruire((Asteroid) b1, listeTemp); //
                    }

                    if (b1.getRectangle().intersects(ssTemp.getRectangle())) {
                        if (b1 instanceof Asteroid) {  //trycatch
                            mHp = ((Asteroid) b1).getMinusPoint();
                            if (b1 instanceof RegAsteroid) {
                                gererAstSS((RegAsteroid) b1, mHp);
                            } else if (b1 instanceof BbAsteroid) {
                                gererVieSS(mHp);
                                if (!((BbAsteroid) b1).getExploser()) {
                                    b1.setDetruire(true);
                                    a = true;
                                    //  ssTemp.setProtec(false);
                                }
                            }
                        }
                        if (b1 instanceof BbAsteroid) {
                            if (b1.getDetruire()) {
                                listeTemp.add((Entite) b1);
                            }
                        } else {
                            listeTemp.add((Entite) b1);
                        }
                    }
                }
                if (b1 instanceof Item) {
                    if (b1.getRectangle().intersects(ssTemp.getRectangle())) {
                        ((Item) b1).itemUp(ssTemp);
                        listeTemp.add((Entite) b1);
                    }
                }
                //}
            }
        }

        listeEntitePrinc.removeAll(listeTemp); //Supprimer les éléments marqués
        listeTemp.clear(); //Supprimer la liste temporaire
        if (a) {
            ssTemp.setProtec(false);
        }
    }

    private void modifierProtection() {

        if (System.currentTimeMillis() - ssTemp.getLastTouch() > ssTemp.getTempsProtec()) {
            if (ssTemp.isProtect()) {
                ssTemp.setProtec(Boolean.FALSE);
            }
        }
    }

    private void gererVieSS(int mHp) {
        if (!ssTemp.isProtect()) {
            ssTemp.gererVie(true, mHp);
            ssTemp.setLastTouch(System.currentTimeMillis());
        }
    }

    private void gererAstSS(RegAsteroid ast, int mHp) {

        if (ast.getStade() == Stade.RAMASSABLE) {
            anim++;
            int cap = (int) (64 * 64) / 2;
            if (anim == 6) {
                ssTemp.broyer(cap);
                anim = 0;
            }
        } else {
            gererVieSS(mHp);
        }

        // return mHp;
    }

    private void gererAstMissile(RegAsteroid ast) {

        switch (ast.getStade()) {
            case GRAND:
                ast.setDetruire(true);
                float x = ast.getX();
                float y = ast.getY();
                listeEntiteTemp.add(new RegAsteroid(x, y, 1, RegAsteroid.getImagePetit(), Stade.PETIT));
                listeEntiteTemp.add(new RegAsteroid(x, y, -1, RegAsteroid.getImagePetit(), Stade.PETIT));
                ssTemp.calculScore(SCORE_G);
                break;
            case PETIT:
                ast.setDetruire(true);
                float x1 = ast.getX();
                float y1 = ast.getY();
                listeEntiteTemp.add(new RegAsteroid(x1, y1, 1, RegAsteroid.getImageRams(), Stade.RAMASSABLE));
                listeEntiteTemp.add(new RegAsteroid(x1, y1, -1, RegAsteroid.getImageRams(), Stade.RAMASSABLE));
                ssTemp.calculScore(SCORE_P);
                break;

        }
    }

    private void gererMissile() {
        ArrayList<Arme> listeTirable = new ArrayList();

        ArrayList<Entite> listeTirableTemp = new ArrayList();

        for (int i = 0; i < listeEntitePrinc.size(); i++) {
            if (listeEntitePrinc.get(i) instanceof Tirable) {

                if (listeEntitePrinc.get(i) instanceof Arme) {
                    listeTirable.add((Arme) listeEntitePrinc.get(i));
                } else {
                    //     listeExplosion.add((Explosion) listeEntitePrinc.get(i));
                }

            }
        }

        for (int i = 0; i < listeTirable.size(); i++) {
            listeTirable.get(i).savoirDetruire();
            if (listeTirable.get(i).getDetruire()) {
                listeTirableTemp.add((Entite) listeTirable.get(i));
            }
        }

        listeEntitePrinc.removeAll(listeTirableTemp);
        listeTirable.clear();
        listeTirableTemp.clear();

    }

    private void bougerEntite() {

        ArrayList<Bougeable> listeBougeableTemp = new ArrayList();
        ArrayList<Animable> listeAnimableTemp = new ArrayList();
        ArrayList<Explosable> listeExplosableTemp = new ArrayList();

        for (int i = 0; i < listeEntitePrinc.size(); i++) {
            if (listeEntitePrinc.get(i) instanceof Bougeable) {
                listeBougeableTemp.add((Bougeable) listeEntitePrinc.get(i));
            }
            if (listeEntitePrinc.get(i) instanceof Animable) {
                listeAnimableTemp.add((Animable) listeEntitePrinc.get(i));
            }
            if (listeEntitePrinc.get(i) instanceof Explosable) {
                listeExplosableTemp.add((Explosable) listeEntitePrinc.get(i));
            }

        }
        for (int i = 0; i < listeBougeableTemp.size(); i++) {
            listeBougeableTemp.get(i).bouger();
        }
        for (int i = 0; i < listeAnimableTemp.size(); i++) {
            listeAnimableTemp.get(i).animer();
        }
        for (int i = 0; i < listeExplosableTemp.size(); i++) {
            listeExplosableTemp.get(i).exploser();
        }
        listeExplosableTemp.clear();
        listeBougeableTemp.clear();
        listeAnimableTemp.clear();
    }

    private void gererSortieEntite() {

        ArrayList<Entite> listeTemp = new ArrayList();
        for (int i = 0; i < listeEntitePrinc.size(); i++) {
            if (listeEntitePrinc.get(i).getX() > (2 * Main.LARGEUR)
                    || listeEntitePrinc.get(i).getX() < (-1 * Main.LARGEUR) + listeEntitePrinc.get(i).getWidth()) {
                listeTemp.add(listeEntitePrinc.get(i));
            } else if (listeEntitePrinc.get(i).getY() > (2 * Main.HAUTEUR)
                    || listeEntitePrinc.get(i).getY() < ((-1 * Main.HAUTEUR) + listeEntitePrinc.get(i).getHeight())) {

                listeTemp.add(listeEntitePrinc.get(i));

            }
        }
        listeEntitePrinc.removeAll(listeTemp);
        listeTemp.clear();

    }

    private void savoirDetruire(Asteroid ast, ArrayList<Entite> listeEntTemp) {
        if (ast.getDetruire()) {
            listeEntTemp.add(ast);
            //ajout dans la liste
        }
    }

    private void calculerTempsAjoutAst() {

        if (System.currentTimeMillis() - lastRegAstTime >= ajoutRegAst) {

            lastRegAstTime = System.currentTimeMillis();
            listeEntitePrinc.add(new RegAsteroid());
        }

        if (System.currentTimeMillis() - lastBbAstTime >= ajoutBbAst) {

            lastBbAstTime = System.currentTimeMillis();
            listeEntitePrinc.add(new BbAsteroid());
        }

    }

    private void calculerTempsAjoutItem() {

        if (System.currentTimeMillis() - lastItemTime1 >= ajoutItem) {

            lastItemTime1 = System.currentTimeMillis();
            listeEntitePrinc.add(new Item(spriteSheet));
        }

    }

    private void calculerMulitplicateur() {

        if (System.currentTimeMillis() - ssTemp.getLastMulti() >= ssTemp.getTempMult()) {
            if (ssTemp.isMulti()) {
                ssTemp.setMulti(false);
                ssTemp.setLastMulti(System.currentTimeMillis());
            }
        }

    }

    private void calculerTempsShield() {

        if (ssTemp.isShield()) {
            if (!shield) {
                listeEntitePrinc.add(new Ability(ssTemp));
                shield = true;
                lastShieldTime = System.currentTimeMillis();
            }
        }
        if (System.currentTimeMillis() - lastShieldTime >= 12000) {
            shield = false;
            ssTemp.setShield(false);

            for (int i = 0; i < listeEntitePrinc.size(); i++) {
                if (listeEntitePrinc.get(i) instanceof Ability) {
                    listeEntitePrinc.get(i).setDetruire(true);
                }
            }

        }

    }

    private void savoirBkg() {
        if (bkg.getY() == 0) {
            bkg = new Backround();
        }
    }

    private void savoirFinPartie() {
        if (ssTemp.getHp() <= 0) {
            ssTemp.exploser();

            if (ssTemp.getDetruire()) {
                partieEnCours = false;
                listeEntitePrinc.clear();
            }
        }

    }

    private void playMusic() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }

}

/*
    
    
    
 



    
    
    
    
    
    
    
    
    
    
    
 */
//    private void bougerShip(int delta) {
//        if (ssTemp.isMovin()) {
//
//            switch (ssTemp.getDirection()) {
//                case UP:
//                    ssTemp.setY(ssTemp.getY() - 15);
//                    break;
//                case DOWN:
//                    ssTemp.setY(ssTemp.getY() + 15);
//                    break;
//                case LEFT:
//                    ssTemp.setX(ssTemp.getX() - 15);
//                    break;
//                case RIGHT:
//                    ssTemp.setX(ssTemp.getX() + 15);
//                    break;
//            }
//        }
//  @Override
//  public void keyPressed(int key, char c) {
//        switch (key) {
//            case Input.KEY_W:
//
//                ssTemp.setDirection(Direction.UP);
//                ssTemp.setMovin(true);
//                break;
//            case Input.KEY_A:
//
//                ssTemp.setDirection(Direction.LEFT);
//                ssTemp.setMovin(true);
//                break;
//            case Input.KEY_S:
//
//                ssTemp.setDirection(Direction.DOWN);
//                ssTemp.setMovin(true);
//                break;
//            case Input.KEY_D:
//
//                ssTemp.setDirection(Direction.RIGHT);
//                ssTemp.setMovin(true);
//                break;
//        }

