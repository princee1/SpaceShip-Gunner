/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog203.spaceshipgunner.res;

import ca.qc.bdeb.prog203.interfacee.Bougeable;

/**
 *
 * @author madzo
 */
public class Backround extends Entite implements Bougeable{

    private int vitesse=2;
    
    
    public Backround( ) {
        super(-30, (-2500+820), "images/Background-3.jpg");
    }

    @Override
    public void bouger() {
        this.setY(y+vitesse);
    }
    
    
}
