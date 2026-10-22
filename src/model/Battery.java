package model;

public class Battery {
    private final String name;
    private double price;

    public Battery(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Battery name cannot be empty.");
        }
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    private static void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Battery price cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Battery{name='" + name + "', price=" + price + "}";
    }
}