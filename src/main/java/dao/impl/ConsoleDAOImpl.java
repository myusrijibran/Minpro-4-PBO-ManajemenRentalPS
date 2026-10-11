package dao.impl;

import config.DatabaseConfig;
import dao.ConsoleDAO;
import model.Console;
import model.PSReguler;
import model.PSVIP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleDAOImpl implements ConsoleDAO {

    @Override
    public List<Console> getAll() {
        List<Console> list = new ArrayList<>();
        String sql = "SELECT * FROM console";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String id = rs.getString("id");
                String jenis = rs.getString("jenis");
                int harga = rs.getInt("harga_per_jam");
                String tipe = rs.getString("tipe_layanan");

                if ("REGULER".equalsIgnoreCase(tipe)) {
                    int stik = rs.getInt("jumlah_stik");
                    list.add(new PSReguler(id, jenis, harga, stik));
                } else if ("VIP".equalsIgnoreCase(tipe)) {
                    String fasilitas = rs.getString("fasilitas_vip");
                    list.add(new PSVIP(id, jenis, harga, fasilitas));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(Console c) {
        String sql = "INSERT INTO console (id, jenis, harga_per_jam, tipe_layanan, jumlah_stik, fasilitas_vip) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, c.getId());
            stmt.setString(2, c.getJenis());
            stmt.setInt(3, c.getHargaPerJam());

            if (c instanceof PSReguler) {
                PSReguler reg = (PSReguler) c;
                stmt.setString(4, "REGULER");
                stmt.setInt(5, reg.getJumlahStik());
                stmt.setNull(6, Types.VARCHAR);
            } else if (c instanceof PSVIP) {
                PSVIP vip = (PSVIP) c;
                stmt.setString(4, "VIP");
                stmt.setNull(5, Types.INTEGER);
                stmt.setString(6, vip.getFasilitasVIP());
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
