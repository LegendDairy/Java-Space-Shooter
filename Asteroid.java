import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Asteroid extends GameObject
{
  private static int WIDTH = 16;
  private static int HEIGHT = 16;
  private BufferedImage sprite;
  private Random r;


  public Asteroid(float x, float vx, ID id, BufferedImage sprite)
  {
    super(x,0,vx,3,id);
    this.sprite = sprite;
    r = new Random();
  }

  public void tick()
  {
    x += vx;
    y += vy;

    if(x <= 0 || x >= Game.WIDTH - 32)
    {
      x = r.nextInt(Game.WIDTH);
      vx = 4 - r.nextInt(9);
      y = 0;
    }

    if(y >= Game.HEIGHT - 32)
    {
      x = r.nextInt(Game.WIDTH);
      vx = 4 - r.nextInt(9);
      y=0;
    }

  }

  public Rectangle getBounds()
  {
    return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
  }

  public void render(Graphics g)
  {
    g.drawImage(sprite, (int)x,(int)y,null);
  }
}
