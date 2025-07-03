package mediaplayer;

public class MediaPlayer {
    WAVAdapter wavAdapter = new WAVAdapter();
    public void play(String fileName) {
        if (fileName.endsWith(".wav"))
            wavAdapter.play(fileName);
        else
            System.out.println("Playing file: " + fileName);
    }
}