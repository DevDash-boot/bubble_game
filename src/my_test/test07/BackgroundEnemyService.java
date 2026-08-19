package my_test.test07;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundEnemyService {
    private BufferedImage image;
    private Enemy enemy;

    public BackgroundEnemyService(Enemy enemy) {
        this.enemy = enemy;

        try {
            image = ImageIO.read(
                    new File("images/BackgroundMapService.png")
            );
        } catch (IOException e) {
            System.out.println("이미지 경로 및 파일명을 확인");
            System.err.println("해당 경로의 이미지를 찾을 수 없음");
        }
    }

    // Enemy의 왼쪽 아래에 플랫폼이 있는지 확인
    public boolean leftGround() {

        int rgb = image.getRGB(
                enemy.getX() + 10,
                enemy.getY() + 53
        );

        Color color = new Color(rgb);

        return isBlue(color);
    }

    // Enemy의 오른쪽 아래에 플랫폼이 있는지 확인
    public boolean rightGround() {

        int rgb = image.getRGB(
                enemy.getX() + 40,
                enemy.getY() + 53
        );

        Color color = new Color(rgb);

        return isBlue(color);
    }

    // Enemy의 왼쪽에 벽이 있는지 확인
    public boolean leftWall() {

        int rgb = image.getRGB(
                enemy.getX() - 1,
                enemy.getY() + 25
        );

        Color color = new Color(rgb);

        return isRed(color);
    }

    // Enemy의 오른쪽에 벽이 있는지 확인
    public boolean rightWall() {

        int rgb = image.getRGB(
                enemy.getX() + 51,
                enemy.getY() + 25
        );

        Color color = new Color(rgb);

        return isRed(color);
    }

    // 파란색 = 플랫폼
    private boolean isBlue(Color color) {
        return color.getRed() == 0
                && color.getGreen() == 0
                && color.getBlue() == 255;
    }

    // 빨간색 = 벽
    private boolean isRed(Color color) {
        return color.getRed() == 255
                && color.getGreen() == 0
                && color.getBlue() == 0;
    }
}