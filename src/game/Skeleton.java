package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Skeleton extends Walker implements StepListener, ActionListener {
    float H = 8;
    boolean right;
    boolean blocking;
    boolean attacking;
    boolean dead;
    BodyImage attack = new BodyImage("data/sattack.gif",H);
    BodyImage attackb = new BodyImage("data/sattackb.gif",H);
    BodyImage walk = new BodyImage("data/swalk.gif",H);
    BodyImage block = new BodyImage("data/sblock.gif",H);
    BodyImage blockb = new BodyImage("data/sblockb.gif",H);
    BodyImage walkb = new BodyImage("data/swalkb.gif",H);
    BodyImage death = new BodyImage("data/sdeath.gif",H);
    BodyImage deathb = new BodyImage("data/sdeathb.gif",H);
    BodyImage hit = new BodyImage("data/skhit.gif",H);
    BodyImage hitb = new BodyImage("data/skhitb.gif",H);
    Shape s = new PolygonShape(0.5f,1.57f, 1.46f,0.22f, 0.88f,-1.42f, -0.43f,-1.44f, -1.2f,0.29f);
    Player p;
    BodyImage current;
    public Skeleton(GameLevel l,float x,float y,Player p){
        super(l);
        this.p = p;
        SolidFixture ss = new SolidFixture(this,s);
        addImage(walkb);
        startWalking(-2);
        setPosition(new Vec2(x,y));
        l.addStepListener(this);
        current = walkb;
    }
    public void Hit(){
        stopWalking();
        removeAllImages();
        if(right){
            addImage(hit);
        }else{addImage(hitb);}
        Timer h = new Timer(460,this);
        h.setRepeats(false);
        h.start();
    }
    public void attacks(){
        attacking = true;
        stopWalking();
        removeAllImages();
        if(right){
            addImage(attack);
        }else{addImage(attackb);}
        Timer a = new Timer(1000,this);
        a.setRepeats(false);
        a.start();
    }
    public void blocked(){
        blocking = true;
        stopWalking();
        removeAllImages();
        if(right){
            addImage(block);
        }else{addImage(blockb);}
        Timer b = new Timer(500,this);
        b.setRepeats(false);
        b.start();
    }
    public void death(){
        dead = true;
        removeAllImages();
        if(right){
            addImage(death);
        }else{
            addImage(deathb);
        }
        Timer d = new Timer(600,this);
        d.setRepeats(false);
        d.start();
    }
    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.getPosition().x < p.getPosition().x && current == walkb){
            right = true;
            removeAllImages();
            addImage(walk);
            current = walk;
            startWalking(2);
        }else if(this.getPosition().x > p.getPosition().x && current == walk){
            right = false;
            removeAllImages();
            addImage(walkb);
            current = walkb;
            startWalking(-2);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (dead) {
            destroy();
        } else {
            removeAllImages();
            if (right) {
                addImage(walk);
                startWalking(2);
            } else {
                addImage(walkb);
                startWalking(-2);
            }
        }
    }
}
