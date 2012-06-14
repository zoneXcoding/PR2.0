
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player 
{
    boolean A, D;
    int x, y, xDirection;
    public void draw(Graphics G)
    {
        
    }
    public void setXDirection(int xdir)
    {
        xDirection = xdir;
    }
    public void playerMovement()
    {
        x += xDirection;
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            setXDirection(1);
            D = true;
        }
         if(e.getKeyCode() == KeyEvent.VK_A)
        {
            setXDirection(-1);
            A = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
    }
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_D)
            D = false;
         if(e.getKeyCode() == KeyEvent.VK_A)
            A = false;
         if(e.getKeyCode() == KeyEvent.VK_D && A)
            setXDirection(-1);
         if(e.getKeyCode() == KeyEvent.VK_A && D)
            setXDirection(1);
        if(e.getKeyCode() == KeyEvent.VK_D && !A)
        {
            setXDirection(0);
        }
         if(e.getKeyCode() == KeyEvent.VK_A && !D)
        {
            setXDirection(0);
        }
    }
}
