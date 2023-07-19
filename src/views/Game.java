package views;

import database.Database;
import exceptions.FileException;
import interfaces.GamePlay;
import interfaces.View;
import lombok.Getter;
import lombok.Setter;
import models.SodokuModel;
import services.PlayerService;
import utils.Constants;
import utils.Utils;
import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class Game extends JFrame implements View, GamePlay {
    private SodokuModel sodokuModel;
    private SodokuView sodokuView;
    private OverGameView overGame;
    private MenuView menuView;
    private ChooseLevelView chooseLevel;
    private HelpView help;
    private RankView rankView;
    private TimeView timeView;
    private SaveView saveView;
    private PlayerService playerService;
    private CardLayout cardLayout;
    private JPanel container;
    private boolean end;
    private int statusEnd=0;
    public Game(){
        initService();
        initView();
        changeView(Constants.MENU);
    }
    @Override
    public void initView() {
        setTitle("SODOKU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        this.add(container);
        initComponent();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void initService(){
        Database database=new Database();
        playerService=new PlayerService(database);

    }
    private void initComponent(){
        this.sodokuModel=new SodokuModel();
        this.chooseLevel=new ChooseLevelView(this);
        chooseLevel.initView();
        this.menuView=new MenuView(this);
        menuView.initView();
        this.overGame=new OverGameView(this);
        overGame.initView();
        this.timeView =new TimeView(this);
        timeView.initView();
        this.sodokuView=new SodokuView(timeView, this);
        this.help=new HelpView(this);
        help.initView();
        this.saveView=new SaveView(this,playerService);
        saveView.initView();
        this.rankView=new RankView(this,playerService);
        rankView.initView();
        container.add(menuView, Constants.MENU);
        container.add(chooseLevel, Constants.CHOOSE_LEVEL);
        container.add(sodokuView, Constants.SODOKU);
        container.add(overGame, Constants.OVER_GAME);
        container.add(help, Constants.HELP);
        container.add(saveView, Constants.SAVE_GAME);
        container.add( rankView,Constants.RANK);

    }
    @Override
    public void gameStart(){
        sodokuModel.initGame();
        initViewPlay();
    }



    private void initViewPlay() {
        if(sodokuView.isInit()){
            sodokuView.resetData();
        }else
            sodokuView.initView();
        changeView(Constants.SODOKU);
        end=false;
    }

    @Override
    public void loadContinue() {
        try{
            this.sodokuModel=Utils.getData();
        }catch (FileException e){
            System.out.println(e.getMessage());
            return;
        }
        initViewPlay();

    }

    @Override
    public void gameOver(String message){
        overGame.setMessageLabel(message);
        changeView(Constants.OVER_GAME);
        end=true;
    }


    public void changeView(String view){
        cardLayout.show(container,view);
    }


}
