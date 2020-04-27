package com.example.player;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.media.MediaPlayer;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    private EditText loop;
    int n;
    String path;
    Intent myfiles;
    TextView rePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loop = (EditText) findViewById(R.id.loop);
        loop.setText("1");
        rePath = (TextView) findViewById(R.id.filepath);
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

        player = MediaPlayer.create(this,R.raw.tone);
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

    public void file_select(View v){

        myfiles = new Intent(Intent.ACTION_GET_CONTENT);
        myfiles.setType("*/*");
        startActivityForResult(myfiles,10);

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case (10):
                if (resultCode==RESULT_OK){
                    path = data.getData().getPath();
                    rePath.setText(path);
                }
                break;
        }

    }
}
