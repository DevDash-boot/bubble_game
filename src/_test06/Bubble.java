package _test06;

import lombok.Getter;

import javax.swing.*;

// 버블 폭탄
@Getter
public class Bubble extends JLabel implements Moveable {

    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon;
    private ImageIcon bombIcon;
    private BackgroundBubbleService backgroundBubbleService;

    // 버블 이동 상태 플래그
    private static final int HORIZONTAL_DISTANCE = 400; // 버블의 수평 이동 거리
    private static final int BUBBLE_SPEED_MS = 1; // 버블의 이동 간격
    private static final int SCREEN_TOP = 0; // 화면 상단 경계(Y값)

    private boolean leftMoving;
    private boolean rightMoving;
    private boolean upMoving;

    public Bubble(Player player) {
        this.player = player;
        this.backgroundBubbleService = new BackgroundBubbleService(this);
        initData();
        setInitLayout();
        // 플레이어 왼쪽 또는 오른쪽 하나만
        new Thread(() -> {
            if (player.getPlayerWay() == PlayerWay.LEFT) {
                left();
            } else if (player.getPlayerWay() == PlayerWay.RIGHT) {
                right();
            }
        }).start();
    }

    private void initData() {
        bubbleIcon = new ImageIcon("images/bubble.png");    // 물방울 아이콘
        bombIcon = new ImageIcon("images/bomb.png");        // 물방울 폭발 아이콘
    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setLocation(x, y);
        setSize(50, 50);
        setIcon(bubbleIcon);
    }

    // 왼쪽 상태
    @Override
    public void left() {
        leftMoving = true;
        for (int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            if (backgroundBubbleService.leftWall()) {
                // true가 넘어온다면 왼쪽 벽에 닿음
                break;
            }
            // x값을 감소시켜 왼쪽으로 가기
            x--;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 왼쪽으로 가는 것을 멈추고 위로 올라가게
        leftMoving = false;
        up();
    }

    // 오른쪽 상태
    @Override
    public void right() {
        rightMoving = true;
        for (int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            if (backgroundBubbleService.rightWall()) {
                // true가 넘어온다면 오른쪽 벽에 닿음
                break;
            }
            // x값을 증가시켜 오른쪽으로 가기
            x++;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 오른쪽으로 가는 것을 멈추고 위로 올라가게
        rightMoving = false;
        up();
    }

    @Override
    public void up() {
        upMoving = true;
        while (y > SCREEN_TOP) {
            if (backgroundBubbleService.topWall()) {
                // true가 넘어온다면 천장에 닿음
                break;
            }
            // y값을 감소 시켜 위로 이동하기
            y--;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 올라가는 것을 멈추고 폭발하게
        upMoving = false;
        explode();
    }

    // 물방울 폭발과 사라지게 하기
    private void explode() {
        try {
            Thread.sleep(1500);
            setIcon(bombIcon);
            Thread.sleep(500);
            // 부모 컴퍼넌트에서 제거
            if (getParent() != null) {
                this.setVisible(false);
                getParent().remove(this);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
