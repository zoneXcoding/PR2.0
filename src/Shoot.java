
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class Shoot 
{
    private int x, y;
    public int bulletX[] = new int[100];
    public int bulletY[] = new int[100];
    public int bulletXdirection[] = new int[100];
    public int bulletYdirection[] = new int[100];
    public Rectangle bullets[] = new Rectangle[100];
    public void MousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }
    public void updateBullets()
    {
        for(int i = 0; i < 500; i++)
        {
            
        }
    }
}
