public class Player extends Creature implements Players {
    private int healingCount = 4;
    private final int HEAL_VALUE;

    public Player() {
        super();
        double health = getHealth();
        HEAL_VALUE = (int) (health / 100 * 30);
    }

    @Override
    public void heal() {
        String res = healingCount != 0 ?
                healing() :
                "исцеления закончились";
        System.out.println(res);
    }

    private String healing() {
        healingCount -= 1;
        int healthModifing = getHealth() + HEAL_VALUE;
        setHealth(healthModifing);
        return "исцеление. heals = " + healthModifing + "\n" + "осталось исцелений = " + healingCount;
    }

    public int getHealingCount() {
        return healingCount;
    }

    @Override
    public void toStringCreature() {
        System.out.println(getNameByCreature(null) + "  : " + "\n"
                + getMessage() + "\n"
                + "healingCount = " + getHealingCount() + "\n"
                + "healValue = " + HEAL_VALUE);
    }
}
