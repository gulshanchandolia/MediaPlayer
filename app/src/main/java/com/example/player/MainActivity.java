package com.example.player;

        import androidx.appcompat.app.AppCompatActivity;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity<privete> extends AppCompatActivity {
    MediaPlayer player;
    private EditText loop;
    int paused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View v) {
        players();
    }

    public void pause(View v) {
        if (player != null) {
            player.pause();
            paused = player.getCurrentPosition();
            Toast.makeText(this, "Music Paused", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View v) {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    public void loop(View v) {
        loop = (EditText) findViewById(R.id.loop);
        int n = Integer.parseInt(loop.getText().toString());

        if (loop == null)
            n = 0;

        while (n != 0) {
            //players();
            Toast.makeText(this, "Music in Loop", Toast.LENGTH_SHORT).show();
            n--;
        }

    }

    private void players() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.tone);
            Toast.makeText(this, "Music Played", Toast.LENGTH_SHORT).show();
            player.start();
        }

        else if (!player.isPlaying()) {
                player.seekTo(paused);
                player.start();
        }
    }
}
