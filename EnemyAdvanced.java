import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.lang.Math;

public class EnemyAdvanced extends GameObject
{
  private static int WIDTH = 16;
  private static int HEIGHT = 16;
  private float distance;
  private GameObjectHandler handler;

  public EnemyAdvanced(float x, float y, ID id, GameObjectHandler go_handler)
  {
    super(x,y,0,0,id);
    handler = go_handler;
  }

  public void tick()
  {
    x += vx;
    y += vy;
    for(int i=0; i<handler.objects.size(); i++)
    {
      GameObject tmp = handler.objects.get(i);
      if(tmp.id == ID.Player)
      {
        float delta_x = (tmp.getx() - x + WIDTH/2);
        float delta_y = (tmp.gety() - y +  WIDTH/2);
        distance = (float)Math.sqrt(((tmp.getx()-x)*(tmp.getx()-x)+(tmp.gety()-y)*(tmp.gety()-y)));
        vx = 2*(delta_x/distance);
        vy = 3;
      }
    }

    if(y>Game.HEIGHT)
    {
      y = 0;
    }
  }

  public Rectangle getBounds()
  {
    return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
  }

  public void render(Graphics g)
  {
    g.setColor(Color.red);
    g.fillRect((int)x,(int)y,WIDTH,HEIGHT);
  }

}
