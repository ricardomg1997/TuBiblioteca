package co.com.uco.tubiblioteca.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.uco.tubiblioteca.persisntence.Tabla;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = Tabla.LIBRO)
@NoArgsConstructor
public class Book {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idLibro")
    private  Integer id;
    @ColumnInfo(name = "nombre")
   private String nombre;
    @ColumnInfo(name = "identificacion")
   private String identificacion;
    @ColumnInfo(name = "celular")
   private String celular;
    @ColumnInfo(name = "libro")
     private   String libro;
    @ColumnInfo(name = "fecha")
    private    String fecha;
}
