import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
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