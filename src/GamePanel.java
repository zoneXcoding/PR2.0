import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

    
public class GamePanel extends JPanel implements Runnable {
    //Double buffering
    private Image dbImage;
    private Graphics dbg;
    //JPanel variables
    static final int GWIDTH = 800, GHEIGHT = 425;
    static final Dimension gameDim = new Dimension(GWIDTH, GHEIGHT);
    //Game variables
    private Thread game;
    private volatile boolean running = false;
    private long period = 6*1000000; //ms -> nano
    private static final int DELAYS_BEFORE_YEILD = 10;
    //Game Objects
    World world;
    Player p1;
    Shoot s;
    
    public GamePanel(){
        world = new World();
        p1 = new Player(world);
        s = new Shoot();
        
        setPreferredSize(gameDim);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();
        //Handle all key inputs from user
        addMouseListener(new MouseAdapter(){
	public void mousePressed(MouseEvent e)
	{
		int xCoord = e.getX();
		int yCoord = e.getY();
                s.MousePressed(e);
        }
        });
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                p1.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e){
                p1.keyReleased(e);
            }
            @Override
            public void keyTyped(KeyEvent e){
                p1.keyTyped(e);
            }
        });
    }

    private void paintScreen(){
        Graphics g;
        try{
            g = this.getGraphics();
            if(dbImage != null && g != null){
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync(); //For some operating systems
            g.dispose();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    @Override
    public void run(){
        long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
        int delays = 0;
        while(running){
            beforeTime = System.nanoTime();
            
            gameUpdate();
            gameRender();
            paintScreen();
            s.updateBullets();
            
            afterTime = System.nanoTime();
            diff = afterTime - beforeTime;
            sleepTime = (period - diff) - overSleepTime;
            // If the sleep time is between 0 and the period, we can happily sleep
            if(sleepTime < period && sleepTime > 0){
                try{
                    game.sleep(sleepTime / 1000000L);
                    overSleepTime = 0;
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // The diff was greater than the period
            else if(diff > period){
                overSleepTime = diff - period;
            }
            // Accumulate the amount of delays, and eventually yeild
            else if(++delays >= DELAYS_BEFORE_YEILD){
                game.yield();
                delays = 0;
                overSleepTime = 0;
            }
            // The loop took less time than expected, but we need to make up
            // for overSleepTime
            else{
                overSleepTime = 0;
            }
//            // Print out game stats
//            log(
//                    "beforeTime:    " + beforeTime + "\n" +
//                    "afterTime:     " + afterTime + "\n" +
//                    "diff:          " + diff + "\n" +
//                    "sleepTime:     " + sleepTime / 1000000L + "\n" +
//                    "overSleepTime: " + overSleepTime / 1000000L + "\n" +
//                    "delays:        " + delays + "\n"
//            );
        }
    }
    
    private void gameUpdate(){
        if(running && game != null){
            p1.update();
        }
    }
    
    private void gameRender(){
        if(dbImage == null){ // Create the buffer
            dbImage = createImage(GWIDTH, GHEIGHT);
            if(dbImage == null){
                System.err.println("dbImage is still null!");
                return;
            }else{
                dbg = dbImage.getGraphics();
            }
        }
        //Clear the screen
        dbg.setColor(new Color(0, 148, 255));
        dbg.fillRect(0, 0, GWIDTH, GHEIGHT);
        //Draw Game elements
        draw(dbg);
    }
    
    /* Draw all game content in this method  world.draw(g);
        p1.draw(g); */
    public void draw(Graphics g){
            world.draw(g);
            p1.draw(g);
            s.draw(g);
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        startGame();
    }
    
    private void startGame(){
        if(game == null || !running){
            game = new Thread(this);
            game.start();
            running = true;
        }
    }
    
    public void stopGame(){
        if(running){
            running = false;
        }
    }
    
    private void log(String s){
        System.out.println(s);
    }
}