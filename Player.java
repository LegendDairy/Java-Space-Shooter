import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends GameObject
{
  private GameObjectHandler go_handler;
  private static int WIDTH = 32;
  private static int HEIGHT = 32;
  private Healthbar health_bar;
  private BufferedImage sprite;

  /* Constructor */
  public Player(float x, float y, ID id, GameObjectHandler go_handler, Healthbar h, BufferedImage sprite)
  {
    /* Call the superclass constructor */
    super(x,y,0,0, id);
    this.go_handler = go_handler;
    this.health_bar = h;
    this.sprite = sprite;
  }

  public void tick()
  {
    /* Update position of the game object */
    this.x += this.vx;
    this.y += this.vy;

    /* Create a torus geometry */
    /*if(this.x > Game.WIDTH)
      this.x -= Game.WIDTH;
    if(this.x < 0)
      this.x += Game.WIDTH;
    if(this.y > Game.HEIGHT)
      this.y -= Game.HEIGHT;
    if(this.y < 0)
      this.y += Game.HEIGHT;*/
      x = Game.clamp(this.x, 0, Game.WIDTH - 33);
      y = Game.clamp(this.y, 0, Game.HEIGHT - 54);

      /* Check for collissions */
      for(int i =0; i<go_handler.objects.size(); i++)
      {
        GameObject tmp = go_handler.objects.get(i);
        if(tmp.id == ID.Asteroid)
        {
          Rectangle player = new Rectangle((int)this.x, (int)this.y, WIDTH, HEIGHT);
          if(player.getBounds().intersects(tmp.getBounds()))
          {
            health_bar.reduceHealth(1);
          }
        }
        if(tmp.id ==  ID.EnemyAdvanced)
        {
          Rectangle player = new Rectangle((int)this.x, (int)this.y, WIDTH, HEIGHT);
          if(player.getBounds().intersects(tmp.getBounds()))
          {
            health_bar.reduceHealth(5);
          }
        }
      }
  }

  public Rectangle getBounds()
  {
    return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
  }

  /* Render the game object. */
  public void render(Graphics g)
  {
    //g.setColor(Color.green);
    //g.fillRect((int)x,(int)y,WIDTH,HEIGHT);
    g.drawImage(sprite, (int)x,(int)y,null);
  }
}
