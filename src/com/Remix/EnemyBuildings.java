import java.util.HashMap;
/**
 * 敌方单位类
 * */
class EnemyBuildings extends Hero{
    private int x;
    private int y;
    private HashMap<Integer,Skills> skillsHashMap = new HashMap<Integer, Skills> ();

    public EnemyBuildings(String name, int hp, int as, int nad) {
        super(name, hp, as, nad);
    }

    public EnemyBuildings(String name, int hp, int mana, int as, int ms, int nad) {
        super(name, hp, mana, as, ms, nad);
    }

    public HashMap<Integer, Skills> getSkillsHashMap() {
        return skillsHashMap;
    }

    public void setSkillsHashMap(HashMap<Integer, Skills> skillsHashMap) {
        this.skillsHashMap = skillsHashMap;
    }

    public int getX(int x) {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY(int y) {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * 技能
     * */
    public void enemyAddSkills(HashMap hashMap){
        skillsHashMap.putAll(hashMap);
    }
    /**
     * 技能攻击
     */
    public void jinengAttack(Mobile my, Skills skills) {
        // 判断法力值
        if (this.getMana()<= 0){
            putongattack(my);
            return;
        }
        // 攻击
        System.out.println(this.getName() + "使用" + skills.getsKillsName() + "攻击敌方" + my.getName());
        System.out.println("打掉其" + skills.getSkillsAp() + "点血.");

        // 掉血
        my.setHp(my.getHp() - skills.getSkillsAp());
        System.out.println(my.getName() + "剩余血量: " + my.getHp());
        // 耗蓝
        this.setMana(this.getMana() - skills.getSkillsNp());
        System.out.println(this.getName() + "剩余蓝量: " + this.getMana());
    }

}

/**
 * @author Remix
 * 技能类
 */
class Skills {
    private String sKillsName;
    private int skillsAp;
    private int skillsNp;
    private int coolingTime;

    public Skills(String sKillsName, int skillsAp, int skillsNp, int coolingTime) {
        this.sKillsName = sKillsName;
        this.skillsAp = skillsAp;
        this.skillsNp = skillsNp;
        this.coolingTime = coolingTime;
    }

    public String getsKillsName() {
        return sKillsName;
    }

    public void setsKillsName(String sKillsName) {
        this.sKillsName = sKillsName;
    }

    public int getSkillsAp() {
        return skillsAp;
    }

    public void setSkillsAp(int skillsAp) {
        this.skillsAp = skillsAp;
    }

    public int getSkillsNp() {
        return skillsNp;
    }

    public void setSkillsNp(int skillsNp) {
        this.skillsNp = skillsNp;
    }

    public int getCoolingTime() {
        return coolingTime;
    }

    public void setCoolingTime(int coolingTime) {
        this.coolingTime = coolingTime;
    }

    @Override
    public String toString() {
        return "技能: '" + sKillsName + '\'' +
                ", 技能伤害: " + skillsAp +
                ", 耗蓝量: " + skillsNp +
                ", 冷却时间: " + coolingTime;
    }

}
