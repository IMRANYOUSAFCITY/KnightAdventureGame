package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterController implements KeyListener {
    static int code;
    Player P;
    static boolean right = true;
    private static boolean jump;
    private static boolean active;
    static boolean attack;
    static boolean block;
    static boolean two = false;
    int controlcounter;
    public static boolean isJump() {
        return jump;
    }
    public static boolean isActive() {
        return active;
    }
    private float speed = 0;
    public CharacterController(Player player) {
        P = player;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void setAttack(boolean attack) {
        CharacterController.attack = attack;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!block) {
            code = e.getKeyCode();
            if (code == KeyEvent.VK_A && e.isShiftDown() && !two) {
                right = false;
                active = true;
                P.crouchLeft();
            } else if (code == KeyEvent.VK_D && e.isShiftDown() && !two) {
                right = true;
                active = true;
                P.crouchRight();
            } else if (code == KeyEvent.VK_W && !two) {
                jump = true;
                if (right) {
                    P.changeRight();
                } else {
                    P.changeLeft();
                }
                jump = false;
            } else if (code == KeyEvent.VK_A && !two) {
                active = true;
                right = false;
                P.changeLeft();
            } else if (code == KeyEvent.VK_D && !two) {
                active = true;
                right = true;
                P.changeRight();
            } else if (code == KeyEvent.VK_SHIFT && !two) {
                active = false;
                if (right) {
                    P.crouchRight();
                } else {
                    P.crouchLeft();
                }
            } else if (code == KeyEvent.VK_J && !two) {
                if (!attack) {
                    attack = true;
                    active = false;
                    if (right) {
                        P.attackRight();
                    } else {
                        P.attackLeft();
                    }
                }
            } else if (code == KeyEvent.VK_H && e.isShiftDown()) {
                controlcounter = 1;
            } else if (code == KeyEvent.VK_G && e.isShiftDown()) {
                controlcounter = 3;
            } else if (code == KeyEvent.VK_L && e.isShiftDown()) {
                controlcounter = 5;
            } else if (code == KeyEvent.VK_R && two) {
                P.speedup();
            } else if (code == KeyEvent.VK_E) {
                controlcounter = 2;
            } else if (code == KeyEvent.VK_O) {
                controlcounter = 4;
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        code = e.getKeyCode();
        if (!block) {
            attack = false;
            if (!two) {
                if (code == KeyEvent.VK_D && e.isShiftDown()) {
                    active = false;
                    P.crouchRight();
                } else if (code == KeyEvent.VK_A && e.isShiftDown()) {
                    active = false;
                    P.crouchLeft();
                } else if (code == KeyEvent.VK_SHIFT) {
                    P.stopWalking();
                    if (right) {
                        P.changeRight();
                    } else {
                        P.changeLeft();
                    }
                } else if (code == KeyEvent.VK_J) {
                    attack = false;
                    active = false;
                    if (right) {
                        P.changeRight();
                    } else {
                        P.changeLeft();
                    }
                } else if (code == KeyEvent.VK_D) {
                    active = false;
                    P.changeRight();
                } else if (code == KeyEvent.VK_A) {
                    active = false;
                    P.changeLeft();
                }
            }
        }
    }

    public void SetControl (Player p){
        P = p;
    }
}
