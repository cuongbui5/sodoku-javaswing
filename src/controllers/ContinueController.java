package controllers;

import exceptions.FileException;
import lombok.RequiredArgsConstructor;
import utils.Constants;
import utils.Utils;
import views.Game;
import views.TimeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@RequiredArgsConstructor
public class ContinueController implements ActionListener {
    private final TimeView timeView;
    private final Game game;


    @Override
    public void actionPerformed(ActionEvent e) throws FileException {
        game.getSodokuModel().setTime(timeView.getTime());
        Utils.saveGame(game.getSodokuModel());
        game.setEnd(true);
        game.changeView(Constants.MENU);

    }


}
