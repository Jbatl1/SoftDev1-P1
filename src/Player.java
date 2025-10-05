import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // room object that the player is currently in
    private Room currentRoom;
    private ArrayList<Item> items = new ArrayList<>();
    private final Scanner scanner;

    // constructor
    public Player(ArrayList<Room> rooms) {
        currentRoom = rooms.get(0);
        scanner = new Scanner(System.in);
    }

    // getter for current room
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }
    // setter for current room
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }



    // This method prompts the user to input the direction they would like to travel and loops until they input a valid direction
    //the users input is passed to the checkInput method
    public String getInput() {
        System.out.println("Enter the direction you would like to go (NORTH-EAST-SOUTH-WEST): ");
        return scanner.nextLine().trim().toUpperCase();
    }

    /*
     * Method to print the description and exits of the current room and if the room has been visited before
     */
    public void printCurrentRoom() {
        System.out.println(currentRoom.getDescription());
        if (currentRoom.visited) {
            System.out.println("You Have visited this room before!");
        }
        System.out.println("EXITS: ");
        for (String s : currentRoom.getExitsArrayList()) System.out.println(s);
        System.out.println("Type \"QUIT\" to quit");
    }

    public void addToInv(Item item) {
        this.items.add(item);
    }

    public int removeItemFromInv(Item item) {
        if (this.items.contains(item)) {
            items.remove(item);
            return 1;
        }
        return 0;
    }

    public void printInv() {
        System.out.print("[");
        for (Item i : this.items) {
            if (this.items.getLast() == i) System.out.print(i.getName());
            else System.out.print(i.getName() + ", ");
        }
        System.out.println("]");
    }
}
