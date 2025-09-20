package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class Demon extends Walker implements StepListener, ActionListener {
    static float H = 7.5f;
    static int dspeed;
    boolean rightw;
    boolean dead;
    private float dleft, dright;
    int range = 2;
    BodyImage demondeath = new BodyImage("data/demondeath.gif",7.5f);
    BodyImage demondeathb = new BodyImage("data/demondeathb.gif",7.5f);
    BodyImage demonattack = new BodyImage("data/demonattack.gif", 9);
    BodyImage demonattackb = new BodyImage("data/demonattackb.gif", 9);
    static BodyImage demonrun = new BodyImage("data/demonrun.gif",H);
    BodyImage demonrunb = new BodyImage("data/demonrunb.gif",H);
    BodyImage current;
    Shape demon = new PolygonShape(-1.74f,-2.2f, 1.55f,-2.23f, 2.13f,-1.54f, 0.65f,-0.03f, -1.36f,0.44f);
    public Demon(World world) {
        super(world);
        SolidFixture demonf = new SolidFixture(this,demon);
        demonf.setFriction(10);
        dspeed = ThreadLocalRandom.current().nextInt(6, 10);
        addImage(demonrun);
        current = demonrun;
        world.addStepListener(this);
        startWalking(dspeed);
    }
    public void Attack(){
        stopWalking();
        removeAllImages();
        if(rightw){
            addImage(demonattack);
        }else {
            addImage(demonattackb);
        }
        Timer a = new Timer(1000,this);
        a.setRepeats(false);
        a.start();
    }
    public void Death(){
        dead = true;
        stopWalking();
        removeAllImages();
        if(rightw){
            addImage(demondeathb);
        }else {
            addImage(demondeath);
        }
        Timer a = new Timer(1000,this);
        a.setRepeats(false);
        a.start();
    }

    public void setPosition(Vec2 position){
        super.setPosition((position));
        dleft = position.x-range;
        dright = position.x+range;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().x > dright){
            rightw = false;
            startWalking(-dspeed);
            removeAllImages();
            addImage(demonrunb);
        }
        if (getPosition().x < dleft){
            rightw = true;
            startWalking(dspeed);
            removeAllImages();
            addImage(demonrun);
            current = demonrun;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(dead){
            destroy();
        }else {
            removeAllImages();
            addImage(demonrun);
            startWalking(dspeed);
        }
    }
}
