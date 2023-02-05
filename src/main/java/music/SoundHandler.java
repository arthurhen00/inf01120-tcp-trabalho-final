package music;

import constants.CommandConstants;
import constants.TextConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SoundHandler {

    private int bpm;
    private int volume;
    private int octave;
    public Map<Character, Integer> notesMap;
    private Map<Integer, String> instrumentMap;

    private static final int INITIAL_BPM = 120;
    private static final int MAX_BPM = 250;
    private static final int MIN_BPM = 20;
    private static final int INITIAL_VOLUME = 63;
    private static final float MAX_VOLUME = 127;
    private static final int INITIAL_OCTAVE = 5;
    private static final int MAX_OCTAVE = 9;
    private static final int MIN_OCTAVE = 0;

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

    private String convertText(String inputMusicText){
        inputMusicText = inputMusicText.toUpperCase();
        inputMusicText = inputMusicText.replaceAll("[IOU]", CommandConstants.CHOICE_NOTE);
        inputMusicText = inputMusicText.replace("R+", CommandConstants.INCREASE_OCTAVE);
        inputMusicText = inputMusicText.replace("R-", CommandConstants.DECREASE_OCTAVE);
        inputMusicText = inputMusicText.replace("NL", CommandConstants.CHANGE_INSTRUMENT);
        inputMusicText = inputMusicText.replace("BPM+", CommandConstants.INCREASE_BPM);

        return inputMusicText;
    }

    public String translateText(String inputMusicText){
        String convetedText = convertText(inputMusicText);
        ArrayList<String> processedText = new ArrayList<>();
        char lastKey = ' ';

        for(char c : convetedText.toCharArray()){
            if(notesMap.get(c) != null){
                String note = Integer.toString((notesMap.get(c) + (this.octave * 12)));
                processedText.add(note);
            } else if(Character.toString(c).equals(CommandConstants.INCREASE_VOLUME)){
                increaseVolume();
                processedText.add(CommandConstants.START_FUNCTION + CommandConstants.CHANGE_VOLUME_FUNCTION + "," + this.volume + CommandConstants.END_FUNCTION);
            } else if(Character.toString(c).equals(CommandConstants.DEFAULT_VOLUME)){
                setDefaultVolume();
                processedText.add(CommandConstants.START_FUNCTION + CommandConstants.CHANGE_VOLUME_FUNCTION + "," + INITIAL_VOLUME + CommandConstants.END_FUNCTION);
            } else if(Character.toString(c).equals(CommandConstants.RANDOM_NOTE)){
                processedText.add(getRandomNote());
            } else if(Character.toString(c).equals(CommandConstants.RANDOM_BPM)){
                randomBPM();
                processedText.add(CommandConstants.CHANGE_BPM_FUNCTION + this.bpm);
            } else if(Character.toString(c).equals(CommandConstants.INCREASE_OCTAVE)){
                increaseOctave();
            } else if(Character.toString(c).equals(CommandConstants.DECREASE_OCTAVE)){
                decreaseOctave();
            } else if(Character.toString(c).equals(CommandConstants.CHANGE_INSTRUMENT)){
                processedText.add(getRandomInstrument());
            } else if(Character.toString(c).equals(CommandConstants.INCREASE_BPM)){
                increaseBPM();
                processedText.add(CommandConstants.CHANGE_BPM_FUNCTION + this.bpm);
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

    private void increaseVolume(){
        if(this.volume * 2 <= MAX_VOLUME){
            this.volume *= 2;
        }
    }

    private void setDefaultVolume(){
        this.volume = INITIAL_VOLUME;
    }

    private String getRandomNote(){
        return Integer.toString(notesMap.get(TextConstants.NOTES.charAt(new Random().nextInt(TextConstants.NOTES.length()))) + (this.octave * 12));
    }

    private String getRandomInstrument(){
        return instrumentMap.get(new Random().nextInt(instrumentMap.size()));
    }

    private void increaseOctave(){
        if(this.octave < MAX_OCTAVE){
            this.octave++;
        }
    }

    private void decreaseOctave(){
        if(this.octave > MIN_OCTAVE){
            this.octave--;
        }
    }

    private void increaseBPM(){
        if(this.bpm + 80 > MAX_BPM){
            this.bpm = MAX_BPM;
        } else {
            this.bpm += 80;
        }
    }

    private void randomBPM(){
        this.bpm = new Random().nextInt((MAX_BPM - MIN_BPM) + 1) + MIN_BPM;
    }

}