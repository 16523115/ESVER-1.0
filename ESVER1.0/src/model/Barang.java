/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author lowlight
 */
public class Barang {
    String nama = null;
    String noSurat = null;
    String perihal = null;
    LocalDate tanggal = LocalDate.parse("0000-01-01");
    String posisi = null;
    String konfirmasi = null;    
    int batas = 0;
    
    public Barang(String _nama, String _noSurat,  String _perihal,
            LocalDate _tanggal, String _posisi, String Konfirmasi, int _batas)
    {
        this.noSurat = _noSurat;
        this.nama = _nama;
        this.perihal = _perihal;
        this.tanggal = _tanggal;
        this.batas = _batas;
        this.konfirmasi = "Belum dikonfirmasi";
        this.posisi = "DAUH";
    }
    
    public void setBarang(String _nama, String _noSurat, String _perihal,
            LocalDate _tanggal, int _batas)
    {
        this.noSurat = _noSurat;
        this.nama = _nama;
        this.perihal = _perihal;
        this.tanggal = _tanggal;
        this.batas = _batas;
    }    
    
    public String getNama()
    {
        return nama;
    }    
    public String getNoSurat()
    {
        return noSurat;
    }
    public String getPerihal()
    {
        return perihal;
    }
    public String getKonfirmasi()
    {
        return konfirmasi;
    }
    public void setKonfirmasi(String x){
        this.konfirmasi = x;
    }
    public int getBatas()
    {
        return batas;
    }
    public String getPosisi()
    {
        return posisi;
    }
    public void setPosisi(String pos){
        posisi = pos;
    }
    public LocalDate getTanggal()
    {
        return tanggal;
    }
}
