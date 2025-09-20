package game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Necromancer extends StaticBody implements ActionListener,StepListener {
    float H = 14;
    boolean right;
    boolean spawning;
    boolean attacking;
    boolean gethit;
    BodyImage hit = new BodyImage("data/whit.gif",H);
    BodyImage hitb = new BodyImage("data/whitb.gif",H);
    BodyImage death = new BodyImage("data/wdeath.gif",H);
    BodyImage deathb = new BodyImage("data/wdeathb.gif",H);
    BodyImage attack = new BodyImage("data/wattack.gif",H);
    BodyImage attackb = new BodyImage("data/wattackb.gif",H);
    BodyImage run = new BodyImage("data/wrun.gif",H);
    BodyImage spawn = new BodyImage("data/wspawn.gif",H);
    BodyImage spawnb = new BodyImage("data/wspawnb.gif",H);
    BodyImage idle = new BodyImage("data/widle.gif",H);
    BodyImage idleb = new BodyImage("data/widleb.gif",H);
    Shape s = new PolygonShape(-1.01f,-2.3f, 1.06f,-2.3f, 1.4f,1.01f, -0.2f,0.5f, -0.95f,-0.78f);
    GameLevel l;
    Player p;
    Timer spawns = new Timer(900,this);
    BodyImage current;
    public Necromancer(GameLevel l, Player p){
        super(l);
        this.l = l;
        this.p = p;
        l.addStepListener(this);
        SolidFixture ns = new SolidFixture(this,s);
        addImage(idle);
        current = idle;
    }
    public void spawn(){
        spawning = true;
        removeAllImages();
        if(right){
            addImage(spawn);
        }else{
            addImage(spawnb);
        }
        spawns.setRepeats(false);
        spawns.start();
    }
    public void attack(){
        attacking = true;
        removeAllImages();
        if(right){
            addImage(attack);
        }else{
            addImage(attackb);
        }
        spawns.setRepeats(false);
        spawns.start();
    }
    public void gethit(){
        gethit = true;
        removeAllImages();
        if(right){
            addImage(hit);
        }else{
            addImage(hitb);
        }
        Timer h = new Timer(500,this);
        h.setRepeats(false);
        h.start();
    }
    public void death(){
        removeAllImages();
        if(right){
            addImage(death);
        }else{
            addImage(deathb);
        }
        Timer d = new Timer(1000,this);
        d.setRepeats(false);
        d.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(spawning) {
            spawning = false;
            removeAllImages();
            if (right) {
                new Skeleton(l, getPosition().x + 5, getPosition().y - 1, p);
                addImage(idle);
            } else {
                new Skeleton(l, getPosition().x - 5, getPosition().y - 1, p);
                addImage(idleb);
            }
        }else if(attacking || gethit){
            attacking = false;
            gethit = false;
            removeAllImages();
            if (right) {
                addImage(idle);
            } else {
                addImage(idleb);
            }
        } else{
            destroy();
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.getPosition().x < p.getPosition().x && current == idleb){
            right = true;
            removeAllImages();
            addImage(idle);
            current = idle;
        }else if(this.getPosition().x > p.getPosition().x && current == idle){
            right = false;
            removeAllImages();
            addImage(idleb);
            current = idleb;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
