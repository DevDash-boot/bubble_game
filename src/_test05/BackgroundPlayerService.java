package _test05;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// 클래스 역할 : 플레이어의 벽 충돌 감지 서비스(백그라운드에서 계속 동작)
// 메인 쓰레드는 너무 바쁘다.
public class BackgroundPlayerService implements Runnable {

    // Image / ImageIcon : 좌표 값으로 현재 픽셀 값을 추출할 수 없다.
    // BufferedImage : 메모리에 픽셀 배열로 저장된 이미지
    // BufferedImage에서 제공하는 getRGB(x, y)로 특정 좌표의 색상 값을 직접 읽을 수 있다.
    private BufferedImage image;
    private Player player;  // BubbleFrame에서 생성되어 있는 Player

    // 생성자 주입(DI : Dependency Injection)
    public BackgroundPlayerService(Player player) {
        this.player = player;

        try {
            image = ImageIO.read(new File("images/BackgroundMapService.png"));
        } catch (IOException e) {
            System.out.println("이미지 경로 및 파일명을 확인");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        // 게임이 종료될 때까지 계속 실행시킬 예정
        while (true) {
            Color leftColor = new Color(image.getRGB(player.getX() + 5, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));

            // System.out.println("leftColor : " + leftColor);
            // System.out.println("rightColor : " + rightColor);

            // 왼쪽 벽 감지 판단 - 빨간색 이라면 플레이어가 왼쪽에 충돌
            if (isRed(leftColor)) {
                // 현재 플레이어가 왼쪽 벽에 충돌된 상태
                player.setLeftWallCrash(true);
                player.setLeft(false); // 움직임 해제
            } else {
                player.setLeftWallCrash(false);
            }

            // 오른쪽 벽 감지 판단 - 빨간색 이라면 플레이어가 오른쪽에 충돌
            if (isRed(rightColor)) {
                // 현재 플레이어가 왼쪽 벽에 충돌된 상태
                player.setRightWallCrash(true);
                player.setRight(false); // 움직임 해제
            } else {
                player.setRightWallCrash(false);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean isRed(Color color) {
        return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0;
    }


}
