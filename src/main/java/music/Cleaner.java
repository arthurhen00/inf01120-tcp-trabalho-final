package music;

public class Cleaner {
    private String finalMusicText;

    public Cleaner(){
        
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
    
    public void setFinalMusicText(String finalMusicText) {
        String cleanText = getCleanText(finalMusicText);
        this.finalMusicText = cleanText;
    }
}
