/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Ivana
 */
public interface GenerickiDomenskiObjekat {
    
    public String dajNazivTabele();
    
    public List<GenerickiDomenskiObjekat> dajSve (ResultSet rs) throws Exception;
    
    public String dajInsertVrednosti();
    
    public String dajApdejtVrednosti();
    
    public String dajImenaSvihAtributa();

    public String dajID();

   
    
    
    
}
