package bl;

import dataRead.Pagescrap;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DataGetter Bekommt Daten durch das Verwalten der Datenklassen und gibt sie an
 * die GUI weiter.
 *
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
        URL u = new URL(Pagescrap.getDomain(regNr, zuNr));
        URLConnection connection = u.openConnection();
        Pagescrap.siteHtml = new Scanner(connection.getInputStream(), "iso-8859-1");
        Pagescrap.siteHtml.useDelimiter("\\Z");
        Pagescrap psv = new Pagescrap(search);
        String val = psv.getValue();
        if ("Antrag auf Erneuerung der Zulassung:".equals(val)) {
            return "---";
        }
        return val;
    }

    public String getIndisValue(int regNr, int zuNr, String search, int indikation) throws IOException {
        Pagescrap psv = new Pagescrap(search);
        String val = psv.getIndisValue(indikation);
        return val;
    }

    public ArrayList getAllIndikation(int regNr, int zuNr) throws IOException {
        Pagescrap psv = new Pagescrap("");
        ArrayList<Integer> numbers = psv.getAllIndis();
        return numbers;
    }
}
