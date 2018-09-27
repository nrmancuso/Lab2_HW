import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {

    static ArrayBag pokedex = new ArrayBag();
    static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {

        char userChoice = ' ';
        boolean fileFound = false;

        //Greeting
        System.out.println("Welcome to Pokedex!");

        //build Pokedex
        if (buildPokedex(pokedex)) {
            System.out.println("'pokedex.txt' was loaded succesfully!");
            fileFound = true;
        }


        while (userChoice != 'q' && fileFound) {

            System.out.println("(D)isplay Pokemon, (O)utput to file, (R)ead 'changes.txt', or (Q)uit");
            System.out.print("~> ");
            String input = scan.next().toLowerCase();
            userChoice = input.charAt(0);

            switch (userChoice) {
                case 'd':
                    System.out.println(pokedex);
                    break;

                case 'o':
                    outputMenu();
                    break;

                case 'r':
                    readChangeFile();
            }

        }

        System.out.println("Goodbye!");

    }

    //Output menu
    private static void outputMenu() {

        char userChoice = ' ';

        System.out.println("What type of remove would you like to use?");
        System.out.println("(R)emove and shift, (D)elete field, or (N)ext field?");
        System.out.print("~> ");

        String input = scan.next().toLowerCase();
        userChoice = input.charAt(0);

        switch (userChoice) {

            case 'r':
                outputToFile(1);
                break;

            case 'd':
                outputToFile(2);
                break;

            case 'n':
                outputToFile(3);
                break;

            default:
                System.out.println("Invalid entry!");
                break;

        }


    }

    //Helper method to handle file input
    private static boolean buildPokedex(ArrayBag pokedex) {

        //Read from input file using scanner
        try {

            File file = new File("pokedex.txt");
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();

            while (fileReader.hasNextLine()) {

                //Build string array from parsed line
                String[] pokemonInfoArray = fileReader.nextLine().split("\\t");

                Pokemon p = buildNewPokemon(pokemonInfoArray);

                //Add to bag
                pokedex.add(p);
            }
        } catch (FileNotFoundException e) {

            System.out.println("\nFile not found!");        //whoops

        }
        return true;

    }

    private static void readChangeFile() {

        //Read from input file using scanner
        try {

            File file = new File("changes.txt");
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();

            while (fileReader.hasNextLine()) {

                //Build string array from parsed line
                String[] pokemonInfoArray = fileReader.nextLine().split("\\t");

                if (pokemonInfoArray[0].equalsIgnoreCase("A")) {

                    //Build new pokemon
                    Pokemon p = new Pokemon(
                            Integer.parseInt(pokemonInfoArray[1]),  //ID number
                            pokemonInfoArray[2]);                   //Name

                    //Add to bag
                    pokedex.add(p);
                } else if (pokemonInfoArray[0].equalsIgnoreCase("D")) {

                    pokedex.setDeleteFieldTrue(Integer.parseInt(pokemonInfoArray[1]));
                }

            }

        } catch (FileNotFoundException e) {

            System.out.println("\nFile not found!");        //whoops

        }


    }

    public static Pokemon buildNewPokemon(String[] pokemonInfoArray) {

        //Build new pokemon
        Pokemon p = new Pokemon(
                Integer.parseInt(pokemonInfoArray[0]),  //ID number
                pokemonInfoArray[1]);                   //Name

        return p;


    }

    private static void outputToFile(int typeOfOutput) {

        try {
            PrintWriter outputFile = new PrintWriter(new FileOutputStream("pokedex" + typeOfOutput + ".txt"));

            switch (typeOfOutput) {

                case 1:
                    outputFile.println(pokedex.toTabFileWithSkip());
                    outputFile.flush();
                    outputFile.close();
                    break;

                case 2:
                    outputFile.println(pokedex.toTabFileWithDelete());
                    outputFile.flush();
                    outputFile.close();
                    break;

                case 3:
                    outputFile.println(pokedex.toTabFileWithNext());
                    outputFile.flush();
                    outputFile.close();
                    break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create file!");
        }

    }
}