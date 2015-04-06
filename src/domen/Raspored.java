/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Megi
 */
public class Raspored {

    private int brojTermina;
    private Zaposleni zaposleni;
    private Tretman tretman;

    public Raspored(int brojTermina,Zaposleni z,Tretman t) {
        this.brojTermina = brojTermina;
        this.zaposleni=z;
        this.tretman=t;
    }

    public int getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(int brojTermina) {
        this.brojTermina = brojTermina;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni z) {
        this.zaposleni = z;
    }

    public Tretman getTretman() {
        return tretman;
    }

    public void setTretman(Tretman t) {
        this.tretman = t;
    }
}
