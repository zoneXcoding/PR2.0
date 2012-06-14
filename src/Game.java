import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable
{
    Player p = new Player();
    private Graphics dbg;
    private Image dbImage;
    private Image Background;
    private String title = "Para Raiders 2.0";
    public Game()
    {
        this.setSize(800, 700);
        this.setTitle(title);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new AL());
        addMouseListener(new Mouse());
        init();
    }
    public void init()
    {                                                                        //Load Images
        BufferedImageLoader loader = new BufferedImageLoader();
    	BufferedImage spriteSheet = null;
	try 
	{
            Background = loader.loadImage("Background.jpg");
	} 
	catch (IOException e) 
	{
            System.err.println(e);
	} 
    }
    public void paint(Graphics g)
    {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    public void paintComponent(Graphics g)
    {
                                                                                //Repaint the screen
        p.draw(g);
        repaint();
    }
    public class AL extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            p.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
            p.keyReleased(e);
        }
    }
    public class Mouse extends MouseAdapter
    {
	public void mousePressed(MouseEvent e)
	{
            int xCoord = e.getX();
            int yCoord = e.getY();
	}   
    }
    public void run() 
    {
        try
	{
            while (true)
            {
                long beforeTime;
                long timeTaken;
                long sleepTime;
        	beforeTime = System.nanoTime();
                p.playerMovement();
                timeTaken = System.nanoTime() - beforeTime;
                sleepTime = 10 - timeTaken / 100000;
        	    if(sleepTime <= 0)
    	    	sleepTime = 0;
                    Thread.sleep(sleepTime);
            }
        }
	catch(Exception e)
	{
            System.out.println(e);
	}
    }
    public static void main(String[] args) 
    {
        
    }
}
