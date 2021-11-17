import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private final Choice[] commands = new Choice[4];
    public ArrayList<Product> inventory = new ArrayList<>();

    public static void main(String[] args) {
        Menu m = new Menu();
        m.run(args);
    }

    public void run(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided");
            initCommands();
            return;
        }

        readFromFile();
        String command = args[0];
        switch (command) {
            case "Read":
                printInventory();
                break;
            case "Add":
                Product newProduct = getProductFromUser();
                writeToFile(newProduct);
                break;
            case "Search":
                searchInventory();
                break;
            default:
                break;
        }
    }

    public void initCommands() {
        commands[1] = this::printInventory;
        commands[2] = () -> writeToFile(getProductFromUser());
        commands[3] = () -> System.out.println(3);
        commands[0] = () -> System.exit(0);
    }

    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("products.csv"))) {
            String line;
            while((line = br.readLine()) != null) {
                if (line.equals("brand,name,category,price,EAN"))
                    continue;

                String[] values = line.split(",");
                String brand = values[0];
                String name = values[1];
                String category = values[2];
                int price = Integer.parseInt(values[3]);
                Long EAN = Long.parseLong(values[4]);
                inventory.add(new Product(brand, name, category, price, EAN));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(Product p) {
        try (FileWriter fileWriter = new FileWriter("products.csv",true)) {
            fileWriter.write(p.toCSV() + "\n");
        } catch (IOException e) {
            System.out.println("caught exception");
        }
    }

    public void printInventory() {
        for(Product p : inventory) {
            System.out.println(p.toString());
        }
    }

    public void printProducts(List<Product> products){
        for(Product p : products){
            System.out.println(p.toString());
        }
    }

    public Product getProductFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the brand of the product?");
        String brand = scanner.nextLine();
        System.out.println("What is the name of the product?");
        String name = scanner.nextLine();
        System.out.println("What is the category of the product?");
        String category = scanner.nextLine();
        System.out.println("What is the price of the product?");
        String price = scanner.nextLine();
        System.out.println("What is the EAN code of the product (13 digits)?");
        String EAN = scanner.nextLine();
        try {
            Product p = new Product();
            p.setBrand(brand);
            p.setName(name);
            p.setCategory(category);
            p.setPrice(Integer.parseInt(price));
            p.setEAN(Long.parseLong(EAN));
            return p;

        } catch (Exception e) {
            System.out.println("Incorrect values..");
            throw e;
        }
    }

    public void searchInventory() {
        System.out.println("Press 1 to search for a product within a price interval\n" );
        int input;
        try {
            input = scanner.nextInt();
            String query;
            switch (input) {
                case 1:
                    System.out.println("Enter min and max values:");
                    int min = scanner.nextInt();
                    int max = scanner.nextInt();
                    priceSearch(min,max);
                default:

            }
        } catch ( Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void priceSearch(int min, int max) {
        List<Product> result = inventory.stream()
                .filter(p -> p.getPrice() <= max && p.getPrice() >= min)
                .sorted((p1, p2) -> {
                    return Integer.compare(p1.getPrice(), p2.getPrice());
                })
                .collect(Collectors.toList());

        printProducts(result);
    }

    public void brandSearch(String query){}

    public void nameSearch(String query){}

    public void categorySearch(String query){}

}