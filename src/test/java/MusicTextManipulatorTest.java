import music.Cleaner;
import music.MusicTextManipulator;
import org.jfugue.parser.ParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.Test;

public class MusicTextManipulatorTest {

    @Test
    public void a(){
        String inputMusicText = "cdefgabCDEFGABc!a";
        MusicTextManipulator mtm = new MusicTextManipulator();
        Cleaner cleaner = new Cleaner();
        cleaner.setFinalMusicText(inputMusicText);
        String teste0 = cleaner.getFinalMusicText();
        String teste1 = mtm.translateText(teste0);

        System.out.println(inputMusicText);
        System.out.println(teste0);
        System.out.println(teste1);

        Player player = new Player();
        Pattern pattern = new Pattern(teste1);
        player.play(pattern);
    }
}
