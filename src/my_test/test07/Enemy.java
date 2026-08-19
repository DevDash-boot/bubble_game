package my_test.test07;

import lombok.Getter;

import javax.swing.*;
@Getter
public class Enemy extends JLabel{
    private int x;
    private int y;

    private ImageIcon enemyR;
    private ImageIcon enemyL;

    private BackgroundEnemyService backgroundEnemyService;

    private boolean rightMoving;
    private boolean leftMoving;

    private boolean alive = true;

    private final int SPEED = 2;
    private final int MOVE_DELAY = 10;

    public Enemy(int x, int y) {

        this.x = x;
        this.y = y;

        backgroundEnemyService = new BackgroundEnemyService(this);

        initData();
        setInitLayout();

        startMove();
    }

    private void initData() {

        enemyR = new ImageIcon("images/enemyR.png");
        enemyL = new ImageIcon("images/enemyL.png");
    }

    private void setInitLayout() {

        setSize(50, 50);
        setLocation(x, y);

        setIcon(enemyR);
    }

    // Enemy 자동 이동
    private void startMove() {

        new Thread(() -> {

            rightMoving = true;

            while (alive) {

                if (rightMoving) {
                    moveRight();
                }

                else if (leftMoving) {
                    moveLeft();
                }

                try {
                    Thread.sleep(MOVE_DELAY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }

    // 오른쪽 이동
    private void moveRight() {

        // 오른쪽 벽
        if (backgroundEnemyService.rightWall()) {
            changeDirection();
            return;
        }

        // 오른쪽 발밑에 플랫폼이 없다면 방향 전환
        if (!backgroundEnemyService.rightGround()) {
            changeDirection();
            return;
        }

        x += SPEED;

        setIcon(enemyR);
        setLocation(x, y);
    }

    // 왼쪽 이동
    private void moveLeft() {

        // 왼쪽 벽
        if (backgroundEnemyService.leftWall()) {
            changeDirection();
            return;
        }

        // 왼쪽 발밑에 플랫폼이 없다면 방향 전환
        if (!backgroundEnemyService.leftGround()) {
            changeDirection();
            return;
        }

        x -= SPEED;

        setIcon(enemyL);
        setLocation(x, y);
    }

    // 방향 전환
    private void changeDirection() {

        if (rightMoving) {

            rightMoving = false;
            leftMoving = true;

        } else {

            leftMoving = false;
            rightMoving = true;
        }
    }

    // Enemy 제거
    public void die() {

        alive = false;

        if (getParent() != null) {

            getParent().remove(this);

            getParent().revalidate();
            getParent().repaint();
        }
    }
}
