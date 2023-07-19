package views;

import interfaces.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.Player;
import services.PlayerService;
import utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class RankView extends JPanel implements View {
    private final Game game;
    private final PlayerService playerService;
    private static final JScrollPane scrollPane=new JScrollPane();
    private static final JTable table=new JTable();
    private static final String[] columnNames = {"Top", "Tên", "Level", "Loại", "Thời gian"};

    @Override
    public void initView() {
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        JButton menu=new JButton("Back");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeView(Constants.MENU);
            }
        });
        JLabel title=new JLabel("RANKS");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 28));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        this.setLayout(new BorderLayout());
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(menu,BorderLayout.SOUTH);
        this.add(title,BorderLayout.NORTH);
        //mỗi khi chuyển đến rankView thì gọi lại hàm showData để cập nhật lại;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                showData(playerService.findAll());
            }
        });
    }





    public static void showData(List<Player> players){
        if(players==null) return;
        Object[][] data = new Object[players.size()][columnNames.length];
        for (int i = 0; i < players.size(); i++) {
            data[i][0] = i + 1;
            data[i][1] = players.get(i).getName();
            data[i][2] = players.get(i).getLevel();
            data[i][3] = players.get(i).getType();
            data[i][4] = players.get(i).getTime() + "s";
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, columnNames);
        scrollPane.setViewportView(table);
    }

}
