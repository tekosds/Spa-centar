package domen;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author student1
 */
public class Kompanija {

    private int kompanijaID;
    private String pib;
    private String maticniBroj;
    private String naziv;
    private String zr;
    private Date datumOsnivanja;
    private String adresa;

    public Kompanija() {
    }
    
    

    public Kompanija(String pib, String maticniBroj, String naziv, String zr, Date datumOsnivanja, String adresa) {
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.zr = zr;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
    }

    
    

    @Override
    public boolean equals(Object obj) {
        Kompanija pp = (Kompanija) obj;
        if (getMaticniBroj().equals(pp.getMaticniBroj())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return naziv;
    }

    /**
     * @return the pib
     */
    public String getPib() {
        return pib;
    }

    /**
     * @param pib the pib to set
     */
    public void setPib(String pib) {
        this.pib = pib;
    }

    /**
     * @return the maticniBroj
     */
    public String getMaticniBroj() {
        return maticniBroj;
    }

    /**
     * @param maticniBroj the maticniBroj to set
     */
    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    /**
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the zr
     */
    public String getZr() {
        return zr;
    }

    /**
     * @param zr the zr to set
     */
    public void setZr(String zr) {
        this.zr = zr;
    }

    /**
     * @return the datumOsnivanja
     */
    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    /**
     * @param datumOsnivanja the datumOsnivanja to set
     */
    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

   
    /**
     * @return the adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * @param adresa the ulicaBroj to set
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getKompanijaID() {
        return kompanijaID;
    }

    public void setKompanijaID(int kompanijaID) {
        this.kompanijaID = kompanijaID;
    }

}
