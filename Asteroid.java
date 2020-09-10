package asteroid;

import java.util.Random;

public class Asteroid extends Unit {

    private double rotationalMovement;

    public Asteroid(int x, int y) {
        super(new AsteroidCreator().createPolygon(), x, y);

        Random rnd = new Random();

        super.getUnit().setRotate(rnd.nextInt(360));

        int accelerationAmount = 1 + rnd.nextInt(10);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }

        this.rotationalMovement = 0.5 - rnd.nextDouble();
    }

    @Override
    public void move() {
        super.move();
        super.getUnit().setRotate(super.getUnit().getRotate() + rotationalMovement);
    }

}