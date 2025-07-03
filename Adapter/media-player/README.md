# LLD Question: Media Player with Third-Party Audio Library (Adapter Pattern)
## Scenario:

You're building a Media Player Application that can play different audio formats. Your system expects all audio players to follow this interface:
```
public interface MediaPlayer {
    void play(String fileName);
}
```
## Existing Situation:

- You have support for playing .mp3 files natively.
- Now, you need to integrate a third-party library called "LegacyAudioPlayer" that only supports playing .wav files:
```
public class LegacyAudioPlayer {
    public void playWavFile(String wavFile) {
        System.out.println("Playing WAV file: " + wavFile);
    }
}
```
## Requirement:

- You cannot modify the LegacyAudioPlayer class.
- You want your system to support .wav files using an Adapter, without breaking the existing system.
- The client code should only interact with the MediaPlayer interface.

## Tasks for You:
- Design an Adapter that makes LegacyAudioPlayer conform to the MediaPlayer interface.
- Show how the client code can play both .mp3 and .wav files using the MediaPlayer interface.