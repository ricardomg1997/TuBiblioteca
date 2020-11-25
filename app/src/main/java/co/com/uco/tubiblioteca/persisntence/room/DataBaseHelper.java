package co.com.uco.tubiblioteca.persisntence.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.uco.tubiblioteca.entities.Book;
import co.com.uco.tubiblioteca.persisntence.dao.BookDAO;

@Database(entities = {
        Book.class,}, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)

public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "libreria";
    private static DataBaseHelper instace;


    public static DataBaseHelper getSimpleDB(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return instace;
    }

    public static DataBaseHelper getDBMainThread(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instace;
    }


    /**
     * Listado de DAO
     */

    public abstract BookDAO getBookDAO();



}