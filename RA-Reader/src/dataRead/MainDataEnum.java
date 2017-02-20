package dataRead;

/**
 * DataField
 * Stellt die Bezeichnungen für die Felder bereit.
 * @author Alex
 * @version 1.0
 */
public enum MainDataEnum {
    NAME("Handelsbezeichnung:"),
    ZU_BEGINN("Beginn der Zulassung:"),
    ZU_ENDE("Ende der Zulassung durch Zeitablauf:"),
    NEU("Antrag auf Erneuerung der Zulassung:"),
    WIRKUNG("Wirkungstyp:"),
    ZUBEREITUNG("Art der Zubereitung:"),
    WIRKSTOFF_GEH("Wirkstoffgehalt rein:"),
    FIRMA("Name (Firma):"),
    ADR("Anschrift:");
    
    private final String bezeichnung;
    
    MainDataEnum (String s) {
       this.bezeichnung = s;
    }

    public String getVal() {
        return bezeichnung;
    }
}
