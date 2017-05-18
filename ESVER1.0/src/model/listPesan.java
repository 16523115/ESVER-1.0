/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;


import java.io.File;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 *
 * @author zain
 */
public class listPesan {
    public List <Pesan> pesan;

    public listPesan(){
        this.pesan = new ArrayList();
    }
    
    public void saveDataToXML(listPesan _larikPsn) throws Exception{
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.newDocument();
       doc.setXmlStandalone(true);
       Element rootElement = doc.createElement("Message");
       doc.appendChild(rootElement);
       
       for(int i=0; i<_larikPsn.pesan.size();i++){
           Element elemen_pesan = doc.createElement("Pesan");
           //elemen_mahasiswa.setAttribute("NIM", ""+_larikMhs.mahasiswa.get(i).getNIM());
           rootElement.appendChild(elemen_pesan);
           
           Element fieldNama = doc.createElement("Nama");
           fieldNama.setTextContent(_larikPsn.pesan.get(i).getNama());
           elemen_pesan.appendChild(fieldNama);

           Element fieldPesan = doc.createElement("IsiPesan");
           fieldPesan.setTextContent(_larikPsn.pesan.get(i).getPesan());
           elemen_pesan.appendChild(fieldPesan);
       }
       
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();
       DOMSource dom = new DOMSource(doc);
       StreamResult result = new StreamResult(new File("pesan.xml"));
       transformer.transform(dom, result);
    }
    
    public void nambahElemen(listPesan _larikPsn, Pesan _psn){
        _larikPsn.pesan.add(_psn);
    }
    
    /*
    public int cariElemen(String _NIM, listPesan _listMhs){
      
        Boolean ketemu=false;
        int j=0;
        while (j<=_listMhs.mahasiswa.size()-1 & !ketemu){
            if (_listMhs.mahasiswa.get(j).getNIM().equals(_NIM)){
                ketemu = true;
            } else {
                j = j + 1;
            }
        }
        return j;
    }
    */
    public void openXMLtoList(listPesan _larikPsn) throws Exception{
        String Nama, Pesan, RootElemen, RowElemen, strTglLahir;
        
        
       File fileXML = new File("pesan.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fileXML);
       doc.getDocumentElement().normalize();
       
       RootElemen = doc.getDocumentElement().getNodeName();
       NodeList nList = doc.getElementsByTagName("Pesan");
          
       for (int i = 0; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);
          RowElemen = nNode.getNodeName();
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             Nama = eElement.getElementsByTagName("Nama").item(0).getTextContent();
             Pesan = eElement.getElementsByTagName("IsiPesan").item(0).getTextContent();
             Pesan A = new Pesan(Nama, Pesan);
             _larikPsn.pesan.add(A);
          }
       }
    }
    
}
