import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private ArrayList<Room> rooms;
    public Game () {
        keepPLaying = true;
    }
    private boolean keepPLaying;

    // getter for keep playing variable
    public boolean isKeepPLaying() {
        return keepPLaying;
    }


    // This method checks if the user wants to quit the game and if not, it marks the current room as visited and moves the player to the next room.
    public void goToNextRoom(ArrayList<Room> rooms, String direction, Player p1) {
        if (direction.equals("QUIT")) keepPLaying = false;
        else {
            // mark the current room as visited
            // I know this is being called before moving, but it's setting the room that the player is leaving as visited, not the one they are moving into
            p1.getCurrentRoom().visit();
            // get the value of the next room from the hashmap based on the direction key that was obtained from the user.
            p1.setCurrentRoom(rooms.get(p1.getCurrentRoom().getExits().get(direction)-1));
        }
    }

    public void getItems(String f) {
        try {
            Scanner in = new Scanner(new FileInputStream(f));
            while (in.hasNext()) {
                // get the next line in the file
                String line = in.nextLine();

                // split the line by the commas into a String array
                String[] sl = line.split(",");

                // create an item object based off known locations of data in items.csv
                Item item = new Item(Integer.parseInt(sl[0]), sl[1], sl[2]);

                // add that item to its correct room.
                rooms.get(Integer.parseInt(sl[3])-1).addItemToRoom(item);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't find file");
        }
    }

    /*
     * This method takes a String line and splits it into the applicable data for a room object
     * [room number, room name, room desc, exit, exit, exit, exit]
     */
    private Room getRoom(String line) {
        // split the line by the commas into a String array
        String[] sl = line.split(",");

        // create a room object based off known locations of data in rooms.csv
        Room r1 = new Room(Integer.parseInt(sl[0]), sl[1], sl[2]);

        // copy just the exit part of the array
        String[] e = Arrays.copyOfRange(sl, 3, sl.length);

        // Loop through each available exit and split them by ":" and then create an entry in the map for each exit
        for(String s : e) {
            String[] se = s.split(":");
            r1.addExit(se[0], Integer.parseInt(se[1]));
        }
        return r1;
    }


    /*
     * This method loads the Rooms.csv file into an ArrayList of Room objects.
     */
    public ArrayList<Room> loadRooms(String f) {
        ArrayList<Room> rooms = new ArrayList<>();
        try (Scanner in = new Scanner(new FileInputStream(f))) {
            while (in.hasNext()) {
                // read the rooms.csv file line by line
                String line = in.nextLine();

                // send the line to getRoom method
                Room r1 = getRoom(line);

                // Add room to rooms ArrayList
                rooms.add(r1);
            }
            return rooms;
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
        }
        return null;
    }

    //method to get the file path from user and validate that it exists by passing it to a FileInputStream object
    public String getFilePath() {
        Scanner scanner = new Scanner(System.in);
        boolean validFile = false;
        String f = "";
        while(!validFile) {
            System.out.println("Enter the name of the file you want to load: ");
            f = scanner.nextLine();
            try (FileInputStream in = new FileInputStream(f)) {
                validFile = true;
            }
            catch (IOException e) {
                System.out.println("Cant find file");
            }
        }
        return f;
    }


    // This method holds all the possible player inputs in a switch statement
    private void processInput(String input, Game g1, Player p1) {
        switch (input) {
            case "NORTH", "N":
                g1.goToNextRoom(g1.rooms, "NORTH", p1);
                break;
            case "SOUTH", "S":
                g1.goToNextRoom(g1.rooms, "SOUTH", p1);
                break;
            case "EAST", "E":
                g1.goToNextRoom(g1.rooms, "EAST", p1);
                break;
            case "WEST", "W":
                g1.goToNextRoom(g1.rooms, "WEST", p1);
                break;
                // regex looking for the word PICKUP followed by a space and then any number of characters or spaces.
            case String s when input.matches("^PICKUP\\s.*$"):
                for (Item i : p1.getCurrentRoom().getItems()) {
                    if (i.getName().toUpperCase().equals(s.substring(7).trim())) {
                        i.pickup(p1);
                        break;
                    }
                }
                break;
            case String s when input.matches("^DROP\\s.*$"):

                for (Item i : p1.getItems()) {
                    if (i.getName().toUpperCase().equals(s.substring(5).trim())) {
                        i.drop(p1);
                        break;
                    }
                }
                break;
            case String s when input.matches("^INSPECT\\s.*$"):
                String itemName = s.substring(8).trim();
                for (Item i : p1.getItems()) {
                    if (i.getName().toUpperCase().equals(itemName)) {
                        i.inspect();
                        break;
                    }
                }
                break;
            case "INVENTORY", "I":
                p1.printInv();
                break;
            case "INSPECT":
                System.out.println("ADD INSPECT FUNCTIONALITY");
                break;
            case "EXPLORE":
                p1.getCurrentRoom().explore();
                break;
            case "QUIT", "Q":
                System.exit(0);
            default:
                System.out.println("That is not a valid input.");
                break;
        }
    }


    public static void main(String[] args) {
        // Create ArrayList of Rooms from a file
        Game g1 = new Game();
        System.out.println("Rooms:");
        String filePath = g1.getFilePath();
        g1.rooms = g1.loadRooms(filePath);

        // load items into the room from a file
        System.out.println("Items:");
        g1.getItems(g1.getFilePath());

        // Main gameplay loop - print the current room
        Player p1 = new Player(g1.rooms);
        while (g1.isKeepPLaying()) {
            p1.printCurrentRoom();
            String input = p1.getInput();
            g1.processInput(input, g1, p1);
        }
    }
}
