package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView{
    GameLevel level;
    int rk = 3;
    int x;
    int y;
    Player p;
    private static  Image background1 = new ImageIcon("data/background.png").getImage();
    public GameView(GameLevel world,int H,int W,Player p){
        super(world,H,W);
        level = world;
        this.p = p;
        x = W;
        y = H;
        p.current = p.h6;
    }
    public void SetPlayer(Player p){
        this.p = p;
        this.p.ReassignHealth();
    }
    public void ChangeView(int b){
        switch (b){
            case 1:
                setZoom(60);
                break;
            case 0:
                setZoom(20);
                break;
        }
    }


    public void changeBackground(int x){
        switch (x) {
            case 1:
                background1 = new ImageIcon("data/fff.gif").getImage();
                repaint();
                break;
            case 2:
                background1 = new ImageIcon("data/b.png").getImage();
                repaint();
                rk = 4;
                break;
            case 3:
                background1 = new ImageIcon("data/background.png").getImage();
                repaint();
                rk = 3;
                break;
        }
    }

    @Override
    protected void paintBackground(Graphics2D g){
        super.paintBackground(g);
        g.drawImage(background1,0,0,1200,650,this);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.setColor(Color.black);
        g.drawString("keys Left: " + (rk-Player.keycounter),0,15);
        g.drawString("Coins Collected: " + Coin.coincounter,0,30);
        g.drawImage(p.current,100,10,200,20,this);
    }
}

