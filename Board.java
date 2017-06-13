import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel
        implements ActionListener {

    private final int B_WIDTH = 720;
    private final int B_HEIGHT = 360;

    private final int X_ORG  = 0;
    private final int Y_ORG = B_HEIGHT/2;

    private final int X_RES = B_WIDTH/360;
    private final int Y_RES = 100;

    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Image star;
    private Timer timer;
    private int x, y;
    private int deg;

    public Board() {
        initBoard();
    }


    private void initBoard() {

//        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        setDoubleBuffered(true);

        
        x = X_ORG;
        y = Y_ORG;
        deg = 0;
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSine(g);
    }

    private void drawSine(Graphics g) {

        g.setColor(Color.blue);
        g.fillOval(x,y,10,10);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        deg++;
        if(deg > 360) {
            deg = 0;
        }
        double radian = deg * Math.PI / 180.0;
        y = Y_ORG + (int)(Math.sin(radian) * Y_RES * -1);
        System.out.println("deg=" + deg + ",sin(deg)="+(int)(Math.sin(radian)*Y_RES * -1));
        x = X_ORG + deg * X_RES;

        repaint();
    }

    public double toRadians(int deg) {
        return deg * Math.PI / 180.0;
    }
}
