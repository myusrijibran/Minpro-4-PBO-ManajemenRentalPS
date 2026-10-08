package view;

import controller.PSController;
import controller.RentalController;
import java.util.Scanner;
import model.*;

public class MainView {
    private final Scanner input = new Scanner(System.in);
    private final PSController psController = new PSController();
    private final RentalController rentalController = new RentalController();

    public void start() {
        int pilihan;
        do {
            System.out.println("\n=== SISTEM MANAJEMEN RENTAL PS ===");
            System.out.println("1. Tambah PS (Reguler/VIP)");
            System.out.println("2. Tampilkan Daftar PS");
            System.out.println("3. Hapus PS");
            System.out.println("4. Transaksi Rental PS");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            while (!input.hasNextInt()) {
                System.out.print("Input harus angka! Pilih menu: ");
                input.next();
            }
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> menuTambahPS();
                case 2 -> menuTampilkanPS();
                case 3 -> menuHapusPS();
                case 4 -> menuRentalPS();
                case 5 -> System.out.println("Terima kasih, program selesai.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private void menuTambahPS() {
        System.out.println("\n--- Tambah Unit PS ---");
        System.out.print("ID PS: ");
        String id = input.nextLine();

        System.out.println("Pilih Jenis PS:");
        System.out.println("1. PS3");
        System.out.println("2. PS4");
        System.out.println("3. PS5");
        System.out.print("Pilihan (1-3): ");
        int pJenis = input.nextInt();
        input.nextLine();

        String jenis = switch (pJenis) {
            case 1 -> "PS3";
            case 2 -> "PS4";
            case 3 -> "PS5";
            default -> null;
        };

        if (jenis == null) {
            System.out.println("Pilihan jenis PS tidak valid!");
            return;
        }

        System.out.print("Harga/Jam (Min Rp5000): ");
        int harga = input.nextInt();
        input.nextLine();

        if (harga < 5000) {
            System.out.println("Harga minimal adalah Rp5.000!");
            return;
        }

        System.out.println("Tipe Layanan:");
        System.out.println("1. Reguler");
        System.out.println("2. VIP");
        System.out.print("Pilihan (1-2): ");
        int tipe = input.nextInt();
        input.nextLine();

        if (tipe == 1) {
            System.out.print("Jumlah Stik Standar: ");
            int stik = input.nextInt();
            input.nextLine();
            psController.tambahConsole(new PSReguler(id, jenis, harga, stik));
        } else if (tipe == 2) {
            System.out.print("Fasilitas VIP (misal: Sofa Empuk + TV 4K): ");
            String fas = input.nextLine();
            psController.tambahConsole(new PSVIP(id, jenis, harga, fas));
        } else {
            System.out.println("Tipe tidak valid!");
            return;
        }

        System.out.println("PS Berhasil ditambahkan!");
    }

    private void menuTampilkanPS() {
        System.out.println("\n--- DAFTAR UNIT PS ---");
        if (psController.getDaftarConsole().isEmpty()) {
            System.out.println("Belum ada unit PS terdaftar.");
            return;
        }

        for (Console c : psController.getDaftarConsole()) {
            System.out.println("----------------------------");
            c.displayInfo(); // Polymorphism Overriding
        }
    }

    private void menuHapusPS() {
        System.out.print("\nMasukkan ID PS yang dihapus: ");
        String id = input.nextLine();
        if (psController.hapusConsole(id)) {
            System.out.println("Unit PS berhasil dihapus!");
        } else {
            System.out.println("ID PS tidak ditemukan.");
        }
    }

    private void menuRentalPS() {
        System.out.println("\n--- TRANSAKSI RENTAL ---");
        System.out.print("ID Pelanggan : ");
        String idPel = input.nextLine();
        System.out.print("Nama Pelanggan: ");
        String nama = input.nextLine();
        System.out.print("No Telepon    : ");
        String hp = input.nextLine();

        Pelanggan pel = new Pelanggan(idPel, nama, hp);

        System.out.print("ID PS dipinjam: ");
        String idPS = input.nextLine();
        Console c = psController.cariConsole(idPS);

        if (c == null) {
            System.out.println("Unit PS tidak ditemukan!");
            return;
        }

        System.out.print("Lama Rental (Jam): ");
        int lama = input.nextInt();
        input.nextLine();

        double total;
        if (lama >= 5) {
            System.out.println("Selamat! Anda mendapat diskon 10% karena rental >= 5 jam.");
            total = rentalController.hitungTotal(c.getHargaPerJam(), lama, 0.10); // Overloading
        } else {
            total = rentalController.hitungTotal(c.getHargaPerJam(), lama);
        }

        Rental r = new Rental("RNT-" + System.currentTimeMillis(), pel, c, lama, total);
        rentalController.tambahRental(r);

        System.out.println("\n=== DETAIL TRANSAKSI ===");
        System.out.println("Pelanggan : " + pel.getNama());
        System.out.println("Unit PS   : " + c.getJenis() + " (" + c.getId() + ")");
        System.out.println("Lama      : " + lama + " Jam");
        System.out.println("Total     : Rp" + total);
    }
}
