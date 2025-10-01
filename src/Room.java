import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private ArrayList<Item> items;
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


    // This method returns an ArrayList of the exits for a room by iterating through each key in the rooms hashmap
    public ArrayList<String> getExits() {
        ArrayList<String> listOfExits = new ArrayList<>();

        for(String exit : exits.keySet()) {
            listOfExits.add(exit);
        }
        return listOfExits;
    }

    public String explore() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Item x : this.items) {
            sb.append(x.toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
