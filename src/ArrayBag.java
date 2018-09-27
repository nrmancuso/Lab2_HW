
public class ArrayBag {

    private Pokemon[] pokemonArray;
    private int numberOfEntries;


    public ArrayBag() {
        pokemonArray = new Pokemon[10];
    }


    /**
     * Gets the current number of entries in this bag.
     *
     * @return The integer number of entries currently in the bag.
     */
    public int getCurrentSize() {
        return pokemonArray.length;
    }

    /**
     * Sees whether this bag is empty.
     *
     * @return True if the bag is empty, or false if not.
     */
    public boolean isEmpty() {
        return pokemonArray == null;
    }

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if successful, false if not
     */
    public boolean add(Pokemon newEntry) {

        if (!this.isArrayFull()) {

            pokemonArray[numberOfEntries] = newEntry;
            pokemonArray[numberOfEntries].setNext(numberOfEntries + 2);
            numberOfEntries++;

            return true;

        } else {
            this.growArray();
            pokemonArray[numberOfEntries] = newEntry;
            pokemonArray[numberOfEntries].setNext(numberOfEntries + 2);
            numberOfEntries++;
            return true;
        }
    }

    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anEntry The entry to be removed.
     */
    private void remove(Pokemon anEntry) {

        anEntry = null;
    }

    /**
     * Deletes an entry and shifts elements to the left to fill in empty spaces
     *
     * @param IDtoRemove The entry to remove
     * @return True if successful, false if not
     */
    public boolean removeAndShift(int IDtoRemove) {

        int index = this.findIndexOf(IDtoRemove);

        //check index is valid
        if (index >= 0) {

            //0-next to last
            if (index < numberOfEntries - 1) {
                pokemonArray[index] = null;
                numberOfEntries--;

                //Shift elements
                for (int i = index; i < pokemonArray.length - 1; i++) {
                    pokemonArray[i] = pokemonArray[i + 1];
                }

                return true;

                //if last element
            } else if (index == numberOfEntries - 1) {
                pokemonArray[index] = null;
                numberOfEntries--;
                return true;
            }

            // if not valid
        } else {
            System.out.println("ID number not found.");
        }
        return false;


    }

    /**
     * Reassigns the delete field of an object to true
     *
     * @param IDtoDelete The entry to 'delete'
     * @return True if successful, false if not
     */
    public boolean setDeleteFieldTrue(int IDtoDelete) {

        int index = this.findIndexOf(IDtoDelete);

        //Set delete flag to true
        if (index >= 0) {
            pokemonArray[index].setDeleteField(true);
            return true;

            //Invalid ID
        } else {
            System.out.println("ID number not valid or Pokemon doesn't exist.");
        }
        return false;
    }

    /**
     * Sets an objects next field to the location of the next object in the array,
     * 0 if 'deleted', -1 if last
     *
     * @return True if successful, false if not
     */
    public void setNextField() {

        //Iterate through array
        for (int i = 0; i < numberOfEntries; i++) {

            //If delete field is not set true
            if (pokemonArray[i].getDeleteField() == false) {

                //Iterate through the remaining elements until another valid element appears
                for (int j = i + 1; j < numberOfEntries; j++) {

                    //If element is valid (delete field is false)
                    if (pokemonArray[j].getDeleteField() == false) {

                        //Set the next element ID to the ID of the valid element, and break out of loop
                        pokemonArray[i].setNext(pokemonArray[j].getID());
                        break;
                    }
                }

                //If delete field is set true, set next to zero
            } else if (pokemonArray[i].getDeleteField() == true) {
                pokemonArray[i].setNext(0);
            }

            //If the last element is has a delete flag not set, set it to -1
            if (i == numberOfEntries - 1 && pokemonArray[i].getDeleteField() == false) {
                pokemonArray[i].setNext(-1);
            }
        }
    }

    /**
     * Removes all entries from the bag.
     */
    public void clear() {

        pokemonArray = new Pokemon[10];
        numberOfEntries = 0;

    }


    private int findIndexOf(int IDtofind) {

        for (int i = 0; i < numberOfEntries; i++) {
            if (pokemonArray[i].getID() == IDtofind && pokemonArray[i] != null) {
                return i;
            }
        }
        return -1;
    }

    private boolean isArrayFull() {

        return (pokemonArray.length == numberOfEntries);

    }

    private void growArray() {
        Pokemon[] newPokemonArray = new Pokemon[pokemonArray.length + 10];

        for (int i = 0; i < pokemonArray.length; i++) {
            newPokemonArray[i] = pokemonArray[i];
        }
        pokemonArray = newPokemonArray;

    }

    @Override
    public String toString() {

        if (numberOfEntries == 0) {
            return "Pokedex is empty!";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numberOfEntries; i++) {
            sb.append(pokemonArray[i].toString() + "\n");
        }

        return sb.toString();
    }

    public String toTabFileWithDelete() {

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < numberOfEntries; i++) {
            sb.append(pokemonArray[i].getID() + "\t" + pokemonArray[i].getName() + "\t" + pokemonArray[i].getDeleteField() + "\n");
        }

        return sb.toString();
    }

    public String toTabFileWithNext() {

        StringBuilder sb = new StringBuilder();

        this.setNextField();

        for (int i = 0; i < numberOfEntries; i++) {
            sb.append(pokemonArray[i].getID() + "\t" + pokemonArray[i].getName() + "\t" + pokemonArray[i].getNext() + "\n");
        }

        return sb.toString();
    }

    public String toTabFileWithSkip() {

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < numberOfEntries; i++) {
            if (pokemonArray[i].getDeleteField() == false) {
                sb.append(pokemonArray[i].getID() + "\t" + pokemonArray[i].getName() + "\t" + "\n");
            }
        }

        return sb.toString();
    }
}
