import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class Shoot 
{
    private int x, y;
    public int bulletX[] = new int[100];
    public int bulletY[] = new int[100];
    public int bulletXdirection[] = new int[100];
    public int bulletYdirection[] = new int[100];
    private int select;
    public Rectangle bullets[] = new Rectangle[100];
    public Shoot()
    {
        select = 0;
        for(int i = 0; i < 100; i++)
        {
            bulletX[i] = -500;
            bulletY[i] = 0;
            bullets[i] = new Rectangle(bulletX[i], bulletY[i], 10, 5);

        }
    }
    public void MousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
        bulletX[select] = x += 10;
        bulletY[select] = y += 10;
    }
    public void updateBullets()
    {
        for(int i = 0; i < 500; i++)
        {
            if(bulletX[i] >= 800 && bulletX[i] <= 0 && bulletY[i] >= 0 && bulletY[i] <= 700)
            {
                bulletX[i] += bulletXdirection[i];
                bulletY[i] += bulletYdirection[i];
            }
            bullets[i] = new Rectangle(bulletX[i], bulletY[i], 10, 5);
        }
    }
    public void paint(Graphics g)
    {
        for(int i = 0; i < 100; i++)
        {
            g.fillRect(bullets[i].x, bullets[i].y, bullets[i].width, bullets[i].height);
        }
    }
}