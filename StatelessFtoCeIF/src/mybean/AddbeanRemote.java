/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybean;

import javax.ejb.Remote;

/**
 *
 * @author Exia
 */
@Remote
public interface AddbeanRemote {

    double add(double fahrenheit);
    
}
