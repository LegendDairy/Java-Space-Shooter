
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
  public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;   // Dimensions of the canvas
  private Thread thread;                                      // Thread for main gameloop
  private Boolean running = false;
  private Random r;
  private Healthbar health_bar;

  /* Handles all the game objects. */
  private GameObjectHandler go_handler;

  /* Game constructor */
  public Game()
  {
    /* Create a window class */
    new Window(WIDTH, HEIGHT, "meme game", this);

    go_handler = new GameObjectHandler();
    health_bar = new Healthbar();

    this.addKeyListener(new KeyHandler(go_handler));

    r = new Random();

    go_handler.addObject(new Player(100,100,ID.Player, go_handler, health_bar));
    for(int i = 0; i < 10; i++)
    {
      go_handler.addObject(new EnemyBasic(r.nextInt(WIDTH),r.nextInt(HEIGHT),r.nextInt(6), r.nextInt(6), ID.EnemyBasic));
    }


  }

  public synchronized void start()
  {
    if (running)
      return;

    thread = new Thread(this);
    running = true;
    thread.start();
    /* still running here */

  }

  public synchronized void stop()
  {
    try
    {
      thread.join();
      running = false;
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

/* Game loop. */
/* This is the runnable part of the created thread.*/
  public void run()
  {
    this.requestFocus();
    double target = 60;
    double nsPerTick = 1000000000/target;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double unprocessed = 0.0;
    int fps = 0;  // frames per second
    int tps = 0;  // ticks per second
    Boolean canRender = false;

    while(running)
    {
      long now = System.nanoTime();
      unprocessed += (now-lastTime) / nsPerTick;
      lastTime = now;

      if(unprocessed >= 1)
      {
        tick();
        unprocessed--;
        tps++;
        canRender = true;
      }
      else canRender = false;

      try
      {
        Thread.sleep(1);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }

      if (canRender)
      {
        render();
        fps++;
      }

      if(System.currentTimeMillis() - 1000 > timer)
      {
        timer += 1000;
        //System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
        fps = 0;
        tps = 0;
      }
    }

      stop();
  }
  private void tick()
  {
    /* The handler class will loop through all the tick() functions of all the game objects. */
    go_handler.tick();
    health_bar.tick();
  }

  private void render()
  {
    BufferStrategy bs = this.getBufferStrategy();
    /* If this is the first render, there's no buffer strategy */
    if(bs == null)
    {
      /* Create a buffer strategy. */
      this.createBufferStrategy(6);
      /* return to set bs correctly.*/
      return;
    }
    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0,0, WIDTH, HEIGHT);

    /* The handler class will loop through all the render() functions of all the game objects. */
    go_handler.render(g);

    /* Render the healthbar */
    health_bar.render(g);

    g.dispose();
    bs.show();
  }

  public static int clamp(int var, int min, int max)
  {
    if(var >= max)
      return var = max;
    else if(var <= min)
      return var = min;
    else
      return var;
  }

  public static void main(String args[])
  {
    new Game();
  }

}
