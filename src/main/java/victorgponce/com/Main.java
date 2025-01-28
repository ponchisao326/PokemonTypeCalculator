package victorgponce.com;

import victorgponce.com.Loaders.Moves;
import victorgponce.com.Objects.Move;
import victorgponce.com.Objects.Pokemon;
import victorgponce.com.Loaders.Pokedex;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        List<Pokemon> pokedex = new Pokedex("src/main/resources/updated_pokemon_base_stats.csv",5).getAllPokemon();
        List<Move> moves = new Moves("src/main/resources/pokemon_moves.csv").getAllMoves();

        /*for (int i = 0; i <= pokedex.toArray().length - 1; i++) {
            System.out.print("Pokedex Nº: " + pokedex.get(i).getPokedexNumber() + ", ");
            System.out.println("Name: " + pokedex.get(i).getName() + ", ");
        }

        System.out.println("Seleccione el número de Pokedex deseado");
        int choice = kb.nextInt();

        if (choice < 1 || choice > 1024) {
            throw new IllegalArgumentException("The number must be between 1 and 1024");
        }

        Pokemon pokemonChoice = pokedex.get(choice - 1);

        System.out.println(pokemonChoice.toString());*/

        for (int i = 0; i <= moves.toArray().length - 1; i++) {
            System.out.print("ID: " + moves.get(i).getId() + ", ");
            System.out.print("NAME: " + moves.get(i).getName() + ", ");
            System.out.print("TYPE: " + moves.get(i).getType() + ", ");
            System.out.print("POWER: " + moves.get(i).getPower() + ", ");
            System.out.print("ACCURACY: " + moves.get(i).getAccuracy() + ", ");
            System.out.print("PP: " + moves.get(i).getPp() + ", ");
            System.out.print("DAMAGE CLASS: " + moves.get(i).getDamageClass() + ", ");
            System.out.println("EFFECT: " + moves.get(i).getEffect() + ", ");
        }

        kb.close();
    }
}