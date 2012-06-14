import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {
    
    private World world;
    
    private Rectangle playerRect;
    private Image playerImg;
    
    protected int xDirection, yDirection;
    
    //Block variables
    private int hoverX, hoverY;
    private boolean hovering = false;
    private int x;
    
    public Player(World world){
        try {
            this.world = world;
            BufferedImageLoader loader = new BufferedImageLoader();
            playerImg = loader.loadImage("images/player.gif");
            playerRect = new Rectangle(50, 0, 10, 36);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setXDirection(int d){
        xDirection = d;
    }
    
    private void setYDirection(int d){
        yDirection = d;
    }
    
    public void update(){
        move();
        checkForCollision();
    }
    
    private void move(){
        playerRect.x += xDirection;
        playerRect.y += yDirection;
        gravity();
    }
    
    private void gravity(){
        for(int i = 0; i < world.arrayNum; i++){
            if(!world.isSolid[i] && !playerRect.intersects(world.blocks[i])){
                setYDirection(1);
            }else if(world.isSolid[i] && playerRect.intersects(world.blocks[i])){
                setYDirection(0);
            }
        }
    }
    
    private void checkForCollision(){
    }
    
    //Drawing methods
    public void draw(Graphics g){
        g.drawImage(playerImg, playerRect.x, playerRect.y, null);
    }
    
    //Key events
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_D){
            setXDirection(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
                setXDirection(-1);
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_D){
            setXDirection(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            setXDirection(0);
        }
    }
    public void keyTyped(KeyEvent e){
        
    }
    
}
