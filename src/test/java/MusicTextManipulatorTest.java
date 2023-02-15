import music.Cleaner;
import music.MusicTextManipulator;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.Test;

public class MusicTextManipulatorTest {

    @Test
    public void a(){
        String inputMusicText = "cdefgabr+NLCDEFGABR+-NL+CDEFGABR+NLCDEFGAB";
        MusicTextManipulator mtm = new MusicTextManipulator();

        Cleaner c = new Cleaner(inputMusicText);
        System.out.println(c.getFinalMusicText());

    }

}
