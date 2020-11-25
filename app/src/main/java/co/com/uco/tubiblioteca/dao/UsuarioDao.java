package co.com.uco.tubiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.com.uco.tubiblioteca.dto.Usuario;

public class UsuarioDao {
    Context context;
    Usuario usuario;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="BdUsuarios";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, password text, nombre text, apellido text)";

    public UsuarioDao(Context context) {
        this.context = context;
        sql = context.openOrCreateDatabase(bd, context.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        usuario = new Usuario();
    }

    public boolean insertUser(Usuario usuario) {
        if(buscar(usuario.getUsuario()) == 0){
            ContentValues content = new ContentValues();
            content.put("usuario", usuario.getUsuario());
            content.put("password", usuario.getPassword());
            content.put("nombre", usuario.getNombre());
            content.put("apellido", usuario.getApellidos());
            return (sql.insert("usuario", null, content)>0);
        }
        else{
            return false;
        }
    }

    public int buscar(String u){
        int x = 0;
        lista = selectUsers();
        for(Usuario user : lista){
            if (user.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsers(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cursor = sql.rawQuery("select * from usuario", null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setUsuario(cursor.getString(1));
                usuario.setPassword(cursor.getString(2));
                usuario.setNombre(cursor.getString(3));
                usuario.setApellidos(cursor.getString(4));
                lista.add(usuario);
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public int login(String usuario, String password){
        int auth = 0;
        Cursor cursor = sql.rawQuery("select * from usuario", null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                if(cursor.getString(1).equals(usuario) && cursor.getString(2).equals(password)){
                    auth++;
                }
            }while (cursor.moveToNext());
        }
        return auth;
    }

    public Usuario getUsuario(String usuario, String password){
        lista = selectUsers();
        for (Usuario user : lista){
            if (user.getUsuario().equals(usuario) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = selectUsers();
        for (Usuario user : lista) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

}
