import java.util.*;

public class DungeonMaster {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("🧙‍♂️ Welcome to Dungeon Master!");
        System.out.println("Your adventure begins...\n");

        System.out.println("Enter your name, brave adventurer!");
        String name = sc.nextLine().trim();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        String playerClass = "";
        while (true) {
            System.out.println("Choose your class: ( Warrior / Mage / Rogue )");
            playerClass = sc.nextLine().trim().toLowerCase();
            if(playerClass.equals("warrior") || playerClass.equals("mage") || playerClass.equals("rogue")){
                break;
            }else{
                System.out.println("Inavlid class. Please choose Warrior, Mage, or Rogue");
            }
        }

        Player player = new Player(name,playerClass);
        System.out.println("Welcome, " + name + " the " + playerClass.substring(0, 1).toUpperCase() + playerClass.substring(1) + "!");


        boolean keepPlaying = true;

        while (keepPlaying) {
            startAdventure(player);

            if (!player.isAlive()) {
                System.out.println("You collapse... The dungeon claims another soul. ☠️");
                break;
            }

            System.out.println("Would you like to play again? (yes/no)");
            String again;

            do {
                again = sc.nextLine().trim().toLowerCase();
            } while (!again.equals("yes") && !again.equals("no") && !again.equals("y") && !again.equals("n"));

            keepPlaying = again.equals("yes") || again.equals("y");

        
        }

        System.out.println("Thank you for playing. Goodbye!");
    }

    static void startAdventure(Player player) {
        String[] locations = {"a mystic library", "a ghost ship", "a cloud city", "an ice palace"};
        String[] enemies = {"skeleton warrior", "rogue sorcerer", "band of goblins", "fire-breathing snake"};
        String[] treasures = {"chest of diamonds", "mysterious scroll", "cursed sword", "glowing amulet"};

        String location = locations[random.nextInt(locations.length)];
        String enemy = enemies[random.nextInt(enemies.length)];
        String treasure = treasures[random.nextInt(treasures.length)];

        System.out.println("You enter " + location + " and stumble upon a " + treasure + ".");
        System.out.println("Do you:");
        System.out.println("1) Take the " + treasure);
        System.out.println("2) Leave it and explore deeper");

        int choice1 = getChoice(2);
        if (choice1 == 1) {
            System.out.println("You pick up the " + treasure + ". Suddenly a " + enemy + " appears.");
        } else {
            System.out.println("You leave the " + treasure + ". But deep inside, the " + enemy + " ambushes you.");
        }

        System.out.println("Do you:");
        System.out.println("1) Fight the " + enemy);
        System.out.println("2) Run away");

        int choice2 = getChoice(2);
        if (choice2 == 1) {
            if (random.nextBoolean()) {
                System.out.println("You bravely fight and defeat the " + enemy + "! 🏆");

                if(random.nextInt(100)<30){
                    int healAmount = 20 + random.nextInt(21);
                    player.gainHealth(healAmount);
                }
            } else {
                int damage = 30 + random.nextInt(21);
                player.loseHealth(damage);
                System.out.println("You took " + damage + " damage!");
                System.out.println("Remaining health: " + player.health);
            }
        } else {
            System.out.println("You run for your life and escape... barely! 🏃‍♂️");
        }
    }

    static int getChoice(int maxOption) {
        int choice = -1;
        while (choice < 1 || choice > maxOption) {
            System.out.print("Enter your choice (1-" + maxOption + "): ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Please enter a number.");
                sc.nextLine(); 
            }
        }
        return choice;
    }
}

class Player {
    String name;
    String playerClass;
    int health;
    int maxHealth;

    public Player(String name,String playerClass) {
        this.name = name;
        this.playerClass = playerClass;
        switch(playerClass.toLowerCase()){
            case "warrior":
                this.maxHealth = 120;
                break;
            case "mage":
                this.maxHealth = 80;
                break;
            case "rogue":
                this.maxHealth = 100;
                break;
            default:
                this.maxHealth = 100;
        } 
        this.health = this.maxHealth;
    }

    public void loseHealth(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void gainHealth(int amount){
        if(health==maxHealth) return;
        health+=amount;
        if(health>maxHealth) health = maxHealth;
        System.out.println("You found a healing potion and regained "+amount+" health!");
        System.out.println("Current health: "+health);
    }
}
