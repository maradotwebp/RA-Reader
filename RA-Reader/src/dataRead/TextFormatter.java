package dataRead;

/**
 * TextFormatter
 * Formatiert HTML zu rohem Text.
 *
 * @author Alex
 * @version 1.0
 */
public class TextFormatter {
    
    // Wandelt html zu einem Roh-Text.
    public String htmlToText(String html) {
        String rawText = "";
        int klammerCount = 0;
        int spitzeKlammerCount = 0;
        int eckigeKlammerCount = 0;
        int htmlTagCount = 0;
        for(int i = 0; i < html.length(); i++) {
            char current = html.charAt(i);
            switch(current) {
                case '<': spitzeKlammerCount++; rawText+="#";             break;
                case '[': eckigeKlammerCount++;                           break;
                case '&': htmlTagCount++;                                 break;
                case '>': if(spitzeKlammerCount!=0) spitzeKlammerCount--; break;
                case ']': eckigeKlammerCount--;                           break;
                case ';': htmlTagCount--;                                 break;
                default:
                    if(klammerCount == 0 && spitzeKlammerCount == 0 &&
                       eckigeKlammerCount == 0 && htmlTagCount == 0) {
                        rawText += current;
                    }
                    break;
            }
        }
        return deleteDuplicateEntries(rawText);
    }
    
    //Löscht bis zu 5 Ansammlungen des Entry-Keys und ersetzt sie durch eine.
    private String deleteDuplicateEntries(String text) {
        text = text.replaceAll("#####", "#");
        text = text.replaceAll("####", "#");
        text = text.replaceAll("###", "#");
        text = text.replaceAll("##", "#");
        text = text.trim();
        if(text.startsWith("#")) {
            text = text.substring(1);
        }
        return text;
    }
    
    //Formatiert die Ausgabe, sodass nur die Value ausgegeben wird.
    public String formatToValue(String text) {
        String[] fields = text.split("#");
        if(fields.length >= 2) {
            return fields[1];
        }
        return "---";
    }
}
