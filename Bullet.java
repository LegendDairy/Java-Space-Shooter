import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;


public class Bullet extends GameObject
{
  private static int WIDTH = 16;
  private static int HEIGHT = 16;
  private GameObjectHandler go_handler;
  private Healthbar hud;
  private Random r;

  public Bullet(float x, float y, ID id, GameObjectHandler go_handler, Healthbar hud)
  {
    super(x,y,0, -5,id);
    this.go_handler = go_handler;
    this.hud = hud;
    r = new Random();
  }

  public void tick()
  {
    y += vy;

    if(x <= 0 || x >= Game.WIDTH)
    {
      go_handler.removeObject(this);
    }
    if(y <= 0 || y >= Game.HEIGHT)
    {
      go_handler.removeObject(this);
    }

    for(int i =0; i<go_handler.objects.size(); i++)
    {
      GameObject tmp = go_handler.objects.get(i);
      if(tmp.id == ID.Asteroid)
      {
        Rectangle bullet = new Rectangle((int)this.x, (int)this.y, 8, 8);
        if(bullet.getBounds().intersects(tmp.getBounds()))
        {
          tmp.setParameters(r.nextInt(Game.WIDTH), 0, 4-r.nextInt(5), 4);
          hud.increaseScore(10);
        }
      }
      else if(tmp.id == ID.EnemyAdvanced)
      {
        Rectangle bullet = new Rectangle((int)this.x, (int)this.y, 8, 8);
        if(bullet.getBounds().intersects(tmp.getBounds()))
        {
          tmp.setParameters(r.nextInt(Game.WIDTH), 0, 4-r.nextInt(5), 4);
          hud.increaseScore(50);
        }
      }
    }

  }

  public Rectangle getBounds()
  {
    return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
  }

  public void render(Graphics g)
  {
    g.setColor(Color.green);
    g.fillOval((int)x,(int)y,8,8);
  }
}
