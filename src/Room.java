import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    int roomNumber;
    String name;
    String description;
    boolean visited;
    Map<String, Integer> exits;  // Directions and corresponding room numbers

    // Constructor
    public Room(int roomNumber, String name, String description) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.description = description;
        this.visited = false;
        this.exits = new HashMap<String, Integer>();
    }

    // Method to add an exit
    public void addExit(String direction, int roomNumber) {
        exits.put(direction, roomNumber);
    }

    // Method to mark the room as visited
    public void visit() {
        visited = true;
    }


    // This method returns an ArrayList of the exits for a room
    public ArrayList<String> getExits() {
        ArrayList<String> listOfExits = new ArrayList<>();

        for(Map.Entry<String, Integer> exit : exits.entrySet()) {
            listOfExits.add(exit.getKey());
        }
        return listOfExits;
    }


    // toString for testing
    @Override
    public String toString() {
        return "\n" + roomNumber + ". " + name + "\nDescription: " + description + "\nVisited: " + visited + "\nExits: " + exits.toString() + "\n";
    }
}
