/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybean;

import javax.ejb.Stateless;

/**
 *
 * @author Exia
 */
@Stateless
public class Addbean implements AddbeanRemote {

    @Override
    public double add(double fahrenheit) {
        return (5/9.0) * (fahrenheit -32.0);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
