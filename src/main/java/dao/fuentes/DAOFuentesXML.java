package dao.fuentes;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import entities.Fuente;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOFuentesXML implements DAOFuentes{

    private final String fileFuentes = "fuentes.xml";

    @Override
    public List<Fuente> getFuentes() {
        List<Fuente> fuentes = new ArrayList<>();
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(fileFuentes);
            Element elementoRaiz = dom.getDocumentElement();
            NodeList elementosFuente = elementoRaiz.getElementsByTagName("fuente");
            for(int i = 0; i < elementosFuente.getLength(); i++){
                Node elementoFuente = elementosFuente.item(i);
                NamedNodeMap atributosFuente = elementoFuente.getAttributes();
                Fuente fuente = new Fuente(atributosFuente.getNamedItem("nombre").getNodeValue(), atributosFuente.getNamedItem("url").getNodeValue());
                fuentes.add(fuente);
            }
        } catch (ParserConfigurationException | SAXException pce) {
            System.out.println(pce.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return fuentes;
    }

    @Override
    public void inserta(Fuente fuente) {
        List<Fuente> fuentes = getFuentes();
        fuentes.add(fuente);
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element elementoRaiz = dom.createElement("fuentes");
            for(Fuente fuenteActual: fuentes){
                //creo el elemento fuente
                Element elementoFuente = dom.createElement("fuente");
                elementoFuente.setAttribute("nombre",fuenteActual.getNombre().toString());
                elementoFuente.setAttribute("url",fuenteActual.getURL());
                elementoRaiz.appendChild(elementoFuente);
            }
            dom.appendChild(elementoRaiz);
            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "fuentes.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                tr.transform(new DOMSource(dom),new StreamResult(new FileOutputStream(fileFuentes)));
            } catch (TransformerException | IOException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }
}
