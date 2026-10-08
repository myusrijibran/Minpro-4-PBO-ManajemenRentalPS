package controller;

import java.util.ArrayList;
import model.Rental;

public class RentalController implements Diskonable {
    private final ArrayList<Rental> daftarRental = new ArrayList<>();

    public void tambahRental(Rental r) {
        daftarRental.add(r);
    }

    public ArrayList<Rental> getDaftarRental() {
        return daftarRental;
    }

    @Override
    public double hitungTotal(int hargaPerJam, int lamaRental) {
        return hargaPerJam * lamaRental;
    }

    @Override
    public double hitungTotal(int hargaPerJam, int lamaRental, double diskon) {
        double subtotal = hargaPerJam * lamaRental;
        return subtotal - (subtotal * diskon);
    }
}
