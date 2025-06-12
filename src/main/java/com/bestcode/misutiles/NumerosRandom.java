/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.util.Random;

/**
 *
 * @author jimmy
 */
public class NumerosRandom {
    
    public static int numeroRandomEntre(int desde, int hasta){
        return new Random().nextInt(hasta-desde) + desde;
    }
}
