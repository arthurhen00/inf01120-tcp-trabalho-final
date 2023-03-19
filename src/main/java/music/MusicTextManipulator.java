package music;


import constants.CommandConstants;
import constants.TextConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MusicTextManipulator {

    private int bpm;
    private int volume;
    private int octave;
    private int instrument;
    private Map<Character, Integer> notesMap;
    private Map<String, Integer> instrumentMap;

    private Map<String, Runnable> commandMap;
    private ArrayList<String> processedText = new ArrayList<>();
    private char lastKey = ' ';
     // piano default

    private static final int INITIAL_INSTRUMENT = 0;
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
        this.instrument = INITIAL_INSTRUMENT;
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
        this.instrumentMap.put(CommandConstants.INSTRUMENT_AGOGO, 114);
        this.instrumentMap.put(CommandConstants.INSTRUMENT_HARPSICHORD, 7);
        this.instrumentMap.put(CommandConstants.INSTRUMENT_TUBULAR_BELLS, 15);
        this.instrumentMap.put(CommandConstants.INSTRUMENT_PAN_FLUTE, 76);
        this.instrumentMap.put(CommandConstants.INSTRUMENT_CHORD_ORGAN, 20);
    }

    private void commandPreset(){
        // [Espaço] -> mudar volume
        // [?]      -> mudar oitava

        //[! I \n ; ,] -> mapeiam instrumentos
        commandMap.put(CommandConstants.CHANGE_VOLUME, this::changeVolume);
        commandMap.put(CommandConstants.CHANGE_OCTAVE, this::changeOctave);
    }

    public String translateText(String clearedMusicText){
        for(char c : clearedMusicText.toCharArray()){
            Boolean isNumber = false;
            int number = 0;
            try{
                number = Integer.parseInt(Character.toString(c));
                isNumber = true;
            }catch (Exception ex){

            }
            if(notesMap.get(c) != null){
                processedText.add(Integer.toString((notesMap.get(c) + (this.octave * 12))));
            } else if (commandMap.get(Character.toString(c)) != null){
                commandMap.get(Character.toString(c)).run();
            } else if (instrumentMap.get(Character.toString(c)) != null){
                this.instrument = instrumentMap.get(Character.toString(c));
                processedText.add(CommandConstants.CHANGE_INSTRUMENT_FUNCTION + Integer.toString(instrumentMap.get(Character.toString(c))));
            } else if(isNumber){
                this.instrument += number;
                this.instrument %= 128;
                System.out.println(this.instrument);
                processedText.add(CommandConstants.CHANGE_INSTRUMENT_FUNCTION + Integer.toString(this.instrument));
            } else {
                ultimaNota();
            }

            this.lastKey = c;
        }

        String finalText = "";
        for(String commands : processedText){
            finalText += commands + TextConstants.EMPTY_SPACE;
        }

        return finalText;
    }

    private void changeVolume(){
        if(this.volume * 2 <= MAX_VOLUME){
            this.volume *= 2;
            this.processedText.add(CommandConstants.CHANGE_VOLUME_FUNCTION + "," + this.volume + CommandConstants.END_FUNCTION);
        } else {
            this.volume = INITIAL_VOLUME;
            processedText.add(CommandConstants.CHANGE_VOLUME_FUNCTION + "," + INITIAL_VOLUME + CommandConstants.END_FUNCTION);
        }
    }

    private void changeOctave(){
        if(this.octave < MAX_OCTAVE){
            this.octave++;
        } else {
            this.octave = INITIAL_OCTAVE;
        }
    }

    private void ultimaNota(){
        if(notesMap.get(lastKey) != null){
            // caractere anterior era nota
            processedText.add(Integer.toString((notesMap.get(lastKey) + (this.octave * 12))));
        } else {
            // silencio ou pausa
            processedText.add("125");
        }
    }

}