package dataWrite;

import dataRead.MainDataEnum;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * WriteToXML Schreibt Daten in eine XML-Datei.
 *
 * @author Alex
 * @version 1.0
 */
public class WriteToXML {
    private final JTable jt;
    
    public WriteToXML(JTable jt) {
        this.jt = jt;
    }
    
    public Document createDOM() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element root = doc.createElement("jTable");
        this.appendToDOM(root);
        doc.appendChild(root);
        return doc;
    }
    
    private void appendToDOM(Element root) {
        Document doc = root.getOwnerDocument();
        TableModel atm = jt.getModel();
        int count = atm.getRowCount();
        for (int i = 0; i < count; i++) {
            MainDataEnum[] xmlNames = MainDataEnum.values();
            String name = xmlNames[i].name();
            String info = (String) atm.getValueAt(i, 2);
            Element e = doc.createElement(name);
            e.appendChild(doc.createTextNode(info));
            root.appendChild(e);
        }
    }
    
    public void writeDOM(Document doc, FileWriter inputFile) throws SAXException, IOException, TransformerConfigurationException, TransformerException, ParserConfigurationException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(inputFile);
        Source input = new DOMSource(doc);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(input, output);
    }
}
