package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyButton extends DynamicBody implements SensorListener, ActionListener {
    static int kpcount = 0;
    BodyImage shift = new BodyImage("data/shift.png");
    Shape button = new BoxShape(1,1);
    public KeyButton(GameLevel g,BodyImage b,boolean s){
        super(g);
        GhostlyFixture gh = new GhostlyFixture(this, button);
        Sensor sl = new Sensor(this, button);
        sl.addSensorListener(this);
        setGravityScale(0);
        addImage(b);
        if(s){
            setPosition(new Vec2(-1,-3));
        }else{
            setPosition(new Vec2(1,-3));
        }
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {

    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
