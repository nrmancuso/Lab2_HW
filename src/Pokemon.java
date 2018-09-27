public class Pokemon {

    private int ID;
    private String name;
    private boolean deleteField = false;
    private int next;

    public Pokemon(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDeleteField() {
        return deleteField;
    }

    public void setDeleteField(boolean bool) {

        this.deleteField = true;
    }


    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object pokemonObj) {

        return this.ID == ((Pokemon) pokemonObj).getID();
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", deleteField=" + deleteField +
                ", next=" + next +
                '}';
    }
}
