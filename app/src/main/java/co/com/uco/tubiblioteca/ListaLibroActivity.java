package co.com.uco.tubiblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.uco.tubiblioteca.adapter.LibroAdapter;
import co.com.uco.tubiblioteca.entities.Book;
import co.com.uco.tubiblioteca.persisntence.room.DataBaseHelper;
import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;
import co.com.uco.tubiblioteca.utilidad.Configuracion;

public class ListaLibroActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.LVListaLibros)
    public ListView listViewLibros;
    private LibroAdapter libroAdapter;
    private List<Book> listaLibros;
    private DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libro);
        initComponents();
        loadInfo();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.libros_prestados));
        db = DataBaseHelper.getDBMainThread(this);

    }

    private void loadInfo(){
        listaLibros = db.getBookDAO().findByNameUser(Configuracion.getUsuario().getNombre());
        libroAdapter = new LibroAdapter(this, listaLibros);
        listViewLibros.setAdapter(libroAdapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}