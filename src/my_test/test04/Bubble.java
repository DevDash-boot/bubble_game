package my_test.test04;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

// 버블 폭탄
@Getter
public class Bubble extends JLabel {

    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon, bombIcon;
    private final int BUBBLE_SPEED = 3;
    private final int FORWARD_BUBBLE = 80;
    private final int UP_BUBBLE = 100;
    private Bubble bubble;

    // 버블 벽 충돌 상태
    @Setter
    private boolean leftWallCrash;
    @Setter
    private boolean rightWallCrash;

    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();
    }

    private void initData() {
        bubbleIcon = new ImageIcon("images/bubble.png");
        bombIcon = new ImageIcon("images/bomb.png");
    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setLocation(x, y);
        setSize(50, 50);
        setIcon(bubbleIcon);
    }

    public void moveBubble() {
        new Thread(() -> {
            if(player.getIcon() == player.getPlayerR() ){
                for (int i = 0; i < FORWARD_BUBBLE; i++) {
                    x += BUBBLE_SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if(player.getIcon() == player.getPlayerL()){
                for (int i = 0; i < FORWARD_BUBBLE; i++) {
                    x -= BUBBLE_SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            for(int j=0;j<UP_BUBBLE; j++){
                y-=BUBBLE_SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            setIcon(bombIcon);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setVisible(false);

        }).start();
    }
}
