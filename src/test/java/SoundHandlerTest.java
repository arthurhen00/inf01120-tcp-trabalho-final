import constants.TextConstants;
import music.SoundHandler;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.Test;

import java.util.Random;

public class SoundHandlerTest {

    @Test
    public void a(){
        SoundHandler sh = new SoundHandler();

        String input = "cdefgabr+NLCDEFGABR+NLCDEFGABR+NLCDEFGAB";

        //String cText = sh.convertText(input);
        //System.out.println(cText);

        String fText = sh.translateText(input);
        System.out.println(fText);

        Player player = new Player();
        Pattern pattern1 = new Pattern(fText);
        player.play(pattern1);

    }

}
