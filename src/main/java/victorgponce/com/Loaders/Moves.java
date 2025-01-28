package victorgponce.com.Loaders;

import victorgponce.com.Objects.Move;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Moves {

    private final List<Move> moveList;

    public Moves(String csvFileMove) {
        moveList = new ArrayList<>();

        loadMoveFromCSV(csvFileMove);
    }

    private void loadMoveFromCSV(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Ensure there are enough columns
                if (data.length < 8) {
                    System.err.println("Invalid line (insufficient columns): " + line);
                    continue;
                }

                // Trim all data elements to handle whitespace
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }

                // Parse integer fields with empty checks
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String type = data[2];
                int power = data[3].isEmpty() ? 0 : Integer.parseInt(data[3]);
                int accuracy = data[4].isEmpty() ? 0 : Integer.parseInt(data[4]);
                int pp = data[5].isEmpty() ? 0 : Integer.parseInt(data[5]);
                String damageClass = data[6];
                String effect = data[7];

                Move move = new Move(id, name, type, power, accuracy, pp, damageClass, effect);
                moveList.add(move);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public List<Move> getAllMoves() {
        return moveList;
    }

}
