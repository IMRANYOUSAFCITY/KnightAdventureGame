package game;

import city.cs.engine.*;

public class Key extends DynamicBody {
    BodyImage keyi = new BodyImage("data/key.png",1.5f);
    Shape skey = new PolygonShape(-0.136f,1.0f, -0.372f,0.76f, -0.384f,-1.56f, 0.496f,-1.552f, 0.5f,0.756f, 0.256f,0.988f);
    public Key(World w) {
        super(w);
        SolidFixture key = new SolidFixture(this,skey);
        addImage(keyi);
    }
}
