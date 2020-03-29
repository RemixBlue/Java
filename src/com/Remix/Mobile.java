import java.util.*;

/**
 * @author a2920
 * 移动
 */
public class Mobile extends Hero {
    private HashMap<Integer, Skills> skillsHashMap = new HashMap<Integer, Skills>();
    /**
     * 为角色添加技能字段
     */
    private Skills skills;
    /**
     * 为角色增加当前位置
     */
    private int x;
    private int y;

    public Mobile() {
    }

    public Mobile(String name, int hp, int mana, int as, int ms, int nad) {
        super(name, hp, mana, as, ms, nad);
    }

    public HashMap<Integer, Skills> getSkillsHashMap() {
        return skillsHashMap;
    }

    public void setSkillsHashMap(HashMap<Integer, Skills> skillsHashMap) {
        this.skillsHashMap = skillsHashMap;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
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

    /**
     * 添加技能
     */
    public void addSkills(HashMap hashMap) {
        skillsHashMap.putAll(hashMap);
    }

    /**
     * 打印当前英雄信息
     */
    @Override
    public String toString() {
        return "你的英雄: " + getName() +
                ", 当前血量: " + getHp() +
                ", 当前魔法值: " + getMana() +
                ", 普通攻击值: " + getNad();
    }

    /**
     * 打印技能信息
     */
    public void skillsInformation() {
        Set<Map.Entry<Integer, Skills>> entrySet = skillsHashMap.entrySet();
        for (Map.Entry<Integer, Skills> entry : entrySet) {
            System.out.println(entry);
        }
    }

    /**
     * 普通攻击
     */
    public void normalAttackMethod(EnemyBuildings en, RemixMap map) {
        // 创建一个新的集合,来装存活的敌方单位
        ArrayList<EnemyBuildings> E = map.getAliveMonsters();
        // 打印存活单位
        // 没卵用
        for (EnemyBuildings ebs : E) {
            System.out.println("\n存活的敌方单位:");
            ebs.print();
            // 有英雄
            if (ebs == en) {
                // 判断双方攻速
                if (this.getAs() > en.getAs()) {
                    System.out.println("你的攻速高于对方,你先出手:");
                    putoremix(this, en);
                } else {
                    System.out.println("对方的攻速高于你,对方先出手:");
                    // 调用单位类中的普通攻击方法
                    en.putongattack(this);
                    // 判断是否死亡
                    if (this.getHp() <= 0) {
                        System.out.println("你死了!");
                        return;
                    }
                    // 你反击
                    System.out.println("\n你" + this.getName() + "反击");
                    this.putongattack(en);
                    // 判断是否死亡
                    if (en.getHp() <= 0) {
                        System.out.println("敌方英雄已死亡!");
                        return;
                    }
                    // 加血
                    int v = this.getHp() + (int) (this.getNad() * 0.8);
                    int i = (int) (this.getNad() * 0.8);
                    this.setHp(v);
                    System.out.println("吸收对方血量: " + i);
                }
            }
            // 没有敌方英雄,只有单位
            if (ebs != en) {
                putoremix(this, ebs);
            }
        }
        return;
    }

    // 你先出手的普通攻击方法
    public static void putoremix(Mobile my, EnemyBuildings en) {
        // 调用单位类中的普通攻击方法
        my.putongattack(en);
        // 吸血
        int v = my.getHp() + (int) (my.getNad() * 0.8);
        int i = (int) (my.getNad() * 0.8);
        my.setHp(v);
        System.out.println("吸收对方血量: " + i);
        // 判断是否死亡
        if (en.getHp() <= 0) {
            System.out.println("敌方英雄已死亡!");
            return;
        }
        // 对方反击
        System.out.println("\n敌方单位" + en.getName() + "反击");
        en.putongattack(my);
        // 判断是否死亡
        if (my.getHp() <= 0) {
            System.out.println("你死了!");
            return;
        }
    }

    /**
     * 技能攻击
     */
    public void skillsAttack(HashMap myMap, HashMap enMap, EnemyBuildings en, RemixMap map) throws InterruptedException {
        // 创建一个新的集合,来装存活的敌方单位
        ArrayList<EnemyBuildings> E = map.getAliveMonsters();
        // 打印存活单位
        // 没卵用
        for (EnemyBuildings ebs : E) {
            System.out.println("\n存活的敌方单位:");
            ebs.print();

            System.out.println("选择技能:\n" +
                    "1." + myMap.get(1) + "\n" +
                    "2." + myMap.get(2) + "\n" +
                    "3." + myMap.get(3));
            int next = new Scanner(System.in).nextInt();

            if (ebs == en) {
                // 判断双方攻速
                if (this.getAs() > en.getAs()) {
                    System.out.println("你的攻速高于对方,你先出手:");
                    switch (next) {
                        case 1: {
                            myenremix(this, en, (Skills) enMap.get(1), (Skills) myMap.get(1));
                            break;
                        }
                        case 2: {
                            myenremix(this, en, (Skills) enMap.get(2), (Skills) myMap.get(2));
                            break;
                        }
                        case 3: {
                            myenremix(this, en, (Skills) enMap.get(3), (Skills) myMap.get(3));
                            break;
                        }
                        default: {
                            System.out.println("输入错误!");
                            break;
                        }
                    }
                } else if (this.getAs() < en.getAs()) {
                    System.out.println("对方攻速高于你,对方先出手:");
                    switch (next) {
                        case 1: {
                            enmyremix(this, en, (Skills) enMap.get(1), (Skills) myMap.get(1));
                            break;
                        }
                        case 2: {
                            enmyremix(this, en, (Skills) enMap.get(2), (Skills) myMap.get(2));
                            break;
                        }
                        case 3: {
                            enmyremix(this, en, (Skills) enMap.get(3), (Skills) myMap.get(3));
                            break;
                        }
                        default: {
                            System.out.println("输入错误!");
                            break;
                        }
                    }
                }
            } else if (ebs != en) {
                switch (next) {
                    case 1: {
                        ebsremix(this, ebs, (Skills) myMap.get(1));
                        break;
                    }
                    case 2: {
                        ebsremix(this, ebs, (Skills) myMap.get(2));
                        break;
                    }
                    case 3: {
                        ebsremix(this, ebs, (Skills) myMap.get(3));
                        break;
                    }
                    default: {
                        System.out.println("输入错误!");
                        break;
                    }
                }
            }
            System.out.println("技能冷却中...");
            if (next == 1) {
                for (int i2 = 30; i2 > 0; i2--) {
                    Thread.sleep(1000);
                    System.out.println("还剩" + i2 + "秒");
                }
            }
            if (next == 2) {
                for (int i2 = 10; i2 > 0; i2--) {
                    Thread.sleep(1000);
                    System.out.println("还剩" + i2 + "秒");
                }
            }
            if (next == 3) {
                for (int i2 = 15; i2 > 0; i2--) {
                    Thread.sleep(1000);
                    System.out.println("还剩" + i2 + "秒");
                }
            }
            System.out.println("技能恢复,请继续进攻!!!");
        }
    }

    // 只有敌方建筑的技能攻击
    private static void ebsremix(Mobile my, EnemyBuildings ebs, Skills skills) {
        my.jinengAttack(ebs, skills);
        // 判断是否死亡
        if (ebs.getHp() <= 0) {
            System.out.println("敌方单位已死亡!");
            return;
        }
        ebs.putongattack(my);
        // 判断是否死亡
        if (my.getHp() <= 0) {
            System.out.println("你死了!");
            return;
        }
    }

    // 敌方英雄比你攻速高的技能攻击
    private static void enmyremix(Mobile my, EnemyBuildings en, Skills enskills, Skills mySkills) {
        en.jinengAttack(my, enskills);
        // 判断是否死亡
        if (my.getHp() <= 0) {
            System.out.println("你死了!");
            return;
        }
        my.jinengAttack(en, mySkills);
        // 判断是否死亡
        if (en.getHp() <= 0) {
            System.out.println("敌方单位已死亡!");
            return;
        }
    }

    // 你比敌方英雄攻速高的技能攻击
    private static void myenremix(Mobile my, EnemyBuildings en, Skills enskills, Skills mysSkills) {
        my.jinengAttack(en, mysSkills);
        // 判断是否死亡
        if (en.getHp() <= 0) {
            System.out.println("敌方单位已死亡!");
            return;
        }
        en.jinengAttack(my, enskills);
        // 判断是否死亡
        if (my.getHp() <= 0) {
            System.out.println("你死了!");
            return;
        }

    }


    /**
     * 进攻方法
     */
    public void attack(RemixMap map, Mobile my, EnemyBuildings en, HashMap<Integer, Skills> myMap, HashMap<Integer, Skills> enMap) throws InterruptedException {
        // 回合计数
        int i = 1;
        // 创建一个新的集合,来装存活的敌方单位
        ArrayList<EnemyBuildings> E = map.getAliveMonsters();
        for (EnemyBuildings ebs : E) {
            while (true) {
                if (this.getHp() <= 0) {
                    // 死亡判断
                    System.out.println("你死了!");
                    return;
                }
                if (this.getX() == 3 && this.getY() == 3 && en.getHp() <= 0 && ebs.getHp() <= 0) {
                    // 胜利判断
                    System.out.println("你胜利了!");
                    return;
                } else if (map.getEnemyBuildings() == null || map.getAliveMonsters().isEmpty()) {
                    // 是否有存活单位判断
                    System.out.println("图中并无敌方单位！");
                    return;
                } else if (this.getX() == 0 && this.getY() == 0) {
                    // 是否在我方基地判断
                    System.out.println("在我方基地不能进攻!!");
                    return;
                } else {
                    System.out.println("选择攻击方式: \n" +
                            "1.普通攻击\n" +
                            "2.技能攻击\n" +
                            "3.撤退");
                    // 输入接收
                    int i1 = new Scanner(System.in).nextInt();
                    // 回合计数
                    System.out.println("===============第" + i + "回合===============");
                    i++;
                    switch (i1) {
                        case 1: {
                            // 调用普通攻击方法
                            my.normalAttackMethod(en, map);
                            break;
                        }
                        case 2: {
                            // 判断蓝条
                            if (this.getMana() <= 0) {
                                System.out.println("你的法力值不足!无法使用技能");
                                break;
                            }
                            // 调用技能攻击方法
                            my.skillsAttack(myMap, enMap, en, map);
                            break;
                        }
                        case 3: {
                            // 撤退方法
                            if (this.getMs()>en.getMs()){
                                System.out.println("逃跑失败!!!不要怂,接着刚!!!");
                                break;
                            }
                            System.out.println("战略撤退不丢人!!!(你个菜鸡🐔)");
                            return;
                        }
                        default: {
                            System.out.println("输入错误!");
                            break;
                        }
                    }
                }
                // 击杀蓝buff判断
                boolean l1 = (this.getX() == 3 && this.getY() == 1 && ebs.getHp() <= 0);
                boolean l2 = (this.getX() == 0 && this.getY() == 2 && ebs.getHp() <= 0);
                if (l1 || l2) {
                    // 打死蓝buff恢复法力值
                    int mymana = this.getMana() + 100;
                    this.setMana(mymana);
                    System.out.println("成功击杀蓝buff!!\n" +
                            "增加法力值: " + 100);
                }
                // 击杀红buff判断
                boolean h1 = (this.getX() == 1 && this.getY() == 3 && ebs.getHp() <= 0);
                boolean h2 = (this.getX() == 2 && this.getY() == 0 && ebs.getHp() <= 0);
                if (h1 || h2) {
                    // 打死红buff增加普通攻击力
                    int mynad = this.getNad() + 150;
                    this.setNad(mynad);
                    System.out.println("成功击杀红buff!!\n" +
                            "增加攻击力: " + 150);
                }// 击杀大小龙判断
                boolean d = (this.getX() == 2 && this.getY() == 1 && ebs.getHp() <= 0);
                boolean x = (this.getX() == 1 && this.getY() == 2 && ebs.getHp() <= 0);
                if (d) {
                    // 击杀大龙增加普通攻击力和生命
                    int mynad = this.getNad() + 230;
                    this.setNad(mynad);
                    int myhp = this.getHp() + 1000;
                    this.setHp(myhp);
                    System.out.println("成功击杀大龙!!\n" +
                            "增加攻击力: " + 230);
                }
                if (x) {
                    // 击杀小龙增加大招伤害,降低消耗法力值
                    int skillsap = myMap.get(1).getSkillsAp() + 1000;
                    myMap.get(1).setSkillsAp(skillsap);
                    int skillsnp = myMap.get(1).getSkillsNp() - 30;
                    myMap.get(1).setSkillsNp(skillsnp);
                    System.out.println("成功击杀小龙!!\n" +
                            "\n技能一伤害 + : " + 1000 +
                            "\n技能一消耗法力值 - :" + 30);
                }
            }
        }
    }


    /**
     * 包含循环逻辑的旅行，完整的用户交互逻辑
     */
    public void do_travel(RemixMap[][] map) {
        do {
            this.travel(map);
            break;
        } while (true);
    }

    /**
     * 移动逻辑
     * 让角色能够在地图上走动，改为私有，只供 travel 调用
     */
    private void goTo(int to_x, int to_y, RemixMap[][] maps) {
        this.setX(to_x);
        this.setY(to_y);
        RemixMap map = maps[this.getX()][this.getY()];
        System.out.println(this.getName() + "来到【" + map.getName()+"】");
    }

    public void travel(RemixMap[][] map) {
        // 获取用户输入，用来判定究竟向哪个地图走
        System.out.println("请操纵摇杆，移动你的英雄 o（上）、u(下)、l（左）、g（右）--->> n(停止移动）");
        // 用字符串或字符变量，来存储用户输入的方向
        Scanner sc = new Scanner(System.in);
        String direction = sc.next();
        //获取玩家角色的当前坐标
        int x = this.getX();
        int y = this.getY();
        //通过条件分支，根据用户输入，来执行对应的语句
        switch (direction) {
            case "o": {
                // 先判断是否会出界
                // 向上走判断y是否越界
                if (y >= 3) {
                    System.out.println("已经移动到地图边缘，撞墙了！");
                    return;
                } else {
                    //向上走，更新 y 坐标，x 保持不变
                    y += 1;
                    this.goTo(x, y, map);
                    this.setX(x);
                    this.setY(y);
                }
                break;
            }
            case "u": {
                // 先判断是否会出界
                // 向下走判断y是否越界
                if (y <= 0) {
                    System.out.println("已经移动到地图边缘，撞墙了！");
                    return;
                } else {
                    //向下走，更新 y 坐标，x 保持不变
                    y -= 1;
                    this.goTo(x, y, map);
                    this.setX(x);
                    this.setY(y);
                }
                break;
            }
            case "l": {
                // 先判断是否会出界
                // 向左走判断x是否越界
                if (x <= 0) {
                    System.out.println("已经移动到地图边缘，撞墙了！");
                    return;
                } else {
                    //向左走，更新 x 坐标，y 保持不变
                    x -= 1;
                    this.goTo(x, y, map);
                    this.setX(x);
                    this.setY(y);
                }
                break;
            }
            case "g": {
                // 先判断是否会出界
                // 向右走判断x是否越界
                if (x >= 3) {
                    System.out.println("已经移动到地图边缘，撞墙了！");
                    return;
                } else {
                    //向右走，更新 x 坐标，y 保持不变
                    x += 1;
                    this.goTo(x, y, map);
                    this.setX(x);
                    this.setY(y);
                }
                break;
            }
            case "n": {
                break;
            }
            default: {
                System.out.println("请正确操纵摇杆:o（上）、u(下)、l（左）、g（右）");
            }
        }
    }
}
