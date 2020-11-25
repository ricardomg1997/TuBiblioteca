package co.com.uco.tubiblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;
import co.com.uco.tubiblioteca.utilidad.Configuracion;

public class UsuarioActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    Button btnSalir;
    @BindView(R.id.txtNombreUsuario)
    public TextView txtNombreUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        ButterKnife.bind(this);
        initComponents();
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UsuarioActivity.this, "Sesión cerrada.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.usuario));
        txtNombreUsuario.setText(Configuracion.getUsuario().getUsuario());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
