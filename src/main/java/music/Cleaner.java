package music;

import constants.CommandConstants;

public class Cleaner {
    private String finalMusicText;

    public Cleaner(){
        
    }
    
    public Cleaner(String inputMusicText){
        setFinalMusicText(inputMusicText);
    }

    private String getCleanText(String inputMusicText){
        inputMusicText = inputMusicText.toUpperCase();
        inputMusicText = inputMusicText.replaceAll("[IOU]", CommandConstants.CHOICE_NOTE);
        inputMusicText = inputMusicText.replace("R+", CommandConstants.INCREASE_OCTAVE);
        inputMusicText = inputMusicText.replace("R-", CommandConstants.DECREASE_OCTAVE);
        inputMusicText = inputMusicText.replace("NL", CommandConstants.CHANGE_INSTRUMENT);
        inputMusicText = inputMusicText.replace("BPM+", CommandConstants.INCREASE_BPM);

        return inputMusicText;
    }

    public String getFinalMusicText() {
        return finalMusicText;
    }
    
    public void setFinalMusicText(String finalMusicText) {
        String cleanText = getCleanText(finalMusicText);
        this.finalMusicText = cleanText;
    }
}
