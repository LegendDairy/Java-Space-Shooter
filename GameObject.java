import java.awt.Graphics;
import java.awt.Rectangle;

/* Abstract class, cannot be instantiaded, but can be subclassed. */
public abstract class GameObject
{
  protected float x,y;    // Can only be accessed by objects that inherit the gameobject.
  protected ID id;      // ID of the object
  protected float vx, vy; // Velocity along x and y

  /* Constructor */
  public GameObject(float x, float y,float vx, float vy, ID id)
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

  public void setx(float x)
  {
    this.x = x;
  }
  public void sety(float y)
  {
    this.y = y;
  }
  public float getx()
  {
    return this.x;
  }
  public float gety()
  {
    return this.y;
  }
  public void setID(ID id)
  {
    this.id=id;
  }
  public ID getID()
  {
    return this.id;
  }
  public void setvx(float vx)
  {
    this.vx = vx;
  }
  public void setvy(float vy)
  {
    this.vy = vy;
  }
  public float getvx()
  {
    return this.vx;
  }
  public float getvy()
  {
    return this.vy;
  }

  public void setParameters(float x, float y, float vx, float vy)
  {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }
}
