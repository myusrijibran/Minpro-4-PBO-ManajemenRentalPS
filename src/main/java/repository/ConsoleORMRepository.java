package repository;

import config.DatabaseConfig;
import model.Console;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConsoleORMRepository {

    public boolean saveORM(Console console) {
        String sql = "INSERT INTO console (id, jenis, harga_per_jam, tipe_layanan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Class<?> clazz = console.getClass().getSuperclass();
            Field idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            
            stmt.setString(1, (String) idField.get(console));
            stmt.setString(2, console.getJenis());
            stmt.setInt(3, console.getHargaPerJam());
            stmt.setString(4, console.getClass().getSimpleName().replace("PS", "").toUpperCase());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
