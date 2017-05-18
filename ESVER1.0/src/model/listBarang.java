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
public class listBarang {
    public List <Barang> barang;

    public listBarang(){
        this.barang = new ArrayList();
    }
    
    public void saveDataToXML(listBarang _larikBrng) throws Exception{
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.newDocument();
       doc.setXmlStandalone(true);
       Element rootElement = doc.createElement("barang");
       doc.appendChild(rootElement);
       
       for(int i=0; i<_larikBrng.barang.size();i++){
           Element elemen_barang = doc.createElement("Barang");
           rootElement.appendChild(elemen_barang);
           
           Element fieldNama = doc.createElement("NamaPengirim");
           fieldNama.setTextContent(_larikBrng.barang.get(i).getNama());
           elemen_barang.appendChild(fieldNama);
           
           elemen_barang.setAttribute("NoSurat", ""+_larikBrng.barang.get(i).getNoSurat());

           Element fieldPerihal = doc.createElement("Perihal");
           fieldPerihal.setTextContent(_larikBrng.barang.get(i).getPerihal());
           elemen_barang.appendChild(fieldPerihal);
           
           Element fieldTanggal = doc.createElement("Tanggal");
           fieldTanggal.setTextContent(_larikBrng.barang.get(i).getTanggal().toString());
           elemen_barang.appendChild(fieldTanggal);
           
           Element fieldPosisi = doc.createElement("Posisi");
           fieldPosisi.setTextContent(_larikBrng.barang.get(i).getPosisi());
           elemen_barang.appendChild(fieldPosisi);  
           
           Element fieldKonfirmasi = doc.createElement("Konfirmasi");
           fieldKonfirmasi.setTextContent(_larikBrng.barang.get(i).getKonfirmasi());
           elemen_barang.appendChild(fieldKonfirmasi);
           
           Element fieldBatas = doc.createElement("Batas");
           fieldBatas.setTextContent(String.valueOf(_larikBrng.barang.get(i).getBatas()));
           elemen_barang.appendChild(fieldBatas);
       }
       
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();
       DOMSource dom = new DOMSource(doc);
       StreamResult result = new StreamResult(new File("barang.xml"));
       transformer.transform(dom, result);
    }
    
    public void nambahElemen(listBarang _larikBrng, Barang _brng){
        _larikBrng.barang.add(_brng);
    }
    
    public int cariBarang(String _NoSurat, listBarang _listBrng){
      
        Boolean ketemu=false;
        int j=0;
        while (j<=_listBrng.barang.size()-1 & !ketemu){
            if (_listBrng.barang.get(j).getNoSurat().equals(_NoSurat)){
                ketemu = true;
            } else {
                j = j + 1;
            }
        }
        return j;
    }
    
    public void openXMLtoList(listBarang _larikBrng) throws Exception{
        String Nama, NoSurat, Perihal, Posisi, Konfirmasi, RootElemen, RowElemen, strTgl;
        int Batas;
        LocalDate Tanggal;
        
        
       File fileXML = new File("barang.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fileXML);
       doc.getDocumentElement().normalize();
       
       RootElemen = doc.getDocumentElement().getNodeName();
       NodeList nList = doc.getElementsByTagName("Barang");
          
       for (int i = 0; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);
          RowElemen = nNode.getNodeName();
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             Nama = eElement.getElementsByTagName("NamaPengirim").item(0).getTextContent();
             NoSurat = eElement.getAttribute("NoSurat");
             Perihal = eElement.getElementsByTagName("Perihal").item(0).getTextContent();
             Tanggal = LocalDate.parse(eElement.getElementsByTagName("Tanggal").item(0).getTextContent());
             Posisi = eElement.getElementsByTagName("Posisi").item(0).getTextContent();
             Konfirmasi = eElement.getElementsByTagName("Konfirmasi").item(0).getTextContent();
             Batas = Integer.parseInt(eElement.getElementsByTagName("Batas").item(0).getTextContent());
             Barang A = new Barang(Nama, NoSurat, Perihal, Tanggal, Posisi, Konfirmasi, Batas);
             _larikBrng.barang.add(A);
          }
       }
    }
    
}
