package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColPickup implements CollisionListener,ActionListener {
    private Player player;
    GameLevel l;


    public ColPickup(Player P, GameLevel a) {
        l = a;
        player = P;
    }


    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Buff){
            player.Boost();
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof Coin) {
            Coin.coincounter++;
            e.getOtherBody().destroy();
            Timer c = new Timer(3000, this);
            c.setRepeats(false);
            c.start();
        } else if (e.getOtherBody() instanceof Key) {
            Player.keycounter++;
            e.getOtherBody().destroy();
            if(Player.keycounter == 3 && l instanceof Level1){
                Game.game.NextLevel();
                Player.keycounter = 0;
            } else if (Player.keycounter == 4 && l instanceof Level3) {
                Game.game.GameEnded();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Coin(l);
    }
}
