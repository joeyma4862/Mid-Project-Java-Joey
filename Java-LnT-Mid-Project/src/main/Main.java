package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class Main {
    Scanner scan = new Scanner(System.in);

    ArrayList<Karyawan> dataKaryawan = new ArrayList<Karyawan>();
    ArrayList<String> dataBonus = new ArrayList<String>();

    public Main() {
        Karyawan karyawan1 = new Karyawan("GJ-1901", "Alicia Gabriella", "Perempuan", "Supervisor", 6000000);
        Karyawan karyawan2 = new Karyawan("AL-0991", "Calvin Nicholas", "Laki-laki", "Supervisor", 6000000);
        Karyawan karyawan3 = new Karyawan("ZZ-1123", "Felix Jonathan", "Laki-laki", "Manager", 8000000);
        Karyawan karyawan4 = new Karyawan("JO-9912", "Herman Kuding", "Laki-laki", "Supervisor", 6000000);
        dataKaryawan.add(karyawan1);
        dataKaryawan.add(karyawan2);
        dataKaryawan.add(karyawan3);
        dataKaryawan.add(karyawan4);

        int menu = 0;
        do {
            System.out.println("1. Insert data karyawan");
            System.out.println("2. View data karyawan");
            System.out.println("3. Update data karyawan");
            System.out.println("4. Delete data karyawan");
            System.out.println("5. Exit");
            System.out.print(">> ");
            menu = scan.nextInt();
            scan.nextLine();

            switch (menu) {
                case 1:
                    insertData();
                    break;
                case 2:
                    viewData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    deleteData();
                    break;
            }
        } while (menu != 5);
    }

    private void insertData() {
        System.out.print("Input nama karyawan [>= 3]: ");
        String nama = scan.nextLine();
        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
        String gender = scan.nextLine();
        System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
        String jabatan = scan.nextLine();
        int gaji = 0;
        if (jabatan.equals("Manager")) {
            gaji = 8000000;
        } else if (jabatan.equals("Supervisor")) {
            gaji = 6000000;
        } else if (jabatan.equals("Admin")) {
            gaji = 4000000;
        }
        Random rnd = new Random();
        char m1 = (char) ('A' + rnd.nextInt(26));
        char m2 = (char) ('A' + rnd.nextInt(26));
        int num = rnd.nextInt(10000);
        String id = String.format("%c%c-%04d", m1, m2, num);
        Karyawan newKaryawan = new Karyawan(id, nama, gender, jabatan, gaji);
        dataKaryawan.add(newKaryawan);
        System.out.println("Berhasil menambahkan karyawan baru dengan id " + id);
        scan.nextLine();
    }

    private void viewData() {
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        Collections.sort(dataKaryawan, Comparator.comparing(karyawan -> karyawan.name));
        for (int i = 0; i < dataKaryawan.size(); i++) {
            System.out.printf("|%4d|", i + 1);
            dataKaryawan.get(i).printData();
        }
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        System.out.println("ENTER to return");
        scan.nextLine();
    }

    private void updateData() {
    	System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        Collections.sort(dataKaryawan, Comparator.comparing(karyawan -> karyawan.name));
        for (int i = 0; i < dataKaryawan.size(); i++) {
            System.out.printf("|%4d|", i+1);
            dataKaryawan.get(i).printData();
        }
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        
        System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
        int urutan = scan.nextInt() - 1; scan.nextLine();
        
        Karyawan updatedData = dataKaryawan.get(urutan);
        
        if (urutan >= 0 && urutan < dataKaryawan.size()) {
            System.out.print("Input nama karyawan [>= 3]: ");
            String newNama = scan.nextLine();
            if(newNama.equals("0")) {
                newNama = dataKaryawan.get(urutan).name;
            } else {
                updatedData.name = newNama;
            }
            System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
            String newGender = scan.nextLine();
            if(newGender.equals("0")) {
                newGender = dataKaryawan.get(urutan).jenis;
            } else {
                updatedData.jenis = newGender;
            }
            System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
            String newJabatan = scan.nextLine();
            if(newJabatan.equals("0")) {
                newNama = dataKaryawan.get(urutan).posisi;
            } else {
                updatedData.posisi = newJabatan;
            }
            int newGaji = 0;
            if(newJabatan.equals("Manager")) {
                newGaji = 8000000;
            } else if(newJabatan.equals("Supervisor")) {
                newGaji = 6000000;
            } else if(newJabatan.equals("Admin")) {
                newGaji = 4000000;
            }
            updatedData.gajian = newGaji;
            System.out.println("Berhasil mengupdate data karyawan dengan id " + updatedData.kode);
        }
        System.out.println("ENTER to return");
        scan.nextLine();
    }

    private void deleteData() {
    	System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        Collections.sort(dataKaryawan, Comparator.comparing(karyawan -> karyawan.name));
        for (int i = 0; i < dataKaryawan.size(); i++) {
            System.out.printf("|%4d|", i+1);
            dataKaryawan.get(i).printData();
        }
        System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
        System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
        int urutanHapus = scan.nextInt() - 1; scan.nextLine();
        
        if (urutanHapus >= 0 && urutanHapus < dataKaryawan.size()) {
            dataKaryawan.remove(urutanHapus);
            System.out.println("Karyawan berhasil dihapus");
        } else {
            System.out.println("Karyawan gagal dihapus");
        }
        System.out.println("ENTER to return");
        scan.nextLine();
    }

    public static void main(String[] args) {
        new Main();
    }
}
