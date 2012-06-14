import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class World {
    
    public Rectangle[] blocks;
    public boolean[] isSolid;
    private Image[] blockImg;
    public final int arrayNum = 1400;
    //Block images
    private Image BLOCK_DIRT_TOP, BLOCK_SKY;
    
    private int x, y;
    
    public World(){
        try {
            BufferedImageLoader loader = new BufferedImageLoader();
            BLOCK_DIRT_TOP = loader.loadImage("images/tile_topDirt1.png");
            BLOCK_SKY = loader.loadImage("images/tile_sky.png");
            blocks = new Rectangle[arrayNum];
            blockImg = new Image[arrayNum];
            isSolid = new boolean[arrayNum];
            
            loadArrays();
        } catch (IOException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadArrays(){
        for(int i = 0; i < arrayNum; i++){
            if(x >= 1400){
                x = 0;
                y += 20;
            }
            if(i >= 0 && i < 1330){
                blockImg[i] = BLOCK_SKY;
                isSolid[i] = false;
                blocks[i] = new Rectangle(x, y, 20, 20);
            }
            if(i >= 1330 && i < 90000){
                blockImg[i] = BLOCK_DIRT_TOP;
                isSolid[i] = true;
                blocks[i] = new Rectangle(x, y, 20, 20);
            }
            x += 20;
        }
    }
    
    public void draw(Graphics g){
        for(int i = 0; i < arrayNum; i++){
            g.drawImage(blockImg[i], blocks[i].x, blocks[i].y, null);
        }
    }
    
}

