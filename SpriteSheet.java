import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class SpriteSheet
{
  private BufferedImage image;

  public SpriteSheet(String path)
  {
    try{
      this.image = ImageIO.read(getClass().getResource(path));
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  public BufferedImage grabImage(int col, int row, int width, int heigth)
  {
    BufferedImage img = image.getSubimage((col*16), row*16, width, heigth);
    return img;
  }
}
