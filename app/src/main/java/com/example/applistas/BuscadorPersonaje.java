package com.example.applistas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONObject;

public class BuscadorPersonaje extends AppCompatActivity {


    RequestQueue requestQueue;
    final String URL = "https://dragonball-api.com/api/characters/";
    EditText edtIdPersonaje, edtNombre, edtKi, edtRaza, edtGenero;
    Button btnBuscarPersonaje;

    private void loadUI(){
        edtIdPersonaje = findViewById(R.id.edtIdPersonaje);
        edtNombre = findViewById(R.id.edtNombre);
        edtKi = findViewById(R.id.edtKi);
        edtRaza = findViewById(R.id.edtRaza);
        edtGenero = findViewById(R.id.edtGenero);
        btnBuscarPersonaje = findViewById(R.id.btnBuscarPersonaje);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscador_personaje);

        this.loadUI();

        //eventos
        btnBuscarPersonaje.setOnClickListener(v -> {getDataChacaracter();});
    } //on create

    private void getDataChacaracter(){
        //Comunicacion Dragon ball API
        if (edtIdPersonaje.getText().toString().isEmpty()){
            edtIdPersonaje.setError("Escriba un id");
            edtIdPersonaje.requestFocus();
            return;
        }

        String endPoint = URL + edtIdPersonaje.getText().toString(); //Se agrga el ID
        //ABrir canal de comunicacion
        requestQueue = Volley.newRequestQueue(this);

        //¿QUE TIPO DE DATOS ME DEVUELEVE EL API?
        //Volley las solicitudes tienen 5 partes
        //verbo, url, JSONenviado, resultado, error
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                endPoint,
                null,
                this::showData,
                this::errorws

        );

        //enviamos la solicitud
        requestQueue.add(jsonObjectRequest);

    }

    //this::showData() se activa cuando el servicio retorna 2XX

    private void showData(JSONObject jsonObject){
        Log.d("Resultados WS", jsonObject.toString());
        try{
            edtNombre.setText(jsonObject.getString("name"));
            edtKi.setText(jsonObject.getString("Ki"));
            edtRaza.setText(jsonObject.getString("race"));
            edtGenero.setText(jsonObject.getString("gender"));
        }catch (Exception e){
            Log.e("ErrorJson", e.toString());
        }
    }

    //this::error() se activa con respuesta 4XX

    private void errorws(VolleyError error){
       // Log.e("Error de WS", error.toString());

        //Para gestionar errores , necesitamos de un objeto
        NetworkResponse response = error.networkResponse;

        if(response != null && response.data != null){
            //¿cual es el codigo de error
            int statusCode = response.statusCode;

            //No lo encontramos
            if (statusCode ==400){
                String dataError = new String(response.data);
                JSONObject jsonError = new JSONObject(dataError);
                Toast.makeText(getApplicationContext(), jsonError.getString("message"), Toast.LENGTH_SHORT).show();
                Log.e("Error de WS", dataError );
            }

        }
    }

} //Buscar personajes