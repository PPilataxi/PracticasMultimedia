package com.example.practica_7_reproductorsonido;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    public TextView txtCancion, tvTiempoActual, tvTiempoTotal;
    private Button btnPlay, btnPausa;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        // Inicializar vistas
        txtCancion = findViewById(R.id.txtCancion);
        seekBar = findViewById(R.id.seekBar);
        tvTiempoActual = findViewById(R.id.tvTiempoActual);
        tvTiempoTotal = findViewById(R.id.tvTiempoTotal);
        btnPlay = findViewById(R.id.btnPlay);
        btnPausa = findViewById(R.id.btnPausa);

        // Crear el reproductor para que cargue la canción
        mediaPlayer = MediaPlayer.create(this, R.raw.cancion);

        // Configurar duración total
        int duracion = mediaPlayer.getDuration();
        seekBar.setMax(duracion);
        tvTiempoTotal.setText(formatearTiempo(duracion));

        // Botón Play
        btnPlay.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                actualizarSeekBar();
            }
        });

        // Botón Pausa
        btnPausa.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        // SeekBar para mover manualmente la canción
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    tvTiempoActual.setText(formatearTiempo(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // Actualizar SeekBar automáticamente
    private void actualizarSeekBar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        tvTiempoActual.setText(formatearTiempo(mediaPlayer.getCurrentPosition()));

        if (mediaPlayer.isPlaying()) {
            //actualizar seekBar cada 100 milisegundos
            handler.postDelayed(this::actualizarSeekBar, 100);
        }
    }

    // Formatear tiempo formato normal minutos y segudos
    private String formatearTiempo(int milisegundos) {
        int minutos = (milisegundos / 1000) / 60;
        int segundos = (milisegundos / 1000) % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    //Buscado en IA: Cuando la actividad se destruye, liberar recursos
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}