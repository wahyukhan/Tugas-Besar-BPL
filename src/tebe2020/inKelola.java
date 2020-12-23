package tebe2020;

import java.sql.SQLException;
import java.util.LinkedList;

public interface inKelola<T> {
    abstract void menu(String menu);
    abstract void tambahData();
    abstract void editData();
    abstract void hapusData();
    abstract void tampilkanData();
    abstract void cariData();
    abstract Object search(String key) throws SQLException;
}
