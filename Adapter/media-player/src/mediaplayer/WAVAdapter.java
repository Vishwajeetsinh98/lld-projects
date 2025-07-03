package mediaplayer;

import legacy.mediaplayer.LegacyMediaPlayer;

public class WAVAdapter implements MediaPlayerAdapterInterface{
    private LegacyMediaPlayer legacyMediaPlayer;

    public WAVAdapter () {
        legacyMediaPlayer = new LegacyMediaPlayer();
    }

    public WAVAdapter(LegacyMediaPlayer lmp) {
        legacyMediaPlayer = lmp;
    }

    @Override
    public void play(String file) {
        legacyMediaPlayer.playWavFile(file);
    }
}
