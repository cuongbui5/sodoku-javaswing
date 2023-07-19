package controllers;

import lombok.RequiredArgsConstructor;
import views.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@RequiredArgsConstructor
public class ChooseLevelController implements ActionListener {
    private final Game game;

    @Override
    public void actionPerformed(ActionEvent e) {
        int level=Integer.parseInt(e.getActionCommand());
        game.getSodokuModel().setLevel(level);
        game.gameStart();
    }
}
