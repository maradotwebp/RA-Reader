package bl;

import dataRead.Pagescrap;
import java.io.IOException;
import java.util.ArrayList;

/**
 * DataGetter
 * Bekommt Daten durch das Verwalten der Datenklassen und gibt sie an die GUI weiter.
 * @author Alex
 * @version 1.0
 */
public class DataGetter {
    private final Pagescrap ps;
    private final ArrayList regisNumbers;
    
    public DataGetter() throws IOException {
        ps = new Pagescrap();
        regisNumbers = ps.getRegisterNr();
    }
    
    //Gibt sämtliche Registriernummern zurück.
    public ArrayList getRegisNumbers() {
        return regisNumbers;
    }
    
    public String getValue(int regNr, int zuNr, String search) throws IOException {
        Pagescrap psv = new Pagescrap(regNr, zuNr, search);
        String val = psv.getValue();
        if("Antrag auf Erneuerung der Zulassung:".equals(val)) {
            return "---";
        }
        return val;
    }
    
    public String getIndisValue(int regNr, int zuNr, String search, int indikation) throws IOException {
        Pagescrap psv = new Pagescrap(regNr, zuNr, search);
        String val = psv.getIndisValue(indikation);
        return val;
    }
    
    public ArrayList getAllIndikation(int regNr, int zuNr) throws IOException {
        Pagescrap psv = new Pagescrap(regNr, zuNr, "");
        ArrayList<Integer> numbers = psv.getAllIndis();
        return numbers;
    }
}
