import java.awt.Color;
import javax.swing.JFrame;

public class Game extends JFrame{
    
    GamePanel gp;
    
    public Game(){
        gp = new GamePanel();
        setSize(800, 425);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(gp);
    }
    
    public static void main(String[] args){
        Game g = new Game();
    } 
}