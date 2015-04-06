/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import db.DatabaseBroker;
import domen.Kompanija;
import domen.Korisnik;
import domen.Preparat;
import domen.Raspored;
import domen.Tretman;
import domen.Zaposleni;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student1
 */
public class Kontroler {

    DatabaseBroker dbbr;
    private static Kontroler instanca;

    public Kontroler() {
        dbbr = DatabaseBroker.getInstance();

    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void kreirajNoviTretman(Tretman tretman) throws SQLException {
        try {
            dbbr.uspostaviKonekcijuPropertiesFile();
            dbbr.unesiTretman(tretman);
            dbbr.commit();
            dbbr.raskiniKonekciju();
        } catch (Exception ex) {
            dbbr.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void kreirajNoveTretmane(List<Tretman> tretmani) throws SQLException {
        try {
            dbbr.uspostaviKonekcijuPropertiesFile();
            for (Tretman tretman : tretmani) {
                dbbr.unesiTretman(tretman);
            }
            dbbr.commit();
            dbbr.raskiniKonekciju();
        } catch (Exception ex) {
            dbbr.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Preparat> vratiSvePreparate() throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return DatabaseBroker.getInstance().vratiSvePreparate();
    }

    public void unesiTretman(Tretman t) throws SQLException, ClassNotFoundException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.unesiTretman(t);
    }

    public void izmeniTretman(int tretmanID, String opis, double cena, int trajanje) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.izmeniTretman(tretmanID, opis, cena, trajanje);
    }

    public List<Tretman> vratiSveTretmane() throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return DatabaseBroker.getInstance().prikaziSveTretmane();
    }

    public List<Preparat> vratiSvePreparateTretmana(int tretmanID) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return dbbr.vratiSvePreparateTretmana(tretmanID);

    }

    public void izmeniPreparate(int tretmanID, ArrayList<Preparat> lp) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.izmeniPreparate(tretmanID, lp);
        dbbr.commit();
        dbbr.raskiniKonekciju();
    }

    public List<Korisnik> vratiKorisnike() throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return dbbr.vratiKorisnike();
    }

    public void unesiKorisnika(Korisnik k) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.unesiKorisnika(k);
        dbbr.raskiniKonekciju();
    }

    public void unesiKompaniju(Kompanija pp) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.unesiKompaniju(pp);
        dbbr.raskiniKonekciju();
    }

    public void unesiPreparat(Preparat p) throws ClassNotFoundException, SQLException, IOException, Exception {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.ubaci(p);
        dbbr.raskiniKonekciju();
    }

    public List<Kompanija> vratiSveKompanije() throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return DatabaseBroker.getInstance().prikaziSveKompanije();
    }

    public void unesiZaposlenog(Zaposleni z) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.unesiZaposlenog(z);
        dbbr.raskiniKonekciju();
    }

    public void obrisiTretman(int tretmanID) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.obrisiTretman(tretmanID);
        dbbr.raskiniKonekciju();
    }

    public List<Zaposleni> vratiSveZaposlene() throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return DatabaseBroker.getInstance().prikaziSveZaposlene();
    }

    public void sacuvajRaspored(Raspored r) throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        dbbr.unesiRaspored(r);
        dbbr.raskiniKonekciju();
    }

    public List<Raspored> vratiRasporede() throws ClassNotFoundException, SQLException, IOException {
        dbbr.uspostaviKonekcijuPropertiesFile();
        return DatabaseBroker.getInstance().prikaziSveRasporede();
    }
}
