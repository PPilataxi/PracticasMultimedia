package com.example.json_monumentos;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMonumentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvMonumentos = findViewById(R.id.rvMonumentos);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8000/monumentos";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Monumento> arrayMonumentos = new ArrayList<>();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String nombre = jsonObject.getString("nombre");
                                String ubicacion = jsonObject.getString("ciudad");
                                String descripcion = jsonObject.getString("descripcion");
                                String imagen = jsonObject.has("imagen_url")
                                        ? jsonObject.getString("imagen_url")
                                        : "";

                                Monumento monumento = new Monumento(nombre, ubicacion, descripcion, imagen);
                                arrayMonumentos.add(monumento);
                            }

                            rvMonumentos.setHasFixedSize(true);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                            rvMonumentos.setLayoutManager(layoutManager);

                            AdaptadorMonumentos adaptadorMonumentos = new AdaptadorMonumentos(MainActivity.this, arrayMonumentos);
                            rvMonumentos.setAdapter(adaptadorMonumentos);
                            adaptadorMonumentos.refrescar();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Esto te dirá en el Logcat por qué no carga (Timeout, No Connection, etc.)
                        Log.e("VOLLEY", "Error en la petición: " + error.toString());
                    }
                });
                queue.add(jsonArrayRequest);

    }
}
