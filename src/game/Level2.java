package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Level2 extends GameLevel implements ActionListener,StepListener {
    KeyButton k;
    KeyButton shft;
    boolean button;
    int kpcount = 0;
    BodyImage H = new BodyImage("data/H.png");
    BodyImage E = new BodyImage("data/E.png");
    BodyImage G = new BodyImage("data/G.png");
    BodyImage O = new BodyImage("data/O.png");
    BodyImage L = new BodyImage("data/L.png");
    BodyImage spam = new BodyImage("data/spam.gif");
    BodyImage shift = new BodyImage("data/shift.png");
    private SoundClip l2m;
    float speed = 0;
    Game g;

    CharacterController controller;
    BodyImage slideportali = new BodyImage("data/slideportal.gif",8);
    BodyImage rollportali = new BodyImage("data/rollportal.gif",8);
    Timer t;
    Timer qt = new Timer(3500,this);
    int eventcount = 0;


    public Level2(CharacterController c,Game g){
        super();
        this.g = g;
        controller = c;
        controller.controlcounter = 0;
        CharacterController.two = true;
        CharacterController.right = true;
        try{ l2m = new SoundClip("data/l2m.wav");
            l2m.loop();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);}
        Shape shape = new BoxShape(800, 0.00000001f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -1.4f));
        getPlayer().setPosition(new Vec2(1,2));
        getPlayer().removeAllImages();
        getPlayer().addImage(new BodyImage("data/run.gif",6));
        Chaser c1 = new Chaser(this);
        c1.setPosition(new Vec2(-6,1.1f));
        Chaser c2 = new Chaser(this);
        c2.setPosition(new Vec2(-8f,1.1f));
        t = new Timer(5000,this);
        qt.setRepeats(false);
        t.start();
        addStepListener(this);
        this.start();
    }
    public void StopMusic2(){
        l2m.close();
    }

    public void slide(){
        getPlayer().ChaseSlide();
        new Skull(this,7);
        new Skull(this,9);
        new Skull(this,11);
        new Skull(this,13);
    }
    public void speedup(){
        speed = speed + 1;
        getPlayer().startWalking(speed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(CharacterController.two){
        if(button){
            kpcount++;
            System.out.println("kpcount: "+kpcount);
            switch (kpcount) {
                case 1:
                    k = new KeyButton(this, H,false);
                    shft = new KeyButton(this, shift,true);
                    break;
                case 2:
                    k = new KeyButton(this, E,false);
                    break;
                case 3:
                    k = new KeyButton(this, G,false);
                    shft = new KeyButton(this, shift,true);
                    break;
                case 4:
                    k = new KeyButton(this, O,false);
                    break;
                case 5:
                    k = new KeyButton(this,L,false);
                    shft = new KeyButton(this,shift,true);
                    break;
                case 6:
                    k = new KeyButton(this,spam,false);
            }
            button = false;
        }else{
            System.out.println("evencount: "+eventcount);
        switch (eventcount){
            case 0:
                System.out.println("Shift H");
                qt.start();
                break;
            case 1:
                if(controller.controlcounter == 1){
                    k.destroy();
                    shft.destroy();
                    getPlayer().ChaseSlide();
                    qt.start();
                }else{
                    k.destroy();
                    shft.destroy();
                    getPlayer().DeadSlide();
                    t.stop();
                    qt.stop();
                }
                break;
            case 2:
                if(controller.controlcounter == 2){
                    k.destroy();
                    getPlayer().ChaseRoll();
                    qt.start();
                }else{
                    k.destroy();
                    getPlayer().DeadRoll();
                    t.stop();
                    qt.stop();
                }
                break;
            case 3:
                if(controller.controlcounter == 3){
                    k.destroy();
                    shft.destroy();
                    getPlayer().ChaseSlide();
                    qt.start();
                }else{
                    k.destroy();
                    shft.destroy();
                    getPlayer().DeadSlide();
                    t.stop();
                    qt.stop();
                }
                break;
            case 4:
                if(controller.controlcounter == 4){
                    k.destroy();
                    getPlayer().ChaseRoll();
                    qt.start();
                }else{
                    k.destroy();
                    getPlayer().DeadRoll();
                    t.stop();
                    qt.stop();
                }
                break;
            case 5:
                if(controller.controlcounter == 5){
                    k.destroy();
                    shft.destroy();
                    getPlayer().ChaseSlide();
                    qt.start();
                }else{
                    k.destroy();
                    shft.destroy();
                    getPlayer().DeadSlide();
                    t.stop();
                    qt.stop();
                }break;
        }
        eventcount++;
        button = true;
    }}}

    @Override
    public void preStep(StepEvent stepEvent) {
        if(getPlayer().getPosition().x > 12 ) g.NextLevel();
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
