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
public class TModelTabele extends AbstractTableModel {

    List<Tretman> lt;

    public TModelTabele(List<Tretman> lt) {
        this.lt = lt;
       
    }

    @Override
    public int getRowCount() {
        return this.lt.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tretman t = this.lt.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return t.getTretmanID();
            case 1:
                return t.getOpis();
            case 2:
                return t.getCena();
            case 3:
                return t.getTrajanjeUMin();
            case 4:
                return "Detalji";
            default:
                return "Greska";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {       
       
       Tretman t = this.lt.get(rowIndex);

        switch (columnIndex) {
            case 0:
                 t.setTretmanID((int) aValue);
            break;
            case 1:
                 t.setOpis((String) aValue);
            break;    
            case 2:
                t.setCena((double) aValue);
            break;
            case 3:
                t.setTrajanjeUMin((int) aValue);
            break;
            case 4:
                //return "Detalji";
            break;
            default:
               // return "Greska";
        }
    }
    

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Opis";
            case 2:
                return "Cena";
            case 3:
                return "Trajanje";
            case 4:
                return "Preparati";
            default:
                return "Greska";
        }
    }

    public void obrisiRed(int red) {
        this.lt.remove(red);
        fireTableDataChanged();
    }

    public List<Tretman> vratiListu() {
        return this.lt;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

}
