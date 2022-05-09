package set.ui;

import set.core.Board;

import javax.swing.*;
import java.awt.*;

public class SetUI extends JFrame {
    SetCard[] playthings = new SetCard[15];
    SetCard button;
    JPanel panel;
    private Board board = new Board();

    public SetUI() throws CloneNotSupportedException {
        JFrame setWindow = new JFrame();
        setWindow.setSize(300, 300);
        setWindow.setTitle("Set Game");
        setWindow.setLocationRelativeTo( null );
        setWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
        setWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        Dimension dim = new Dimension(250, 250);
        panel.setMinimumSize(dim);
        panel.setPreferredSize(dim);
        panel.setSize(dim);
        panel.setLayout(new GridLayout(3, 4, 4, 4));
        setWindow.add(panel);
        fillTheBoard();
        setWindow.setVisible(true);
    }
    public void fillTheBoard() throws CloneNotSupportedException {
        for (int i = 0; i < 12; i++) {
            button = new SetCard(i);
            panel.add(button);
            playthings[i] = button;
        }
        for (int i = 0; i < 12; i++) playthings[i].displayCards(board.arrayNew.get(i));

    }
    
}
