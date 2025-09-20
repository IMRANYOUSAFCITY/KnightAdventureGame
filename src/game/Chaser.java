package game;

import city.cs.engine.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chaser extends Walker implements SensorListener, ActionListener {
    BodyImage chase = new BodyImage("data/beastrun.gif",8);
    Shape s = new PolygonShape(0.3f,0.58f, 1.54f,-0.35f, 1.05f,-2.35f, -2.3f,-2.47f, -3.51f,-0.65f);
    public Chaser(game.GameLevel w){
        super(w);
        addImage(chase);
        GhostlyFixture g = new GhostlyFixture(this,s);
        Sensor sl = new Sensor(this,s);
        sl.addSensorListener(this);
        setGravityScale(0);
        startWalking(0.13f);
    }


    @Override
    public void beginContact(SensorEvent e) {
        if(e.getContactBody() instanceof Player){
            ((Player) e.getContactBody()).Death();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stopWalking();
    }
}
