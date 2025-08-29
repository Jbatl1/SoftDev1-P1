import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // room object that the player is currently in
    private Room currentRoom;
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

    // setter for current room
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    // This method checks if the input from the user is a valid direction or quit
    private boolean checkInput(String userInput) {
        if (userInput.equals("QUIT")) return true;
        for (String s : currentRoom.getExits()) {
            if (userInput.equals(s)) return true;
        }
        return false;
    }

    // This method prompts the user to input the direction they would like to travel and loops until they input a valid direction
    public String getInput() {
        System.out.println("Enter the direction you would like to go (NORTH-EAST-SOUTH-WEST): ");
        String direction = scanner.nextLine().trim().toUpperCase();
        while(!checkInput(direction)){
            System.out.println("There is no exit to the " + direction);
            System.out.println("Enter the direction you would like to go (NORTH-EAST-SOUTH-WEST): ");
            direction = scanner.nextLine().trim().toUpperCase();
        }
        return direction;
    }

    /*
     * Method to print the description and exits of the current room
     */
    public void printCurrentRoom() {
        System.out.println(currentRoom.description);
        if (currentRoom.visited) {
            System.out.println("You Have visited this room before!");
        }
        System.out.println("EXITS: ");
        for (String s : currentRoom.getExits()) System.out.println(s);
        System.out.println("Type \"QUIT\" to quit");

    }
}
