package _test05;

import lombok.Getter;

import javax.swing.*;

// 버블 폭탄
@Getter
public class Bubble extends JLabel implements Moveable {

    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon, bombIcon;

    // 버블 이동 상태 플래그
    private static final int HORIZONTAL_DISTANCE = 400; // 버블의 수평 이동 거리
    private static final int BUBBLE_SPEED_MS = 1; // 버블의 이동 간격
    private static final int SCREEN_TOP = 0; // 화면 상단 경계(Y값)

    private boolean leftMoving;
    private boolean rightMoving;
    private boolean upMoving;

    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();

        new Thread(() -> {
            if(player.getPlayerWay() == PlayerWay.LEFT){
                left();
            }else if(player.getPlayerWay() == PlayerWay.RIGHT){
                right();
            }

        }).start();
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

    @Override
    public void left() {
        leftMoving = true;
        for(int i = 0; i<HORIZONTAL_DISTANCE;i++){
            x--;
            setLocation(x,y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        leftMoving = false;
        up();
    }

    @Override
    public void right() {
        rightMoving = true;
        for(int i = 0; i<HORIZONTAL_DISTANCE;i++){
            x++;
            setLocation(x,y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        rightMoving = false;
        up();
    }

    @Override
    public void up() {
        upMoving = true;
        while(y>SCREEN_TOP){
            y--;
            setLocation(x,y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
