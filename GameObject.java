import java.awt.Graphics;
import java.awt.Rectangle;

/* Abstract class, cannot be instantiaded, but can be subclassed. */
public abstract class GameObject
{
  protected int x,y;    // Can only be accessed by objects that inherit the gameobject.
  protected ID id;      // ID of the object
  protected int vx, vy; // Velocity along x and y

  /* Constructor */
  public GameObject(int x, int y,int vx, int vy, ID id)
  {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.id = id;
  }

  /* Methods to be implemented by the subclass */
  public abstract void tick();
  public abstract void render(Graphics g);
  public abstract Rectangle getBounds();

  public void setx(int x)
  {
    this.x = x;
  }
  public void sety(int y)
  {
    this.y = y;
  }
  public int getx()
  {
    return this.x;
  }
  public int gety()
  {
    return this.x;
  }
  public void setID(ID id)
  {
    this.id=id;
  }
  public ID getID()
  {
    return this.id;
  }
  public void setvx(int vx)
  {
    this.vx = vx;
  }
  public void setvy(int vy)
  {
    this.vy = vy;
  }
  public int getvx()
  {
    return this.vx;
  }
  public int getvy()
  {
    return this.vy;
  }

}
