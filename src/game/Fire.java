package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fire extends DynamicBody implements SensorListener, ActionListener,StepListener {
    BodyImage fire = new BodyImage("data/wfire.gif",5);
    Player p;
    Shape fires = new PolygonShape(-0.02f,1.36f, 1.35f,-0.19f, 0.03f,-1.44f, -1.23f,-0.07f);
    public Fire(GameLevel l,float x,float y,Player p){
        super(l);
        this.p = p;
        GhostlyFixture f = new GhostlyFixture(this,fires);
        Sensor fs = new Sensor(this,fires);
        fs.addSensorListener(this);
        addImage(fire);
        setGravityScale(0);
        setPosition(new Vec2(x,y));
        l.addStepListener(this);
        Timer fi = new Timer(5000,this);
        fi.setRepeats(false);
        fi.start();
    }

    @Override
    public void beginContact(SensorEvent e) {
        if(e.getContactBody() instanceof Player){
            destroy();
            ((Player) e.getContactBody()).changehealth();
        }
        if(e.getContactBody() instanceof Crumblingfloor){
            destroy();
            ((Crumblingfloor) e.getContactBody()).changestate();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        destroy();
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(getPosition().x >= p.getPosition().x && getPosition().y >= p.getPosition().y-1){
            setLinearVelocity(new Vec2(-6,-6));
        }else if(getPosition().x <= p.getPosition().x && getPosition().y >= p.getPosition().y-1){
            setLinearVelocity(new Vec2(6,-6));
        }else if(getPosition().x <= p.getPosition().x && getPosition().y <= p.getPosition().y-1){
            setLinearVelocity(new Vec2(6,6));
        }else if(getPosition().x >= p.getPosition().x && getPosition().y <= p.getPosition().y-1){
            setLinearVelocity(new Vec2(-6,6));
        }

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
