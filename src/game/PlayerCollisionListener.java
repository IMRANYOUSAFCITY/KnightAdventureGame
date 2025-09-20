package game;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerCollisionListener implements CollisionListener,ActionListener {
    private Body otherBody;
    private Body thisBody;
    private boolean uno;
    private boolean demond;
    BodyImage demondeath = new BodyImage("data/demondeath.gif",7.5f);
    BodyImage bossattackb = new BodyImage("data/bossattack.gif",7);
    BodyImage bossdeath = new BodyImage("data/bossdeath.gif",7);
    BodyImage bosswalk = new BodyImage("data/bosswalk.gif",7);
    BodyImage death = new BodyImage("data/death.gif",6);
    BodyImage deathb = new BodyImage("data/deathb.gif",6);


    private CollisionEvent e;
    private boolean boss;
    boolean sdead;
    private int fcount;
    private static boolean dead;
    Player p;
    Timer time = new Timer(775,this);
    int hcount = 0;
    int ncounter = 0;

    @Override
    public void collide(CollisionEvent e) {
        thisBody = e.getReportingBody();
        otherBody = e.getOtherBody();
        if(e.getOtherBody() instanceof Necromancer && (CharacterController.code == KeyEvent.VK_J)){
            if(ncounter >= 5){
                ((Necromancer) e.getOtherBody()).death();
                ncounter = 0;
            }else {
                ((Necromancer) e.getOtherBody()).gethit();
                ncounter++;
            }
        } else if (e.getOtherBody() instanceof Necromancer) {
            ((Necromancer) e.getOtherBody()).attack();
            Timer an = new Timer(1000,this);
            an.setRepeats(false);
            an.start();
        } else if(e.getOtherBody() instanceof Skeleton && (CharacterController.code == KeyEvent.VK_J)){
            int chance = ThreadLocalRandom.current().nextInt(1, 10);
            if(chance > 5){
                ((Skeleton) e.getOtherBody()).blocked();
            }else if(hcount == 2){
                ((Skeleton) e.getOtherBody()).death();
                hcount = 0;
            }
            else{
                ((Skeleton) e.getOtherBody()).Hit();
                hcount++;
            }
        } else if (e.getOtherBody() instanceof Skeleton) {
            thisBody = e.getReportingBody();
            ((Skeleton) e.getOtherBody()).attacks();
            Timer as = new Timer(1000,this);
            as.setRepeats(false);
            as.start();
        } else if(e.getOtherBody() instanceof Crumblingfloor) {
            fcount++;
            if (fcount % 3 == 0){
                ((Crumblingfloor) e.getOtherBody()).changestate();
        }
        }else if (e.getOtherBody() instanceof Boss && (CharacterController.code == KeyEvent.VK_J)) {
            dead = true;
            uno = true;
            ((Boss) e.getOtherBody()).BossDeath();
        } else if (e.getOtherBody() instanceof Boss) {
            ((Boss) e.getOtherBody()).BossAttack();
            time.setRepeats(false);
            time.start();
    }else if (e.getOtherBody() instanceof Demon && (CharacterController.code == KeyEvent.VK_J )) {
            ((Demon) e.getOtherBody()).Death();
        } else if(e.getOtherBody() instanceof Demon && e.getReportingBody() instanceof Player){
            ((Demon) e.getOtherBody()).Attack();
            Timer d = new Timer(1000,this);
            d.setRepeats(false);
            d.start();
        }
    }




    public boolean IfDead(){
        System.out.println(dead);
        return dead;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ((Player) thisBody).changehealth();
    }
}