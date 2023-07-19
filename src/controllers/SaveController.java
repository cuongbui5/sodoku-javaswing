package controllers;

import models.Player;
import models.SodokuModel;
import services.PlayerService;
import utils.Constants;
import views.Game;
import views.SaveView;
import views.TimeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveController implements ActionListener {
    private final Game game;
    private final PlayerService playerService;
    private final SaveView saveView;

    public SaveController(Game game, PlayerService playerService, SaveView saveView) {
        this.game = game;
        this.playerService = playerService;
        this.saveView = saveView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(game.getStatusEnd()== Constants.STATUS_WIN){
            SodokuModel sodokuModel=game.getSodokuModel();
            TimeView timeView=game.getTimeView();
            String name=saveView.getTextField().getText();
            int time=sodokuModel.getTime()- timeView.getTime();
            int level=sodokuModel.getLevel();
            int type=sodokuModel.getType();
            String typeStr;
            if(type==5){
                typeStr="Easy";
            }else if(type==6){
                typeStr="Medium";
            }else {
                typeStr="Difficult";
            }

            Player player=new Player(null,name,time,level,typeStr);
            playerService.save(player);
            System.out.println("save completed!");
        }else {
            System.out.println("back menu");
        }

        game.changeView(Constants.MENU);
    }
}
