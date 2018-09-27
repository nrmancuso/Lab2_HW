public class Test {

    //public static void main(String [] args) {

        ArrayBag pokedex = new ArrayBag();

        Pokemon dude = new Pokemon(1, "dude");
        Pokemon asdf = new Pokemon(2, "fgui");
        Pokemon dfgh = new Pokemon(3, "fg");
        Pokemon tyiu = new Pokemon(4, "df");
        Pokemon fghj = new Pokemon(5, "er");
        Pokemon tyui = new Pokemon(6, "43e");
        Pokemon uioi = new Pokemon(7, "34");

        pokedex.add(dude);
        pokedex.add(asdf);
        pokedex.add(dfgh);
        pokedex.add(tyiu);
        pokedex.add(fghj);


        System.out.println(pokedex);

        pokedex.removeAndShift(0);
        pokedex.removeAndShift(2);
        pokedex.removeAndShift(4);
        pokedex.removeAndShift(5);
        pokedex.removeAndShift(1);
        pokedex.removeAndShift(3);
        pokedex.removeAndShift(34345);


        System.out.println(pokedex);

        pokedex.add(dude);
        pokedex.add(asdf);
        pokedex.add(dfgh);
        pokedex.add(tyiu);
        pokedex.add(fghj);


        pokedex.setDeleteFieldTrue(3);
        pokedex.setDeleteFieldTrue(5);
        pokedex.setDeleteFieldTrue(9);

        System.out.println(pokedex);


        pokedex.clear();

        System.out.println("Testing clear:::");

        System.out.println(pokedex);

        pokedex.add(dude);
        pokedex.add(asdf);
        pokedex.add(dfgh);
        pokedex.add(tyiu);
        pokedex.add(fghj);


        pokedex.setNexttoDelete();

        System.out.println(pokedex);

        pokedex.setDeleteFieldTrue(3);
        pokedex.setDeleteFieldTrue(5);
        pokedex.setDeleteFieldTrue(9);

        pokedex.setNexttoDelete();

        System.out.println(pokedex);

    }
}
