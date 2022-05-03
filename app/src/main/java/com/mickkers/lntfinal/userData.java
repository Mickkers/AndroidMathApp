package com.mickkers.lntfinal;

public class userData {
    private String nama;
    private String idBimbel;

    public userData(){

    }

    public userData(String nama, String idBimbel){
        this.nama = nama;
        this.idBimbel = idBimbel;
    }

    public String getNama() {
        return nama;
    }

    public String getIdBimbel() {
        return idBimbel;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setIdBimbel(String idBimbel) {
        this.idBimbel = idBimbel;
    }
}
