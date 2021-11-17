import java.util.Objects;
import java.util.ArrayList;

public final class Product {
    private String brand;
    private String name;
    private String category;
    private int price;
    private Long EAN;

    public Product() { }

    public Product(String brand, String name, String category, int price, Long EAN) {
        this.brand = brand;
        this.name = name;
        this.category = category;
        this.price = price;
        this.EAN = EAN;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category){

        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public Long getEAN() {
        return EAN;
    }

    public void setEAN(Long EAN) {
        this.EAN = EAN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product object = (Product) o;
        return price == object.price
                && Objects.equals(brand, object.brand)
                && Objects.equals(name, object.name)
                && Objects.equals(category, object.category)
                && Objects.equals(EAN, object.EAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, name, category, price, EAN);
    }

    @Override
    public String toString() {
        return "Product[" +
                "brand=" + brand + ", " +
                "name=" + name + ", " +
                "category=" + category + ", " +
                "price=" + price + ", " +
                "EAN=" + EAN + ']';
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%d,%d",
                brand, name, category, price, EAN);
    }
}

