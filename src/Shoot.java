import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class Shoot 
{
    Player P;
    private int x, y;
    public int bulletX[] = new int[100];
    public int bulletY[] = new int[100];
    public int bulletXdirection[] = new int[100];
    public int bulletYdirection[] = new int[100];
    private int select;
    public Rectangle bullets[] = new Rectangle[100];
    public Shoot(Player p)
    {
        P = p;
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
        bulletX[select] = P.playerRect.x;
        bulletX[select] += 10;
        bulletY[select] = P.playerRect.y;
        bulletY[select] += 20;
        int tempXdir;
        int tempYdir;
        tempXdir = x - bulletX[select];
        if(x >= bulletX[select])
        {
            while(tempXdir >= 5)
                tempXdir--;
            if(tempXdir == 4)
                bulletXdirection[select] = tempXdir;
        }
        else if(x <= bulletX[select])
        {
            while(tempXdir <= -5)
                tempXdir++;
            if(tempXdir == -4)
                bulletXdirection[select] = tempXdir;
        }
        select++;
        System.out.println(bulletXdirection[select]);
    }
    public void updateBullets()
    {
        for(int i = 0; i < 100; i++)
        {
            if(bulletX[i] <= 800 && bulletX[i] >= 0 && bulletY[i] >= 0 && bulletY[i] <= 700)
            {
                bulletX[i] += bulletXdirection[i];
                bulletY[i] += bulletYdirection[i];
            }
            bullets[i] = new Rectangle(bulletX[i], bulletY[i], 10, 5);
        }
    }
    public void draw(Graphics g)
    {
        for(int i = 0; i < 100; i++)
        {
            g.setColor(Color.GREEN);
            g.fillRect(bullets[i].x, bullets[i].y, bullets[i].width, bullets[i].height);
        }
    }
}