package controllers;

import lombok.RequiredArgsConstructor;
import utils.Constants;
import utils.Utils;
import views.Game;
import views.TimeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class TimeController implements ActionListener {
    private final TimeView timeView;
    private final Game game;
    private int count=0;

    @Override
    public void actionPerformed(ActionEvent e) {
        int time = timeView.getTime() - 1;
        /*count++;
        if(count==15){
            JOptionPane.showMessageDialog(null,"15s");
            System.out.println("canh bao!");
            count=0;
        }*/
        timeView.getTimeJLabel().setText("Time: " + Utils.convertSecondToMinute(time));
        timeView.setTime(time);
        if (game.isEnd()) {
            timeView.getTimer().stop();
        }
        if (timeView.getTime() <= 10) {
            timeView.getTimeJLabel().setForeground(Color.red);
        }
        if (timeView.getTime() == 0) {
            game.gameOver(Constants.MESSAGE_LOSE);
        }

    }
}
