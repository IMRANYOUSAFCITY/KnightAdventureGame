package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class Wolf extends Walker implements SensorListener {
    float H = 1.5f;
    private float left, right;
    static boolean rightw;
    static int counter = 0;
    float range = 5.7f;
    Random rnd = new Random();
    static int speed;
    BodyImage wolfidle = new BodyImage("data/wolfidleb.gif", H);
    BodyImage wolfwalk = new BodyImage("data/wolfwalk.gif", H);
    BodyImage wolfwalkb = new BodyImage("data/wolfwalkb.gif", H);
    BodyImage wolfrun = new BodyImage("data/wolfrunb.gif", H);
    BodyImage wolfjump = new BodyImage("data/wolfjumpb.gif", H);
    Shape wolf = new PolygonShape(-1.432f, -0.984f, 0.96f, -0.992f, 1.408f, 0.184f, 0.784f, 0.608f, -1.512f, -0.16f);

    public Wolf(World world,float x) {
        super(world);
        addImage(wolfrun);
        GhostlyFixture g = new GhostlyFixture(this, wolf);
        Sensor sl = new Sensor(this, wolf);
        sl.addSensorListener(this);
        setGravityScale(0);
        setPosition(new Vec2(x,-0.75f));
        startWalking(-16);
    }



    @Override
    public void beginContact(SensorEvent sensorEvent) {

    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
