package beadandó_2;

import java.awt.Point;
import java.util.Random;

public class Board {

    private final Field[][] board;
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        int blackDb = 0;
        int whiteDb = 0;
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field("none");
            }
        }
        
        while(blackDb < boardSize || whiteDb < boardSize){
            for (int i = 0; i < this.boardSize; ++i) {
                for (int j = 0; j < this.boardSize; ++j) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;
                    if(!"black".equals(board[i][j].getValue()) && !"white".equals(board[i][j].getValue())){
                        if(randomNumber == 1 && blackDb < boardSize){
                            board[i][j] = new Field("black");
                            blackDb++;
                        }else if(randomNumber == 2 && whiteDb < boardSize){
                            board[i][j] = new Field("white");
                            whiteDb++;
                        }else{
                            board[i][j] = new Field("none");
                        }
                    }
                }
            }
        }
    }
    
    public Field get(int x, int y) {
        return board[x][y];
    }
    
    public Field get(Point point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return get(x, y);
    }
    
    public void set(int x, int y, String value){
        board[x][y] = new Field(value);
    }

    public int getBoardSize() {
        return boardSize;
    }
}