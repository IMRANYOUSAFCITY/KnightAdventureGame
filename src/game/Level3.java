package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Level3 extends GameLevel implements ActionListener,StepListener{
    Shape floor = new PolygonShape(-0.992f,0.988f, 0.98f,1.0f, 0.984f,-0.984f, -0.988f,-0.984f);
    SoundClip l3m;
    Necromancer N;
    Timer t = new Timer(8000,this);
    public Level3(){
        super();
        try{ l3m = new SoundClip("data/l3m.wav");
            l3m.loop();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);}
        CharacterController.two = false;
        getPlayer().setPosition(new Vec2(-29,-10));
        Shape shape = new BoxShape(30, 1f);
        StaticBody ground1 = new StaticBody(this, shape);
        ground1.setPosition(new Vec2(0f, -15.5f));
        ground1.setFillColor(Color.black);
        platform(-18,-10);
        platform(18,-10);
        platform(0,-6);
        platform(-18,-2);
        platform(18,-2);
        platform(0,2);
        this.setGravity(12f);
        Lich l1 = new Lich(this,getPlayer(),16,10);
        Lich l2 = new Lich(this,getPlayer(),-16,10);
        Key k1 = new Key(this);
        k1.setPosition(new Vec2(18f,-5));
        Key k2 = new Key(this);
        k2.setPosition(new Vec2(18,0));
        Key k3 = new Key(this);
        k3.setPosition(new Vec2(-18,0));
        Key k4 = new Key(this);
        k4.setPosition(new Vec2(18,-10));
        N = new Necromancer(this,getPlayer());
        N.setPosition(new Vec2(0,5));
        new Coin(this);
        new Buff(this);
        t.start();
        this.start();
    }
    public void StopMusic3(){
        l3m.stop();
    }
    public void platform(float x , float y){
        new Crumblingfloor(this,x-4,y);
        new Crumblingfloor(this,x-2,y);
        new Crumblingfloor(this,x,y);
        new Crumblingfloor(this,x+2,y);
        new Crumblingfloor(this,x+4,y);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        N.spawn();
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
