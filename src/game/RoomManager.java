package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.Room;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class RoomManager {
    private List<Room> rooms;

    public RoomManager() {

    }

    public void roomParser() {
        final String FILEPATH = "rooms/room";
        try {
            // TODO: check all files inside folder and read
            Scanner fileScanner = new Scanner(new File(FILEPATH + "0.txt"));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] letters = line.split("");

                for (String letter : letters) {
                    System.out.println(letter);
                }
            }

            fileScanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
