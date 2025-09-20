package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Lift extends StaticBody implements StepListener {
    BodyImage liftshape = new BodyImage("data/lift.png",2);
    static Shape lifti = new PolygonShape(-0.928f,0.872f, -0.94f,-0.872f, 0.932f,-0.872f, 0.932f,0.872f);
    Vec2 startposition;
    float top, bottom;
    float delta;

    public Lift(World world){
        super(world, lifti);
        addImage(liftshape);
        delta = 0.03f;
        world.addStepListener(this);
    }
    public void setPosition(Vec2 v){
        super.setPosition(v);
        startposition = this.getPosition();
        bottom = startposition.y;
        top = startposition.y + 15.2f;
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().y < bottom){
            super.setPosition(startposition);
            delta*=-1;
        }
        if(getPosition().y > top){
            delta *= -1;
        }
        super.setPosition(new Vec2(this.getPosition().x, this.getPosition().y + delta));
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
