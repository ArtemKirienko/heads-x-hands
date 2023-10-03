import java.util.Scanner;

public class Main {
    public static void readScanner(String line) throws CreatureException {
        String[] arr = line.split(" ");
        // �������� ��������, ��� name ��� ��� "create creature name"
        if (arr.length == 3 && arr[0].equals("create") && arr[1].equals("creature")) {
            if(Creature.getCreatures().containsKey(arr[0])){
                return;
            }
            createCreature(arr[2]);
            return;
        }
        // ��������� ��� player
        if (line.equals("heal")) {
            Players player = (Players) Creature.getCreatureByName("player");
            player.heal();
            return;
        }
        // ������, ��� name ��� ��� ��������. "name attack name"
        if (arr.length == 3 && arr[1].equals("attack")) {
            Creatures striker = Creature.getCreatureByName(arr[0]);
            striker.attack(arr[2]);
            return;
        }
        // �������� ���� ������������ ������� "list info"
        if (line.equals("list info")) {
            Creature.getCreatures().keySet().forEach(System.out::println);
            return;
        }
        // �������� �������� ,��� name ��� ��� �������� "name info"
        if (arr.length == 2 && arr[1].equals("info")) {
            Creature.getCreatureByName(arr[0]).toStringCreature();
            return;
        }
        System.out.println("����� ������ ���");
    }

    private static void createPlayer() {
        Creature.getCreatures().put("player", new Player());
    }

    private static void createCreature(String name) {
        Creature.getCreatures().put(name, new Creature());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        createPlayer();
        while (Creature.isIndicator()) {
            try {
                readScanner(scanner.nextLine());
            } catch (CreatureException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
