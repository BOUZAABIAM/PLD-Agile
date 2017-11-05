/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.jdom2.DataConversionException;
////import org.jdom2.Document;
//import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author carhiliuc
 */
public class XMLParser {
    
    private static XMLParser instance = null;
    private final String XSD_PLAN = "Modele/validateurPlan.xsd";
    private final String XSD_LIVRAISONS = "Modele/validateurLivraisons.xsd";

    public XMLParser() {
    }
    public static XMLParser getInstance() {
    	if (instance == null) {
    		instance = new XMLParser();
    	}
    	return instance;
    }
    /**
     * Valide un fichier XML avec le fichier XSD fourni.
     *
     * @param xsdResourceName  Le nom schéma XSD à utiliser.
     * @param fichierXML Le fichier XML à valider.
     * @return Renvoie le document correspondant si la validation est effective.
     * @throws JDOMException Si la validation du XML a échoué.
     * @throws IOException   S'il y a eu une erreur de lecture.
     */
    private org.jdom2.Document validerFichierXML(final String xsdResourceName, final InputStream fichierXML)
            throws JDOMException, IOException {

        InputStream xsdStream = ClassLoader.getSystemResourceAsStream(xsdResourceName);
        
        XMLReaderJDOMFactory factory = new XMLReaderXSDFactory(new StreamSource(xsdStream));
        SAXBuilder saxBuilder = new SAXBuilder(factory);
        
        org.jdom2.Document document = saxBuilder.build(fichierXML);

        return document;
    }

    public Plan getPlan(File xmlFile) throws IOException, SAXException, ParserConfigurationException,ParseException,JDOMException,ExceptionXML {
        Map<Long, Intersection> intersections = new TreeMap<Long, Intersection>();
        List<Intersection> intersectionsList = new LinkedList<Intersection>();
        InputStream inputStream = new FileInputStream(xmlFile);
        // Validation du fichier XML avec le schéma
        org.jdom2.Document document = validerFichierXML(XSD_PLAN, inputStream);
        Document mapDocument = null;
        try {
            mapDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        } catch (IOException e) {
            throw e;
        }

        NodeList nList = mapDocument.getElementsByTagName("noeud");

        for (int i = 0; i < nList.getLength(); i++) {
            long id;
            double x;
            double y;
            Element element = (Element) nList.item(i);

            id = Long.parseLong(element.getAttribute("id"));
            x = (Double.parseDouble(element.getAttribute("x")));
            y = (Double.parseDouble(element.getAttribute("y")));
            Intersection intersection = new Intersection(id, x, y, i);
            
            intersectionsList.add(intersection);
            intersections.put(id, intersection);
        }

        NodeList streetSectionList = mapDocument.getElementsByTagName("troncon");

        for (int i = 0; i < streetSectionList.getLength(); i++) {
            Long idIntersectionStart;
            Long idIntersectionEnd;
            double longueur;
            String rueNom;
            Element element = (Element) streetSectionList.item(i);

            idIntersectionStart = Long.parseLong(element.getAttribute("origine"));
            idIntersectionEnd = Long.parseLong(element.getAttribute("destination"));
            if (idIntersectionStart == idIntersectionEnd) {
                  throw new ExceptionXML("Impossible de charger le plan : une intersection plointe vers elle même");
            }
            longueur = Double.parseDouble(element.getAttribute("longueur"));

            rueNom = element.getAttribute("nomRue");
            Intersection origine = intersections.get(idIntersectionStart);
            
            Troncon troncon = new Troncon(rueNom, intersections.get(idIntersectionEnd), origine, longueur);
            

            origine.addTroncon(troncon);

        }

        return new Plan(intersections, intersectionsList);
    }
    
    private String goodTimeForm(String time){
        String newTime = time;
        int prec = -1;    
        for (int i = 0; i < newTime.length(); i++){
            if (newTime.charAt(i) == ':' && ((i - prec) < 3)){
                    prec = i;
                    newTime = newTime.substring(0, i-2) + 0 + newTime.substring(i-2, newTime.length());
             
            }
        }
        return newTime;
    }
    *
     * Convertit un String sous la fomre HH:mm:ss en séconde
     *
     * @param heureMnSec Chaine de caractère à convertir
     * @return Le timestamp en seconde
     */
    private int convertirHeureEnSeconde(String heureMnSec) {
        String[] decoupage = heureMnSec.split(":");

        int heure = Integer.parseInt(decoupage[0]);
        int mn = Integer.parseInt(decoupage[1]);
        int sec = Integer.parseInt(decoupage[2]);

        return heure * 3600 + mn * 60 + sec;
    }
    
    public DemandeLivraison getDL(final File xmlFile, final Plan plan) throws IOException,JDOMException, SAXException, ParserConfigurationException,ParseException,ExceptionXML {
        Map<Long, Livraison> livraisons = new TreeMap<Long, Livraison>();
        Intersection entrepot = null;
        Time heureDepart = null;
        // Chargement du validateur xsd et validation
        InputStream inputStream = new FileInputStream(xmlFile);
        org.jdom2.Document document = validerFichierXML(XSD_LIVRAISONS, inputStream);

        Document mapDocument = null;
        try {
            mapDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        } catch (IOException e) {
            throw e;
        }
        
        NodeList entrepots = mapDocument.getElementsByTagName("entrepot");
        
        for (int i = 0; i < entrepots.getLength(); i++) {
            Long idAdresse;
            String time;
            Element element = (Element) entrepots.item(i);

            idAdresse = Long.parseLong(element.getAttribute("adresse"));
            entrepot = plan.getIntersectionsMap().get(idAdresse);
            if (entrepot == null) {
	            throw new ExceptionXML("Il semblerait que l'entrepot ne se trouve pas dans la ville. Veuillez vérifier votre fichier");
	        }
            time = element.getAttribute("heureDepart");
            //goodTimeForm générait une erreur, ça a l'air de larcher comma ça
            //String newTime = goodTimeForm(time);
            heureDepart = Time.valueOf(time);
        }

        NodeList livr = mapDocument.getElementsByTagName("livraison");

        for (int i = 0; i < livr.getLength(); i++) {
            Livraison livraison;
            long id;
            Integer duree;
            String debutPlage;
            String finPlage;
            int DP;
            int FP;
            Element element = (Element) livr.item(i);

            id = Long.parseLong(element.getAttribute("adresse"));
            // Vérification de la présence de l'intersection dans la ville
            if (plan.getIntersection(id) == null) {
                 throw new ExceptionXML(String.format("Impossible de charger la demande de livraison. L'intersection : %d ne se trouve pas dans la ville.", id));
            }
            Intersection adresse = plan.getIntersectionsMap().get(id);
            
            duree = Integer.parseInt(element.getAttribute("duree"));
            
            debutPlage = element.getAttribute("debutPlage");
            finPlage = element.getAttribute("finPlage");
            
            if (debutPlage!="" && finPlage != "" ){
            DP = convertirHeureEnSeconde(element.getAttribute("debutPlage"));
            FP = convertirHeureEnSeconde (finPlage);
            if ( DP >= FP) {
            throw new ExceptionXML("Une des livraisons est incorrecte : L'heure de début est supérieure ou égale à l'heure de fin");
            }}
            if(debutPlage.isEmpty()||finPlage.isEmpty()){
                livraison = new Livraison(adresse, duree);
            }else{
                livraison = new Livraison(adresse, duree, debutPlage, finPlage);
            }
            
            livraisons.put(id, livraison);
        }

        return new DemandeLivraison(entrepot, heureDepart, livraisons);
    }

}

