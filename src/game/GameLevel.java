package game;

import city.cs.engine.World;

public abstract class GameLevel extends World {
    private static Player player;
    PlayerCollisionListener Pc;

    public GameLevel() {
        Pc = new PlayerCollisionListener();
        player = new Player(this,Pc);
        player.addCollisionListener(Pc);
        ColPickup cp = new ColPickup(getPlayer(),this);
        player.addCollisionListener(cp);
    }

    public static Player getPlayer() {
        return player;
    }

}
