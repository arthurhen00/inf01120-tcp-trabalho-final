package music;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MusicPlayer{
    
    Pattern pattern;
    
    public MusicPlayer(){
        
    }

    public void play(String finalMusicText){
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

    public void GenerateMIDI(){
        
    }
}
