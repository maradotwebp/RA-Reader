package bl;

import dataWrite.WriteToFile;
import java.io.BufferedWriter;
import java.io.IOException;
import model.InfoTable;

/**
 * DataField
 * Leitet Informationen von der GUI zu den Datenklassen weiter.
 * @author Alex
 * @version 1.0
 */
public class DataSetter {
    
    public void writeToFile(BufferedWriter bw, InfoTable it) throws IOException {
        WriteToFile wtf = new WriteToFile();
        wtf.writeTo(bw, it);
    }
    
    public void writeToFile(BufferedWriter bw, int regNr, int zuNr) throws IOException {
        WriteToFile wtf = new WriteToFile();
        InfoTable it = new InfoTable(regNr, zuNr);
        wtf.writeTo(bw, it);
    }
}
