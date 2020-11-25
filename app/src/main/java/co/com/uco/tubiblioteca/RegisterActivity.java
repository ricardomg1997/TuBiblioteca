package co.com.uco.tubiblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.com.uco.tubiblioteca.dao.UsuarioDao;
import co.com.uco.tubiblioteca.dto.Usuario;
import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ActionBarUtil actionBarUtil;
    EditText usuario;
    EditText password;
    EditText nombre;
    EditText apellido;
    Button btnRegistrar;
    Button btnCancelar;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();

        usuario = (EditText) findViewById(R.id.RegUser);
        password = (EditText) findViewById(R.id.RegPassword);
        nombre = (EditText) findViewById(R.id.RegNombre);
        apellido = (EditText) findViewById(R.id.RegApellido);
        btnRegistrar = (Button) findViewById(R.id.btnCreateUser);
        btnCancelar = (Button) findViewById(R.id.btnCancel);
        btnRegistrar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        usuarioDao = new UsuarioDao(this);

    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.Register));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreateUser:
                Usuario u = new Usuario();
                u.setUsuario(usuario.getText().toString());
                u.setPassword(password.getText().toString());
                u.setNombre(nombre.getText().toString());
                u.setApellidos(apellido.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(RegisterActivity.this, "Error: Campos vacíos", Toast.LENGTH_LONG).show();
                }
                else if(usuarioDao.insertUser(u)){
                    Toast.makeText(RegisterActivity.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Usuario ya registrado.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancel:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
