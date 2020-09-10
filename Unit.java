package asteroid;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Unit {

    private Polygon unit;
    private Point2D movement;
    private boolean alive;

    public Unit(Polygon polygon, int x, int y) {
        this.unit = polygon;
        this.unit.setTranslateX(x);
        this.unit.setTranslateY(y);
        this.alive = true;

        this.movement = new Point2D(0, 0);
    }

    public Polygon getUnit() {
        return unit;
    }

    public void turnLeft() {
        this.unit.setRotate(this.unit.getRotate() - 5);
    }

    public void turnRight() {
        this.unit.setRotate(this.unit.getRotate() + 5);
    }

    public void move() {
        this.unit.setTranslateX(this.unit.getTranslateX() + this.movement.getX());
        this.unit.setTranslateY(this.unit.getTranslateY() + this.movement.getY());

        if (this.unit.getTranslateX() < 0) {
            this.unit.setTranslateX(this.unit.getTranslateX() + AsteroidApp.WIDTH);
        }

        if (this.unit.getTranslateX() > AsteroidApp.WIDTH) {
            this.unit.setTranslateX(this.unit.getTranslateX() % AsteroidApp.WIDTH);
        }

        if (this.unit.getTranslateY() < 0) {
            this.unit.setTranslateY(this.unit.getTranslateY() + AsteroidApp.HEIGHT);
        }

        if (this.unit.getTranslateY() > AsteroidApp.HEIGHT) {
            this.unit.setTranslateY(this.unit.getTranslateY() % AsteroidApp.HEIGHT);
        }
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.unit.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.unit.getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.movement = this.movement.add(changeX, changeY);
    }

    public boolean collide(Unit other) {
        Shape collisionArea = Shape.intersect(this.unit, other.getUnit());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }

    public Point2D getMovement(){
        return this.movement;
    }

    public void setMovement(Point2D newMovement){
        this.movement = newMovement;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }
}
