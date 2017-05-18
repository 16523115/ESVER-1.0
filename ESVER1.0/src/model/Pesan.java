/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lowlight
 */
public class Pesan {
    String Nama;
    String Pesan;
    
    public Pesan(String _Nama, String _Pesan)
    {
        this.Nama = _Nama;
        this.Pesan = _Pesan;
    }

    public String getNama()
    {
        return Nama;
    }

    public String getPesan()
    {
        return Pesan;
    }
}
