package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.concurrent.ThreadLocalRandom;

public class Coin extends DynamicBody {
    static int coincounter;
    BodyImage coini = new BodyImage("data/coin.png",2);
    float xpos,ypos;
    Shape scoin = new PolygonShape(0.256f,0.6f, 0.604f,0.248f, 0.604f,-0.116f, 0.256f,-0.476f, -0.352f,-0.488f, -0.764f,-0.12f, -0.764f,0.248f, -0.408f,0.604f);
    public Coin(World w){
        super(w);
        SolidFixture coin = new SolidFixture(this,scoin);
        addImage(coini);
        xpos = ThreadLocalRandom.current().nextInt(-20, 20);
        ypos = ThreadLocalRandom.current().nextInt(-10, 10);
        setPosition(new Vec2(xpos,ypos));
    }
}
