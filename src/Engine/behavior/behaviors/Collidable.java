package Engine.behavior.behaviors;

//import behavior.Behavior;

import Engine.behavior.Behavior;

public interface Collidable extends Behavior {
    void handleCollision(Collidable var1);
}
