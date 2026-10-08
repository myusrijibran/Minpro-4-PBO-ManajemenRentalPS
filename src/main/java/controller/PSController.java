package controller;

import java.util.ArrayList;
import model.Console;

public class PSController {
    private final ArrayList<Console> daftarConsole = new ArrayList<>();

    public void tambahConsole(Console c) {
        daftarConsole.add(c);
    }

    public ArrayList<Console> getDaftarConsole() {
        return daftarConsole;
    }

    public Console cariConsole(String id) {
        for (Console c : daftarConsole) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean hapusConsole(String id) {
        Console c = cariConsole(id);
        if (c != null) {
            daftarConsole.remove(c);
            return true;
        }
        return false;
    }
}