package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PengelolaanKaryawan {
    private final List<Karyawan> karyawanList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public static void main(String[] args) {
        new PengelolaanKaryawan().menuUtama();
    }

    private String generateUniqueId() {
        String id;
        boolean isUnique;
        do {
            id = "" + (char) (random.nextInt(26) + 'A') + (char) (random.nextInt(26) + 'A') + "-" + (random.nextInt(9000) + 1000);
            isUnique = true;
            for (Karyawan k : karyawanList) {
                if (k.getKodeKaryawan().equals(id)) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);
        return id;
    }

    private void menuUtama() {
        while (true) {
            System.out.println("Menu Utama:");
            System.out.println("1. Insert Data Karyawan");
            System.out.println("2. View Data Karyawan");
            System.out.println("3. Update Data Karyawan");
            System.out.println("4. Delete Data Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int menu;
            try {
                menu = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
                continue;
            }

            switch (menu) {
                case 1:
                    insertDataKaryawan();
                    break;
                case 2:
                    viewDataKaryawan();
                    break;
                case 3:
                    updateDataKaryawan();
                    break;
                case 4:
                    deleteDataKaryawan();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid, silakan pilih lagi.");
            }
        }
    }
    
    private void berikanBonus(String jabatan) {
        int jumlah = 0;
        for (Karyawan k : karyawanList) {
            if (k.getJabatan().equals(jabatan)) {
                jumlah++;
            }
        }

        if (jumlah % 3 == 0) {
            double bonusRate;
            switch (jabatan) {
                case "Manager":
                    bonusRate = 0.10;
                    break;
                case "Supervisor":
                    bonusRate = 0.075;
                    break;
                case "Admin":
                    bonusRate = 0.05;
                    break;
                default:
                    return;
            }

            for (int i = karyawanList.size() - 1; i >= 0 && jumlah > 1; i--) {
                Karyawan k = karyawanList.get(i);
                if (k.getJabatan().equals(jabatan)) {
                    double gajiLama = k.getGaji();
                    double bonus = gajiLama * bonusRate;
                    k.setGaji(gajiLama + bonus);
                    jumlah--;
                }
            }
        }
    }


    private void insertDataKaryawan() {
        System.out.print("Input nama karyawan [>= 3]: ");
        String nama = scanner.nextLine();
        if (nama.length() < 3) {
            System.out.println("Nama karyawan harus minimal 3 karakter.");
            return;
        }

        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
        String jenisKelamin = scanner.nextLine();
        if (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan")) {
            System.out.println("Jenis kelamin harus 'Laki-laki' atau 'Perempuan'.");
            return;
        }

        System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
        String jabatan = scanner.nextLine();
        if (!Arrays.asList("Manager", "Supervisor", "Admin").contains(jabatan)) {
            System.out.println("Jabatan harus 'Manager', 'Supervisor', atau 'Admin'.");
            return;
        }

        double gaji = 0;
        switch (jabatan) {
            case "Manager":
                gaji = 8000000;
                break;
            case "Supervisor":
                gaji = 6000000;
                break;
            case "Admin":
                gaji = 4000000;
                break;
        }

        String kodeKaryawan = generateUniqueId();
        Karyawan karyawanBaru = new Karyawan(kodeKaryawan, nama, jenisKelamin, jabatan, gaji);
        karyawanList.add(new Karyawan(kodeKaryawan, nama, jenisKelamin, jabatan, gaji));
        berikanBonus(jabatan);
        System.out.println("Berhasil menambahkan karyawan dengan id " + kodeKaryawan);
    }

    private Karyawan findKaryawanByKode(String kode) {
        for (Karyawan k : karyawanList) {
            if (k.getKodeKaryawan().equals(kode)) {
                return k;
            }
        }
        return null;
    }

    private void updateDataKaryawan() {
        System.out.print("Masukkan kode karyawan yang ingin diupdate: ");
        String kode = scanner.nextLine();
        Karyawan karyawan = findKaryawanByKode(kode);

        if (karyawan == null) {
            System.out.println("Karyawan dengan kode " + kode + " tidak ditemukan.");
            return;
        }

        System.out.println("Mengupdate data untuk: " + karyawan.getNama());
        System.out.println("Tekan 'skip' jika tidak ingin mengupdate data tersebut.");

        System.out.print("Nama [" + karyawan.getNama() + "]: ");
        String nama = scanner.nextLine();
        if (!nama.equals("skip") && !nama.isEmpty()) {
            if (nama.length() >= 3) {
                karyawan.setNama(nama);
            } else {
                System.out.println("Nama harus minimal 3 karakter.");
            }
        }

        System.out.print("Jenis Kelamin [Laki-laki/Perempuan] (" + karyawan.getJenisKelamin() + "): ");
        String jenisKelamin = scanner.nextLine();
        if (!jenisKelamin.equals("skip") && !jenisKelamin.isEmpty()) {
            if (jenisKelamin.equals("Laki-laki") || jenisKelamin.equals("Perempuan")) {
                karyawan.setJenisKelamin(jenisKelamin);
            } else {
                System.out.println("Jenis kelamin harus 'Laki-laki' atau 'Perempuan'.");
            }
        }

        System.out.print("Jabatan [Manager/Supervisor/Admin] (" + karyawan.getJabatan() + "): ");
        String jabatan = scanner.nextLine();
        if (!jabatan.equals("skip") && !jabatan.isEmpty()) {
            if (Arrays.asList("Manager", "Supervisor", "Admin").contains(jabatan)) {
                karyawan.setJabatan(jabatan);
                double gaji;
                switch (jabatan) {
                    case "Manager":
                        gaji = 8000000;
                        break;
                    case "Supervisor":
                        gaji = 6000000;
                        break;
                    case "Admin":
                        gaji = 4000000;
                        break;
                    default:
                        return;
                }
                karyawan.setGaji(gaji);
                System.out.println("Data berhasil diupdate.");
            } else {
                System.out.println("Jabatan harus 'Manager', 'Supervisor', atau 'Admin'.");
            }
        }
    }

    private void deleteDataKaryawan() {
        System.out.print("Masukkan kode karyawan yang ingin dihapus: ");
        String kode = scanner.nextLine();
        Karyawan karyawan = findKaryawanByKode(kode);

        if (karyawan != null) {
            karyawanList.remove(karyawan);
            System.out.println("Karyawan dengan kode " + kode + " berhasil dihapus.");
        } else {
            System.out.println("Karyawan dengan kode " + kode + " tidak ditemukan.");
        }
    }

    private void viewDataKaryawan() {
        if (karyawanList.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
            return;
        }

        karyawanList.sort(Comparator.comparing(Karyawan::getNama));
        for (Karyawan k : karyawanList) {
            System.out.println("Kode Karyawan: " + k.getKodeKaryawan());
            System.out.println("Nama Karyawan: " + k.getNama());
            System.out.println("Jenis Kelamin: " + k.getJenisKelamin());
            System.out.println("Jabatan: " + k.getJabatan());
            System.out.println("Gaji: Rp" + k.getGaji());
            System.out.println("----------------------------");
        }
    }
}
