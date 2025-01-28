package victorgponce.com.Pokedex;

import victorgponce.com.Objects.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private List<Pokemon> pokemonList;

    public Pokedex(String csvFile, int level) {
        pokemonList = new ArrayList<>();
        loadPokemonFromCSV(csvFile, level);
    }

    private void loadPokemonFromCSV(String csvFile, int level) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Saltar cabecera
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Validar longitud de las columnas
                if (data.length < 10) {
                    System.err.println("Línea inválida en el CSV: " + line);
                    continue; // Saltar esta línea
                }

                // Obtener tipos (combinar type1 y type2, ignorando "none")
                List<String> typesList = new ArrayList<>();
                if (!data[2].equalsIgnoreCase("none")) typesList.add(data[2]);
                if (!data[3].equalsIgnoreCase("none")) typesList.add(data[3]);
                String[] types = typesList.toArray(new String[0]);

                Pokemon pokemon = new Pokemon(
                        Integer.parseInt(data[0]),  // ID
                        data[1],                    // Nombre
                        types,                      // Tipos como String[]
                        Integer.parseInt(data[4]),  // baseHp (data[4] = 45)
                        Integer.parseInt(data[5]),  // baseAttack (data[5] = 49)
                        Integer.parseInt(data[6]),  // baseDefense (data[6] = 49)
                        Integer.parseInt(data[7]),  // baseSpAttack (data[7] = 65)
                        Integer.parseInt(data[8]),  // baseSpDefense (data[8] = 65)
                        Integer.parseInt(data[9]),  // baseSpeed (data[9] = 45)
                        level,                      // Nivel
                        31, 31, 31, 31, 31, 31,     // IVs
                        0, 0, 0, 0, 0, 0            // EVs
                );

                pokemonList.add(pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pokemon> getAllPokemon() {
        return pokemonList;
    }

    // Métodos adicionales (buscar por nombre, tipo, etc.)
}