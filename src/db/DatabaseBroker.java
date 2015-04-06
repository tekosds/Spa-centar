/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import domen.GenerickiDomenskiObjekat;
import domen.Kompanija;
import domen.Korisnik;
import domen.Preparat;
import domen.Raspored;
import domen.Tretman;
import domen.Zaposleni;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student1
 */
public class DatabaseBroker {
    
    private Connection konekcija;
    private MysqlConnectionPoolDataSource ds;
    private static DatabaseBroker instance;
    
    public DatabaseBroker() {
        
    }
    
    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }
    
    public void raskiniKonekciju() throws SQLException {
        konekcija.close();
    }
    
    public void uspostaviKonekcijuPropertiesFile() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(Util.getInstance().getDriver());
        String url = Util.getInstance().getURL();
        String user = Util.getInstance().getUser();
        String password = Util.getInstance().getPassword();
        konekcija = DriverManager.getConnection(url, user, password);
        konekcija.setAutoCommit(false);
        System.out.println("Konekcija uspesna!");
    }
    
    public void commit() throws SQLException {
        konekcija.commit();
    }
    
    public void rollback() throws SQLException {
        konekcija.rollback();
    }
    
    public void unesiTretman(Tretman tretman) throws SQLException {
        int tretmanID = 0;
        String upit = "INSERT INTO tretman (opis, cena, trajanjeUMin) VALUES (?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setString(1, tretman.getOpis());
        ps.setDouble(2, tretman.getCena());
        ps.setInt(3, (int) tretman.getTrajanjeUMin());
        
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            tretmanID = rs.getInt(1);
        }
        
        String upitTP = "INSERT INTO tretmanpreparati (tretmanID,preparatID) VALUES (?,?)";
        PreparedStatement psTP = konekcija.prepareStatement(upitTP);
        
        for (Preparat p : tretman.getListaPreparata()) {
            psTP.setInt(1, tretmanID);
            psTP.setInt(2, p.getPreparatID());
        }
        psTP.executeUpdate();
        commit();
        ps.close();
    }
    
    public List<Tretman> prikaziSveTretmane() throws SQLException {
        ArrayList<Tretman> tretmani = new ArrayList<>();
        String upit = "SELECT tretmanID, opis, cena, trajanjeUMin FROM tretman";
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            Tretman tretman = new Tretman();
            tretman.setTretmanID(rs.getInt("tretmanID"));
            tretman.setOpis(rs.getString("opis"));
            tretman.setCena(rs.getDouble("cena"));
            tretman.setTrajanjeUMin(rs.getInt("trajanjeUMin"));
            tretmani.add(tretman);
        }
        rs.close();
        statement.close();
        
        return tretmani;
    }
    
    public List<Preparat> vratiSvePreparate() {
        String upit = "SELECT p.preparatID, p.naziv, p.cena,k.pib, k.maticniBroj, k.naziv, k.ziroRacun, k.datumOsnivanja, k.adresa FROM preparat p join kompanija k on(p.proizvodjac=k.kompanijaID)";
        List<Preparat> listaPreparata = new ArrayList<Preparat>();
        try {
            Statement statement = konekcija.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("p.preparatID");
                String nazivP = rs.getString("p.naziv");
                double cena = rs.getDouble("p.cena");
                String proizvodjac = rs.getString("k.naziv");
                Preparat p = new Preparat();
                
                Date datumOsnivanja = rs.getDate("k.datumOsnivanja");
                java.util.Date uDatum = new Date(datumOsnivanja.getTime());
                Kompanija k = new Kompanija(rs.getString("k.pib"), rs.getString("k.maticniBroj"), rs.getString("k.naziv"),
                        rs.getString("k.ziroRacun"), uDatum, rs.getString("k.adresa"));
                
                p.setPreparatID(id);
                p.setNaziv(nazivP);
                p.setCena(cena);
                p.setProizvodjac(k);
                listaPreparata.add(p);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: " + ex.getMessage());
        }
        return listaPreparata;
    }
    
    public List<Preparat> vratiSvePreparateTretmana(int tretmanID) {
        String upit = "SELECT p.preparatID, p.naziv, p.cena,k.pib,"
                + " k.maticniBroj, k.naziv, k.ziroRacun, k.datumOsnivanja, k.adresa "
                + "FROM tretmanpreparati tp JOIN preparat p ON (tp.preparatID=p.preparatID)"
                + " JOIN kompanija k ON(p.proizvodjac=k.kompanijaID) "
                + "JOIN tretman t ON(t.tretmanID=tp.tretmanID) WHERE tp.tretmanID=" + "'" + tretmanID + "'";
        List<Preparat> listaPreparata = new ArrayList<Preparat>();
        try {
            Statement statement = konekcija.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("p.preparatID");
                String nazivP = rs.getString("p.naziv");
                double cena = rs.getDouble("p.cena");
                String proizvodjac = rs.getString("k.naziv");
                Preparat p = new Preparat();
                
                Date datumOsnivanja = rs.getDate("k.datumOsnivanja");
                java.util.Date uDatum = new Date(datumOsnivanja.getTime());
                Kompanija k = new Kompanija(rs.getString("k.pib"), rs.getString("k.maticniBroj"), rs.getString("k.naziv"),
                        rs.getString("k.ziroRacun"), uDatum, rs.getString("k.adresa"));
                
                p.setPreparatID(id);
                p.setNaziv(nazivP);
                p.setCena(cena);
                p.setProizvodjac(k);
                listaPreparata.add(p);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: " + ex.getMessage());
        }
        return listaPreparata;
    }
    
    public void izmeniPreparate(int tretmanID, ArrayList<Preparat> lp) throws SQLException {
        obrisiSvePreparateTretmana(tretmanID);
        
        String upit = "INSERT INTO tretmanpreparati (tretmanID,preparatID) VALUES (?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        
        for (Preparat p : lp) {
            ps.setInt(1, tretmanID);
            ps.setInt(2, p.getPreparatID());
        }
        ps.executeUpdate();
        commit();
        ps.close();
        
    }
    
    public List<Korisnik> vratiKorisnike() throws SQLException {
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        String upit = "SELECT klijentID,imePrezime,korisnickoIme,korisnickaSifra FROM klijent";
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            Korisnik k = new Korisnik();
            k.setKlijentID(rs.getInt("klijentID"));
            k.setImePrezime(rs.getString("imePrezime"));
            k.setKorisnickoIme(rs.getString("korisnickoIme"));
            k.setKorisnickaSifra(rs.getString("korisnickaSifra"));
            korisnici.add(k);
        }
        rs.close();
        statement.close();
        
        return korisnici;
    }
    
    public void unesiKorisnika(Korisnik k) throws SQLException {
        String upit = "INSERT INTO klijent (imePrezime, korisnickoIme, korisnickaSifra) VALUES (?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        
        ps.setString(1, k.getImePrezime());
        ps.setString(2, k.getKorisnickoIme());
        ps.setString(3, k.getKorisnickaSifra());
        
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public void izmeniTretman(int tretmanID, String opis, double cena, int trajanje) throws SQLException {
        String upit = "UPDATE tretman SET opis= ?, cena=? trajanjeUMin=? WHERE tretmanID=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        
        ps.setString(1, opis);
        ps.setDouble(2, cena);
        ps.setInt(3, trajanje);
        ps.setInt(4, tretmanID);
        
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    private void obrisiSvePreparateTretmana(int tretmanID) throws SQLException {
        String upit = "DELETE FROM tretmanpreparati WHERE tretmanID=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, tretmanID);
        
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public void unesiKompaniju(Kompanija pp) throws SQLException {
        int kompanijaID;
        String upit = "INSERT INTO kompanija ( pib, maticniBroj, naziv, ziroRacun, datumOsnivanja, adresa) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        
        ps.setString(1, pp.getPib());
        ps.setString(2, pp.getMaticniBroj());
        ps.setString(3, pp.getNaziv());
        ps.setString(4, pp.getZr());
        java.sql.Date datum = new Date(pp.getDatumOsnivanja().getTime());
        ps.setDate(5, datum);
        ps.setString(6, pp.getAdresa());
        
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public void unesiPreparat(Preparat p) throws SQLException {
        String upit = "INSERT INTO preparat (naziv, cena, proizvodjac) VALUES (?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        
        ps.setString(1, p.getNaziv());
        ps.setDouble(2, p.getCena());
        ps.setInt(3, p.getProizvodjac().getKompanijaID());
        
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public void unesiZaposlenog(Zaposleni z) throws SQLException {
        String upit = "INSERT INTO zaposleni (imePrezime, stepenSS, datumRodjenja) VALUES (?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setString(1, z.getImePrezime());
        ps.setString(2, z.getStepenSS());
        ps.setDate(3, new java.sql.Date(z.getDatumRodjenja().getTime()));
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public List<Kompanija> prikaziSveKompanije() throws SQLException {
        ArrayList<Kompanija> kompanije = new ArrayList<>();
        String upit = "SELECT kompanijaID, pib, maticniBroj, naziv, ziroRacun, datumOsnivanja, adresa from kompanija";
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            Kompanija k = new Kompanija();
            k.setKompanijaID(rs.getInt("kompanijaID"));
            k.setPib(rs.getString("pib"));
            k.setMaticniBroj(rs.getString("maticniBroj"));
            k.setNaziv(rs.getString("naziv"));
            k.setZr(rs.getString("ziroRacun"));
            k.setDatumOsnivanja(new java.util.Date(rs.getDate("datumOsnivanja").getTime()));
            k.setAdresa(rs.getString("adresa"));
            kompanije.add(k);
        }
        rs.close();
        statement.close();
        
        return kompanije;
    }
    
    public void obrisiTretman(int tretmanID) throws SQLException {
        String upit = "DELETE FROM tretman WHERE tretmanID=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, tretmanID);
        
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public List<Zaposleni> prikaziSveZaposlene() throws SQLException {
        ArrayList<Zaposleni> zaposleni = new ArrayList<>();
        String upit = "SELECT zaposleniID, imePrezime, stepenSS, datumRodjenja from zaposleni";
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setZaposleniID(rs.getInt("zaposleniID"));
            z.setImePrezime(rs.getString("imePrezime"));
            z.setStepenSS(rs.getString("stepenSS"));
            z.setDatumRodjenja(new java.util.Date(rs.getDate("datumRodjenja").getTime()));
            zaposleni.add(z);
        }
        
        rs.close();
        statement.close();
        return zaposleni;
    }
    
    public void unesiRaspored(Raspored r) throws SQLException {
        String upit = "INSERT INTO raspored (zaposleniID, tretmanID, brojTermina) VALUES (?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        
        ps.setInt(1, r.getZaposleni().getZaposleniID());
        ps.setInt(2, r.getTretman().getTretmanID());
        ps.setInt(3, r.getBrojTermina());
        ps.executeUpdate();
        commit();
        ps.close();
    }
    
    public List<Raspored> prikaziSveRasporede() throws SQLException {
        ArrayList<Raspored> rasporedi = new ArrayList<>();
        String upit = "SELECT tretmanID,zaposleniID,brojTermina from raspored";
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            Zaposleni z = vratiZaposlenog(rs.getInt("zaposleniID"));
            Tretman t = vratiTretman(rs.getInt("tretmanID"));
            int brojTermina = rs.getInt("brojTermina");
            Raspored r = new Raspored(brojTermina, z, t);
            rasporedi.add(r);
        }
        
        rs.close();
        statement.close();
        return rasporedi;
    }
    
    private Zaposleni vratiZaposlenog(int zaposleniID) throws SQLException {
        String upit = "SELECT zaposleniID, imePrezime,stepenSS, datumRodjenja from zaposleni WHERE zaposleniID=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, zaposleniID);
        
        ResultSet rs = ps.executeQuery();
        
        Zaposleni z = new Zaposleni();
        while (rs.next()) {
            z.setZaposleniID(rs.getInt("zaposleniID"));
            z.setImePrezime(rs.getString("imePrezime"));
            z.setStepenSS(rs.getString("stepenSS"));
            z.setDatumRodjenja(new java.util.Date(rs.getDate("datumRodjenja").getTime()));
        }
        rs.close();
        ps.close();
        return z;
    }
    
    private Tretman vratiTretman(int tretmanID) throws SQLException {
        String upit = "SELECT tretmanID, opis, cena, trajanjeUMin from tretman WHERE tretmanID=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, tretmanID);
        
        ResultSet rs = ps.executeQuery();
        
        Tretman t = new Tretman();
        while (rs.next()) {
            t.setTretmanID(rs.getInt("tretmanID"));
            t.setOpis(rs.getString("opis"));
            t.setCena(rs.getDouble("cena"));
            t.setTrajanjeUMin(rs.getInt("trajanjeUMin"));
        }
        rs.close();
        ps.close();
        return t;
    }
    
    public List<GenerickiDomenskiObjekat> dajSve(GenerickiDomenskiObjekat domenskiObjekat) throws Exception {
        try {
            String upit = "SELECT * FROM " + domenskiObjekat.dajNazivTabele();
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            return domenskiObjekat.dajSve(rs);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje podataka.");
        }
    }
    
    public void ubaci(GenerickiDomenskiObjekat domenskiObjekat) throws Exception {
        try {
            String upit = "INSERT INTO " + domenskiObjekat.dajNazivTabele()
                    + "(" + domenskiObjekat.dajImenaSvihAtributa() + ")"
                    + " VALUES(" + domenskiObjekat.dajInsertVrednosti() + ")";
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            commit();
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno cuvanje objekta.");
        }
    }
    
    public void ubaci(List<GenerickiDomenskiObjekat> lista) throws Exception {
        try {
            Statement s = konekcija.createStatement();
            for (GenerickiDomenskiObjekat domenskiObjekat : lista) {
                String upit = "INSERT INTO " + domenskiObjekat.dajNazivTabele()
                        + "(" + domenskiObjekat.dajImenaSvihAtributa() + ")"
                        + " VALUES(" + domenskiObjekat.dajInsertVrednosti() + ")";
                System.out.println(upit);
                s.executeUpdate(upit);
            }
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno cuvanje objekta.");
        }
    }
    
    public void azuriraj(GenerickiDomenskiObjekat domenskiObjekat) throws Exception {
        
        try {
            String upit = "UPDATE " + domenskiObjekat.dajNazivTabele() + " SET " + domenskiObjekat.dajApdejtVrednosti();
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno azuriranje objekta.");
        }
    }
    
    public GenerickiDomenskiObjekat dajPoID(GenerickiDomenskiObjekat domenskiObjekat, int id) throws Exception {
        try {
            String upit = "SELECT * FROM " + domenskiObjekat.dajNazivTabele() + "WHERE " + domenskiObjekat.dajID() + "=" + id;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            
            return domenskiObjekat.dajSve(rs).get(0);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje podataka.");
        }
    }
    
}
