import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Player extends GameObject
{
  private GameObjectHandler go_handler;
  private static int WIDTH = 32;
  private static int HEIGHT = 32;
  private Healthbar health_bar;
  /* Constructor */
  public Player(int x, int y, ID id, GameObjectHandler go_handler, Healthbar h)
  {
    /* Call the superclass constructor */
    super(x,y,0,0, id);
    this.go_handler = go_handler;
    this.health_bar = h;
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
        if(tmp.id == ID.EnemyBasic)
        {
          Rectangle player = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
          if(player.getBounds().intersects(tmp.getBounds()))
          {
            health_bar.reduceHealth(1);
          }
        }
      }

  }

  public Rectangle getBounds()
  {
    return new Rectangle(x,y,WIDTH,HEIGHT);
  }

  /* Render the game object. */
  public void render(Graphics g)
  {
    g.setColor(Color.green);
    g.fillRect(x,y,WIDTH,HEIGHT);
  }
}
