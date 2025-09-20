package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Crumblingfloor extends StaticBody {
    BodyImage current;
    SolidFixture s;
    float H = 2;
    BodyImage floor1 = new BodyImage("data/floor1.png",H);
    BodyImage floor2 = new BodyImage("data/floor2.png",H);
    BodyImage floor3 = new BodyImage("data/floor3.png",H);
    BodyImage floor4 = new BodyImage("data/floor4.png",H);
    static Shape floors = new PolygonShape(-0.992f,0.988f, 0.98f,1.0f, 0.984f,-0.984f, -0.988f,-0.984f);
    static Shape ghost = new BoxShape(1,1);
    public Crumblingfloor(game.GameLevel l, float x, float y){
        super(l);
        s = new SolidFixture(this,floors);
        addImage(floor1);
        setPosition(new Vec2(x,y));
        setAlwaysOutline(true);
        current = floor1;
    }
    public void changestate(){
        if(current == floor1){
            addImage(floor2);
            current = floor2;
        }else if(current == floor2){
            addImage(floor3);
            current = floor3;
        }else if(current == floor3){
            addImage(floor4);
            current = floor4;
        }else if(current == floor4){
            destroy();
        }
    }
}
