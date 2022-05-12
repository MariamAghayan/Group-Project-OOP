package set.ui;

import set.core.Board;
import set.core.Card;
import set.core.Player;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetUI extends JFrame {
    ArrayList<SetCard> playthings;
    SetCard button;
    JPanel panel;
    private boolean turn = false;
    private Board board= new Board();
    JFrame opening = new JFrame();
    JFrame setWindow = new JFrame();
    JFrame inputName;
    Player player1 = new Player();
    Player player2 = new Player();
    JLabel player1Points;
    JLabel player2Points;
    ImageIcon icon  = new ImageIcon( "images\\setimage.png");
    JButton endButton = new JButton();
    JLabel playerScores = new JLabel();
    public SetUI() throws CloneNotSupportedException {
        fillTheBoard();
        opening.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        endButton.setIcon(icon);
        endButton.setBackground(Color.red);
        EndingListener buttonEar = new EndingListener();
        endButton.addActionListener(buttonEar);
        opening.add(endButton);
        inputName = new JFrame();
        String name = JOptionPane.showInputDialog(inputName, "Enter Player 1 name");
        String name1 = JOptionPane.showInputDialog(inputName, "Enter Player 2 name");
        player1.setName(name);
        player2.setName(name1);
        player1Points = new JLabel(player1.getName() + ":  " + player1.getPoints());
        player2Points = new JLabel(player2.getName() + ":  " + player2.getPoints());
        player1Points.setFont(new Font("Helvetica", Font.BOLD, 25));
        player2Points.setFont(new Font("Helvetica", Font.BOLD, 25));
        setWindow.setSize(700, 700);
        setWindow.setTitle("Set Game");
        setWindow.setLocationRelativeTo( null );
        setWindow.setLayout(new GridLayout(2,1, 20, 20));
        setWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setWindow.setResizable(false);
        playerScores.add(player1Points);
        playerScores.add(player2Points);
        playerScores.setSize(200,50);
        playerScores.setLayout(new GridLayout(4,4,10,10));
        panel.setMinimumSize(dim);
        panel.setPreferredSize(dim);
        panel.setSize(dim);
        panel.setLayout(new GridLayout(3, 4, 4, 4));
        opening.setVisible(true);
        opening.setSize(400,400);
        setWindow.add(playerScores);
        setWindow.add(panel);
        setWindow.setVisible(true);
    }
    Dimension dim = new Dimension(480, 500);
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
    public class EndingListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
        }

    private Card firstCard;
    private Card secondCard;
    private Card thirdCard;
    private int num;
    private int[] coordinates;
    int clickNumber = 1;
    Border original;
    private void boardClicked(int coordinate) throws CloneNotSupportedException {
        if (clickNumber == 1){
            coordinates = new int[3];
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
            if (coordinate != coordinates[0]) {
                coordinates[1] = coordinate;
                clickNumber = 3;
            } else clickNumber = 1;


        }
        else if (clickNumber == 3){
            turn = !turn;
            playthings.get(coordinate).setBorder(new LineBorder(new Color(56, 139, 87), 4));
            thirdCard = board.arrayNew.get(coordinate);
            clickNumber = 1;
            if (coordinate != coordinates[0] && coordinate != coordinates[1]) {
                coordinates[2] = coordinate;
                if (board.isASet(firstCard, secondCard, thirdCard)) {
                    if (board.array81Copy.size() < 3 || board.arrayNew.size() == 15){
                        for (int i = 2; i >= 0; i--) {
                                board.arrayNew.remove(coordinates[i]);
                                board.arrayNew.trimToSize();
                                setWindow.remove(panel);
                                fillTheBoard();
                            panel.setMinimumSize(dim);
                            panel.setPreferredSize(dim);
                            panel.setSize(dim);
                            panel.setLayout(new GridLayout(3, 4, 4, 4));
                            opening.setVisible(true);
                            opening.setSize(400,400);
                            setWindow.add(playerScores);
                            setWindow.add(panel);
                            setWindow.setVisible(true);
                        }
                    }
                    else{
                        for (int i = 0; i < 3; ) {
                            num = (int) (Math.random() * (board.array81Copy.size())-1);
                            board.arrayNew.set(coordinates[i], board.array81Copy.get(num));
                            for (int j = 0; j < board.arrayNew.size(); j++){
                                playthings.get(j).displayCards(board.arrayNew.get(j));
                            }
                            board.array81Copy.remove(num);
                            playthings.get(coordinates[i]).setBorder(original);
                            i++;
                        }
                    }
                    panel.revalidate();
                    panel.repaint();
                    board.allSets();
                    if (board.objectJan.isEmpty()){
                        if(board.array81Copy.size() >= 3){
                            board.addThree();
                            setWindow.remove(panel);
                            fillTheBoard();
                            panel.setMinimumSize(dim);
                            panel.setPreferredSize(dim);
                            panel.setSize(dim);
                            panel.setLayout(new GridLayout(3, 4, 4, 4));
                            opening.setVisible(true);
                            opening.setSize(400,400);
                            setWindow.add(playerScores);
                            setWindow.add(panel);
                        } else System.out.println("End of the game. Congratulations!");
                    }
                    System.out.println("Great job!");
                    updateScore();
                }
                else {
                    for (int i = 0; i < coordinates.length; i++) {
                        playthings.get(coordinates[i]).setBorder(new LineBorder(new Color(164, 11, 14), 4));
                    }
                    board.allSets();
                    System.out.println("Try again");
                }
                for (int i = 0; i < playthings.size(); i++) {
                    playthings.get(i).displayCards(board.arrayNew.get(i));
                }
            } else clickNumber = 3;
            System.out.println(board.array81Copy.size());

        }
    }
    public void updateScore() {
        if (getTurn()) {
            player1.addPoints(100);
            player1Points.setText(player1.getName() +": " + player1.getPoints());
        } else {
            player2.addPoints(100);
            player2Points.setText(player2.getName() +": " + player2.getPoints());
        }
    }
    public boolean getTurn(){
        return turn;
    }

    public void fillTheBoard() throws CloneNotSupportedException {
        panel = new JPanel();
        playthings = new ArrayList<>();
        for (int i = 0; i < board.arrayNew.size(); i++) {
            button = new SetCard(i);
            button.setBackground(Color.WHITE);
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
