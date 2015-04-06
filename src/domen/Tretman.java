package domen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author student1
 */
public class Tretman {

    private int tretmanID;
    private String opis;
    private double cena;
    private int trajanjeUMin;
    private List<Preparat> listaPreparata;

    public Tretman() {

    }

    @Override
    public boolean equals(Object obj) {
        Tretman t = (Tretman) obj;
        if (getOpis().equals(t.getOpis())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getTrajanjeUMin() {
        return trajanjeUMin;
    }

    public void setTrajanjeUMin(int trajanjeUMin) {
        this.trajanjeUMin = trajanjeUMin;
    }

    public List<Preparat> getListaPreparata() {
        return listaPreparata;
    }

    public void setListaPreparata(List<Preparat> listaPreparata) {
        this.listaPreparata = listaPreparata;
    }

    public int getTretmanID() {
        return tretmanID;
    }

    public void setTretmanID(int tretmanID) {
        this.tretmanID = tretmanID;
    }

}
