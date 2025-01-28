package victorgponce.com;

import victorgponce.com.Objects.Pokemon;
import victorgponce.com.Pokedex.Pokedex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        List<Pokemon> pokedex = new Pokedex("src/main/resources/pokemon_base_stats.csv", 5).getAllPokemon();

        for (int i = 0; i <= pokedex.toArray().length - 1; i++) {
            System.out.print("Name: " + pokedex.get(i).getName() + ", ");
            System.out.print("MAX HP: " + pokedex.get(i).getMaxHP() + ", ");
            System.out.print("ATTACK: " + pokedex.get(i).getAttack() + ", ");
            System.out.print("SP ATTACK: " + pokedex.get(i).getSpecialAttack() + ", ");
            System.out.print("DEFENSE: " + pokedex.get(i).getDefense() + ", ");
            System.out.print("SP DEFENSE: " + pokedex.get(i).getSpecialDefense() + ", ");
            System.out.println("SPEED: " + pokedex.get(i).getSpeed() + ", ");
        }

        kb.close();
    }
}