package main;

import java.util.Date;

public class Karyawan {
    private final String kodeKaryawan;
    private String nama;
    private String jenisKelamin;
    private String jabatan;
    private double gaji;
    private final Date tanggalMasuk;

    public Karyawan(String kodeKaryawan, String nama, String jenisKelamin, String jabatan, double gaji) {
        this.kodeKaryawan = kodeKaryawan;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jabatan = jabatan;
        this.gaji = gaji;
        this.tanggalMasuk = new Date();
    }

    // Getter dan Setter
    public String getKodeKaryawan() {
        return kodeKaryawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }
}
