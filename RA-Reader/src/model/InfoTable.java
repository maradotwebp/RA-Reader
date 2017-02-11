package model;

import bl.DataGetter;
import dataRead.MainDataEnum;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * DataField
 * Stellt ein AbstractTableModel für die GUIs bereit.
 * @author Alex
 * @version 1.0
 */
public class InfoTable extends AbstractTableModel {
    private final int regNr;
    private final int zuNr;
    private final ArrayList<String> names;
    private final ArrayList<String> infos;
    
    public InfoTable(int regNr, int zuNr) throws IOException {
        this.regNr = regNr;
        this.zuNr = zuNr;
        DataGetter dg = new DataGetter();
        names = new ArrayList<>();
        infos = new ArrayList<>();
        for (MainDataEnum value : MainDataEnum.values()) {
            names.add(value.getVal());
            String val = dg.getValue(regNr, zuNr, value.getVal());
            infos.add(val);
        }
    }

    public InfoTable(ArrayList<String> names, ArrayList<String> infos) {
        this.names = names;
        this.infos = infos;
        this.regNr = 0;
        this.zuNr = 0;
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
