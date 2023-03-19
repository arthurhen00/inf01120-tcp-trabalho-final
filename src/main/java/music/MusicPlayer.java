package music;

import java.io.File;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MusicPlayer {

    private int i = 1;
    private Pattern pattern;

    public MusicPlayer() {

    }

    public void play(String finalMusicText) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = new Player();
                Pattern pattern = new Pattern(finalMusicText);
                this.pattern = pattern;
                player.play(pattern);
            }
        }).start();
    }

    public void GenerateMIDI() {
        File file = new File(System.getProperty("user.dir") + "/music (" + i + ").mid");
        i++;
        org.jfugue.midi.MidiFileManager.savePatternToMidi(pattern, file);
    }
}
