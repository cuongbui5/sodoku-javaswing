package controllers;

import lombok.RequiredArgsConstructor;
import utils.Constants;
import views.Game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@RequiredArgsConstructor
public class OverGameController implements ActionListener {
    private final Game game;



    @Override
    public void actionPerformed(ActionEvent e) {
        int value = Integer.parseInt(e.getActionCommand());
        if (value == Constants.CONTINUE) {
            game.gameStart();
        }else if(value==1){
            game.changeView(Constants.SAVE_GAME);
        }
        else {
            game.changeView(Constants.MENU);
        }
    }
}
