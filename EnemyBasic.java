import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class EnemyBasic extends GameObject
{
  private static int WIDTH = 16;
  private static int HEIGHT = 16;

  public EnemyBasic(int x, int y, int vx, int vy, ID id)
  {
    super(x,y,vx,vy,id);
  }

  public void tick()
  {
    x += vx;
    y += vy;

    if(x <= 0 || x >= Game.WIDTH - 32)
      vx *= -1;
    if(y <= 0 || y >= Game.HEIGHT - 32)
      vy *= -1;
    /*x = Game.clamp(x, 0, Game.WIDTH);
    y = Game.clamp(y, 0, Game.HEIGHT);*/

  }

  public Rectangle getBounds()
  {
    return new Rectangle(x,y,WIDTH,HEIGHT);
  }

  public void render(Graphics g)
  {
    g.setColor(Color.white);
    g.fillRect(x,y,WIDTH,HEIGHT);
  }
}
