package views;

import controllers.OverGameController;
import interfaces.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
@RequiredArgsConstructor
public class OverGameView extends JPanel implements View {
    private JButton[] buttons;
    private static int bottomMessage=100;
    private JLabel messageLabel;
    private final Game game;

    @Override
    public void initView() {
        OverGameController overGameController=new OverGameController(game);
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        setLayout(new GridBagLayout());
        messageLabel =new JLabel("");
        buttons=new JButton[Constants.OVER_OPTIONS.length];
        GridBagConstraints gbc = new GridBagConstraints();
        messageLabel.setFont(Constants.FONT_OVER_GAME);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, bottomMessage, 0);
        add(messageLabel, gbc);
        for (int i=0;i<Constants.OVER_OPTIONS.length;i++) {
            buttons[i]=new JButton(Constants.OVER_OPTIONS[i]);
            buttons[i].setPreferredSize(new Dimension(200,50));
            buttons[i].setFont(Constants.FONT_BUTTON);
            buttons[i].addActionListener(overGameController);
            buttons[i].setActionCommand(i+"");
            gbc.gridx = 0;
            gbc.gridy = i+1;
            gbc.insets = new Insets(0, 0, Constants.BOTTOM_GAP, 0);
            add(buttons[i], gbc);
        }
    }


    public void setMessageLabel(String message) {
        this.messageLabel.setText(message);
    }
}
