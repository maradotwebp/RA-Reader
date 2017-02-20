package model;

import bl.DataGetter;
import dataRead.IndikationEnum;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * IndikationTable
 * Stellt eine AbstracttableModel für die Indikationen bereit.
 * @author Alex
 * @version 1.0
 */
public class IndikationTable extends AbstractTableModel {
    private final int regNr;
    private final int zuNr;
    private final ArrayList<String> names;
    private final ArrayList<String> infos;
    
    public IndikationTable(int regNr, int zuNr, int indikation) throws IOException {
        this.regNr = regNr;
        this.zuNr = zuNr;
        DataGetter dg = new DataGetter();
        names = new ArrayList<>();
        infos = new ArrayList<>();
        for (IndikationEnum value : IndikationEnum.values()) {
            names.add(value.getVal());
            String val = dg.getIndisValue(regNr, zuNr, value.getVal(), indikation);
            infos.add(val);
        }
    }
    
    @Override
    public int getRowCount() {
        return names.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
         if(columnIndex == 0) {
             return "Definition";
         }
         return "Info";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
             return names.get(rowIndex);
         }
         return infos.get(rowIndex);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getInfos() {
        return infos;
    }

    public int getRegNr() {
        return regNr;
    }

    public int getZuNr() {
        return zuNr;
    }
}
