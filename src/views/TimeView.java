package views;

import controllers.ContinueController;
import controllers.TimeController;

import interfaces.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.SodokuModel;
import utils.Utils;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@RequiredArgsConstructor
public class TimeView extends JPanel implements View {
    private int time;
    private final Game game;
    private JLabel falseJLabel, timeJLabel;
    private Timer timer;
    private static final Font font=new Font(Font.SERIF, Font.PLAIN, 16);
    private static final Dimension dimension=new Dimension(500, 35);

    @Override
    public void initView() {
        ContinueController continueController =new ContinueController(this, game);
        TimeController timeController=new TimeController(this,game);
        this.setPreferredSize(dimension);
        setLayout(new BorderLayout());
        timeJLabel = new JLabel("Time: " + Utils.convertSecondToMinute(time));
        falseJLabel = new JLabel("False: "+"0/3");
        style(timeJLabel);
        style(falseJLabel);
        JButton save=new JButton("Save");
        save.addActionListener(continueController);
        add(timeJLabel, BorderLayout.CENTER);
        add(falseJLabel, BorderLayout.WEST);
        add(save, BorderLayout.EAST);
        timer=new Timer(1000,timeController);
    }


    public void style(JLabel jLabel) {
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
    }


    public void updateCheck(int num) {
        falseJLabel.setText("False: "+num+"/3");
    }

    public void updateTime(int time) {
        timeJLabel.setText("Time: " + Utils.convertSecondToMinute(time));
    }

    public void update(){
        SodokuModel sodokuModel=game.getSodokuModel();
        time=sodokuModel.getTime();
        updateCheck(sodokuModel.getCheckFalse());
        updateTime(sodokuModel.getTime());
        timeJLabel.setForeground(Color.BLACK);
        timer.start();
    }





}
