package dataRead;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.InfoTable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ReadFromXML Liest eine InfoTable von XML ein.
 *
 * @author Alex
 * @version 1.0
 */
public class ReadFromXML {

    public InfoTable LoadXML(File f) throws IOException, ParserConfigurationException, SAXException {
        File inputFile = new File("src/infotable.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        Element root = doc.getDocumentElement();
        root.normalize();
        NodeList namesList = root.getChildNodes();

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> infos = new ArrayList<>();
        for (int i = 0; i < namesList.getLength(); i++) {
            Node n = namesList.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element tableElement = (Element) n;
            String tagName = tableElement.getTagName();
            MainDataEnum e = MainDataEnum.valueOf(tagName);
            names.add(e.getVal());
            infos.add(tableElement.getTextContent());
        }

        InfoTable it = new InfoTable(names, infos);
        return it;
    }

}
