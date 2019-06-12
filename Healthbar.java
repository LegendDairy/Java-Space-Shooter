import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Healthbar
{
  private int Health = 100;
  private int score = 0;

  public void reduceHealth(int h)
  {
    Health -= h;
    if(Health < 0)
      Health = 0;
  }

  public void increaseHealth(int h)
  {
    Health += h;
    if(Health > 100)
      Health = 100;
  }

  public void tick()
  {
    score++;
  }

  public void render(Graphics g)
  {
    g.setColor(Color.gray);
    g.fillRect(15, 15, 200, 32);
    g.setColor(Color.green);
    g.fillRect(15, 15, Health*2, 32);
    g.setColor(Color.white);
    g.drawRect(15, 15, 200, 32);

    g.drawString("Score: " + score, 10, 64);
  }
}
