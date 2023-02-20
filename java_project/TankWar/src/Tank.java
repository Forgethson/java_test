/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * Tank类-基础类
 */
public class Tank {
    private int x;  // 坦克横坐标
    private int y;  // 坦卡纵坐标
    private int dir = 0;  // 坦克方向: 0上 1右 2左 3下
    private int speed = 1;
    boolean isLive = true;
    boolean isTouchUp = false;  // 相撞标记
    boolean isTouchRight = false;
    boolean isTouchLeft = false;
    boolean isTouchDown = false;

    // 判断两个坦克是否碰撞
    public void isTouchTank(Tank tank) {
        switch (this.dir) {
            case 0:  // up
                if (tank.dir == 0 || tank.dir == 3) {
                    isTouchUp = x - 51 < tank.x && tank.x < x + 51 && y - 61 < tank.y && tank.y < y;
                }
                if (tank.getDir() == 1 || tank.getDir() == 2) {
                    isTouchUp = x - 61 < tank.x && tank.x < x + 61 && y - 51 < tank.y && tank.y < y;
                }
                if (isTouchUp) {
                    System.out.println("前向碰撞");
                }
                break;
            case 1:  // right
                if (tank.dir == 0 || tank.dir == 3) {
                    isTouchRight = x < tank.x && tank.x < x + 51 && y - 61 < tank.y && tank.y < y + 61;
                }
                if (tank.getDir() == 1 || tank.getDir() == 2) {
                    isTouchRight = x < tank.x && tank.x < x + 61 && y - 51 < tank.y && tank.y < y + 51;
                }
                if (isTouchRight) {
                    System.out.println("右向碰撞");
                }
                break;
            case 2:  // left
                if (tank.dir == 0 || tank.dir == 3) {
                    isTouchLeft = x - 51 < tank.x && tank.x < x && y - 61 < tank.y && tank.y < y + 61;
                }
                if (tank.dir == 1 || tank.dir == 2) {
                    isTouchLeft = x - 61 < tank.x && tank.x < x && y - 51 < tank.y && tank.y < y + 51;
                }
                if (isTouchLeft) {
                    System.out.println("左向碰撞");
                }
                break;
            case 3:  // down
                if (tank.dir == 0 || tank.dir == 3) {
                    isTouchDown = x - 51 < tank.x && tank.x < x && y - 61 < tank.y && tank.y < y + 61;
                }
                if (tank.dir == 1 || tank.dir == 2) {
                    isTouchDown = x - 61 < tank.x && tank.x < x && y - 51 < tank.y && tank.y < y + 51;
                }
                if (isTouchDown) {
                    System.out.println("后向碰撞");
                }
                break;
        }
    }

    public Shot createBullet() {
        Shot s = null;
        switch (getDir()) {
            case 0: // up
                s = new Shot(getX() + 25, getY() + 5, 0);
                break;
            case 1: // right
                s = new Shot(getX() + 65, getY() + 25, 1);
                break;
            case 2: // left
                s = new Shot(getX() - 5, getY() + 25, 2);
                break;
            case 3: // down
                s = new Shot(getX() + 25, getY() + 66, 3);
                break;
        }
        return s;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveUp() {
        if (y > 0 && !isTouchUp) {
            y -= speed;
        }
    }

    public void moveRight() {
        if (x + 61 < MyPanel.panelWidth && !isTouchRight) {
            x += speed;
        }
    }

    public void moveDown() {
        if (y + 61 < MyPanel.panelHeight && !isTouchDown) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (x > 0 && !isTouchLeft) {
            x -= speed;
        }
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
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

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
