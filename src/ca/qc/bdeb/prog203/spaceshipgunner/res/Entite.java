/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author madzo
 */
public abstract class Entite {

    protected float x, y, width, height;
    protected Image image;
    protected boolean detruire = false;

    public Entite(float x, float y, String imagepath) {
        this.x = x;
        this.y = y;

        try {
            image = new Image(imagepath);
            this.width = this.image.getWidth() - 20;
            this.height = this.image.getHeight() - 20;
        } catch (SlickException e) {
            System.out.println("Image non trouvée pour " + getClass());
        }

    }

    public Entite(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        this.x = x;
        this.y = y;
        this.image = spriteSheet.getSubImage(ligne, colonne);
        this.width = this.image.getWidth() - 18;
        this.height = this.image.getHeight() - 18;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;

    }

    public boolean getDetruire() {
        return detruire;
    }

    public void setDetruire(boolean detruire) {
        this.detruire = detruire;
    }

    // A CHANGER APRES !!! 
    public void setImage(String imagePath) {

        try {
            Image img = new Image(imagePath);
            this.image = img;
            this.width = this.image.getWidth() - 18;
            this.height = this.image.getHeight() - 18;

        } catch (SlickException ex) {
        }
    }

    public void setImage(Image img) {

        this.image = img;
        this.width = this.image.getWidth() - 18;
        this.height = this.image.getHeight() - 18;

    }
}
