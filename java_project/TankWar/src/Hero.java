import java.util.Vector;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * Tank类-个人的坦克类
 */
public class Hero extends Tank {
    private Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
        this.setSpeed(10);
    }

    public Shot getShot() {
        return shot;
    }

    public void shotEnemyTank() {

        // 最多同时5枚子弹
        if (shots.size() == 5) {
            return;
        }
        shot = createBullet();
        // 启动 Hero 的 Shot 线程
        shots.add(shot);
        new Thread(shot).start();
    }
}
