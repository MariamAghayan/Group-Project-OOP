package set.ui;

import set.core.Board;
import set.core.Card;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetUI extends JFrame {
    ArrayList<SetCard> playthings = new ArrayList<>();
    SetCard button;
    JPanel panel;
    private Board board = new Board();

    public SetUI() throws CloneNotSupportedException {
        JFrame setWindow = new JFrame();
        setWindow.setSize(320, 350);
        setWindow.setTitle("Set Game");
        setWindow.setLocationRelativeTo( null );
        setWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
        setWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        Dimension dim = new Dimension(280, 300);
        panel.setMinimumSize(dim);
        panel.setPreferredSize(dim);
        panel.setSize(dim);
        panel.setLayout(new GridLayout(3, 4, 4, 4));
        setWindow.add(panel);
        fillTheBoard();
        setWindow.setVisible(true);
    }
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            button = (SetCard) e.getSource();
            try {
                boardClicked(button.getCoordinate());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
    };

    private Card firstCard;
    private Card secondCard;
    private Card thirdCard;
    private int num;
    private int[] coordinates = new int[3];
    int clickNumber = 1;
    Border original;
    private void boardClicked(int coordinate) throws CloneNotSupportedException {
        if (clickNumber == 1){
            for (int i = 0; i < playthings.size(); i++){
                playthings.get(i).setBorder(original);
            }
            playthings.get(coordinate).setBorder(new LineBorder(new Color(56, 139, 87), 4));
            firstCard = board.arrayNew.get(coordinate);
            coordinates[0] = coordinate;
            clickNumber = 2;
        }
        else if (clickNumber == 2){
            playthings.get(coordinate).setBorder(new LineBorder(new Color(56, 139, 87), 4));
            secondCard = board.arrayNew.get(coordinate);
            coordinates[1] = coordinate;
            clickNumber = 3;
        }
        else if (clickNumber == 3){
            playthings.get(coordinate).setBorder(new LineBorder(new Color(56, 139, 87), 4));
            thirdCard = board.arrayNew.get(coordinate);
            coordinates[2] = coordinate;
            for (int i = 0; i < 3; i++){
                System.out.println(coordinates[i]);
            }
            if (board.isASet(firstCard, secondCard, thirdCard)){
                for (int i = 0; i < 3;){
                    num = (int) (Math.random() * 80);
                    if (board.array2[num] != null) {
                        board.arrayNew.set(coordinates[i], board.array2[num]);
                        board.array2[num] = null;
                        i++;
                    }
                }
                for (int i = 0; i < 3; i++){
                    playthings.get(coordinates[i]).setBorder(original);
                }
                board.allSets();
                System.out.println("Great job!");
            }
            else {
                for (int i = 0; i < coordinates.length; i++){
                    playthings.get(coordinates[i]).setBorder(new LineBorder(new Color(164, 11, 14), 4));
                }
                board.allSets();
                System.out.println("Try again");
            }
            clickNumber = 1;
            for (int i = 0; i < playthings.size(); i++){
                playthings.get(i).displayCards(board.arrayNew.get(i));
            }

        }
    }
    public void fillTheBoard() throws CloneNotSupportedException {
        for (int i = 0; i < board.arrayNew.size(); i++) {
            button = new SetCard(i);
            button.addActionListener(actionListener);
            panel.add(button);
            playthings.add(button);
        }
        for (int i = 0; i < board.arrayNew.size(); i++){
            playthings.get(i).displayCards(board.arrayNew.get(i));
        }
        original = playthings.get(0).getBorder();
    }

}
