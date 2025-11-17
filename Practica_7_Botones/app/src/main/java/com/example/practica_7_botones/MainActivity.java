package com.example.practica_7_botones;

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
    private MediaPlayer soundPlayer; // Para los sonidos cortos

    private SeekBar seekBar;
    public TextView txtCancion, tvTiempoActual, tvTiempoTotal;
    private Button btnPlay, btnPausa, btnCJ, btnAlerta, btnGrito, btnBadumTss;
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

        // Inicializar botones de sonidos
        btnCJ = findViewById(R.id.button);
        btnAlerta = findViewById(R.id.button2);
        btnGrito = findViewById(R.id.button3);
        btnBadumTss = findViewById(R.id.button4);

        // Crear el reproductor para que cargue la canción
        mediaPlayer = MediaPlayer.create(this, R.raw.cancion);

        // Configurar duración total
        int duracion = mediaPlayer.getDuration();
        seekBar.setMax(duracion);
        tvTiempoTotal.setText(formatearTiempo(duracion));
        tvTiempoActual.setText(formatearTiempo(0)); // Inicializar en 0:00

        // Listener cuando la canción termine
        mediaPlayer.setOnCompletionListener(mp -> {
            // Reiniciar la posición al inicio
            mp.seekTo(0);
            seekBar.setProgress(0);
            tvTiempoActual.setText(formatearTiempo(0));
        });

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

        // Botones de sonidos cortos
        btnCJ.setOnClickListener(v -> reproducirSonido(R.raw.gta));
        btnAlerta.setOnClickListener(v -> reproducirSonido(R.raw.alerta));
        btnGrito.setOnClickListener(v -> reproducirSonido(R.raw.grito));
        btnBadumTss.setOnClickListener(v -> reproducirSonido(R.raw.tambor));

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

    // Método soundPLayer para reproducir sonidos
    private void reproducirSonido(int idSonido) {
        // Si hay un sonido reproduciéndose, liberarlo
        if (soundPlayer != null) {
            soundPlayer.release();
            soundPlayer = null;
        }

        // Crear nuevo MediaPlayer para el sonido
        soundPlayer = MediaPlayer.create(this, idSonido);
        soundPlayer.start();

        // Liberar recursos cuando termine el sonido
        soundPlayer.setOnCompletionListener(mp -> {
            mp.release();
            soundPlayer = null;
        });
    }

    // Actualizar SeekBar automáticamente
    private void actualizarSeekBar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        tvTiempoActual.setText(formatearTiempo(mediaPlayer.getCurrentPosition()));

        if (mediaPlayer.isPlaying()) {
            // Actualizar seekBar cada 100 milisegundos
            handler.postDelayed(this::actualizarSeekBar, 100);
        }
    }

    // Formatear tiempo formato normal minutos y segundos
    private String formatearTiempo(int milisegundos) {
        int minutos = (milisegundos / 1000) / 60;
        int segundos = (milisegundos / 1000) % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    // Cuando la actividad se destruye, liberar recursos
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (soundPlayer != null) {
            soundPlayer.release();
            soundPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}