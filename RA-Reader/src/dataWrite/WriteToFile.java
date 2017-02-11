package dataWrite;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.InfoTable;

/**
 * WriteToFile
 * Schreibt Daten in eine Datei.
 * 
 * @author Alex
 * @version 1.0
 */
public class WriteToFile {
    
    public void writeTo(BufferedWriter bw, InfoTable it) throws IOException {
        bw.write("RegNr:"+it.getRegNr());
        bw.newLine();
        bw.write("ZuNr:"+it.getZuNr());
        ArrayList<String> names = it.getNames();
        ArrayList<String> infos = it.getInfos();
        for(int i = 0; i < it.getRowCount(); i++) {
            bw.newLine();
            bw.write(names.get(i) + infos.get(i));
        }
        bw.flush();
        bw.close();
    }
}
