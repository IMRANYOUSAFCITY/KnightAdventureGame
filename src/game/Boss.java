package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Boss extends Walker implements StepListener, ActionListener {
    BodyImage bossattackb = new BodyImage("data/bossattack.gif",7);
    BodyImage bossattack = new BodyImage("data/bossattack.gif",7);
    BodyImage bossdeath = new BodyImage("data/bossdeath.gif",7);
    BodyImage bosswalkb = new BodyImage("data/bosswalkb.gif",7);
    BodyImage bosswalk = new BodyImage("data/bosswalk.gif",7);
    private float right,left;
    boolean facingr;
    boolean dead;
    private int speed = 2;
    int range = 3;
    Shape sbosswalk = new PolygonShape(3.33f,-3.43f, 4.51f,-1.49f, 2.8f,0.7f, 0.44f,-1.22f, 1.96f,-3.35f);


    public Boss(World world){
        super(world);
        SolidFixture bosswalkf = new SolidFixture(this,sbosswalk);
        bosswalkf.setFriction(10);
        addImage(bosswalk).setOffset(new Vec2(5,0));
        startWalking(speed);
        world.addStepListener(this);
    }

    public void setPosition(Vec2 pos){
        super.setPosition(pos);
        right = pos.x + range;
        left = pos.x - range;
    }
    public void BossAttack(){
            removeAllImages();
            addImage(bossattack);
        Timer as = new Timer(1000,this);
        as.setRepeats(false);
        as.start();
    }
    public void BossDeath(){
        dead = true;
        removeAllImages();
        addImage(bossdeath);
        Timer d = new Timer(1000,this);
        d.setRepeats(false);
        d.start();
    }
    @Override
    public void preStep(StepEvent stepEvent) {
        if(getPosition().x > right){
            startWalking(-speed);
            removeAllImages();
            addImage(bosswalkb);
        }
        if(getPosition().x < left){
            startWalking(speed);
            removeAllImages();
            addImage(bosswalk).setOffset(new Vec2(5,0));
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
            addImage(bosswalk).setOffset(new Vec2(5, 0));
            startWalking(speed);
        }
    }
}
