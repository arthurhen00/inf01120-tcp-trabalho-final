package music;

import constants.CommandConstants;

public class Cleaner {
    private String finalMusicText;

    public Cleaner(){
        // iniciar
        // volume
        // instrumento
    }
    
    public Cleaner(String inputMusicText){
        setFinalMusicText(inputMusicText);
    }

    private String getCleanText(String inputMusicText){
        inputMusicText = inputMusicText.replaceAll("[IiOoUu]", "i");
        return inputMusicText;
    }

    public String getFinalMusicText() {
        return finalMusicText;
    }
    
    public void setFinalMusicText(String inputMusicText) {
        String cleanText = getCleanText(inputMusicText);
        this.finalMusicText = cleanText;
    }
}
