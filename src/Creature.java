import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Creature implements Creatures {
    private final static Map<String, Creatures> CREATURES_MAP = new HashMap<>();
    private static boolean indicator = true;
    private final int ATTACK_VALUE;
    private final int DEFENSE;
    private int health;
    private final int DAMAGE_VALUE;

    public Creature() {
        ATTACK_VALUE = new Random().nextInt(30) + 1;
        DEFENSE = new Random().nextInt(30) + 1;
        health = new Random().nextInt(15) + 1;
        DAMAGE_VALUE = new Random().nextInt(6) + 1;
    }

    private int getAttackModifier(Creature creature) {
        int difference = ATTACK_VALUE - creature.getDEFENS();
        return difference > 0 ?
                difference + 1 :
                Math.abs(difference) + 1;
    }

    public void attack(String name) {
        try {
            Creature creature = (Creature) getCreatureByName(name);
            if (!diceRoll(getAttackModifier(creature))) {
                return;
            }
            int damageValueRandom = new Random().nextInt(DAMAGE_VALUE) + 1;
            System.out.println("нанесение урона" + damageValueRandom);
            damageToHealthMetods(damageValueRandom, creature, name);
        } catch (CreatureException e) {
            e.printStackTrace();
        }
    }

    public static Creatures getCreatureByName(String name) throws CreatureException {
        if (!CREATURES_MAP.containsKey(name)) {
            throw new CreatureException("not exist creature with name this");
        }
        return CREATURES_MAP.get(name);
    }

    private boolean diceRoll(int attackModifier) {
        for (int i = 0; i < attackModifier; i++) {
            int diceValue = new Random().nextInt(6) + 1;
            if (comparison(diceValue)) {
                return true;
            }
        }
        return false;
    }

    private void damageToHealthMetods(int damageValue, Creature creature, String name) throws CreatureException {
        int healthNew = creature.getHealth() - damageValue;
        creature.setHealth(healthNew);
        System.out.println("осталось heath = " + creature.getHealth());
        if (creature.getHealth() <= 0) {
            CREATURES_MAP.remove(name);
            System.out.println("максимальный урон для жизни " + name);
            if (name.equals("player")) {
                setIndicator(false);
                System.out.println("End");
            }
        }
    }

    private boolean comparison(int diceValue) {
        return diceValue == 5 || diceValue == 6;
    }

    private int getDEFENS() {
        return DEFENSE;
    }

    public int getHealth() {
        return health;
    }

    public int getATTACK_VALUE() {
        return ATTACK_VALUE;
    }

    private int getDAMAGE_VALUE() {
        return DAMAGE_VALUE;
    }

    public static boolean isIndicator() {
        return indicator;
    }

    public static void setIndicator(boolean indicator) {
        Creature.indicator = indicator;
    }

    public String getMessage() {
        return "attackValue = " + getATTACK_VALUE() + "\n"
                + "defence = " + getDEFENS() + "\n"
                + "health = " + getHealth() + "\n"
                + "demageValue" + getDAMAGE_VALUE();
    }

    public static Map<String, Creatures> getCreatures() {
        return CREATURES_MAP;
    }

    public void setHealth(int healthVal) {
        this.health = healthVal;
    }

    @Override
    public void toStringCreature() {
        System.out.println(getNameByCreature(null) + "  : " + "\n"
                + getMessage());
    }

    public String getNameByCreature(Creature creature) {
        creature = creature == null ?
                this : creature;
        return findNameCreature(creature);
    }


    private String findNameCreature(Creature creature) {
        return CREATURES_MAP.keySet().stream()
                .filter(k -> CREATURES_MAP.get(k).equals(creature))
                .findFirst()
                .get();
    }
}
