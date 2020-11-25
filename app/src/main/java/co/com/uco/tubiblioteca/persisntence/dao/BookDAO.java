package co.com.uco.tubiblioteca.persisntence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.uco.tubiblioteca.entities.Book;


@Dao
public interface BookDAO {
    @Insert
    void insert(Book book);
    @Query("SELECT * FROM LIBRO WHERE nombre=:name")
    List<Book> findByNameUser(String name);
}
