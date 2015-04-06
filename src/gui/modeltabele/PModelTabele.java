/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.modeltabele;

import domen.Tretman;
import domen.Preparat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author student1
 */
public class PModelTabele extends AbstractTableModel {

    List<Preparat> lp;

    public PModelTabele(List<Preparat> lp) {
        this.lp = lp;
    }

    @Override
    public int getRowCount() {
        return this.lp.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Preparat p = this.lp.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return p.getPreparatID();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getCena();
            case 3:
                return p.getProizvodjac();
            default:
                return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Naziv";
            case 2:
                return "Cena";
            case 3:
                return "Proizvođač";
            default:
                return "Greska";
        }
    }

    public void obrisiRed(int red) {
        this.lp.remove(red);
        fireTableDataChanged();
    }

    public List<Preparat> vratiListu() {
        return this.lp;
    }

}
