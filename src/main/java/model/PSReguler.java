package model;

public class PSReguler extends Console {
    private final int jumlahStik;

    public PSReguler(String id, String jenis, int hargaPerJam, int jumlahStik) {
        super(id, jenis, hargaPerJam);
        this.jumlahStik = jumlahStik;
    }

    public int getJumlahStik() {
        return jumlahStik;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID           : " + getId());
        System.out.println("Tipe         : Reguler");
        System.out.println("Jenis PS     : " + getJenis());
        System.out.println("Harga/Jam    : Rp" + getHargaPerJam());
        System.out.println("Fasilitas    : " + jumlahStik + " Stik Standar");
    }
}