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

public class Level1 extends GameLevel implements ActionListener {
    static Boss b1;
    static boolean done;
    static boolean block;
    BodyImage platform = new BodyImage("data/platform.png", 2);
    BodyImage walli = new BodyImage("data/wall.png", 4);
    BodyImage bosscast = new BodyImage("data/bosscast.gif",7);
    BodyImage bossspell = new BodyImage("data/bossspell.gif",7);
    BodyImage bosswalk = new BodyImage("data/bosswalk.gif",7);
    BodyImage death = new BodyImage("data/death.gif",6);
    private static SoundClip l1m;
    Timer t;
    public Level1() {
        super();
        t = new Timer(60000,this);
        CharacterController.two = false;
        try{ l1m = new SoundClip("data/l1m.mp3");
            l1m.loop();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);}
        t.setRepeats(false);
        Shape shape = new BoxShape(800, 1f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -15f));
        ground.setFillColor(Color.black);

        StaticBody walls = new StaticBody(this, shape);
        walls.setPosition(new Vec2(-31f, 0f));
        walls.rotateDegrees(90);
        walls.setFillColor(Color.black);

        StaticBody walls2 = new StaticBody(this, shape);
        walls2.setPosition(new Vec2(31f, 0f));
        walls2.rotateDegrees(90);
        walls2.setFillColor(Color.black);

        Shape platformShape = new BoxShape(5f, 0.9f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(0, -11f));
        platform1.addImage(platform);

        StaticBody platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(-15, -10f));
        platform2.addImage(platform);

        StaticBody platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(12, 4f));
        platform3.addImage(platform);

        StaticBody platform4 = new StaticBody(this, platformShape);
        platform4.setPosition(new Vec2(21.95f, 4f));
        platform4.addImage(platform);

        StaticBody platform5 = new StaticBody(this, platformShape);
        platform5.setPosition(new Vec2(24f, -11f));
        platform5.addImage(platform);

        Shape swall = new BoxShape(0.5f,2);
        StaticBody wall = new StaticBody(this,swall);
        wall.setPosition(new Vec2(7.5f,1));
        wall.addImage(walli);

        StaticBody wall2 = new StaticBody(this,swall);
        wall2.setPosition(new Vec2(7.5f,-2));
        wall2.addImage(walli);

        StaticBody wall3 = new StaticBody(this,swall);
        wall3.setPosition(new Vec2(7.5f,-6));
        wall3.addImage(walli);

        StaticBody wall4 = new StaticBody(this,swall);
        wall4.setPosition(new Vec2(7.5f,-9.9f));
        wall4.addImage(walli);

        getPlayer().setPosition(new Vec2(-30, 0));

        Key k1 = new Key(this);
        k1.setPosition(new Vec2(-19,5));
        Key k2 = new Key(this);
        k2.setPosition(new Vec2(22,5));
        Key k3 = new Key(this);
        k3.setPosition(new Vec2(25,-5));


        Demon d1 = new Demon(this);
        d1.setPosition(new Vec2(0, -9));

        Demon d2 = new Demon(this);
        d2.setPosition(new Vec2(-14, -8));

        Demon d3 = new Demon(this);
        d3.setPosition(new Vec2(14, -12));

        Lift l1 = new Lift(this);
        l1.setPosition(new Vec2(6,-11));

        b1 = new Boss(this);
        b1.setPosition(new Vec2(14,7));
        new Coin(this);

        this.setGravity(15);
        this.start();
        t.start();
    }
    public void StopMusic1(){
        l1m.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (done) {
                b1.removeAllImages();
                b1.addImage(bosswalk).setOffset(new Vec2(5, 0));
                b1.startWalking(2);
                done = false;
            } else if(!Pc.IfDead()) {
                block = true;
                Timer td = new Timer(650, this);
                b1.stopWalking();
                b1.removeAllImages();
                b1.addImage(bosscast);
                getPlayer().Death();
                getPlayer().addImage(bossspell).setOffset(new Vec2(0, 1));
                done = true;
                td.setRepeats(false);
                td.start();
            }
        }
    }

