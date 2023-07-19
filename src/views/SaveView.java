package views;

import controllers.SaveController;
import interfaces.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.Player;
import models.SodokuModel;
import services.PlayerService;
import utils.Constants;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@RequiredArgsConstructor
public class SaveView extends JPanel implements View {
    private final Game game;
    private final PlayerService playerService;
    private JTextField textField;


    @Override
    public void initView() {
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        textField = new JTextField("Enter your name");
        textField.setPreferredSize(new Dimension(300,40));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, Constants.BOTTOM_GAP, 0);
        add(textField, gbc);
        SaveController saveController=new SaveController(game,playerService,this);
        JButton btnSave=new JButton("SAVE");
        btnSave.setActionCommand(Constants.AC_SAVE);
        btnSave.addActionListener(saveController);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, Constants.BOTTOM_GAP, 0);
        add(btnSave, gbc);

    }
}
