package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class Lich extends Walker implements StepListener, ActionListener,SensorListener {
    float H = 7;
    GameLevel l;
    Player p;
    BodyImage fly = new BodyImage("data/lfly.gif",H);
    BodyImage flyb = new BodyImage("data/lflyb.gif",H);
    BodyImage attack = new BodyImage("data/lattack.gif",H);
    BodyImage attackb = new BodyImage("data/lattackb.gif",H);
    BodyImage cast = new BodyImage("data/lcast.gif",H);
    BodyImage castb = new BodyImage("data/lcastb.gif",H);
    BodyImage death = new BodyImage("data/ldeath.gif",H);
    BodyImage deathb = new BodyImage("data/ldeathb.gif",H);
    BodyImage emerge = new BodyImage("data/lemerge.gif",H);
    float right,left;
    boolean facingright = true;
    boolean start;
    boolean dead;
    boolean attacking;
    boolean repeat;
    boolean block;
    boolean casting;
    Shape L = new PolygonShape(-0.38f,2.27f, 1.37f,1.46f, 1.75f,-1.65f, -2.06f,-1.55f, -1.93f,1.61f);
    public Lich(GameLevel l, Player p,float x,float y){
        super(l);
        this.l = l;
        this.p = p;
        GhostlyFixture ns = new GhostlyFixture(this,L);
        Sensor s = new Sensor(this,L);
        s.addSensorListener(this);
        setGravityScale(0);
        setPosition(new Vec2(x,y));
        right = x + 8;
        left = x - 8;
        addImage(fly);
        startWalking(4);
        l.addStepListener(this);
        start = true;
        int t = ThreadLocalRandom.current().nextInt(4000,6000);
        Timer tt = new Timer(t,this);
        tt.setRepeats(false);
        tt.start();
    }
    public void Cast(){
        stopWalking();
        block = true;
        casting = true;
        removeAllImages();
        if(facingright){
            addImage(cast);
        } else{
            addImage(castb);
        }
        Timer c = new Timer(1000,this);
        c.setRepeats(false);
        c.start();
    }
    public void Death(){
        setLinearVelocity(new Vec2(0,0));
        dead = true;
        if(facingright){
            addImage(death);
        } else{
            addImage(deathb);
        }
        Timer d = new Timer(1000,this);
        d.setRepeats(false);
        d.start();
    }


    @Override
    public void preStep(StepEvent stepEvent) {
            if (getPosition().x > right && facingright) {
                facingright = false;
                removeAllImages();
                addImage(flyb);
                startWalking(-4);
            } else if (getPosition().x < left && !facingright) {
                facingright = true;
                removeAllImages();
                addImage(fly);
                startWalking(4);
            }


    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(start){
            setLinearVelocity(new Vec2(0,0));
            Cast();
            start = false;
        } else if (casting) {
            new Fire(l,getPosition().x,getPosition().y+2,p);
            int times = ThreadLocalRandom.current().nextInt(2000,5000);
            Timer r = new Timer(times,this);
            casting = false;
            start = true;
            facingright = true;
            removeAllImages();
            addImage(fly);
            startWalking(4);
            r.setRepeats(false);
            r.start();
        }else if(dead){
            destroy();
            dead = false;
        }

    }

    @Override
    public void beginContact(SensorEvent e) {
       if (e.getContactBody() instanceof Player) {
            ((Player) e.getContactBody()).changehealth();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
