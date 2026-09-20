package com.example.applistas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuscadorPersonaje extends AppCompatActivity {

    final String URL = "https://dragonball-api.com/api/characters/";

    EditText edtIdPersonaje;
    Button btnBuscarPersonaje, btnReiniciar, btnTransformaciones;
    LinearLayout layoutResultado, layoutTransformaciones, contenedorTransformaciones;
    ImageView imgPersonaje;
    TextView txtNombre, txtKi, txtRaza, txtGenero;

    ArrayList<String> listaTransformaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscador_personaje);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtIdPersonaje            = findViewById(R.id.edtIdPersonaje);
        btnBuscarPersonaje        = findViewById(R.id.btnBuscarPersonaje);
        btnReiniciar              = findViewById(R.id.btnReiniciar);
        btnTransformaciones       = findViewById(R.id.btnTransformaciones);
        layoutResultado           = findViewById(R.id.layoutResultado);
        layoutTransformaciones    = findViewById(R.id.layoutTransformaciones);
        contenedorTransformaciones= findViewById(R.id.contenedorTransformaciones);
        imgPersonaje              = findViewById(R.id.imgPersonaje);
        txtNombre                 = findViewById(R.id.txtNombre);
        txtKi                     = findViewById(R.id.txtKi);
        txtRaza                   = findViewById(R.id.txtRaza);
        txtGenero                 = findViewById(R.id.txtGenero);

        btnBuscarPersonaje.setOnClickListener(v -> buscarPersonaje());
        btnReiniciar.setOnClickListener(v -> resetUI());
        btnTransformaciones.setOnClickListener(v -> mostrarTransformaciones());
    }

    private void buscarPersonaje() {
        String id = edtIdPersonaje.getText().toString().trim();
        if (id.isEmpty()) {
            edtIdPersonaje.setError("Escriba un ID");
            edtIdPersonaje.requestFocus();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL + id,
                null,
                this::mostrarDatos,
                this::manejarError
        );

        queue.add(request);
    }

    private void mostrarDatos(JSONObject json) {
        try {
            txtNombre.setText(json.optString("name", "-"));
            txtKi.setText(json.optString("ki", "-"));
            txtRaza.setText(json.optString("race", "-"));
            txtGenero.setText(json.optString("gender", "-"));

            // Imagen del personaje
            String urlImagen = json.optString("image", "");
            if (!urlImagen.isEmpty()) {
                Glide.with(this).load(urlImagen).into(imgPersonaje);
            }

            // Leer transformaciones del JSON
            listaTransformaciones.clear();
            JSONArray transformaciones = json.optJSONArray("transformations");
            if (transformaciones != null) {
                for (int i = 0; i < transformaciones.length(); i++) {
                    JSONObject t = transformaciones.getJSONObject(i);
                    listaTransformaciones.add(t.optString("name", "Desconocido"));
                }
            }

            // Mostrar boton solo si tiene transformaciones
            if (listaTransformaciones.isEmpty()) {
                btnTransformaciones.setVisibility(View.GONE);
            } else {
                btnTransformaciones.setVisibility(View.VISIBLE);
            }

            // Ocultar lista hasta que el usuario presione el boton
            layoutTransformaciones.setVisibility(View.GONE);

            layoutResultado.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void mostrarTransformaciones() {
        // Limpiar lista anterior
        contenedorTransformaciones.removeAllViews();

        // Agregar cada nombre como TextView en el UI
        for (String nombre : listaTransformaciones) {
            TextView tv = new TextView(this);
            tv.setText("- " + nombre);
            tv.setTextSize(15);
            tv.setPadding(0, 4, 0, 4);
            contenedorTransformaciones.addView(tv);
        }

        layoutTransformaciones.setVisibility(View.VISIBLE);
    }

    private void manejarError(VolleyError error) {
        String mensaje = "Personaje no encontrado";
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null) {
            try {
                JSONObject jsonError = new JSONObject(new String(response.data));
                if (jsonError.has("message")) {
                    mensaje = jsonError.getString("message");
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        resetUI();
    }

    private void resetUI() {
        edtIdPersonaje.setText("");
        edtIdPersonaje.setError(null);
        layoutResultado.setVisibility(View.GONE);
        layoutTransformaciones.setVisibility(View.GONE);
        contenedorTransformaciones.removeAllViews();
        imgPersonaje.setImageDrawable(null);
        txtNombre.setText("");
        txtKi.setText("");
        txtRaza.setText("");
        txtGenero.setText("");
        btnTransformaciones.setVisibility(View.GONE);
        listaTransformaciones.clear();
        edtIdPersonaje.requestFocus();
    }
}