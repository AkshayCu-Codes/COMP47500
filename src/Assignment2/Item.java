package Assignment2;

public class Item {
	
	String name;
    double price;
    String category;

    Item(String name, double price, String category) {
        this.name     = name;
        this.price    = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%-28s  €%8.2f  [%s]", name, price, category);
    }

}
