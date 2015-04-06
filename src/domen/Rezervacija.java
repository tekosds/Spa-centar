/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.Date;

/**
 *
 * @author Megi
 */
public class Rezervacija {
    private Date vreme;
    private String nacinPlacanja;

    public Rezervacija(Date vreme, String nacinPlacanja) {
        this.vreme = vreme;
        this.nacinPlacanja = nacinPlacanja;
    }
    
    

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }
}
