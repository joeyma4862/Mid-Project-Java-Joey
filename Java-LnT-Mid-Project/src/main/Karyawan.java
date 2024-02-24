package main;

public class Karyawan {
    public String kode;
    public String name;
    public String jenis;
    public String posisi;
    public int gajian;

    public Karyawan(String kode, String name, String jenis, String posisi, int gajian) {
        this.kode = kode;
        this.name = name;
        this.jenis = jenis;
        this.posisi = posisi;
        this.gajian = gajian;
    }

    public void printData() {
        System.out.printf("%17s| %29s| %14s| %13s| %12d|\n", this.kode, this.name, this.jenis, this.posisi, this.gajian);
    }
}
