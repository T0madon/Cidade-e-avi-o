package com.mycompany.generalpathairplane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GeneralPathAirPlane extends Frame implements KeyListener {
    private int airplaneY =80;  // Coordenada Y inicial do avião
    private final int airplaneX =50;  // Coordenada X fixa do avião
    private int backgroundX =0; //Coordenada X do cenário de fundo
    private int sceneSpeed =5; 
    private boolean isRunning = true;
    private int score = 0;// Placar 

    // Flags para controle de movimento
    private boolean movingUp = false;
    private boolean movingDown = false;

    private final ArrayList<Area> buildings = new ArrayList<>();
    private final ArrayList<Area> clouds = new ArrayList<>();

    private Image bufferImage; 
    private Graphics bufferGraphics;

    // Constructor
    public GeneralPathAirPlane() {
        addWindowListener(new MyFinishWindow());
        addKeyListener(this);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isRunning) {
                    backgroundX -= sceneSpeed;
                    if (backgroundX<=-470) {
                        backgroundX=0;
                    }
                    // Movimento do avião
                    if (movingUp){
                        airplaneY-=5;
                    }
                    if (movingDown){
                        airplaneY += 5;
                    }

                    checkCollision();
                    score++;  // Incrementa o placar a cada ciclo
                    sceneSpeed = 5 + score / 100;  // Aumenta a velocidade pelo placar 
                    repaint(); // Re-renderiza a tela
                }
            }
        }, 0, 50);  // Atualiza a cada 50 ms
    }
    
    private void checkCollision() {
        Area airplane = createAirplaneArea(airplaneX, airplaneY);

        for (Area building : buildings) {
            Area intersection = (Area) airplane.clone();
            intersection.intersect(building);
            if (!intersection.isEmpty()) {
                isRunning = false;
                return; // Colisão com prédio
            }
        }

        for (Area cloud : clouds) {
            Area intersection = (Area) airplane.clone();
            intersection.intersect(cloud);
            if (!intersection.isEmpty()) {
                isRunning = false;
                return; // Bateu na nuvem
            }
        }
    }

    public void geraNuvem(Graphics2D g2d, int x, int y) {
        Ellipse2D.Double el2 = new Ellipse2D.Double(x, y, 50, 25);
        Ellipse2D.Double el3 = new Ellipse2D.Double(x + 20, y - 20, 50, 25);
        Ellipse2D.Double el4 = new Ellipse2D.Double(x + 40, y, 50, 25);
        Area e1 = new Area(el2);
        Area e2 = new Area(el3);
        Area e3 = new Area(el4);

        e1.add(e2);
        e1.add(e3);
        g2d.setColor(new Color(255, 255, 255, 180));
        g2d.fill(e1);

        clouds.add(e1);
    }

    public void geraCidade(Graphics2D g2d, int x) {
        GeneralPath gp = new GeneralPath();

        gp.moveTo(30 + x,330 * 1.5);
        gp.lineTo(50 + x,330 * 1.5);
        gp.lineTo(50 + x,310 * 1.5);
        gp.lineTo(90 + x,310 * 1.5);
        gp.lineTo(90 + x,370 * 1.5);
        gp.lineTo(110 + x, 370 * 1.5);
        gp.lineTo(110 + x, 290 * 1.5);
        gp.lineTo(150 + x, 290 * 1.5);
        gp.lineTo(150 + x, 330 * 1.5);
        gp.lineTo(190 + x, 330 * 1.5);
        gp.lineTo(190 + x, 230 * 1.5);
        gp.lineTo(230 + x, 250 * 1.5);
        gp.lineTo(230 + x, 350 * 1.5);
        gp.lineTo(250 + x, 350 * 1.5);
        gp.lineTo(250 + x, 290 * 1.5);
        gp.lineTo(290 + x, 290 * 1.5);
        gp.lineTo(290 + x, 310 * 1.5);
        gp.lineTo(330 + x, 310 * 1.5);
        gp.lineTo(330 + x, 240 * 1.5);
        gp.lineTo(350 + x, 210 * 1.5);
        gp.lineTo(370 + x, 240 * 1.5);
        gp.lineTo(370 + x, 290 * 1.5);
        gp.lineTo(410 + x, 290 * 1.5);
        gp.lineTo(410 + x, 370 * 1.5);
        gp.lineTo(430 + x, 370 * 1.5);
        gp.lineTo(430 + x, 260 * 1.5);
        gp.lineTo(470 + x, 260 * 1.5);
        gp.lineTo(470 + x, 330 * 1.5);
        gp.lineTo(500 + x, 330 * 1.5);

        g2d.setColor(new Color(102,102,153));
        g2d.fill(gp);

        buildings.add(new Area(gp));
    }
    private Area createAirplaneArea(int x, int y) {
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x, y);
        gp.lineTo(x, y + 30);
        gp.lineTo(x + 40, y + 30);
        gp.lineTo(x + 40, y + 40);
        gp.lineTo(x + 80, y + 30);
        gp.lineTo(x + 110, y + 30);
        gp.curveTo(x + 110, y+20, x+80, y+10, x+90, y+10);
        gp.lineTo(x+20, y+10);
        gp.lineTo(x,y);
        return new Area(gp);
    }
    @Override
    public void update(Graphics g) {
        if (bufferImage ==null) {
            bufferImage =createImage(getWidth(),getHeight());
            bufferGraphics = bufferImage.getGraphics();
        }
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0,0, getWidth(), getHeight());
        bufferGraphics.setColor(getForeground());
        paint(bufferGraphics);

        g.drawImage(bufferImage,0,0,this);
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke bs= new BasicStroke(3.0f);
        g2d.setStroke(bs);

        // Fundo do céu
        g2d.setPaint(new GradientPaint(0, 0, new Color(135, 206, 250),0,getHeight(),new Color(0, 102,204)));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Desenha o avião
        Area airplane = createAirplaneArea(airplaneX, airplaneY);
        g2d.setColor(new Color(255,69,0));
        g2d.fill(airplane);
        buildings.clear();
        clouds.clear();
        geraCidade(g2d, backgroundX);
        geraCidade(g2d, backgroundX + 470);
       
        // NUVENS
        geraNuvem(g2d, 90+ backgroundX, 150);
        geraNuvem(g2d, 195 + backgroundX, 210);
        geraNuvem(g2d, 330 + backgroundX, 75);
        geraNuvem(g2d, 310+ backgroundX, 283);
        geraNuvem(g2d, 430+ backgroundX, 250);
        geraNuvem(g2d, 490+ backgroundX, 210); 
        geraNuvem(g2d, 610+ backgroundX, 150);
        
        geraNuvem(g2d, 90+ backgroundX+650, 150);
        geraNuvem(g2d, 195 + backgroundX+650, 210);
        geraNuvem(g2d, 330 + backgroundX+650, 75);
        geraNuvem(g2d, 310+ backgroundX+650, 283);
        geraNuvem(g2d, 430+ backgroundX+650, 250);
        geraNuvem(g2d, 490+ backgroundX+650, 210); 
        geraNuvem(g2d, 610+ backgroundX+650, 150);
        
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + score, 10, 50);
        
        if (!isRunning) {
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.setColor(Color.RED);
            g2d.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
        }
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_UP){
            movingUp = true;
        }
        if(key==KeyEvent.VK_DOWN){
            movingDown = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key==KeyEvent.VK_UP) {
            movingUp = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            movingDown = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    public static void main(String[] args) {
        GeneralPathAirPlane gp = new GeneralPathAirPlane();
        gp.setSize(470, 500);
        gp.setTitle("General Path Airplane");
        gp.setVisible(true);
    }
}
