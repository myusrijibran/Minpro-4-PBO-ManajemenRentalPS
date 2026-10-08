package model;

public class PSVIP extends Console {
    private final String fasilitasVIP;

    public PSVIP(String id, String jenis, int hargaPerJam, String fasilitasVIP) {
        super(id, jenis, hargaPerJam);
        this.fasilitasVIP = fasilitasVIP;
    }

    public String getFasilitasVIP() {
        return fasilitasVIP;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID           : " + getId());
        System.out.println("Tipe         : VIP");
        System.out.println("Jenis PS     : " + getJenis());
        System.out.println("Harga/Jam    : Rp" + getHargaPerJam());
        System.out.println("Fasilitas VIP: " + fasilitasVIP);
    }
}
