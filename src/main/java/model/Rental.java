package model;

public class Rental {
    private final String idRental;
    private final Pelanggan pelanggan;
    private final Console console;
    private final int lamaRental;
    private final double totalHarga;

    public Rental(String idRental, Pelanggan pelanggan, Console console, int lamaRental, double totalHarga) {
        this.idRental = idRental;
        this.pelanggan = pelanggan;
        this.console = console;
        this.lamaRental = lamaRental;
        this.totalHarga = totalHarga;
    }

    public String getIdRental() {
        return idRental;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public Console getConsole() {
        return console;
    }

    public int getLamaRental() {
        return lamaRental;
    }

    public double getTotalHarga() {
        return totalHarga;
    }
}
