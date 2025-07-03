import mediaplayer.MediaPlayer;

public class Main {
    public static void main(String[] args) {
        MediaPlayer mp = new MediaPlayer();
        mp.play("test.mp3");
        mp.play("old.wav");
    }
}