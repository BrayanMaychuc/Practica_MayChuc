package com.maychuc.practica_maychuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnValidar, btnregistro;
    private EditText etxtpass, etxtusu;
    private Credenciales archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnValidar= findViewById(R.id.bntCvalidar);
        btnregistro= findViewById(R.id.btnCregistro);
        etxtusu= findViewById(R.id.etxtCusuario);
        etxtpass= findViewById(R.id.etxtCpassword);
        
        archivo = new Credenciales(getApplicationContext().getExternalFilesDir(null).getPath());

        btnregistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    Intent i = new Intent(getApplicationContext(), registro.class);
                    startActivity(i);
            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etxtusu.getText().toString().isEmpty() |
                        etxtpass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"No deje campos vacíos",
                            Toast.LENGTH_SHORT).show();
                }
                else if
                (archivo.getValidacion(etxtusu.getText().toString(),etxtpass.getText().toString()
                        )) {
                    Toast.makeText(getApplicationContext(),"Usuario válido",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),archivo.getEncriptar(etxtpass.getText().toString()),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"El usuario no está registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}