package views;

import controllers.SodokuController;
import interfaces.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.Node;
import models.SodokuModel;
import utils.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


@Getter
@Setter
@RequiredArgsConstructor
public class SodokuView extends JPanel implements View {
    private final TimeView timeView;
    private final Game game;
    private JButton[][] buttonNumber = new JButton[Constants.REAL_SIZE+1][Constants.REAL_SIZE+1];
    private boolean init=false;
    @Override
    public void initView() {
        SodokuController sodokuController=new SodokuController(this,game, timeView);
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        JPanel sodokuContainer=new JPanel(new GridLayout(3, 3,2,2));
        sodokuContainer.setBorder(BorderFactory.createEmptyBorder(3,3, 3, 3));
        JPanel[] block= new JPanel[Constants.REAL_SIZE+1];
        GridLayout gridLayout=new GridLayout(3,3);
        LineBorder lineBorder=new LineBorder(Color.BLACK, 1);
        for (int i = 1; i <= Constants.REAL_SIZE; i++) {
            block[i] = new JPanel(gridLayout);
            block[i].setBorder(lineBorder);
            sodokuContainer.add(block[i]);
        }
        SodokuModel sodokuModel=game.getSodokuModel();
        for(int i = 1; i<= Constants.REAL_SIZE; i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                Node node=sodokuModel.getNodes()[i][j];
                buttonNumber[i][j]=new JButton(node.isProtect()? node.getValue()+"" : "");
                buttonNumber[i][j].setActionCommand(i+""+j);
                buttonNumber[i][j].setFont(Constants.FONT_BOARD_GAME);
                buttonNumber[i][j].addActionListener(sodokuController);
                buttonNumber[i][j].addKeyListener(sodokuController);
                block[node.getZone()].add(buttonNumber[i][j]);
            }
        }

        this.setLayout(new BorderLayout());
        this.add(timeView,BorderLayout.NORTH);
        this.add(sodokuContainer,BorderLayout.CENTER);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                timeView.update();
            }
        });
        init=true;
    }



    public void resetData(){
       SodokuModel sodokuModel= game.getSodokuModel();
        for(int i = 1; i<= Constants.REAL_SIZE; i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                Node node=sodokuModel.getNodes()[i][j];
                buttonNumber[i][j].setText(node.isProtect()? node.getValue()+"" : "");
                buttonNumber[i][j].setForeground(Color.BLACK);
            }
        }
    }


    public void updateView(int row, int col, int value) {
        buttonNumber[row][col].setText("" + value);
        buttonNumber[row][col].setActionCommand(row + "" + col);
        buttonNumber[row][col].setForeground(Constants.COLOR_DEFAULT);

    }




}
