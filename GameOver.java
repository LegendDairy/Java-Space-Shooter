import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class GameOver extends GameObject
{
  public GameOver(int score, GameObjectHandler go_handler)
  {
    super(0,0,0,0,ID.GameOver);
    for(int i =0; i<go_handler.objects.size(); i++)
    {
      GameObject tmp = go_handler.objects.get(i);
      if(/*tmp.id != ID.Healthbar && */ tmp.id != ID.GameOver)
      {
        go_handler.removeObject(tmp);
      }
    }
  }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
      g.setColor(Color.white);
      g.drawRect((Game.WIDTH/2-250/2), Game.HEIGHT/2-100/2, 250, 100);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      g.drawString("GAME OVER", Game.WIDTH/2-250/2+65, Game.HEIGHT/2-100/2+40);
    }
    public Rectangle getBounds()
    {
      return new Rectangle(0, 0, 0, 0);
    }
}
