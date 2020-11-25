package co.com.uco.tubiblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;


import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;

public class MainActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        ImageButton btnCreateLoan = findViewById(R.id.nav_createPage);
        ImageButton btnPrestamos = findViewById(R.id.nav_listPage);
        ImageButton btnUsuario = findViewById(R.id.nav_userPage);

        btnCreateLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateLoanActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btnPrestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaLibroActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UsuarioActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        int images[] = {R.drawable.harry, R.drawable.anillos, R.drawable.after};
        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images) {
            flipperImages(image);
        }
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.home));
    }
}