import music.SoundHandler;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.Test;

import java.util.Random;

public class SoundHandlerTest {
    //java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Byte
    @Test
    public void a(){
        SoundHandler sh = new SoundHandler();

        String input = "CDEFGABR+NLCDEFGABR+NLCDEFGABR+NLCDEFGAB";

        String pText = sh.convertText(input);
        System.out.println(pText);

        String fText = sh.translateText(pText);
        System.out.println(fText);

        Player player = new Player();
        Pattern pattern1 = new Pattern(fText);
        player.play(pattern1);

    }

}
