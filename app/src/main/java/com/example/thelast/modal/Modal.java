package com.example.thelast.modal;

public class Modal {
    private String arti, asma, audio, ayat, keterangan, nama, nomor, rukuk, type, urut;

    public Modal() {
    }

    public Modal(String arti, String type, String ayat, String asma, String audio, String nama, String nomor) {
        this.arti = arti;
        this.type = type;
        this.ayat = ayat;
        this.asma = asma;
        this.audio = audio;
        this.nama = nama;
        this.nomor = nomor;
    }

    public Modal(String arti, String audio, String keterangan) {
        this.arti = arti;
        this.audio = audio;
        this.keterangan = keterangan;
    }

    public Modal(String arti, String asma, String audio, String ayat, String keterangan, String nama, String nomor, String rukuk, String type, String urut) {
        this.arti = arti;
        this.asma = asma;
        this.audio = audio;
        this.ayat = ayat;
        this.keterangan = keterangan;
        this.nama = nama;
        this.nomor = nomor;
        this.rukuk = rukuk;
        this.type = type;
        this.urut = urut;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getRukuk() {
        return rukuk;
    }

    public void setRukuk(String rukuk) {
        this.rukuk = rukuk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrut() {
        return urut;
    }

    public void setUrut(String urut) {
        this.urut = urut;
    }
}