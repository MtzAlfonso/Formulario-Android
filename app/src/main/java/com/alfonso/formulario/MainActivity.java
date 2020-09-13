package com.alfonso.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    /**
     * Se declaran de manera global para ser utilizadas en cualquier método dentro de la clase
     */
    private TextInputEditText nombre;
    private TextInputEditText telefono;
    private TextInputEditText email;
    private TextInputEditText descripcion;
    private int[] date;
    private DatePicker datePicker;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.tieFullname);
        telefono = findViewById(R.id.tiePhone);
        email = findViewById(R.id.tieEmail);
        descripcion = findViewById(R.id.tieDescription);
        datePicker = findViewById(R.id.dpCalendar);
        btnNext = findViewById(R.id.btnNext);

        try {
            Bundle params = getIntent().getExtras();

            nombre.setText(params.getString(getString(R.string.text_name)));
            telefono.setText(params.getString(getString(R.string.text_phone)));
            email.setText(params.getString(getString(R.string.text_email)));
            date = params.getIntArray("date");
            datePicker.updateDate(date[2], date[1], date[0]);
            nombre.setText(params.getString(getString(R.string.text_name)));
            descripcion.setText(params.getString(getString(R.string.text_description)));

        } catch (Exception ignored) {
        }

        /*
         * nos permite definir la fecha actual como fecha máxima a elegir
         */
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMaxDate(now);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = new int[]{datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear()};
                Intent intent = new Intent(MainActivity.this, ConfirmaDatos.class);
                intent.putExtra(getString(R.string.text_name), nombre.getEditableText().toString());
                intent.putExtra("date", date);
                intent.putExtra(getString(R.string.text_phone), telefono.getEditableText().toString());
                intent.putExtra(getString(R.string.text_email), email.getEditableText().toString());
                intent.putExtra(getString(R.string.text_description), descripcion.getEditableText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}