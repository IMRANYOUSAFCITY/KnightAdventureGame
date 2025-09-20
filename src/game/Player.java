package game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Walker implements ActionListener {
    static int keycounter = 0;
    static float speed = 6;
    float cspeed = 3;
    static boolean rolling = false;
    static boolean fail;
    static boolean dead;
    int jump = 12;
    GameLevel g;
    static Shape pnormal = new PolygonShape(0.45f, -1.75f, -0.25f, -0.17f, -1.22f, -1.41f, -1.19f, -2.98f, 0.4f, -2.98f);
    static Shape pnormalb = new PolygonShape(-0.34f, -1.45f, 0.31f, -0.11f, 1.15f, -1.68f, 1.15f, -2.98f, -0.31f, -2.98f);
    static Shape pcrouch = new PolygonShape(-1.26f, -2.13f, 0.07f, -0.92f, 0.79f, -1.99f, 0.29f, -2.98f, -1.08f, -2.98f);
    static Shape pcrouchb = new PolygonShape(0.07f,-1.01f, 1.12f,-1.82f, 1.12f,-2.94f, -0.23f,-2.94f, -0.88f,-2.11f);
    static Shape pattack = new PolygonShape(-1.21f,-1.28f, 0.02f,0.21f, 2.81f,-0.31f, 3.42f,-1.9f, 3.04f,-2.98f, -1.37f,-2.96f);
    static Shape pattackb = new PolygonShape(0.9f,-0.67f, -0.54f,0.14f, -3.08f,-0.24f, -4.23f,-1.48f, -3.69f,-2.96f, 0.47f,-2.96f);
    SolidFixture normalb;
    SolidFixture crouch;
    SolidFixture crouchb;
    SolidFixture normal;
    SolidFixture attack;
    SolidFixture attackb;
    int Height = 6;
    static int hcount = 0;
    Image h1 = new ImageIcon("data/h1.png").getImage();
    Image h2 = new ImageIcon("data/h2.png").getImage();
    Image h3 = new ImageIcon("data/h3.png").getImage();
    Image h4 = new ImageIcon("data/h4.png").getImage();
    Image h5 = new ImageIcon("data/h5.png").getImage();
    Image h6 = new ImageIcon("data/h6.png").getImage();
    Image current;
    BodyImage normali = new BodyImage("data/normal.gif", Height);
    BodyImage normalbi = new BodyImage("data/normalb.gif", Height);
    BodyImage attacki = new BodyImage("data/attack.gif", Height);
    BodyImage attackbi = new BodyImage("data/attackb.gif", Height);
    BodyImage runi = new BodyImage("data/run.gif", Height);
    BodyImage runbi = new BodyImage("data/runb.gif", Height);
    BodyImage jumpi = new BodyImage("data/jump.gif", Height);
    BodyImage jumpbi = new BodyImage("data/jumpb.gif", Height);
    BodyImage rolli = new BodyImage("data/roll.gif", Height);
    BodyImage rollbi = new BodyImage("data/rollb.gif", Height);
    BodyImage death = new BodyImage("data/death.gif",6);
    BodyImage deathb = new BodyImage("data/deathb.gif",6);
    BodyImage crouchwbi = new BodyImage("data/crouchwb.gif", Height);
    BodyImage crouchwi = new BodyImage("data/crouchw.gif", Height);
    BodyImage crouchi = new BodyImage("data/crouch.gif", Height);
    BodyImage crouchbi = new BodyImage("data/crouchb.gif", Height);
    BodyImage slidei = new BodyImage("data/slide.gif", Height);
    BodyImage chasei = new BodyImage("data/spellchase.gif",10);
    Timer t = new Timer(270, this);
    private SoundClip swing;
    PlayerCollisionListener P;



    public Player(GameLevel w,PlayerCollisionListener pc) {
        super(w);
        P = pc;
        g = w;
        normal = new SolidFixture(this, pnormal);
        normal.setFriction(10);
        addImage(normali);
    }


    public void changeRight() {
        stopWalking();
        removeAllImages();
        if(CharacterController.isActive()) {
            startWalking(speed);
            removeAllImages();
            addImage(runi);}
        if (CharacterController.isJump()) {
            jump(jump);
            removeAllImages();
            addImage(jumpi);}
        if(!CharacterController.isActive() && !CharacterController.isJump()){
            removeAllImages();
            addImage(normali);}
        if (this.getFixtureList().contains(normalb)) {
            normalb.destroy();
            normal = new SolidFixture(this, pnormal);
            normal.setFriction(10);
        } else if (this.getFixtureList().contains(crouch)) {
            crouch.destroy();
            normal = new SolidFixture(this, pnormal);
            normal.setFriction(10);
        } else if (this.getFixtureList().contains(crouchb)) {
            crouchb.destroy();
            normal = new SolidFixture(this, pnormal);
            normal.setFriction(10);
        } else if (this.getFixtureList().contains(this.attack)){
            attack.destroy();
            normal = new SolidFixture(this, pnormal);
            normal.setFriction(10);
        }else if (this.getFixtureList().contains(this.attackb)){
            attackb.destroy();
            normal = new SolidFixture(this, pnormal);
            normal.setFriction(10);
        }
    }

    public void changeLeft() {
        stopWalking();
        removeAllImages();
        if(CharacterController.isActive()) {
            startWalking(-speed);
            removeAllImages();
            addImage(runbi);}
        if (CharacterController.isJump()) {
            jump(jump);
            removeAllImages();
            addImage(jumpbi);}
        if(!CharacterController.isActive() && !CharacterController.isJump()){
            removeAllImages();
            addImage(normalbi);}
        if (this.getFixtureList().contains(normal)) {
            normal.destroy();
            normalb = new SolidFixture(this, pnormalb);
            normalb.setFriction(10);
        } else if (this.getFixtureList().contains(crouch)) {
            crouch.destroy();
            normalb = new SolidFixture(this, pnormalb);
            normalb.setFriction(10);
        } else if (this.getFixtureList().contains(crouchb)) {
            crouchb.destroy();
            normalb = new SolidFixture(this, pnormalb);
            normalb.setFriction(10);
        } else if (this.getFixtureList().contains(this.attack)) {
            attack.destroy();
            normalb = new SolidFixture(this, pnormalb);
            normalb.setFriction(10);
        } else if (this.getFixtureList().contains(this.attackb)) {
            attackb.destroy();
            normalb = new SolidFixture(this, pnormalb);
            normalb.setFriction(10);
        }
    }

    public void crouchRight() {
        stopWalking();
        removeAllImages();
        if(CharacterController.isActive()){
            startWalking(cspeed);
            addImage(crouchwi);
        }else{addImage(crouchi);}
        if (this.getFixtureList().contains(normal)) {
            normal.destroy();
            crouch = new SolidFixture(this,pcrouch);
            crouch.setFriction(10);
        } else if (this.getFixtureList().contains(normalb)) {
            normalb.destroy();
            crouch = new SolidFixture(this,pcrouch);
            crouch.setFriction(10);
        } else if (this.getFixtureList().contains(crouchb)) {
            crouchb.destroy();
            crouch = new SolidFixture(this,pcrouch);
            crouch.setFriction(10);
        }
    }
    public void crouchLeft() {
        stopWalking();
        removeAllImages();
        if(CharacterController.isActive()){
            startWalking(-cspeed);
            addImage(crouchwbi);
        }else{addImage(crouchbi);}
        if (this.getFixtureList().contains(normal)) {
            normal.destroy();
            crouchb = new SolidFixture(this,pcrouchb);
            crouchb.setFriction(10);
        } else if (this.getFixtureList().contains(normalb)) {
            normalb.destroy();
            crouchb = new SolidFixture(this,pcrouchb);
            crouchb.setFriction(10);
        } else if (this.getFixtureList().contains(crouch)) {
            crouch.destroy();
            crouchb = new SolidFixture(this,pcrouchb);
            crouchb.setFriction(10);
        }
    }
    public void attackRight(){
        stopWalking();
        removeAllImages();
        addImage(attacki);
        if (this.getFixtureList().contains(normal)) {
            normal.destroy();
            attack = new SolidFixture(this,pattack);
            attack.setFriction(10);
        } else if (this.getFixtureList().contains(normalb)) {
            normalb.destroy();
            attack = new SolidFixture(this,pattack);
            attack.setFriction(10);
        }else if (this.getFixtureList().contains(crouch)) {
            crouch.destroy();
            attack = new SolidFixture(this,pattack);
            attack.setFriction(10);
        } else if (this.getFixtureList().contains(crouchb)) {
            crouchb.destroy();
            attack = new SolidFixture(this,pattack);
            attack.setFriction(10);
        }
        //t.setRepeats(false);
        //t.start();
    }

    public void attackLeft(){
        stopWalking();
        removeAllImages();
        addImage(attackbi);
        if (this.getFixtureList().contains(normal)) {
            normal.destroy();
            attackb = new SolidFixture(this,pattackb);
            attackb.setFriction(10);
        } else if (this.getFixtureList().contains(normalb)) {
            normalb.destroy();
            attackb = new SolidFixture(this,pattackb);
            attackb.setFriction(10);
        }else if (this.getFixtureList().contains(crouch)) {
            crouch.destroy();
            attackb = new SolidFixture(this,pattackb);
            attackb.setFriction(10);
        } else if (this.getFixtureList().contains(crouchb)) {
            crouchb.destroy();
            attackb = new SolidFixture(this,pattackb);
            attackb.setFriction(10);
        }
        //t.setRepeats(false);
        //t.start();
    }
    public void Boost(){
        current = h6;
        jump = 15;
        speed = 8;
        cspeed = 4;
    }
    public void changehealth(){
        if(current == h6){
            current = h5;
            hcount++;
        } else if (current == h5) {
            current = h4;
            hcount++;
        } else if (current == h4) {
            current = h3;
            hcount++;
        } else if (current == h3) {
            current = h2;
            hcount++;
        }else if(current == h2){
            current = h1;
            hcount++;
        } else if (current == h1) {
            Death();
        }
    }
    public void ReassignHealth(){
        System.out.println(hcount);
        switch (hcount){
            case 0:
                current = h6;
                break;
            case 1:
                current = h5;
                break;
            case 2:
                current = h4;
                break;
            case 3:
                current = h3;
                break;
            case 4:
                current = h2;
                break;
            case 5:
                current = h1;
                break;
        }
    }
    public void Death(){
        CharacterController.block = true;
        current = null;
        stopWalking();
        dead = true;
        Timer ts = new Timer(700,this);
        removeAllImages();
        if(CharacterController.right){
            addImage(death);
        }else{addImage(deathb);}
        ts.setRepeats(false);
        ts.start();

    }
    public void ChaseRun(){
        removeAllImages();
        addImage(runi);
    }
    public void ChaseRoll(){
        rolling = true;
        new Wolf(g,10);
        Timer wolf = new Timer(450,this);
        wolf.setRepeats(false);
        wolf.start();
    }
    public void ChaseSlide(){
        removeAllImages();
        addImage(slidei);
        new Skull(g,7);
        new Skull(g,9);
        new Skull(g,11);
        new Skull(g,13);
        Timer slidet = new Timer(1000,this);
        slidet.setRepeats(false);
        slidet.start();
    }

    public void DeadRoll(){
        fail = true;
        rolling = true;
        new Wolf(g,10);
        Timer wolf = new Timer(450,this);
        wolf.setRepeats(false);
        wolf.start();
    }
    public void DeadSlide(){
        fail = true;
        new Skull(g,7);
        new Skull(g,9);
        new Skull(g,11);
        new Skull(g,13);
        Timer slidet = new Timer(400,this);
        slidet.setRepeats(false);
        slidet.start();
    }
    public void speedup(){
        speed = speed + 0.1f;
        startWalking(speed);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getFixtureList().contains(attack)) {
            attack.destroy();
            normal = new SolidFixture(this, pnormal);
            normal.setFriction(10);
            removeAllImages();
            addImage(normali);
            CharacterController.setAttack(false);
        } else if (this.getFixtureList().contains(attackb)) {
            attackb.destroy();
            normalb = new SolidFixture(this, pnormalb);
            normalb.setFriction(10);
            removeAllImages();
            addImage(normalbi);
            CharacterController.setAttack(false);
        } else if (rolling) {
            if(fail){
                Death();
                fail = false;
                rolling = false;
            }else {
                Timer rollt = new Timer(600, this);
                rollt.setRepeats(false);
                jump(2);
                removeAllImages();
                addImage(rolli);
                rolling = false;
                rollt.start();
            }
        } else if (dead) {
            destroy();
            hcount = 0;
            Game.game.GameEnded();
            CharacterController.block = false;
            dead = false;
        } else {
            if(fail){
                Death();
                fail = false;
            }else {
                ChaseRun();
            }
        }


    }
}



