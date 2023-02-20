import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * 定义画板类 MyPanel
 * 继承于 JPanel，实现 KeyListener 接口
 */
// 为了让 Panel 不同地重绘子弹，需要将 MyPanel 实现 Runnable，当做一个线程
public class MyPanel extends JPanel implements KeyListener, Runnable {
    static int panelWidth = 1000;
    static int panelHeight = 750;

    // 定义自己的坦克
    Hero hero = null;
    // 定义敌人的坦克
    Vector<EnemyTank> enemyTanks = null;
    //当坦克被击中加入bomb对象
    Vector<Bomb> bombs = new Vector<>();
    int defaultEnemyTankSize = 6;

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        // 初始化自己的坦克
        hero = new Hero(500, 500);

        // 初始化敌人的坦克
        enemyTanks = new Vector<>();
        for (int i = 0; i < defaultEnemyTankSize; i++) {
            // 创建一个敌人坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            // 启动线程
            new Thread(enemyTank).start();
            // 创建新shot并加入Vector
            Shot shot = new Shot(enemyTank.getX() + 25, enemyTank.getY() + 66, enemyTank.getDir());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        // 初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, panelWidth, panelHeight);  // 界面背景

        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDir(), 0);  // 画自己坦克
        }
        for (int i = 0; i < enemyTanks.size(); i++) {  // 遍历 Vector 画敌方坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.getLive()) {  // 若该坦克已被销毁，则不画
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDir(), 1);
            }
            //绘出每个坦克的每个子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (shot.getLive()) {
                    g.draw3DRect(shot.getX(), shot.getY(), 5, 5, false);
                } else {
                    enemyTank.shots.remove(shot);
                }
            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 将hero的子弹集合 shots 中遍历画出子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.getLive()) {
                g.setColor(Color.yellow);
                g.draw3DRect(shot.getX(), shot.getY(), 5, 5, false);
                g.setColor(Color.black);
            } else {
                hero.shots.remove(shot);
            }
        }
        //若bomb不为空，则有坦克爆炸
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

    }

    /**
     * 画出坦克的方法
     *
     * @param x    坦克左上角 x 坐标
     * @param y    坦克左上角 y 坐标
     * @param g    画笔
     * @param dir  坦克头的朝向
     * @param type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int dir, int type) {

        // 根据坦克类型确定颜色
        switch (type) {
            case 0: // 自己的坦克
                g.setColor(Color.yellow);
                break;
            case 1: // 敌人的坦克
                g.setColor(Color.cyan);
                break;
        }

        //根据坦克方向绘制坦克
        switch (dir) {
            case 0:  // 向上
                g.fill3DRect(x, y, 10, 61, false);  // 左边轮子
                g.fill3DRect(x + 41, y, 10, 61, false);  // 右边轮子
                g.fill3DRect(x + 10, y + 10, 31, 41, false); // 坦克身体
                g.fillOval(x + 15, y + 20, 20, 20); // 坦克圆盖
                g.fill3DRect(x + 23, y - 5, 5, 35, false); // 炮管
                break;
            case 1:  // 向右
                g.fill3DRect(x, y, 61, 10, false);
                g.fill3DRect(x, y + 41, 61, 10, false);
                g.fill3DRect(x + 10, y + 10, 41, 31, false);
                g.fillOval(x + 20, y + 15, 20, 20);
                g.fill3DRect(x + 30, y + 23, 35, 5, false);
                break;
            case 2:  // 向左
                g.fill3DRect(x, y, 61, 10, false);
                g.fill3DRect(x, y + 41, 61, 10, false);
                g.fill3DRect(x + 10, y + 10, 41, 31, false);
                g.fillOval(x + 20, y + 15, 20, 20);
                g.fill3DRect(x - 5, y + 23, 35, 5, false);
                break;
            case 3:  // 向下
                g.fill3DRect(x, y, 10, 61, false);
                g.fill3DRect(x + 41, y, 10, 61, false);
                g.fill3DRect(x + 10, y + 10, 31, 41, false);
                g.fillOval(x + 15, y + 20, 20, 20);
                g.fill3DRect(x + 23, y + 30, 5, 35, false);
                break;
        }
    }

    // 编写方法，判断子弹是否击中我的坦克
    public void  hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if(hero.isLive && shot.getLive()) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    // 编写方法，判断子弹是否击中敌人坦克
    // 在哪个循环里面判断？-run
    public void hitTank(Shot s, Tank anyTank) {
        switch (anyTank.getDir()) {
            case 0:
            case 3:
                if ((s.getX() >= anyTank.getX() && s.getX() <= anyTank.getX() + 51) &&
                        (s.getY() >= anyTank.getY() && s.getY() <= anyTank.getY() + 61)) {
                    s.setLive(false);
                    anyTank.isLive = false;
                    // 移除该被击中的敌方坦克
                    if(anyTank instanceof EnemyTank ){
                        enemyTanks.remove(anyTank);  // 注意，这里一定要remove Object，不能按照索引！
                    }
                    Bomb bomb = new Bomb(anyTank.getX(), anyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 2:
                if ((s.getX() >= anyTank.getX() && s.getX() <= anyTank.getX() + 61) &&
                        (s.getY() >= anyTank.getY() && s.getY() <= anyTank.getY() + 51)) {
                    s.setLive(false);
                    anyTank.isLive = false;
                    // 移除该被击中的敌方坦克
                    if(anyTank instanceof EnemyTank ){
                        enemyTanks.remove(anyTank);  // 注意，这里一定要remove Object，不能按照索引！
                    }
                    Bomb bomb = new Bomb(anyTank.getX(), anyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println((char) e.getExtendedKeyCode());
        if (e.getExtendedKeyCode() == KeyEvent.VK_W) {
            hero.setDir(0);
            hero.moveUp();
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_D) {
            hero.setDir(1);
            hero.moveRight();
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_A) {
            hero.setDir(2);
            hero.moveLeft();
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_S) {
            hero.setDir(3);
            hero.moveDown();
        }

        if (e.getExtendedKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J，开始设射击");
            hero.shotEnemyTank();
        }
        // 重绘面板
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void hitEnemyTank() {
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.getLive()) {  // 判断自己的子弹是否存活
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }
    public void touchJudge() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            hero.isTouchTank(enemyTanks.get(i));
            for (int j = 0; j < enemyTanks.size(); j++) {
                EnemyTank Tank1 = enemyTanks.get(i);
                EnemyTank Tank2 = enemyTanks.get(j);
                if (!Tank1.equals(Tank2)) {
                    Tank1.isTouchTank(Tank2);
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            // System.out.println("重绘-mp线程");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 判断是否击中敌人
            hitEnemyTank();
            // 判断是否击中自己
//            hitHero();
            // 判断是否相撞
            touchJudge();

            this.repaint();
        }
    }
}
