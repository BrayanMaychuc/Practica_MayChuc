package com.maychuc.practica_maychuc;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class registro extends AppCompatActivity {
    private EditText etxtusuario, etxtpassw, etxtvalidar;
    private Button btnGuardar;
    private int PERMISO_REQUERIDO=11;
    private static Credenciales archivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etxtusuario = findViewById(R.id.etxtCuser);
        etxtpassw =findViewById(R.id.etxtCpassw);
        etxtvalidar = findViewById(R.id.etxtCvalidar);
        btnGuardar= findViewById(R.id.btnRegistrar);
        archivo = new Credenciales(getApplicationContext().getExternalFilesDir(null).getPath());
        setPermiso();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etxtpassw.getText().toString().isEmpty() |
                        etxtvalidar.getText().toString().isEmpty() || etxtusuario.getText().toString().isEmpty
                        ()) {
                    Toast.makeText(getApplicationContext(), "No deje campos vacíos",
                            Toast.LENGTH_SHORT).show();
                } else if
                (archivo.getValidaPassUs(etxtpassw.getText().toString().trim(), etxtvalidar.getText().
                                toString().trim()) == false) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas son diferentes",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (archivo.getDuplicado(etxtusuario.getText().toString()) == true) {
                    Toast.makeText(getApplicationContext(), "El usuario se encuentra en la BD",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int resultado =
                            archivo.setInsertarCredenciales(etxtusuario.getText().toString(), etxtpassw.getText(
                            ).toString(), getApplicationContext());
                    if (resultado == 1) {
                        finish();
                        Toast.makeText(getApplicationContext(), "El usuario se registró con éxito", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public void setPermiso() {
        int permisosd = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permisosdescribir = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permisosd != getPackageManager().PERMISSION_GRANTED || permisosdescribir
                != getPackageManager().PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new
                        String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISO_REQUERIDO);
            }
        }
    }
}
