package GUI;

import music.MusicPlayer;
import music.SoundHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Screen extends JFrame {

    JButton buttonGenerateMusic;
    JTextArea inputTextArea;

    public Screen(){

        setTitle("music");
        setVisible(true);
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonGenerateMusic = new JButton("Gerar musica");
        buttonGenerateMusic.setBounds(10, 310, 120, 40);
        add(buttonGenerateMusic);
        buttonGenerateMusic.addActionListener(this::actionGenerateMusic);

        inputTextArea = new JTextArea(460, 200);
        inputTextArea.setBounds(10, 10, 460, 200);
        inputTextArea.setLineWrap(true);
        //inputTextArea.setText("cdefgabr+NLCDEFGABR+NLCDEFGABR+NLCDEFGAB");
        add(inputTextArea);


    }

    public void actionGenerateMusic(ActionEvent e) {
        new MusicPlayer().play(new SoundHandler().translateText(inputTextArea.getText()));
    }



}
