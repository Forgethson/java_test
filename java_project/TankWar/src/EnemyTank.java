import java.util.Vector;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 */
public class EnemyTank extends Tank implements Runnable {

    Vector<Shot> shots = new Vector<>();

    public boolean getLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
        setDir(3);
        this.setSpeed(1);
    }

    @Override
    public void run() {
        while (true) {

            // 如果shots size = 0，
            // 发射子弹
            if(isLive && shots.size() <= 1) {
                Shot s = null;
                s = createBullet();
                shots.add(s);
                new Thread(s).start();

            }

            // 根据坦克的方向继续移动
            switch (getDir()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //随机改变坦克方向
            setDir((int) (Math.random() * 4)); // 返回 [0, 4) 的小数
            if (!getLive()) {
                break;
            }
        }
    }
}
