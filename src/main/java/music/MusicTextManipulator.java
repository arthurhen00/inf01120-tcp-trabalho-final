package music;


import constants.CommandConstants;
import constants.TextConstants;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class MusicTextManipulator {

    private int bpm;
    private int volume;
    private int octave;
    private Map<Character, Integer> notesMap;
    private Map<Integer, String> instrumentMap;

    private Map<String, Runnable> commandMap;
    private ArrayList<String> processedText = new ArrayList<>();
    private char lastKey = ' ';

    private static final int INITIAL_BPM = 120;
    private static final int MAX_BPM = 250;
    private static final int MIN_BPM = 20;
    private static final int INITIAL_VOLUME = 63;
    private static final float MAX_VOLUME = 127;
    private static final int INITIAL_OCTAVE = 5;
    private static final int MAX_OCTAVE = 9;
    private static final int MIN_OCTAVE = 0;

    public MusicTextManipulator(){
        this.bpm = INITIAL_BPM;
        this.volume = INITIAL_VOLUME;
        this.octave = INITIAL_OCTAVE;
        this.notesMap = new HashMap<>();
        this.instrumentMap = new HashMap<>();
        this.commandMap = new HashMap<>();
        mapPreset();
        instrumentPreset();
        commandPreset();
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

    private void commandPreset(){
        commandMap.put(CommandConstants.INCREASE_VOLUME, this::increaseVolume);
        commandMap.put(CommandConstants.DEFAULT_VOLUME, this::setDefaultVolume);
        commandMap.put(CommandConstants.RANDOM_NOTE, this::getRandomNote);
        commandMap.put(CommandConstants.INCREASE_BPM, this::increaseBPM);
        commandMap.put(CommandConstants.RANDOM_BPM, this::randomBPM);
        commandMap.put(CommandConstants.INCREASE_OCTAVE, this::increaseOctave);
        commandMap.put(CommandConstants.DECREASE_OCTAVE, this::decreaseOctave);
        commandMap.put(CommandConstants.CHANGE_INSTRUMENT, this::getRandomInstrument);
        commandMap.put(CommandConstants.CHOICE_NOTE, this::choiceNote);
    }

    public String translateText(String clearedMusicText){
        for(char c : clearedMusicText.toCharArray()){
            if(commandMap.get(Character.toString(c)) != null){
                commandMap.get(Character.toString(c)).run();
            } else if (notesMap.get(c) != null){
                processedText.add(Integer.toString((notesMap.get(c) + (this.octave * 12))));
            } else {
                processedText.add(TextConstants.EMPTY_SPACE);
            }
            this.lastKey = c;
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
            this.processedText.add(CommandConstants.START_FUNCTION + CommandConstants.CHANGE_VOLUME_FUNCTION + "," + this.volume + CommandConstants.END_FUNCTION);
        }
    }

    private void setDefaultVolume(){
        this.volume = INITIAL_VOLUME;
        processedText.add(CommandConstants.START_FUNCTION + CommandConstants.CHANGE_VOLUME_FUNCTION + "," + INITIAL_VOLUME + CommandConstants.END_FUNCTION);
    }

    private void getRandomNote(){
        processedText.add(Integer.toString(notesMap.get(TextConstants.NOTES.charAt(new Random().nextInt(TextConstants.NOTES.length()))) + (this.octave * 12)));
    }

    private void getRandomInstrument(){
        processedText.add(instrumentMap.get(new Random().nextInt(instrumentMap.size())));
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
        processedText.add(CommandConstants.CHANGE_BPM_FUNCTION + this.bpm);
    }

    private void randomBPM(){
        this.bpm = new Random().nextInt((MAX_BPM - MIN_BPM) + 1) + MIN_BPM;
        processedText.add(CommandConstants.CHANGE_BPM_FUNCTION + this.bpm);
    }

    private void choiceNote(){
        if(notesMap.get(lastKey) != null){
            processedText.add(Integer.toString((notesMap.get(lastKey) + (this.octave * 12))));
        } else {
            processedText.add("125");
        }
    }

}