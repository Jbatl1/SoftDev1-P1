import java.util.ArrayList;
import java.util.Scanner;

public class PlayerInteraction {
    private boolean keepPLaying;
    // room object that the player is currently in
    private Room currentRoom;
    private final Scanner scanner;

    // constructor
    public PlayerInteraction(ArrayList<Room> rooms) {
        currentRoom = rooms.get(0);
        keepPLaying = true;
        scanner = new Scanner(System.in);
    }

    // getter for keep playing variable
    public boolean isKeepPLaying() {
        return keepPLaying;
    }

    // This method checks if the input from the user is a valid direction or quit
    private boolean checkInput(String userInput) {
        if (userInput.equals("QUIT")) return true;
        for (String s : currentRoom.getExits()) {
            if (userInput.equals(s)) return true;
        }
        return false;
    }

    // This method checks if the user wants to quit the game and if not, it marks the current room as visited and moves the player to the next room.
    public void goToNextRoom(ArrayList<Room> rooms, String direction) {
        if (direction.equals("QUIT")) keepPLaying = false;

        else {
            currentRoom.visit();
            currentRoom = rooms.get(currentRoom.exits.get(direction)-1);
        }
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
        System.out.println("EXITS: ");
        for (String s : currentRoom.getExits()) System.out.println(s);
        System.out.println("Type \"QUIT\" to quit");

    }



}
