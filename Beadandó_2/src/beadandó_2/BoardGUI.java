package beadandó_2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardGUI {
    public int fromX; 
    public int fromY; 
    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private ArrayList<Point> points;
    public boolean clickedOn;
    private int feketeDb;
    private int feherDb;
    private int kor;
    private String kilep;
    private int boardSize;

    public BoardGUI(int boardSize) {
        this.boardSize = boardSize;
        this.kilep = "black";
        this.feketeDb = boardSize;
        this.feherDb = boardSize;
        this.kor = 0;
        this.clickedOn = false;
        board = new Board(boardSize);
        boardPanel = new JPanel();
        points = new ArrayList<>();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(50, 50));
                buttons[i][j] = button;
                boardPanel.add(button);
                points.add(new Point(i, j));
                button.setBackground(Color.red);
                if(null == board.get(i, j).getValue()){
                    button.setBackground(Color.red);
                }
                else switch (board.get(i, j).getValue()) {
                    case "black" ->                             {
                            ImageIcon icon = new ImageIcon("C:\\Users\\Andor\\Desktop\\Harmadik félév\\Progtek\\Beadandó_2\\black_point.png");
                            button.setIcon(icon);
                        }
                    case "white" ->                             {
                            ImageIcon icon = new ImageIcon("C:\\Users\\Andor\\Desktop\\Harmadik félév\\Progtek\\Beadandó_2\\white_point.png");
                            button.setIcon(icon);
                        }
                    default -> button.setBackground(Color.red);
                }
            }
        }
    }
    
    public int getBoardSize(){
        return this.boardSize;
    }    
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    public void setFeketeDb(int db){
        this.feketeDb = db;
    }
    
    public void setFeherDb(int db){
        this.feherDb = db;
    }
    
    public int getFeketeDb(){
        return this.feketeDb;
    }
    
    public int getFeherDb(){
        return this.feherDb;
    }
    
    public void setKor(int n){
        this.kor = n;
    }
    
    public int getKor(){
        return this.kor;
    }
    
    public void setKilep(String ki){
        this.kilep = ki;
    }
    
    public String getKilep(){
        return this.kilep;
    }
    
    public void movePiece(int FromX, int FromY, int toX, int toY){
        if(toX >= board.getBoardSize() || toY >= board.getBoardSize() || toX < 0 || toY < 0){
            buttons[FromX][FromY].setIcon(null);
            board.set(FromX, FromY, "none");
        }
        else{
            if(!"none".equals(board.get(FromX, FromY).getValue()) && "none".equals(board.get(toX, toY).getValue())){
                if("black".equals(board.get(FromX, FromY).getValue())){
                    ImageIcon icon = new ImageIcon("C:\\Users\\Andor\\Desktop\\Harmadik félév\\Progtek\\Beadandó_2\\black_point.png");
                    buttons[toX][toY].setIcon(icon);
                    board.set(toX, toY, "black");
                    buttons[FromX][FromY].setIcon(null);
                    board.set(FromX, FromY, "none");
                }
                else{
                    ImageIcon icon = new ImageIcon("C:\\Users\\Andor\\Desktop\\Harmadik félév\\Progtek\\Beadandó_2\\white_point.png");
                    buttons[toX][toY].setIcon(icon);
                    board.set(toX, toY, "white");
                    buttons[FromX][FromY].setIcon(null);
                    board.set(FromX, FromY, "none");
                }
            }
        }
    }

    class ButtonListener implements ActionListener {
        private final int x;
        private final int y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!clickedOn){
                if (board.get(x, y).getValue() != null && getKilep().equals(board.get(x, y).getValue())) {
                    fromX = x;
                    fromY = y;
                    clickedOn = true;
                    if("black".equals(getKilep())){
                        setKilep("white");
                    }
                    else if("white".equals(getKilep())){
                        setKilep("black");
                    }
                }
            }
            else{
                if(fromX != x || fromY != y){
                    clickedOn = false;
                    if(fromX + 1 == x && fromY == y){
                        if("none".equals(board.get(x, y).getValue())){
                            movePiece(fromX, fromY, x, y);
                        }
                        else{
                            int varX = x;
                            while(varX != board.getBoardSize() && !"none".equals(board.get(varX, y).getValue())){
                                varX++;
                            }
                            varX--;
                            while(varX != fromX - 1){
                                movePiece(varX, fromY, varX + 1, fromY);
                                varX--;
                            }
                        }
                    }
                    else if(fromX - 1 == x && fromY == y){
                        if("none".equals(board.get(x, y).getValue())){
                            movePiece(fromX, fromY, x, y);
                        }
                        else{
                            int varX = x;
                            while(varX != -1 && !"none".equals(board.get(varX, y).getValue())){
                                varX--;
                            }
                            varX++;
                            while(varX != fromX + 1){
                                movePiece(varX, fromY, varX - 1, fromY);
                                varX++;
                            }
                        }
                    }
                    else if(fromX == x && fromY + 1 == y){
                        if("none".equals(board.get(x, y).getValue())){
                            movePiece(fromX, fromY, x, y);
                        }
                        else{
                            if("none".equals(board.get(x, y).getValue())){
                            movePiece(fromX, fromY, x, y);
                            }
                            else{
                                int varY = y;
                                while(varY != board.getBoardSize() && !"none".equals(board.get(x, varY).getValue())){
                                    varY++;
                                }
                                varY--;
                                while(varY != fromY - 1){
                                    movePiece(fromX, varY, fromX, varY + 1);
                                    varY--;
                                }
                            }
                        }
                    }
                    else if(fromX == x && fromY - 1 == y){
                        if("none".equals(board.get(x, y).getValue())){
                            movePiece(fromX, fromY, x, y);
                        }
                        else{
                            if("none".equals(board.get(x, y).getValue())){
                            movePiece(fromX, fromY, x, y);
                            }
                            else{
                                int varY = y;
                                while(varY != -1 && !"none".equals(board.get(x, varY).getValue())){
                                    varY--;
                                }
                                varY++;
                                while(varY != fromY + 1){
                                    movePiece(fromX, varY, fromX, varY - 1);
                                    varY++;
                                }
                            }
                        }
                    }
                    clickedOn = false;
                    int fekDb = 0;
                    int fehDb = 0;
                    for(int i = 0; i < board.getBoardSize(); i++){
                        for(int j = 0; j < board.getBoardSize(); j++){
                            if("black".equals(board.get(i, j).getValue())){
                                fekDb++;
                            }
                            else if("white".equals(board.get(i, j).getValue())){
                                fehDb++;
                            }
                        }
                    }
                    System.out.println("Fehér darab: " + fehDb + "\n" + "Fekete darab: " +  fekDb + "\nKör: " + getKor() + "/" + board.getBoardSize() * 5);
                    setFeketeDb(fekDb);
                    setFeherDb(fehDb);
                    setKor(getKor() + 1);
                    if(getKor() == board.getBoardSize() * 5){
                        String uzenet;
                        if(fekDb > fehDb){
                            uzenet = "Gratulálok, nyert a fekete!";
                        }
                        else if(fekDb < fehDb){
                            uzenet = "Gratulálok, nyert a fehér!";
                        }
                        else{
                            uzenet = "Döntetlen lett!";
                        }
                        
                        Object[] options = {"Új játék kezdése", "Kilépés"};
                        int choice = JOptionPane.showOptionDialog(
                                boardPanel,
                                uzenet,
                                "Vége a játéknak!",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);

                        if (choice == JOptionPane.YES_OPTION) {
                            gameGUI game = new gameGUI();
                        }
                        else{
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }
}
