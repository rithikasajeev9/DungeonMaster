import java.util.*;
public class DungeonMaster{
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        System.out.println("🧙‍♂️ Welcome to Dungeon Master!");
        System.out.println("Your adventure begins...\n");

        startAdventure();
    }
    static void startAdventure() {
        String[] locations = {"a mystic library","a ghost ship","a cloud city","an ice palace"};
        String[] enemies = {"skeleton warrior","rogue sorcerer","band of goblins","fire-breathing snake"};
        String[] treasures = {"chest of diamonds","mysterious scroll","cursed sword","glowing amulet"};

        String location = locations[random.nextInt(locations.length)];
        String enemy = enemies[random.nextInt(enemies.length)];
        String treasure = treasures[random.nextInt(treasures.length)];

        System.out.println("You enter "+location+" and stumble upon a "+treasure+".");
        System.out.println("Do you:");
        System.out.println("1) Take the "+treasure);
        System.out.println("2) Leave it and explore deeper");

        int choice1=getChoice(2);
        if(choice1==1){
            System.out.println("You pick up the "+treasure+". Suddenly a "+enemy+" appears.");
        }else{
            System.out.println("You leave the "+treasure+". But deep inside, the "+enemy+" ambushes you.");
        }

        System.out.println("Do you:");
        System.out.println("1) Fight the "+enemy);
        System.out.println("2) Run away");

        int choice2 = getChoice(2);
        if(choice2==1){
            if(random.nextBoolean()){
                System.out.println("You bravely fight and defeat the "+enemy+"! 🏆");
            }else{
                System.out.println("The "+enemy+" defeats you. Your journey ends here! ☠️");
            }
        }else{
            System.out.println("You run for your life and escape...barely! 🏃‍♂️");
        }

        System.out.println("\nWould you like to play again? (yes/no)");
        String again = sc.next().toLowerCase();
        if(again.equals("yes") || again.equals("y")){
            System.out.println("\n--- Starting New Adventure ---\n");
            startAdventure();
        }else{
            System.out.println("Thank you for playing. Goodbye!");
        }
    }
    static int getChoice(int max){
        int choice = 0;
        while(choice < 1 || choice > max){
            System.out.println("Enter your choice (1-"+max+"): ");
            try{
                choice = Integer.parseInt(sc.next());
            }catch(NumberFormatException e){
                System.out.println("Inavlid input. Please enter a number.");
            }
        }
        return choice;
    }
}