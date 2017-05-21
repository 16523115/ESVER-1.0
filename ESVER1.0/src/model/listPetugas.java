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
public class listPetugas {
    public List <Petugas> petugas;

    public listPetugas(){
        this.petugas = new ArrayList();
    }
    
    public void saveDataToXML(listPetugas _larikPtgs) throws Exception{
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.newDocument();
       doc.setXmlStandalone(true);
       Element rootElement = doc.createElement("User");
       doc.appendChild(rootElement);
       
       for(int i=0; i<_larikPtgs.petugas.size();i++){
           Element elemen_petugas = doc.createElement("PETUGAS");
           elemen_petugas.setAttribute("Email", ""+_larikPtgs.petugas.get(i).getUsername());
           rootElement.appendChild(elemen_petugas);
           
           Element fieldPassword = doc.createElement("Password");
           fieldPassword.setTextContent(_larikPtgs.petugas.get(i).getPassword());
           elemen_petugas.appendChild(fieldPassword);
           
       }
       
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();
       DOMSource dom = new DOMSource(doc);
       StreamResult result = new StreamResult(new File("petugas.xml"));
       transformer.transform(dom, result);
    }
    /*
    public void nambahElemen(listPetugas _larikMhs, Mahasiswa _mhs){
        _larikMhs.mahasiswa.add(_mhs);
    }
    */
    public boolean login(String _user, String _pass, listPetugas _listPtgs){
      
        Boolean ketemu=false;
        int j=0;
        while (j<=_listPtgs.petugas.size()-1 & !ketemu){
            if ((_listPtgs.petugas.get(j).getUsername().equals(_user)) & 
                    (_listPtgs.petugas.get(j).getPassword().equals(_pass))){
                ketemu = true;
            } else {
                j = j + 1;
            }
        }
        return ketemu;
    }
    
    public void openXMLtoList(listPetugas _larikPtgs) throws Exception{
        String Username, Password, RootElemen, RowElemen;
        
        
       File fileXML = new File("petugas.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fileXML);
       doc.getDocumentElement().normalize();
       
       RootElemen = doc.getDocumentElement().getNodeName();
       NodeList nList = doc.getElementsByTagName("PETUGAS");
          
       for (int i = 0; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);
          RowElemen = nNode.getNodeName();
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             Username = eElement.getAttribute("Email");
             Password = eElement.getElementsByTagName("Password").item(0).getTextContent();
             Petugas A = new Petugas(Username, Password);
             _larikPtgs.petugas.add(A);
          }
       }
    }
    
}
