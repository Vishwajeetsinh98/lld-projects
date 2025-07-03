package mediaplayer;

public class AudioPlayer implements MediaPlayer {
    private final WAVAdapter wavAdapter = new WAVAdapter();

    @Override
    public void play (String fileName)  {
        if (fileName.endsWith(".wav"))
            wavAdapter.play(fileName);
        else
            System.out.println("Playing file: " + fileName);
    }
}
