package views;

import interfaces.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import utils.Constants;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class HelpView extends JPanel implements View {
    private final Game game;
    private static Font font=new Font("TimesRoman", Font.BOLD, 20);

    @Override
    public void initView() {
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        JTextArea textArea=new JTextArea("Hàng ngang: Đủ các số từ 1-9, không trùng số và không cần đúng thứ tự.\n"+
                "Hàng dọc: Đủ các số từ 1-9, không trùng số, không cần theo thứ tự.\n"+
                "Mỗi vùng 3x3: Đủ các số từ 1-9, không trùng số nào trong cùng 1 vùng 3 x3.\n"+
                "Thời gian mỗi bàn chơi:\n"+
                "Easy: "+ Utils.convertSecondToMinute(Constants.time[0])+"s"+
                "\nMedium: "+ Utils.convertSecondToMinute(Constants.time[1])+"s"+
                "\nDifficult: "+ Utils.convertSecondToMinute(Constants.time[2])+"s"+
                "\nChỉ được sai tốt đa 2 lần.");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(font);
        JButton menu=new JButton("Back");
        menu.setPreferredSize(new Dimension(200,50));
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeView(Constants.MENU);
            }
        });
        setLayout(new BorderLayout());
        add(textArea,BorderLayout.CENTER);
        add(menu,BorderLayout.SOUTH);
    }
}
