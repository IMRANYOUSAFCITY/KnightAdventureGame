package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.concurrent.ThreadLocalRandom;

public class Buff extends DynamicBody {
    BodyImage boost = new BodyImage("data/swiftness.png",1);
    Shape boosts = new PolygonShape(0.434f,0.442f, 0.436f,-0.492f, -0.436f,-0.492f, -0.434f,0.45f);
    public Buff(GameLevel l){
        super(l);
        SolidFixture bs = new SolidFixture(this,boosts);
        addImage(boost);
        int x = ThreadLocalRandom.current().nextInt(-20,20);
        int y = ThreadLocalRandom.current().nextInt(-10,10);
        setPosition(new Vec2(x,y));
    }
}
