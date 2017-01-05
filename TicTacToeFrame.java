import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window. Creates a Frame and array of Buttons which models tic tac toe.
 * 
 * @author Rajat Bansal (101019954)
 * @version December 12, 2016
 */

public class TicTacToeFrame extends TicTacToe implements ActionListener 
{ 
    private JButton[][] btn;
    private JPanel[] pnlrow;
    int count = 0;
    String player = "X";
    String winner = "";
    JMenuItem itm_new;
    JMenuItem itm_quit;
    JFrame frm;
    JLabel lbl;
    /** 
     * Creates a new Tic tac toe board and creates buttons and gui for it. Creates a menu bar and sets keyboard shortcuts for new game and quit. 
     * No input parameters
     */
    public TicTacToeFrame(){
        frm = new JFrame("TicTacToe");
        JPanel pnlmain = new JPanel();
        pnlmain.setLayout(new GridLayout(0,1,0,0));
        frm.getContentPane().add(pnlmain, BorderLayout.CENTER);
        pnlrow = new JPanel[3];
        btn = new JButton[3][3];
        JPanel pnls = new JPanel();
        frm.getContentPane().add(pnls, BorderLayout.SOUTH);
        lbl  = new JLabel("Turn: " + player);
        pnls.add(lbl);
        JMenuBar mbar = new JMenuBar();
        frm.setJMenuBar(mbar);
        JMenu mn = new JMenu("File"); 
        mbar.add(mn);
        itm_new = new JMenuItem("New Game");
        mn.add(itm_new);
        itm_quit = new JMenuItem("Quit");
        mn.add(itm_quit);
        itm_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itm_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        for(int i = 0 ; i <3; i++){ 
            pnlrow[i] = new JPanel();
            pnlrow[i].setLayout(new GridLayout(0,3,0,0));
            pnlmain.add(pnlrow[i]);            
            for(int j = 0; j < 3; j++){ 
                btn[i][j] = (new JButton());
                btn[i][j].addActionListener(this);
                pnlrow[i].add(btn[i][j]);
            }

        }
        itm_new.addActionListener(this);
        itm_quit.addActionListener(this);
        frm.setVisible(true);
        frm.pack();
        frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
    }
/**
 * Checks for buttons pressed and if its a menu item, performs appropriate function. If its a button, disables and sets text for it. If winner is declared, disables 
 * all the buttons and outputs dialog for winner. 
 */
    public void actionPerformed(ActionEvent e){
        if(count % 2 == 0){
            player = "X";
            lbl.setText("Turn: O");
        }
        else{
            player = "O";
            lbl.setText("Turn: X");
        }
        if(e.getSource() == itm_new){
            for(int k = 0 ; k < 3; k++){
                for(int l = 0; l < 3; l++){
                    btn[k][l].setText("");
                    btn[k][l].setEnabled(true);
                    count = 0;
                }
            }
        }
        if(e.getSource() == itm_quit){
            System.exit(0);
        }
        else{
            count++;
            JButton chose = (JButton) e.getSource();
            chose.setText(player);
            chose.setEnabled(false);

            if(haveWinner(btn) == true){
                winner = player;
                JOptionPane.showMessageDialog(frm,"Winner is: " + winner);
                for(int k = 0 ; k < 3; k++){
                    for(int l = 0; l < 3; l++){
                        btn[k][l].setEnabled(false);
                    }
                }
            }
            if(count == 9){
                winner = "TIE";
                JOptionPane.showMessageDialog(frm,"It is a: " + winner);
            }
        }

    }
/**
 * Checks winner by checking the entire board if more than 4 turns occured. Checks by compaing text of each button 
 * Input parameter is the array of buttons 
 * Output - boolean 
 */
    public boolean haveWinner(JButton b[][]){
        if(count < 4) return false; 
        for(int i = 0; i < 3; i++){
            if(b[i][0].getText() == "X" || b[i][0].getText() == "O"){
                if(b[i][0].getText() == b[i][1].getText() && b[i][0].getText() == b[i][2].getText())return true; 
            }
        }
        for(int i = 0; i < 3; i++){
            if(b[0][i].getText() == "X" || b[0][i].getText() == "O"){
                if(b[0][i].getText() ==b[1][i].getText() && 
                b[0][i]==b[2][i])return true; 
            }
        }
        if(b[1][1].getText() == "X" || b[1][1].getText() == "O"){
            if(b[0][0].getText() == b[1][1].getText() && 
            b[0][0].getText() == b[2][2].getText())return true;
            if(b[0][2].getText() == b[1][1].getText() && 
            b[0][2].getText() == b[2][0].getText())return true;
        }
        return false;
    }
}
