import java.util.ArrayList;

/**
 * @author a2920
 */
public class RemixMap {
    private String name;
    private int x;
    private int y;
    private ArrayList<EnemyBuildings> enemyBuildings = new ArrayList<EnemyBuildings>();

    public RemixMap() {
    }

    public RemixMap(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public ArrayList<EnemyBuildings> getEnemyBuildings() {
        return enemyBuildings;
    }

    public void setEnemyBuildings(ArrayList<EnemyBuildings> enemyBuildings) {
        this.enemyBuildings = enemyBuildings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "RemixMap{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * 将敌方单位放入地图
     */
    public void addBuildings(EnemyBuildings enBs) {
        enemyBuildings.add(enBs);
        enBs.getX(this.x);
        enBs.getY(this.y);
    }

    /**
     * 获得地图中还活着的怪物
     */
    public ArrayList<EnemyBuildings> getAliveMonsters() {
        //新建一个数组列表，作为容器
        ArrayList<EnemyBuildings> am = new ArrayList<>();
        //遍历当前地图中的所有怪物
        for (EnemyBuildings m : this.enemyBuildings) {
            //挑出血大于0的，放入新容器
            if (m.getHp() > 0) {
                am.add(m);
            }
        }
        return am;
    }
}
