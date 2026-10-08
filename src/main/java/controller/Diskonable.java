package controller;

public interface Diskonable {
    double hitungTotal(int hargaPerJam, int lamaRental);
    double hitungTotal(int hargaPerJam, int lamaRental, double diskon); // Overloading
}