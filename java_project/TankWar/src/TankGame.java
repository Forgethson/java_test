import javax.swing.*;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * 画框类
 */
public class TankGame extends JFrame {

    MyPanel mp = null;
    static int frameWidth = 1100;
    static int frameHeight = 850;

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        mp = new MyPanel();

        // 将mp放入到Thread并启动
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp); // 把面板加入到面框（this）
        this.addKeyListener(mp); // 让JFrame监听键盘事件（this）
        this.setSize(frameWidth, frameHeight);  // 设置面框尺寸
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 关闭窗口后自动停止运行
        this.setVisible(true);  // 可见
    }
}
