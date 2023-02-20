/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 射击一颗子弹
 */
public class Shot implements Runnable {
    private int x;  // x坐标
    private int y;  // y坐标
    private int dir = 0;  // 方向
    private int speed = 3;  // 速度
    private boolean isLive = true;  // 是否存活

    public Shot(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (dir) {
                case 0:  // up
                    y -= speed;
                    break;
                case 1:  // right
                    x += speed;
                    break;
                case 2:  // left
                    x -= speed;
                    break;
                case 3:  // down
                    y += speed;
            }

//            System.out.println("子弹" + Thread.currentThread().getName() + "：" + x + " " + y);

            if (!(x >= 0 && x <= 1000 && y >= 0 && y < +750) || !isLive) {
                setLive(false);
                break;
            }
        }
    }
}
