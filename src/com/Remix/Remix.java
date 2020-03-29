import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Remix {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 构造英雄
        Mobile my = new Mobile("【啊嘞】", 6000, 300, 100, 100, 650);
        // 敌方英雄1
        EnemyBuildings en = new EnemyBuildings("【污奈】", 7000, 300, 90, 90, 460);
        // 敌方英雄2
        EnemyBuildings en1 = new EnemyBuildings("【混音】", 4000, 600, 90, 110, 460);
        // 敌方英雄3
        EnemyBuildings en2 = new EnemyBuildings("【成涔】", 2000, 600, 90, 90, 460);
        // 敌方英雄4
        EnemyBuildings en3 = new EnemyBuildings("【神牧】", 5000, 400, 90, 100, 460);

        // 构造地图
        RemixMap[][] maps = tectonicMap();
        // 敌方单位
        ceu(maps, my, en, en1, en2, en3);

        // 开始
        remixmain(my, maps, en, my.getSkillsHashMap(), en.getSkillsHashMap());

        long endTime = System.currentTimeMillis();
        long s = (endTime - startTime) / 1000;
        System.out.println("总耗时: " + s + " s");
    }

    /**
     * 游戏进程
     */
    public static void remixmain(Mobile my,
                                 RemixMap[][] maps,
                                 EnemyBuildings en,
                                 HashMap<Integer, Skills> myMap,
                                 HashMap<Integer, Skills> enMap)
            throws InterruptedException {
        System.out.println("开始!");
        while (true) {
            RemixMap map = maps[my.getX()][my.getY()];
            ArrayList<EnemyBuildings> E = map.getAliveMonsters();
            for (EnemyBuildings e : E) {
                if (my.getX() == 3 &&
                        my.getY() == 3 &&
                        en.getHp() <= 0 &&
                        e.getHp() <= 0) {
                    System.out.println("你胜利了!");
                    return;
                }
            }
            System.out.println("操作: \n" +
                    "1.查看当前英雄信息\n" +
                    "2.查看技能信息\n" +
                    "3.操纵摇杆\n" +
                    "4.进攻\n" +
                    "5.退出游戏");
            Scanner sc = new Scanner(System.in);
            int nextInt = sc.nextInt();
            switch (nextInt) {
                case 1: {
                    System.out.println(my.toString());
                    break;
                }
                case 2: {
                    my.skillsInformation();
                    break;
                }
                case 3: {
                    my.do_travel(maps);
                    break;
                }
                case 4: {
                    my.attack(map, my, en, myMap, enMap);
                    for (EnemyBuildings e : E) {
                        if (my.getX() == 3 &&
                                my.getY() == 3 &&
                                en.getHp() <= 0 &&
                                e.getHp() <= 0) {
                            return;
                        }
                        if (my.getHp() <= 0) {
                            System.out.println("你死了!");
                            return;
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("你认怂了!");
                    return;
                }
                default: {
                    System.out.println("输入错误!");
                }
            }
        }
    }


    /**
     * 构造地图
     */
    public static RemixMap[][] tectonicMap() {
        RemixMap m1 = new RemixMap("蓝水晶", 0, 0);
        RemixMap m2 = new RemixMap("下一塔", 1, 0);
        RemixMap m3 = new RemixMap("红buff", 2, 0);
        RemixMap m4 = new RemixMap("下塔", 3, 0);
        RemixMap m5 = new RemixMap("上一塔", 0, 1);
        RemixMap m6 = new RemixMap("中一塔", 1, 1);
        RemixMap m7 = new RemixMap("大龙", 2, 1);
        RemixMap m8 = new RemixMap("蓝buff", 3, 1);
        RemixMap m9 = new RemixMap("蓝buff", 0, 2);
        RemixMap m10 = new RemixMap("小龙", 1, 2);
        RemixMap m11 = new RemixMap("中二塔", 2, 2);
        RemixMap m12 = new RemixMap("下二塔", 3, 2);
        RemixMap m13 = new RemixMap("上塔", 0, 3);
        RemixMap m14 = new RemixMap("红buff", 1, 3);
        RemixMap m15 = new RemixMap("上二塔", 2, 3);
        RemixMap m16 = new RemixMap("红水晶", 3, 3);

        // 创建存储地图的二维数组
        RemixMap[][] maps = new RemixMap[4][4];
        maps[0][0] = m1;
        maps[1][0] = m2;
        maps[2][0] = m3;
        maps[3][0] = m4;
        maps[0][1] = m5;
        maps[1][1] = m6;
        maps[2][1] = m7;
        maps[3][1] = m8;
        maps[0][2] = m9;
        maps[1][2] = m10;
        maps[2][2] = m11;
        maps[3][2] = m12;
        maps[0][3] = m13;
        maps[1][3] = m14;
        maps[2][3] = m15;
        maps[3][3] = m16;

        System.out.println("构造地图完成...");
        return maps;
    }

    /**
     * 构造敌方单位
     */
    public static void ceu(RemixMap[][] maps, Mobile my, EnemyBuildings en1, EnemyBuildings en2, EnemyBuildings en3, EnemyBuildings en4) {
        EnemyBuildings m1 = new EnemyBuildings("【蓝水晶】", 100, 100, 12000);
        EnemyBuildings m2 = new EnemyBuildings("【下一塔】", 3000, 100, 300);
        EnemyBuildings m3 = new EnemyBuildings("【红buff】", 1000, 50, 300);
        EnemyBuildings m4 = new EnemyBuildings("【下塔】", 3000, 100, 300);
        EnemyBuildings m5 = new EnemyBuildings("【上一塔】", 3000, 100, 300);
        EnemyBuildings m6 = new EnemyBuildings("【中一塔】", 3000, 100, 300);
        EnemyBuildings m7 = new EnemyBuildings("【大龙】", 15000, 50, 800);
        EnemyBuildings m8 = new EnemyBuildings("【蓝buff】", 1000, 50, 300);
        EnemyBuildings m9 = new EnemyBuildings("【蓝buff】", 1000, 50, 300);
        EnemyBuildings m10 = new EnemyBuildings("【小龙】", 10000, 50, 600);
        EnemyBuildings m11 = new EnemyBuildings("【中二塔】", 3000, 100, 300);
        EnemyBuildings m12 = new EnemyBuildings("【下二塔】", 3000, 100, 300);
        EnemyBuildings m13 = new EnemyBuildings("【上塔】", 3000, 100, 300);
        EnemyBuildings m14 = new EnemyBuildings("【红buff】", 1000, 50, 300);
        EnemyBuildings m15 = new EnemyBuildings("【上二塔】", 3000, 100, 300);
        EnemyBuildings m16 = new EnemyBuildings("【红水晶】", 23000, 200, 800);
        maps[0][0].addBuildings(m1);
        maps[1][0].addBuildings(m2);
        maps[1][0].addBuildings(en4);
        maps[2][0].addBuildings(m3);
        maps[3][0].addBuildings(m4);
        maps[3][0].addBuildings(en2);
        maps[0][1].addBuildings(m5);
        maps[1][1].addBuildings(m6);
        maps[2][1].addBuildings(m7);
        maps[3][1].addBuildings(m8);
        maps[0][2].addBuildings(m9);
        maps[1][2].addBuildings(m10);
        maps[2][2].addBuildings(m11);
        maps[2][2].addBuildings(en3);
        maps[3][2].addBuildings(m12);
        maps[0][3].addBuildings(m13);
        maps[1][3].addBuildings(m14);
        maps[2][3].addBuildings(m15);
        maps[3][3].addBuildings(m16);
        maps[3][3].addBuildings(en1);

        /** 英雄技能集合*/
        Skills s1 = new Skills("《病入膏肓》", 2300, 80, 30);
        Skills s2 = new Skills("《不可描述》", 1300, 30, 10);
        Skills s3 = new Skills("《无理取闹》", 1600, 50, 15);
        for (int i = 0; i < 1; i++) {
            HashMap<Integer, Skills> hashMap = new HashMap<Integer, Skills>();
            hashMap.put(1, s1);
            hashMap.put(2, s2);
            hashMap.put(3, s3);
            my.addSkills(hashMap);
        }

        /** 敌方英雄 */
        Skills s4 = new Skills("《无可救药》", 2700, 50, 15);
        Skills s5 = new Skills("《凭空想象》", 2000, 50, 15);
        Skills s6 = new Skills("《暗渡陈仓》", 1400, 50, 15);
        for (int i = 0; i < 1; i++) {
            HashMap<Integer, Skills> hashMap = new HashMap<Integer, Skills>();
            hashMap.put(1, s4);
            hashMap.put(2, s5);
            hashMap.put(3, s6);
            en1.enemyAddSkills(hashMap);
        }

        System.out.println("构造英雄完成...");
    }
}
