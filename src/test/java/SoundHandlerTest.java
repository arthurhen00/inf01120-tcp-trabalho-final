import music.MusicPlayer;
import music.SoundHandler;
import org.junit.Test;

public class SoundHandlerTest {

    @Test
    public void a(){
        SoundHandler sh = new SoundHandler();

        String input = "cdefgabr+NLCDEFGABR+NLCDEFGABR+NLCDEFGAB";

        //String cText = sh.convertText(input);
        //System.out.println(cText);

        String fText = sh.translateText(input);
        System.out.println(fText);

        MusicPlayer ms = new MusicPlayer();
        ms.play(fText);

    }

}
