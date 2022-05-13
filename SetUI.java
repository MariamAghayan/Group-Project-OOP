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
    JFrame setWindow = new JFrame();
    JFrame winner = new JFrame();
    JButton winnerName = new JButton();
    JFrame inputName;
    Player player1 = new Player();
    Player player2 = new Player();
    JLabel player1Points;
    JLabel player2Points;
    JPanel playerScores = new JPanel();

    public SetUI() throws CloneNotSupportedException {
        fillTheBoard();
        inputName = new JFrame();
        String name = JOptionPane.showInputDialog(inputName, "Enter Player 1 name");
        String name1 = JOptionPane.showInputDialog(inputName, "Enter Player 2 name");
        player1.setName(name);
        player2.setName(name1);
        player1Points = new JLabel(player1.getName() + ":  " + player1.getPoints());
        player2Points = new JLabel( player2.getName() + ":  " + player2.getPoints());
        player1Points.setFont(new Font("Monospaced", Font.BOLD, 30));
        player2Points.setFont(new Font("Monospaced", Font.BOLD, 30));
        playerScores.setSize(580,60);
        playerScores.setLayout(new GridLayout(1,2,30,50));
        playerScores.add(player1Points);
        playerScores.add(player2Points);
        setWindow.setSize(600, 650);
        setWindow.setTitle("Set Game");
        setWindow.setLocationRelativeTo( null );
        setWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setWindow.setResizable(false);
        panel.setLayout(new GridLayout(3, 4, 4, 4));
        setWindow.add(playerScores, BorderLayout.NORTH);
        setWindow.add(panel, BorderLayout.CENTER);
        setWindow.setVisible(true);
        winner.setSize(400, 400);
        winner.setResizable(false);
        winner.setLocationRelativeTo( null );
        winnerName.setBackground(new Color(50,120,120));
        winnerName.setFont(new Font("Monospaced", Font.BOLD, 30));
        winnerName.setForeground(Color.WHITE);
        EndingListener buttonEar = new EndingListener();
        winnerName.addActionListener(buttonEar);
        winner.add(winnerName);
    }
    public class EndingListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
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
                            panel.setLayout(new GridLayout(3, 4, 4, 4));
                            setWindow.add(panel, BorderLayout.CENTER);
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
                        if(board.array81Copy.size() >= 3 &&  board.array81Copy.size() != 15){
                            board.addThree();
                            setWindow.remove(panel);
                            fillTheBoard();
                            panel.setLayout(new GridLayout(3, 4, 4, 4));
                            setWindow.add(panel, BorderLayout.CENTER);
                        }
                        else{
                            winnerName.setText("<html>Game Over<br/>" + winnerOfTheGame() + " WON!!!");
                            winner.setVisible(true);
                        }
                    }
                    System.out.println("Great job!");
                    updateScore(100);
                }
                else {
                    updateScore(-50);
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
    public String winnerOfTheGame(){
        if (player1.getPoints() > player2.getPoints()){
            return player1.getName();
        }
        else if(player1.getPoints() < player2.getPoints()){
            return player2.getName();
        }
        else {
            return "No one";
        }
    }
    public void updateScore(int n) {
        if (getTurn()) {
            if(n>0){
                player1Points.setForeground(new Color(65, 180, 45));
            }else  player1Points.setForeground(new Color(200, 30, 45));

            player2Points.setForeground(new Color(0,0,0));
            player1.addPoints(n);
            player1Points.setText(player1.getName()+ ": "+ player1.getPoints());
        } else {
            if(n>0){
                player2Points.setForeground(new Color(65, 180, 45));
            }else  player2Points.setForeground(new Color(200, 30, 45));

            player1Points.setForeground(new Color(0,0,0));
            player2.addPoints(n);
            player2Points.setText( player2.getName() + ":  " + player2.getPoints());

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
