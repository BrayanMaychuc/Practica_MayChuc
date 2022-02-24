package com.maychuc.practica_maychuc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Credenciales {

    //Declaramos las variables e importamos la libreria de File
    private static final String nombre = "credenciales.txt";
    private static String ruta = "";
    private static File archivo;


    public Credenciales(String ruta) {

        this.ruta = ruta;
        File crearArchivo = new File(ruta, nombre);
        this.archivo = crearArchivo;
    }

    public String getFilas() {
        String resultado = "";
        if (archivo.exists()) { //Si el archivo existe?
            //Gestionar algún error de la operación en android (Captura el error en
            //caso de darse), debe implementarse.
            //El try, cuando estamos trabajando con archivos de no ser implementado
            //el propio framework marca el error.
            try {
                //Variable de tipo InputStreamReader para poder traer el archivo,
                //abrirlo y leerlo
                InputStreamReader abrir = new InputStreamReader(new
                        FileInputStream(archivo));
                BufferedReader leer = new BufferedReader(abrir);
                String linea = leer.readLine();
                String completo = "";
                while (linea != null) {
                    completo = completo + linea + "'";
                    linea = leer.readLine();
                }
                return completo;
            } catch (IOException e) {
                return "";
            }
        } else {
            //En caso no existir el archivo se crea.
            try {
                //Crear archivos
                archivo.createNewFile();
                return "";
            } catch (IOException e) {
                return "";
            }
        }

    }

    public boolean getDuplicado(String usuario) {
        String credenciales = getFilas();
        if (!credenciales.equals("")) {
            for (int i = 0; i <= (credenciales.split("'").length - 1); i++) {
                String primeracoma = credenciales.split("'")[i];
                String segunda = primeracoma.split(",")[0];
                String us = segunda.split(":")[1];
                if (usuario.equals(us)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getValidacion(String user, String pass) {
        String datos = getFilas();
        String encUs = getEncriptar(user);
        String encPass = getEncriptar(pass);
        if (datos.isEmpty()) {
            return false;
        }
        for (int i = 0; i <= datos.split("'").length - 1; i++) {
            String cadena = datos.split("'")[i];
            String coma = cadena.split(",")[0];
            String coma2 = cadena.split(",")[1];
            String usuario = coma.split(":")[1];
            String password = coma2.split(":")[1];
            if ((encUs.equals(usuario)) && (encPass.equals(password))) {
                return true;
            }
        }
        return false;
    }

    public int setInsertarCredenciales(String us, String pass, Context context) {
        String encUs = getEncriptar(us);
        String encPass = getEncriptar(pass);
        try {
            FileWriter append = new FileWriter(archivo.getAbsolutePath(), true);
            BufferedWriter escribir = new BufferedWriter(append);
            escribir.write("user:" + encUs + ",pass:" + encPass + "\n");
            escribir.flush();
            escribir.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }


    }

    public boolean getValidaPassUs(String pass1, String pass2) {
        if ((pass1.trim()).equals((pass2.trim()))) {
            return true;
        } else {
            return false;
        }
    }

    public String getEncriptar(String valor) {
        //Lógica del algoritmo
        return "Valor encriptado";
    }

}

