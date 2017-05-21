/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utama;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.*;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.util.Locale;
/**
 *
 * @author lowlight
 */
public class ESVER10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        char pilih;
        //String NIM, Nama, strTglLahir, tempTgl;
        //LocalDate tglLahir;
        String Nama, NoSurat, Perihal, tgl, tempTgl, email, pass;
        String Posisi = null, Konfirmasi = null;
        int Batas;
        LocalDate Tanggal;
        Barang tempBrng;
        Pesan tempPsn;
        Petugas tempPtgs;
        //Boolean blmDisimpan;
        Boolean blmDisimpan;
        
        
        DateTimeFormatter formatTanggal;
        formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        listBarang larikBarang = new listBarang();
        larikBarang.openXMLtoList(larikBarang);
        listPetugas larikPetugas = new listPetugas();
        larikPetugas.openXMLtoList(larikPetugas);
        listPesan larikPesan = new listPesan();
        larikPesan.openXMLtoList(larikPesan);
        Scanner scChar = new Scanner(System.in);
        Scanner scString = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Boolean menu = false;
        blmDisimpan = false;
        
        while (menu == false){
            System.out.println("SILAHKAN LOGIN TERLEBIH DAHULU");
            System.out.print("Masukkan email : ");
            email = scString.nextLine();
            System.out.print("Masukkan password : ");
            pass = scString.nextLine();
            menu = larikPetugas.login(email, pass, larikPetugas);         
            if (menu == false){
                System.out.println("Data yang anda masukkan salah");
                System.out.println("-----------------------------");
            }
        }
        
        while (menu == true) {
            System.out.println("");
            System.out.println("PENGOLAHAN DATA SURAT ATAU DOKUMEN (karakter lain untuk Exit)");
            System.out.println("1. Tambah Data");
            System.out.println("2. Edit Data");
            System.out.println("3. Edit Status");
            System.out.println("4. Tampilkan Rincian Data");
            System.out.println("5. Hapus Data");
            System.out.println("6. Simpan Data");
            System.out.println("7. Tampilkan Pesan");
            System.out.print("PILIHAN ANDA: "); 
            pilih = scChar.next().toString().charAt(0);
            
            switch (pilih){
                case '1': {
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("TAMBAH DATA BARU: "); 
                    System.out.print("Nama Pengirim : "); 
                    Nama = scString.nextLine();
                    System.out.print("No Surat : "); 
                    NoSurat = scString.nextLine();
                    System.out.print("Perihal : "); 
                    Perihal = scString.nextLine();
                    System.out.print("Tanggal Surat (format dd-MM-yyyy) : "); 
                    tgl = scString.nextLine();
                    Tanggal = LocalDate.parse(tgl.substring(6, 10)+"-"+tgl.substring(3, 5)+"-"+tgl.substring(0, 2));
                    System.out.print("Batas Waktu (hari): ");
                    Batas = scInt.nextInt();
                    tempBrng = new Barang(Nama, NoSurat, Perihal, Tanggal, Posisi, Konfirmasi, Batas);
                    larikBarang.nambahElemen(larikBarang, tempBrng);
                    blmDisimpan = true;
                    break;
                }
                case '2': {
                    System.out.println();
                    System.out.println("UBAH DATA :");
                    System.out.print("Masukkan no surat yang akan diubah : ");
                    NoSurat = scString.nextLine();
                    int posisi = larikBarang.cariBarang(NoSurat, larikBarang);
                    if (posisi < larikBarang.barang.size()){
                        System.out.println("Nama Pengirim : "+larikBarang.barang.get(posisi).getNama());
                        System.out.println("No Surat : "+larikBarang.barang.get(posisi).getNoSurat());
                        LocalDate temp = larikBarang.barang.get(posisi).getTanggal();
                        tempTgl = temp.format(formatTanggal);
                        System.out.println("Tanggal Surat  : "+tempTgl);
                        System.out.println("Batas Waktu : "+larikBarang.barang.get(posisi).getBatas());
                        System.out.print("DATA DI ATAS YAKIN DIEDIT (y/t)? ");
                        char yakin = scChar.next().charAt(0);
                        if (yakin=='y'){
                            System.out.println("MASUKKAN DATA YANG DIEDIT: "); 
                            System.out.print("Nama Pengirim : "); 
                            Nama = scString.nextLine();
                            System.out.print("No Surat : "); 
                            NoSurat = scString.nextLine();
                            System.out.print("Perihal : "); 
                            Perihal = scString.nextLine();
                            System.out.print("Tanggal Surat (format dd-MM-yyyy) : "); 
                            tgl = scString.nextLine();
                            Tanggal = LocalDate.parse(tgl.substring(6, 10)+"-"+tgl.substring(3, 5)+"-"+tgl.substring(0, 2));
                            System.out.print("Batas Waktu (hari): ");
                            Batas = scInt.nextInt();
                            larikBarang.barang.get(posisi).setBarang(Nama, NoSurat, Perihal, Tanggal, Batas);
                            blmDisimpan = true;
                        }
                    } else {
                        System.out.println("DATA YANG AKAN DIHAPUS TIDAK ADA"); 
                    }
                    System.out.println();
                    break;   
                }
                
                case '3':{
                    System.out.println();
                    System.out.println("EDIT STATUS : ");
                    System.out.println("Masukkan no surat : ");
                    NoSurat = scString.nextLine();
                    
                    int posisi = larikBarang.cariBarang(NoSurat, larikBarang);
                    if (posisi < larikBarang.barang.size()){
                        System.out.println("Nama Pengirim : "+larikBarang.barang.get(posisi).getNama());
                        System.out.println("No Surat : "+larikBarang.barang.get(posisi).getNoSurat());
                        System.out.println("Posisi : "+larikBarang.barang.get(posisi).getPosisi());
                        System.out.println("Konfirmasi : "+larikBarang.barang.get(posisi).getKonfirmasi());
                        System.out.print("STATUS DI ATAS YAKIN DIEDIT (y/t)? ");
                        char yakin = scChar.next().charAt(0);
                        if (yakin=='y'){
                            System.out.println("MASUKKAN DATA YANG DIEDIT: "); 
                            System.out.print("Posisi : "); 
                            Posisi = scString.nextLine();
                            System.out.print("Konfirmasi : "); 
                            Konfirmasi = scString.nextLine();
                            larikBarang.barang.get(posisi).setPosisi(Posisi);
                            larikBarang.barang.get(posisi).setKonfirmasi(Konfirmasi);
                            blmDisimpan = true;
                        }
                    } else {
                        System.out.println("DATA STATUS YANG AKAN DIEDIT TIDAK ADA"); 
                    }
                    System.out.println();
                    break;
                    
                   
                }
                case '4':{
                    System.out.println();
                    System.out.println("RINCIAN DATA DARI SURAT ATAU DOKUMEN: "); 
                    System.out.print("Masukkan no surat : "); 
                    NoSurat = scString.nextLine();
                    System.out.println("-----------------------");
                    System.out.println("-----------------------");
                    int posisi = larikBarang.cariBarang(NoSurat, larikBarang);
                    if (posisi < larikBarang.barang.size()){
                        System.out.println("Nama Pengirim : "+larikBarang.barang.get(posisi).getNama());
                        System.out.println("No Surat : "+larikBarang.barang.get(posisi).getNoSurat());
                        LocalDate temp = larikBarang.barang.get(posisi).getTanggal();
                        tempTgl = temp.format(formatTanggal);
                        System.out.println("Tanggal Surat  : "+tempTgl);
                        System.out.println("Posisi  : "+larikBarang.barang.get(posisi).getPosisi());
                        System.out.println("Konfirmasi : "+larikBarang.barang.get(posisi).getKonfirmasi());
                        System.out.println("Batas Waktu (hari) : "+larikBarang.barang.get(posisi).getBatas());
                        
                    } else {
                        System.out.println("DATA YANG DICARI TIDAK ADA"); 
                    }
                    System.out.println();
                    break;
                }
                case '5' :{
                    System.out.println();
                    System.out.println("HAPUS DATA SURAT ATAU DOKUMEN: "); 
                    System.out.print("Masukkan No Surat : "); 
                    NoSurat = scString.nextLine();
                    int posisi = larikBarang.cariBarang(NoSurat, larikBarang);
                    if (posisi < larikBarang.barang.size()){
                        System.out.println("Nama Pengirim : "+larikBarang.barang.get(posisi).getNama());
                        System.out.println("No Surat : "+larikBarang.barang.get(posisi).getNoSurat());
                        LocalDate temp = larikBarang.barang.get(posisi).getTanggal();
                        tempTgl = temp.format(formatTanggal);
                        System.out.println("Tanggal Surat  : "+tempTgl);
                        System.out.println("Posisi  : "+larikBarang.barang.get(posisi).getPosisi());
                        System.out.println("Konfirmasi : "+larikBarang.barang.get(posisi).getKonfirmasi());
                        System.out.println("Batas Waktu (hari): "+larikBarang.barang.get(posisi).getBatas());
                        System.out.print("DATA DI ATAS YAKIN DIHAPUS (y/t)? ");
                        char yakin = scChar.next().charAt(0);
                        if (yakin=='y'){
                            larikBarang.barang.remove(posisi);
                            blmDisimpan = true;
                        }
                    } else {
                        System.out.println("DATA YANG AKAN DIHAPUS TIDAK ADA"); 
                    }
                    System.out.println();
                    break;

                }
                case '6':{
                    System.out.println("SIMPAN DATA KE XML "); 

                    System.out.println();
                    larikBarang.saveDataToXML(larikBarang);
                    blmDisimpan = false;
                    break;
                }
                case '7':{
                    System.out.println("LIHAT PESAN "); 
                    System.out.println();
                    for (int a=0; a<larikPesan.pesan.size(); a++){
                        System.out.println("Pengirim  : " + larikPesan.pesan.get(a).getNama());
                        System.out.println("Pesan  : " + larikPesan.pesan.get(a).getPesan());
                        System.out.println("-------------------------------------------------");
                    }
                    break;
                }
                
                default : {
                        if (blmDisimpan) {
                           System.out.print("TERJADI PERUBAHAN DATA, MAU DISIMPAN (y/t)? "); 
                           pilih = scChar.next().toString().charAt(0);
                           if (pilih=='y'){
                               larikBarang.saveDataToXML(larikBarang);
                           }
                        }
                        menu = false;
                } 
            }
            
        }
        
        

    }
    
}
