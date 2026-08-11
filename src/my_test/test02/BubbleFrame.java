package my_test.test02;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {
    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initData();
        setInitLayout();
        addEventListener();
        setVisible(true);
    }

    private void initData() {
        setTitle("버블버블");
        setSize(1000, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
        setContentPane(backgroundMap);  // root 패널에 JLabel 넣기
        player = new Player();
    }

    private void setInitLayout() {
        setLayout(null);    // 좌표기반
        setResizable(false);    // 마우스로 크기조정 불가
        setLocationRelativeTo(null);    // JFrame을 화면 가운데 배치
        add(player);
    }

    private void addEventListener() {
        // 프레임에 키보드 리스너 등록
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KeyCode : " + e.getKeyCode());

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
                    case KeyEvent.VK_LEFT:
                        player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.right();
                        break;
                    case KeyEvent.VK_DOWN:
                        player.down();
                        break;
                    default:
                        return;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("KeyReleased : " + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        player.setUp(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        // 왼쪽으로 가고 있다가 방향키를 떼면 while문을 멈추는 동작이 필요
                        player.setLeft(false); // 돌아가고 있던 while 문이 while문이 false 되어서 멈추게 된다.
                        break;
                    case KeyEvent.VK_RIGHT:
                        // 오른쪽으로 가고 있다가 방향키를 떼면 while문을 멈추는 동작이 필요
                        player.setRight(false);
                        break;
                }
            }
        });
    }

    // Test 코드 작성
    public static void main(String[] args) {
        new BubbleFrame();
    }
}
