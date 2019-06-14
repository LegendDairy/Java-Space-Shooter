import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/* Handles the keyboard input. */
public class KeyHandler extends KeyAdapter
{
  private GameObjectHandler go_handler;
  private Healthbar hud;
  private int playerspeed = 5;

  /* Bolean array to keep track of pressed down keys*/
  private Boolean [] key_down = new Boolean[4];

  public KeyHandler (GameObjectHandler go_handler, Healthbar hud)
  {
    this.go_handler = go_handler;
    this.hud = hud;

    key_down[0] = false;  // key: D
    key_down[1] = false;  // key: Q
    key_down[2] = false;  // key: Z
    key_down[3] = false;  // key: Q
  }

  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();

    for(int i=0; i<go_handler.objects.size();i++)
    {
      GameObject tmp = go_handler.objects.get(i);
      if(tmp.getID() == ID.Player)
      {
        if(key == KeyEvent.VK_SPACE)
        {
          go_handler.addObject(new Bullet(tmp.getx()+12, tmp.gety(), ID.Bullet, go_handler, hud));
        }
        if(key == KeyEvent.VK_D)
        {
          tmp.setvx(playerspeed);
          key_down[0] = true;
        }
        if(key == KeyEvent.VK_Q)
        {
          tmp.setvx(-playerspeed);
          key_down[1] = true;
        }
        /*if(key == KeyEvent.VK_Z)
        {
          tmp.setvy(-playerspeed);
          key_down[2] = true;
        }
        if(key == KeyEvent.VK_S)
        {
          tmp.setvy(playerspeed);
          key_down[3] = true;
        }*/
      }
    }
    if(key == KeyEvent.VK_ESCAPE)
      System.exit(1);

  }

  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();

    for(int i=0; i<go_handler.objects.size();i++)
    {
      GameObject tmp = go_handler.objects.get(i);
      if(tmp.getID() == ID.Player)
      {
        if(key == KeyEvent.VK_D)
        {
          key_down[0] = false;
          /* Check for 'stickey' keys */
          if(key_down[1])             // Is Q held down?
            tmp.setvx(-playerspeed);  // Set speed for Q
          else                        // Else set speed to zero
            tmp.setvx(0);
        }
        if(key == KeyEvent.VK_Q)
        {
          key_down[1] = false;
          if(key_down[0])
            tmp.setvx(playerspeed);
          else
            tmp.setvx(0);
        }
        if(key == KeyEvent.VK_Z)
        {
          key_down[2] = false;
          if(key_down[3])
            tmp.setvy(playerspeed);
          else
            tmp.setvy(0);
        }
        if(key == KeyEvent.VK_S)
        {
          key_down[3] = false;
          if(key_down[2])
            tmp.setvy(-playerspeed);
          else
            tmp.setvy(0);
        }
      }
    }


  }
}
