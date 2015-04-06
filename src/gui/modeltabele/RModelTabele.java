/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.modeltabele;

import domen.Raspored;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class RModelTabele extends AbstractTableModel {

    List<Raspored> lr;

    public RModelTabele(List<Raspored> lr) {
        this.lr = lr;

    }

    @Override
    public int getRowCount() {
        return this.lr.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Raspored r = this.lr.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return r.getTretman().getOpis();
            case 1:
                return r.getZaposleni().getImePrezime();

            default:
                return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Tretman";
            case 1:
                return "Zaposleni";
            default:
                return "Greska";
        }
    }

    public void obrisiRed(int red) {
        this.lr.remove(red);
        fireTableDataChanged();
    }

    public List<Raspored> vratiListu() {
        return this.lr;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

}
