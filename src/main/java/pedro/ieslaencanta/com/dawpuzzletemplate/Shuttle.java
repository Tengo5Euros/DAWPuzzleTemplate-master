package pedro.ieslaencanta.com.dawpuzzletemplate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author DAWTarde
 */
public class Shuttle {
    private int posX = 1, posY = 1805;
    private int posXF = 0, posYF = 1545;
    private float angle;
    private Bubble actual;
    private Bubble siguiente;
    private Float incr = 180f / 128f;
    private Point2D center;
    private static final int HEIGHT = 39;
    private static final int WIDTH = 62;

    private static final int HEIGHT_F = 64, WIDTH_F = 64;

    private static final float MIN_ANGLE = 0f;
    private static final float MAX_ANGLE = 180.0f;
    private boolean debug;


    public Shuttle(Point2D center) {
        this.center = center;
        this.angle=90.0f;
    }

    /**
     * @return the Actual
     */
    public Bubble getActual() {
        return actual;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private Bubble generateBall() {
        this.actual.init(center, MIN_ANGLE);
        return this.actual;
    }

    public void paint(GraphicsContext gc) {
        Resources r = Resources.getInstance();
        Point2D p= this.getArrowPoint2D();
        gc.drawImage(r.getImage("spriters"),
                this.posX,
                this.posY,
                Shuttle.WIDTH,
                Shuttle.HEIGHT,
                (this.center.getX() - 60 / 2) * Game.SCALE,
                (this.center.getY() - 40 / 2) * Game.SCALE,
                Shuttle.WIDTH * Game.SCALE,
                Shuttle.HEIGHT * Game.SCALE);


if(p!=null){
    gc.drawImage(r.getImage("spriters"),
            this.posXF+ p.getX()*65,
            this.posYF+ p.getY()*65,
            Shuttle.WIDTH_F,
            Shuttle.HEIGHT_F,
            (this.center.getX() - 64 / 2) * Game.SCALE,
            (this.center.getY() - 64 / 2) * Game.SCALE,
            Shuttle.WIDTH_F * Game.SCALE,
            Shuttle.HEIGHT_F * Game.SCALE);
}


        if (this.isDebug()) {
            gc.setStroke(Color.RED);

            gc.strokeText(this.angle + "º", this.center.getX(), this.center.getY());
        }


    }
/*
1.Dado un angulo de 90 a 0 pasar una imagen de 0-64
2. Sacar la fila imagen/16 y columna imagen/16
3.Crear metodo getArrowPoint2D que devuelve un Point2D con la fila y la columna
4. Pintar la flecha con gc.drawImage(todos, iniciox + w*Columna, inicioy + h*column
 */
//(90- el angulo= i/1,405=resultado)

    public Point2D getArrowPoint2D() {
        Point2D p = null;

        int imagen;
        int fila, columna;

        if (this.angle < 90) {
            imagen =(int)( (90.0f - this.angle) / this.incr);
            fila = imagen / 16;
            columna = imagen % 16;
            p = new Point2D(columna, fila);

        }
        return p;
    }

    /*public Bubble Shoot(){
        Bubble tempo= this.siguiente;
    }*/

    public void moveRight() {

        this.angle -= this.incr;
        if (this.angle < Shuttle.MIN_ANGLE) {
            this.angle = Shuttle.MIN_ANGLE;
        }
    }

    private void updateAngle() {

    }

    public void moveLeft() {
        this.angle += this.incr;
        if (this.angle > Shuttle.MAX_ANGLE) {
            this.angle = Shuttle.MAX_ANGLE;
        }

    }

    public boolean isDebug() {
        return this.debug;
    }

    public void TicTac() {

    }
}