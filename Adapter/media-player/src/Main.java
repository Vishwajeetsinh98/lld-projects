import mediaplayer.AudioPlayer;
import mediaplayer.MediaPlayer;

public class Main {
    public static void main(String[] args) {
        MediaPlayer mp = new AudioPlayer();
        mp.play("test.mp3");
        mp.play("old.wav");
    }
}