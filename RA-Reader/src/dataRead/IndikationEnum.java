package dataRead;

/**
 * IndikationEnum
 * Stellt die Bezeichnungen für die Indikatoren-Felder bereit.
 * @author Alex
 * @version 1.0
 */
public enum IndikationEnum {
    ZWECK("Schadorganismus/Zweckbestimmung:"),
    KULTUR("Kultur/Objekt:"),
    EINSATZ("Einsatzgebiet:"),
    ANWNDUNG("Anwendungsbereich:"),
    AUFWAND("Aufwandmenge:"),
    WASSER("Wasseraufwandmenge:"),
    ZEITPKT("Anwendungszeitpunkt:"),
    ANZHL("Max. Anzahl der Anwendungen:"),
    ABSTND("Zeitlicher Abstand in Tagen:"),
    ART("Anwendungsart:"),
    NACHBAU("Nachbaufrist in Tagen:"),
    WARTE("Wartefrist in Tagen:");
    
    private final String bezeichnung;
    
    IndikationEnum (String s) {
       this.bezeichnung = s;
    }

    public String getVal() {
        return bezeichnung;
    }
}

