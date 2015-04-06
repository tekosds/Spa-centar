/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeoperacije.preparat;


import db.DatabaseBroker;
import domen.Preparat;
import sistemskeoperacije.GenerickaSistemskaOperacija;

/**
 *
 * @author Ivana
 */
public class DodajPreparat extends GenerickaSistemskaOperacija{

    @Override
    protected void proveriPreduslov(DatabaseBroker broker, Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsi(DatabaseBroker broker, Object objekat) throws Exception {
        Preparat preparat = (Preparat) objekat;
        broker.ubaci(preparat);
    }

    
    
}
