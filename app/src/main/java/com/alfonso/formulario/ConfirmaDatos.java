package com.alfonso.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmaDatos extends AppCompatActivity {

    private TextView tvName;
    private TextView tvDate;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvDescription;
    private Button btnEditar;
    private String date;
    private Bundle params;
    private int[] dateArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_datos);

        btnEditar = findViewById(R.id.btnEditar);
        tvName = findViewById(R.id.tvName);
        tvDate = findViewById(R.id.tvDate);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescription = findViewById(R.id.tvDescription);

        params = getIntent().getExtras();

        dateArray = params.getIntArray("date");
        date = dateFormatter(dateArray[0], dateArray[1], dateArray[2]);
        tvName.setText(params.getString(getString(R.string.text_name)));
        tvDate.setText(date);
        tvPhone.setText(params.getString(getString(R.string.text_phone)));
        tvEmail.setText(params.getString(getString(R.string.text_email)));
        tvDescription.setText(params.getString(getString(R.string.text_description)));

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmaDatos.this, MainActivity.class);
                intent.putExtra(getString(R.string.text_name), tvName.getText().toString());
                intent.putExtra(getString(R.string.text_phone), tvPhone.getText().toString());
                intent.putExtra(getString(R.string.text_email), tvEmail.getText().toString());
                intent.putExtra("date", params.getIntArray("date"));
                intent.putExtra(getString(R.string.text_description), tvDescription.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ConfirmaDatos.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Método privado para dar formato a la fecha
     *
     * @param d Día
     * @param m Mes
     * @param y Año
     * @return Devuelve un String
     */
    private String dateFormatter(int d, int m, int y) {
        String date = d + " / " + (m + 1) + " / " + y;

        if (d < 10 && m < 9) {
            date = "0" + d + " / 0" + (m + 1) + " / " + y;
        } else if (d < 10) {
            date = "0" + d + " / " + (m + 1) + " / " + y;
        } else if (m < 9) {
            date = d + " / 0" + (m + 1) + " / " + y;
        }
        return date;
    }

}