package music;

public class CreateMusic {
    public String finalMusicText;

    public CreateMusic(String inputText){
        SoundHandler sh = new SoundHandler();
        this.finalMusicText = sh.translateText(inputText);
    }
}
