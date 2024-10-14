package beadandó_2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class gameGUI {    
    private JFrame frame;
    private BoardGUI boardGUI;
    
    public gameGUI(){
        frame = new JFrame("Kitolós");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardGUI = new BoardGUI(10);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Játék");
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("Új játék");
        gameMenu.add(newMenu);
        int[] boardSizes = new int[]{5, 10, 15, 20};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener((ActionEvent e) -> {
                frame.getContentPane().removeAll();
                boardGUI = new BoardGUI(boardSize);
                frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
                frame.pack();
            });
        }
        
        JMenuItem exitMenuItem = new JMenuItem("Kilépés");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        
        frame.pack();
        frame.setVisible(true);
    }
}

