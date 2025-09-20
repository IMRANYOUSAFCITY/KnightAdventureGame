package game;

import javax.swing.*;
import java.awt.*;

/**
 * Your main game entry point
 */
public class Game {
    static Game game;
    GameLevel level;
    GameView view;
    JFrame f;
    Menu m;
    CharacterController controller;
    /** Initialise a new Game. */
    public Game() {
        game = this;
        level = new Level1();
        controller = new CharacterController(GameLevel.getPlayer());
        view = new GameView(level,1200,650,GameLevel.getPlayer());
        f = new JFrame("City Game");
        f.add(view);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(false);
        f.setResizable(true);
        f.pack();
        f.setVisible(true);
        view.addKeyListener(controller);
        view.requestFocus();
    }
    public void GameEnded(){
        level.stop();
        if(level instanceof Level1){
            ((Level1) level).StopMusic1();
        } else if (level instanceof Level2) {
            ((Level2) level).StopMusic2();
        } else if (level instanceof Level3) {
            ((Level3) level).StopMusic3();
        }
        f.remove(view);
        m = new Menu(this);
        m.GetJPanel().setPreferredSize(new Dimension(1200,650));
        f.add(m.GetJPanel());
        f.pack();
    }
    public void Redo(){
        level = new Level1();
        view.setWorld(level);
        view.SetPlayer(level.getPlayer());
        view.changeBackground(3);
        view.ChangeView(2);
        f.remove(m.GetJPanel());
        f.add(view);
        f.pack();
        controller.SetControl(level.getPlayer());
        view.requestFocus();
    }
    public void NextLevel(){
        if(level instanceof Level1){
            level.stop();
            ((Level1) level).StopMusic1();
            level = new Level2(controller,this);
            view.setWorld(level);
            view.SetPlayer(level.getPlayer());
            view.changeBackground(1);
            view.ChangeView(1);
            controller.SetControl(level.getPlayer());
        } else if (level instanceof  Level2) {
            level.stop();;
            ((Level2)level).StopMusic2();
            level = new Level3();
            view.setWorld(level);
            view.SetPlayer(level.getPlayer());
            view.changeBackground(2);
            view.ChangeView(0);
            controller.SetControl(level.getPlayer());
        }
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
