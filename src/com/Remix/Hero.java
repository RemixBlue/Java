// 单位抽象类
public abstract class Hero {
    // 名字
    private String name;
    // 血量
    private int hp;
    // 法力值
    private int mana;
    // 攻击速度 Attack speed
    private int as;
    // 移动速度 Movement speed
    private int ms;
    // 普通攻击伤害值 Normal attack damage
    private int nad;


    public Hero() {
    }

    public Hero(String name, int hp, int mana, int as, int ms, int nad) {
        this.name = name;
        this.hp = hp;
        this.mana = mana;
        this.as = as;
        this.ms = ms;
        this.nad = nad;
    }

    /**
     * 提供给敌方单位的构造方法
     */
    public Hero(String name, int hp, int as, int nad) {
        this.name = name;
        this.hp = hp;
        this.as = as;
        this.nad = nad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getAs() {
        return as;
    }

    public void setAs(int as) {
        this.as = as;
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public int getNad() {
        return nad;
    }

    public void setNad(int nad) {
        this.nad = nad;
    }

    // 打印信息
    public void print() {
        System.out.println("当前单位: " + this.name + "的生命为:" + this.hp + "，攻击力为：" + this.nad);
    }

    // 攻击
    public void putongattack(Hero enemy) {
        // 攻击
        System.out.println(this.name + "攻击" + enemy.name + ",打掉其" + this.nad + "点血");
        // 掉血
        enemy.hp -= this.nad;
        enemy.setHp(enemy.getHp());
        System.out.println(enemy.name + "当前血量：" + enemy.hp);
    }


    /**
     * 技能攻击
     */
    public void jinengAttack(EnemyBuildings en, Skills skills) {
        // 判断法力值是否足够
        if (skills.getsKillsName().equals("病入膏肓") && this.getMana() < 70) {
            System.out.println("你的法力值不足,无法使用一技能!");
            System.out.println("自动使用普通攻击!");
            putongattack(en);
            return;
        }
        // 判断法力值是否足够
        if (skills.getsKillsName().equals("不可描述") && this.getMana() < 30) {
            System.out.println("你的法力值不足,无法使用二技能!");
            System.out.println("自动使用普通攻击!");
            putongattack(en);
            return;
        }
        // 判断法力值是否足够
        if (skills.getsKillsName().equals("无理取闹") && this.getMana() < 50) {
            System.out.println("你的法力值不足,无法使用三技能!");
            System.out.println("自动使用普通攻击!");
            putongattack(en);
            return;
        }
        // 判断法力值是否足够
        if (this.getMana() <= 0) {
            System.out.println("你的法力值不足,无法使用技能!");
            System.out.println("自动使用普通攻击!");
            putongattack(en);
            return;
        }
        // 攻击
        System.out.println(this.getName() + "使用" + skills.getsKillsName() + "攻击敌方" + en.getName());
        System.out.println("打掉其" + skills.getSkillsAp() + "点血.");

        // 敌方掉血
        en.setHp(en.getHp() - skills.getSkillsAp());
        System.out.println(en.getName() + "剩余血量: " + en.getHp());
        // 耗法力值
        this.setMana(this.getMana() - skills.getSkillsNp());
        System.out.println(this.getName() + "剩余蓝量: " + this.getMana());
        // 我方吸血
        this.setHp(this.getHp() + (int) (skills.getSkillsAp() * 0.5));
        System.out.println(this.getName() + "吸取血量: " + (int) (skills.getSkillsAp() * 0.5));
        System.out.println(this.getName() + "当前血量: " + this.getHp());
    }
}
