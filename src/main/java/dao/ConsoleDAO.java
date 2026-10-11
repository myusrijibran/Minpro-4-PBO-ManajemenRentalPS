package dao;

import model.Console;
import java.util.List;

public interface ConsoleDAO {
    List<Console> getAll();
    boolean insert(Console c);
}