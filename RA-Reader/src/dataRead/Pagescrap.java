package dataRead;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pagescrap
 * Liest Daten von der angegebenen Website.
 *
 * @author Alex
 * @version 1.0
 */
public class Pagescrap {
    Scanner siteHtml;
    String searchDeli;
    
    // Gibt die Domain der Registernummer und Zusatznummer zurück.
    private String getDomain(int RegNr, int zuNr) {
        return "http://pmg.ages.at/export/PMG/PMG/web/reg/" + RegNr + "_" + zuNr + ".html";
    }
    
    // Startet eine Verbindung zur angegebenen Websites
    public Pagescrap (int RegNr, int zuNr, String search) throws MalformedURLException, IOException {
        this.searchDeli = search;
        URLConnection connection = new URL(getDomain(RegNr, zuNr)).openConnection();
        siteHtml = new Scanner(connection.getInputStream(), "iso-8859-1");
        siteHtml.useDelimiter("\\Z");
    }
    
    // Wird für die Registriernummern verwendet.
    public Pagescrap() {
        
    }
    
    // Scannt den Code der Website.
    private String getHtmlCode(Scanner s) {
        while(s.hasNext()) {
            String nextLine = s.nextLine();
            if(nextLine.contains(searchDeli)) {
                nextLine += s.nextLine() + s.nextLine();
                return nextLine + s.nextLine();
            }
        }
        return "";
    }
    
    //Gibt alle Indikatoren einer Website zurück.
    private String getIndikation(Scanner s, int indikation) {
        String indisKennung = indikation + ". Indikation";
        while(s.hasNext()) {
            String nextLine = s.nextLine();
            if(nextLine.contains(indisKennung)) {
                if(nextLine.contains(searchDeli)) {
                    nextLine += s.nextLine() + s.nextLine();
                    return nextLine + s.nextLine();
                } 
            }
        }
        return "";
    }
    
    private ArrayList getAllIndikation(Scanner s) {
        ArrayList<Integer> numbers = new ArrayList<>();
        while(s.hasNext()) {
            String nextLine = s.nextLine();
            if(nextLine.contains(". Indikation") && !nextLine.contains("Für die")) {
                String formatted = new TextFormatter().htmlToText(nextLine);
                String[] val = formatted.split("\\.");
                int number = Integer.parseInt(val[0]);
                numbers.add(number);
            }
        }
        return numbers;
    }
    
    // Scannt den Code einer Website auf Registrierte Nummern.
    private String getRegValues(Scanner s) {
        while(s.hasNext()) {
            String nextLine = s.nextLine();
            if(nextLine.contains(searchDeli)) {
                s.nextLine(); s.nextLine(); s.nextLine(); s.nextLine();
                nextLine = s.nextLine();
                boolean noNumberLeft = false;
                String values = "";
                while(noNumberLeft == false) {
                    if(nextLine.startsWith("<option>")) {
                        boolean number = (nextLine.contains("0") || nextLine.contains("1") ||
                                         nextLine.contains("2") || nextLine.contains("3")  ||
                                         nextLine.contains("4") || nextLine.contains("5")  ||
                                         nextLine.contains("6") || nextLine.contains("7")  ||
                                         nextLine.contains("8") || nextLine.contains("9")) &&
                                         !nextLine.contains("&");
                        if(number) {
                            values += nextLine + "#";
                        } else {
                            noNumberLeft = true;
                        }
                    }
                    nextLine = s.nextLine();
                }
                return values;
            }
        }
        return null;
    }
    
    // Gibt Daten der Website zurück.
    public String getValue() {
        TextFormatter tf = new TextFormatter();
        return tf.formatToValue((tf.htmlToText(getHtmlCode(siteHtml))));
    }
    
    //Gibt die Daten einer Indikation zurück.
    public String getIndisValue(int indikation) {
        TextFormatter tf = new TextFormatter();
        return tf.formatToValue((tf.htmlToText(getIndikation(siteHtml, indikation))));
    }
    
    //Gibt die RegistrierNummnern einer Website zurück.
    public ArrayList getRegisterNr() throws MalformedURLException, IOException {
        TextFormatter tf = new TextFormatter();
        URLConnection connection = new URL("http://pmg.ages.at/pls/psmlfrz/pmgweb2$.Startup").openConnection();
        Scanner regis = new Scanner(connection.getInputStream());
        regis.useDelimiter("\\Z");
        String helpi = searchDeli;
        searchDeli = "Registernummer:";
        String numbersText = tf.htmlToText(getRegValues(regis));
        String[] numbersField = numbersText.split("#");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String number : numbersField) {
            numbers.add(Integer.parseInt(number));
        }
        searchDeli = helpi;
        regis.close();
        return numbers;
    }
    
    //Gibt eine ArrayList mit Indikatoren zurück.
    public ArrayList getAllIndis() {
        return getAllIndikation(siteHtml);
    }
}
