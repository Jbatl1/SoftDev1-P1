import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    /*
     * This method takes a String line and splits it into the applicable data for a room object
     */
    public static Room getRoom(String line) {
        // split the line by the camas
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
    public static ArrayList<Room> loadGame(String f) {
        ArrayList<Room> rooms = new ArrayList<>();
        try (Scanner in = new Scanner(new FileInputStream(f))) {
            while (in.hasNext()) {
                // hashmap for the exits of each room
                HashMap<String, Integer> exits = new HashMap<>();

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
            System.out.println("Cant Find File");
        }
        return null;
    }


    // This method gets the name of the file to load from the user
    public static String getFileName() {
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


    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<>();

        String FileName = getFileName();
        rooms = loadGame(FileName);
        // Create ArrayList of Rooms from Rooms.csv


        //System.out.println(rooms);

        // Main gameplay loop
        PlayerInteraction p1 = new PlayerInteraction(rooms);
        while (p1.isKeepPLaying()) {
            p1.printCurrentRoom();
            String direction = p1.getInput();
            p1.goToNextRoom(rooms, direction);
        }
    }
}
