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
        Toast.makeText(this, "MediaPlayer Played", Toast.LENGTH_SHORT).show();
    }


    public void pause(View v) {
        if (player != null) {
            player.pause();
            Toast.makeText(this, "MediaPlayer Paused", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    public void loop(View v) {
        loop = (EditText) findViewById(R.id.loop);
        int n = Integer.parseInt(loop.getText().toString());

        if (loop == null)
            n = 0;

        while (n != 0) {
            players();
            Toast.makeText(this, "Music in Loop", Toast.LENGTH_SHORT).show();
            n--;
        }
    }

    private void players(){
            if (player == null) {
                player = MediaPlayer.create(this, R.raw.tone);
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopPlayer();
                        player = null;
                    }
                });
            }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
