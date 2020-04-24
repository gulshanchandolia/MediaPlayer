package com.example.player;

    import androidx.appcompat.app.AppCompatActivity;
    import android.media.MediaPlayer;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    private EditText loop;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loop = (EditText) findViewById(R.id.loop);
        loop.setText("1");
    }

//    public void pause(View v) {
//        if (player != null) {
//            player.pause();
//            Toast.makeText(this, "MediaPlayer Paused", Toast.LENGTH_SHORT).show();
//        }
//    }

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

    public void play(View v) {

        n = Integer.parseInt(loop.getText().toString());

        player = MediaPlayer.create(this, R.raw.tone);
        player.start();
        Toast.makeText(this, "MediaPlayer Played", Toast.LENGTH_SHORT).show();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (n>1) {
                    n--;
                    player.start();
                }
                else {
                    player.stop();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
