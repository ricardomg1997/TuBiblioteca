package co.com.uco.tubiblioteca.utilidad;

import androidx.appcompat.app.AppCompatActivity;

public class ActionBarUtil2 {

    private final AppCompatActivity appCompatActivity;


    public ActionBarUtil2(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }




    public void setToolBar(String mensaje) {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            appCompatActivity.getSupportActionBar().setTitle(mensaje);
        }
    }
}
