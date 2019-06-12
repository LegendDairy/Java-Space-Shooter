import java.util.LinkedList;
import java.awt.Graphics;

/* Handles the different game objects: rendering and ticks */
public class GameObjectHandler
{
  LinkedList<GameObject> objects = new LinkedList<GameObject>();

  public void tick()
  {
    for(int i=0 ;i < objects.size();i++)
    {
      GameObject tmp = objects.get(i);
      tmp.tick();
    }
  }

  public void render(Graphics g)
  {
    for(int i=0 ;i < objects.size();i++)
    {
      GameObject tmp = objects.get(i);
      tmp.render(g);
    }
  }

  public void addObject(GameObject object)
  {
    this.objects.add(object);
  }
  public void removeObject(GameObject object)
  {
    this.objects.remove(object);
  }

}
