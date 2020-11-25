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
import co.com.uco.tubiblioteca.utilidad.Configuracion;

public class LoginActivity extends AppCompatActivity {

    EditText user;
    EditText password;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btnLogin = findViewById(R.id.btnLogin);
        final Button btnRegister = findViewById(R.id.btnRegister);

        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        usuarioDao = new UsuarioDao(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = user.getText().toString();
                String pass = password.getText().toString();
                if(usuario.equals("") && pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Error: Campos vacíos", Toast.LENGTH_LONG).show();
                }
                else if(usuarioDao.login(usuario,pass) == 1){
                    Usuario ux = usuarioDao.getUsuario(usuario,pass);
                    Toast.makeText(LoginActivity.this, "Ingreso autorizado.", Toast.LENGTH_LONG).show();
                    Configuracion.setUsuario(ux);
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("Id", ux.getId());
                    startActivityForResult(intent, 0);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }


}