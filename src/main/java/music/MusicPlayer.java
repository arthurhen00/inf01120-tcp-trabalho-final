package music;

import java.io.File;
import java.io.IOException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MusicPlayer {

    private int i = 1;
    private Pattern pattern;

    public MusicPlayer() {

    }

    public void play(String finalMusicText) {
        new Thread(() -> {
            Player player = new Player();
            pattern = new Pattern(finalMusicText);
            player.play(pattern);
        }).start();
    }

    public void generateMidi() {
        File file = new File(System.getProperty("user.dir") + "/music (" + i + ").mid");
        i++;
        try{
            org.jfugue.midi.MidiFileManager.savePatternToMidi(pattern, file);
        }catch (IOException ignored){

        }
    }
}
