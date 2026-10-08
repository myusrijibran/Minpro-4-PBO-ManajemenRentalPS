package model;

public abstract class Console {
    private final String id;
    private String jenis;
    private int hargaPerJam;

    public Console(String id, String jenis, int hargaPerJam) {
        this.id = id;
        this.jenis = jenis;
        this.hargaPerJam = hargaPerJam;
    }

    public String getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setHargaPerJam(int hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }

    // Abstract method (Aturan Abstraction)
    public abstract void displayInfo();
}