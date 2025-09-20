package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Skull extends Walker implements SensorListener {
    BodyImage skull = new BodyImage("data/skull.gif",3);
    Shape point = new BoxShape(1,1);
    public Skull(World world, float x) {
        super(world);
        addImage(skull);
        GhostlyFixture g = new GhostlyFixture(this, point);
        Sensor sl = new Sensor(this, point);
        sl.addSensorListener(this);
        setGravityScale(0);
        setPosition(new Vec2(x,2));
        startWalking(-17);
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {

    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
