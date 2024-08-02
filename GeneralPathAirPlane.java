import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class GeneralPathAirPlane extends Frame
{
  //Constructor
  GeneralPathAirPlane()
  {
    //Enables the closing of the window.
    addWindowListener(new MyFinishWindow());
  }
  
  public void desenhaNuvens(Graphics2D g2d){
      for(int i=0; i<10; i++){
          int altura = new Random().nextInt(70,310);
          int distancia = new Random().nextInt(0,30);
          geraNuvem(g2d, (i*100) + distancia, altura);
      }
  }
  
  public void geraNuvem(Graphics2D g2d, int x, int y){
      Ellipse2D.Double el2 = new Ellipse2D.Double(x,y,50,25);
//      g2d.draw(el2);
      Ellipse2D.Double el3 = new Ellipse2D.Double(x+20,y-20,50,25);
//      g2d.draw(el3);
      Ellipse2D.Double el4 = new Ellipse2D.Double(x+40,y,50,25);
      Area e1 = new Area(el2);
      Area e2 = new Area(el3);
      Area e3 = new Area(el4);
      
      e1.add(e2);
      e1.add(e3);
      g2d.draw(e1);
  }


  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);

    //The GeneralPath to decribe the plane.
    //Avião comprimento: 110 altura: 40
    GeneralPath gp = new GeneralPath();
      gp.moveTo(50, 80);
      gp.lineTo(50,110);
      gp.lineTo(90,110);
      gp.lineTo(90,120);
      gp.lineTo(130,110);
      gp.lineTo(160,110);
      gp.curveTo(160, 100, 130 , 90, 140, 90);
      gp.lineTo(70,90);
      gp.lineTo(50,80);

      //Função para criar nuvens
      
      
      //NUVENS
      geraNuvem(g2d, 90, 150);
      geraNuvem(g2d, 95, 270);
      geraNuvem(g2d, 330, 75);
      geraNuvem(g2d, 310, 283);
      geraNuvem(g2d, 490, 190);
      geraNuvem(g2d, 620, 315);
      geraNuvem(g2d, 670, 95);
      geraNuvem(g2d, 710, 240);
      geraNuvem(g2d, 500, 350);
      geraNuvem(g2d, 190, 200);
      geraNuvem(g2d, 850, 111);
      geraNuvem(g2d, 840, 275);
    
    //PRÉDIOS
    gp.moveTo(50, 400 * 1.5);
    gp.lineTo(50, 310 * 1.5);
    gp.lineTo(90,310 * 1.5);
    gp.lineTo(90,370 * 1.5);
    gp.lineTo(110,370 * 1.5);
    gp.lineTo(110,290 * 1.5);
    gp.lineTo(150,290 * 1.5);
    gp.lineTo(150,330 * 1.5);
    gp.lineTo(190,330 * 1.5);
    gp.lineTo(190,230 * 1.5);
    gp.lineTo(230,250 * 1.5);
    gp.lineTo(230,350 * 1.5);
    gp.lineTo(250,350 * 1.5);
    gp.lineTo(250,290 * 1.5);
    gp.lineTo(290,290 * 1.5);
    gp.lineTo(290,310 * 1.5);
    gp.lineTo(330,310 * 1.5);
    gp.lineTo(330,240 * 1.5);
    gp.lineTo(350,210 * 1.5);
    gp.lineTo(370,240 * 1.5);
    gp.lineTo(370,290 * 1.5);
    gp.lineTo(410,290 * 1.5);
    gp.lineTo(410,370 * 1.5);
    gp.lineTo(430,370 * 1.5);
    gp.lineTo(430,260 * 1.5);
    gp.lineTo(470,260 * 1.5);
    gp.lineTo(470,330 * 1.5);
    gp.lineTo(500,330 * 1.5);
    
    gp.lineTo(50 + 490, 310 * 1.5);
    gp.lineTo(90 + 490,310 * 1.5);
    gp.lineTo(90 + 490,370 * 1.5);
    gp.lineTo(110 + 490,370 * 1.5);
    gp.lineTo(110 + 490,290 * 1.5);
    gp.lineTo(150 + 490,290 * 1.5);
    gp.lineTo(150 + 490,330 * 1.5);
    gp.lineTo(190 + 490,330 * 1.5);
    gp.lineTo(190 + 490,230 * 1.5);
    gp.lineTo(230 + 490,250 * 1.5);
    gp.lineTo(230 + 490,350 * 1.5);
    gp.lineTo(250 + 490,350 * 1.5);
    gp.lineTo(250 + 490,290 * 1.5);
    gp.lineTo(290 + 490,290 * 1.5);
    gp.lineTo(290 + 490,310 * 1.5);
    gp.lineTo(330 + 490,310 * 1.5);
    gp.lineTo(330 + 490,240 * 1.5);
    gp.lineTo(350 + 490,210 * 1.5);
    gp.lineTo(370 + 490,240 * 1.5);
    gp.lineTo(370 + 490,290 * 1.5);
    gp.lineTo(410 + 490,290 * 1.5);
    gp.lineTo(410 + 490,370 * 1.5);
    gp.lineTo(430 + 490,370 * 1.5);
    gp.lineTo(430 + 490,260 * 1.5);
    gp.lineTo(470 + 490,260 * 1.5);
    gp.lineTo(470 + 490,330 * 1.5);
    gp.lineTo(500 + 490,330 * 1.5);
    
    g2d.draw(gp);


    g2d.setStroke(new BasicStroke(1.0f));
    //Draw a coordinate system.
    drawSimpleCoordinateSystem(1000,600,g2d);

  }



  /**
  * Draws a coordinate system (according to the window coordinates).
  *
  * @param xmax     x-coordinate to which the x-axis should extend.
  * @param ymax     y-coordinate to which the y-axis should extend.
  * @param g2d      Graphics2D object for drawing.
  */
  public static void drawSimpleCoordinateSystem(int xmax, int ymax,
                                                Graphics2D g2d)
  {
    int xOffset = 30; //tava 30
    int yOffset = 50; //tava 50
    int step = 20;
    String s;
    //Remember the actual font.
    Font fo = g2d.getFont();
    //Use a small font.
    g2d.setFont(new Font("sansserif",Font.PLAIN,9));
    //x-axis.
    g2d.drawLine(xOffset,yOffset,xmax,yOffset);
    //Marks and labels for the x-axis.
    for (int i=xOffset+step; i<=xmax; i=i+step)
    {
      g2d.drawLine(i,yOffset-2,i,yOffset+2);
      g2d.drawString(String.valueOf(i),i-7,yOffset-7);
    }

    //y-axis.
    g2d.drawLine(xOffset,yOffset,xOffset,ymax);

    //Marks and labels for the y-axis.
    s=" "; //for indention of numbers < 100
    for (int i=yOffset+step; i<=ymax; i=i+step)
    {
      g2d.drawLine(xOffset-2,i,xOffset+2,i);
      if (i>1000){s="";}
      g2d.drawString(s+String.valueOf(i),xOffset-25,i+5);
    }

    //Reset to the original font.
    g2d.setFont(fo);
  }


   public static void main(String[] argv)
  {
    GeneralPathAirPlane f = new GeneralPathAirPlane();
    f.setTitle("GeneralPath example");
    f.setSize(1000,600);
    f.setVisible(true);
  }
}
