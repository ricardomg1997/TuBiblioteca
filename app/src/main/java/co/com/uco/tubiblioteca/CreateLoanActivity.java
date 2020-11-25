package co.com.uco.tubiblioteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import co.com.uco.tubiblioteca.entities.Book;
import co.com.uco.tubiblioteca.persisntence.room.DataBaseHelper;
import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;
import co.com.uco.tubiblioteca.utilidad.Configuracion;

public class CreateLoanActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.input_layout_nombre)
    EditText nombres;
    EditText identificacion;
    EditText celular;
    EditText libro;
    EditText inputDate;
    Button btnCreateLoan;

    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_loan);
        initComponents();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nombres = (EditText) findViewById(R.id.inputNombre);
        identificacion = (EditText) findViewById(R.id.inputId);
        celular = (EditText) findViewById(R.id.inputPhone);
        libro = (EditText) findViewById(R.id.inputBook);
        inputDate = findViewById(R.id.inputDate);
        btnCreateLoan = findViewById(R.id.btnCreateLoan);
        db = DataBaseHelper.getDBMainThread(this);
        nombres.setText(Configuracion.getUsuario().getNombre());



        btnCreateLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarCampos();
                if(!validarCampos()){
                    Toast.makeText(CreateLoanActivity.this, "Todos los campos son requeridos", Toast.LENGTH_LONG).show();
                }else {
                        Book b = new Book();
                        b.setNombre(nombres.getText().toString());
                        b.setIdentificacion(identificacion.getText().toString());
                        b.setCelular(celular.getText().toString());
                        b.setLibro(libro.getText().toString());
                        b.setFecha(inputDate.getText().toString());

                        db.getBookDAO().insert(b);
                        Toast.makeText(CreateLoanActivity.this, "Prestamo creado correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateLoanActivity.this, MainActivity.class);
                        startActivity(intent);
                     }
            }
        });

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.inputDate:
                        showDatePickerDialog();
                        break;
                }
            }
        });

    }

    private boolean validarCampos(){
        boolean flag = true;
        if(nombres.getText().toString().trim().equals("")){
            nombres.setError("campo requerido");
            flag = false;
        }
        if(identificacion.getText().toString().trim().equals("")){
            identificacion.setError("Campo requerido");
            flag = false;
        }
        if(celular.getText().toString().trim().equals("")){
            celular.setError("Campo requerido");
           flag = false;
        }
        if(libro.getText().toString().trim().equals("")){
            libro.setError("Campo requerido");
            flag = false;
        }
        if(inputDate.getText().toString().trim().equals("")){
            inputDate.setError("Campo requerido");
            flag = false;
        }
        return flag;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                inputDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.create_loan));
    }

}