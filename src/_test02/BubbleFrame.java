package _test02;

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
        setSize(1016, 640);
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
                int keyCode = e.getKeyCode();

                System.out.println("KeyCode : " + keyCode);

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
                    case KeyEvent.VK_LEFT:
                        player.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.right();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        player.setLocation(200, 511);
                        break;
                    default:
                        return;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    }

    // Test 코드 작성
    public static void main(String[] args) {
        new BubbleFrame();
    }
}
