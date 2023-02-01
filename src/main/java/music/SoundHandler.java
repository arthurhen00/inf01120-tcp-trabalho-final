package music;

import constants.CommandConstants;
import constants.TextConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SoundHandler {

    private int bpm;
    private float volume;
    private int octave;
    private Map<Character, Integer> notesMap;
    private Map<Integer, String> instrumentMap;

    private static final int INITIAL_BPM = 80;
    private static final float INITIAL_VOLUME = 1;
    private static final int INITIAL_OCTAVE = 5;

    public SoundHandler(){
        this.bpm = INITIAL_BPM;
        this.volume = INITIAL_VOLUME;
        this.octave = INITIAL_OCTAVE;
        this.notesMap = new HashMap<>();
        this.instrumentMap = new HashMap<>();
        mapPreset();
        instrumentPreset();
    }

    private void mapPreset() {
        this.notesMap.put('C', 0);
        this.notesMap.put('D', 2);
        this.notesMap.put('E', 4);
        this.notesMap.put('F', 5);
        this.notesMap.put('G', 7);
        this.notesMap.put('A', 9);
        this.notesMap.put('B', 11);
    }

    private void instrumentPreset(){
        this.instrumentMap.put(0, "I[Piano]");
        this.instrumentMap.put(1, "I[Violin]");
        this.instrumentMap.put(2, "I[Guitar]");
        this.instrumentMap.put(3, "I[Celesta]");
    }

    public String translateText(String inputMusicText){

        ArrayList<String> processedText = new ArrayList<>();
        char lastKey = ' ';

        for(char c : inputMusicText.toUpperCase().toCharArray()){
            if(notesMap.get(c) != null){
                String note = Integer.toString((notesMap.get(c) + (this.octave * 12)));
                processedText.add(note);
            } else if(Character.toString(c).equals(CommandConstants.INCREASE_OCTAVE)){
                increaseOctave();
            } else if(Character.toString(c).equals(CommandConstants.DECREASE_OCTAVE)){
                decreaseOctave();
            } else if(Character.toString(c).equals(CommandConstants.CHANGE_INSTRUMENT)){
                processedText.add(instrumentMap.get(new Random().nextInt(instrumentMap.size())));
            } else if(Character.toString(c).equals(CommandConstants.INCREASE_BPM)){
                increaseBPM();
            } else if(Character.toString(c).equals(CommandConstants.CHOICE_NOTE)){
                if(notesMap.get(lastKey) != null){
                    processedText.add(Integer.toString((notesMap.get(lastKey) + (this.octave * 12))));
                } else {
                    processedText.add("125");
                }
            } else {
                processedText.add(TextConstants.EMPTY_SPACE);
            }
            lastKey = c;
        }

        String finalText = "";
        for(String commands : processedText){
            finalText += commands + TextConstants.EMPTY_SPACE;
        }

        return finalText;
    }

    public String convertText(String inputMusicText){
        inputMusicText = inputMusicText.replaceAll("[iouIOU]", CommandConstants.CHOICE_NOTE);
        inputMusicText = inputMusicText.replace("R+", CommandConstants.INCREASE_OCTAVE);
        inputMusicText = inputMusicText.replace("R-", CommandConstants.DECREASE_OCTAVE);
        inputMusicText = inputMusicText.replace("NL", CommandConstants.CHANGE_INSTRUMENT);
        inputMusicText = inputMusicText.replace("BPM+", CommandConstants.INCREASE_BPM);

        return inputMusicText;
    }

    private void increaseOctave(){
        if(this.octave < 10){
            this.octave++;
        }
    }

    private void decreaseOctave(){
        if(this.octave > 0){
            this.octave--;
        }
    }

    private void increaseBPM(){
        if(this.bpm + 80 > 250){
            this.bpm = 250;
        } else {
            this.bpm += 80;
        }
    }

}
