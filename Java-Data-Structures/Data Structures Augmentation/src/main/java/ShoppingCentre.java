import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCentre {

    private Map<String, Set<Product>> productByName;
    private Map<String, Set<Product>> productByProducer;

    public ShoppingCentre() {
        this.productByName = new HashMap<>();
        this.productByProducer = new HashMap<>();
    }

    public String addProduct(String name, double price, String producer) {
        this.productByName.putIfAbsent(name, new TreeSet<>());
        this.productByProducer.putIfAbsent(producer, new HashSet<>());

        Product product = new Product(name, price, producer);

        if (!this.productByName.get(name).contains(product)) {
            this.productByName.get(name).add(product);
            this.productByProducer.get(producer).add(product);
        }

        return "Product added";
    }

    public String delete(String name, String producer) {
        Set<Product> removed = this.productByProducer.get(producer);

        if (removed == null || removed.isEmpty()) {
            return "No products found";
        }

        removed = removed.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toSet());

        this.productByName.values().removeAll(removed);
        this.productByProducer.get(producer).removeAll(removed);

        return removed.size() + " products deleted";
    }

    public String delete(String producer) {
        Set<Product> removed = this.productByProducer.remove(producer);

        if (removed == null || removed.isEmpty()) {
            return "No products found";
        }

        this.productByName.values().removeAll(removed);

        return removed.size() + " products deleted";
    }

    public String findProductsByName(String name) {
        StringBuilder builder = new StringBuilder();
        Set<Product> productsSet = this.productByName.get(name);

        if (productsSet == null || productsSet.isEmpty()) {
            return "No products found";
        }

        TreeSet<Product> products = new TreeSet<>(productsSet);

        for (Product product : products) {
            builder.append(product)
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    public String findProductsByProducer(String producer) {
        StringBuilder builder = new StringBuilder();
        Set<Product> products = this.productByProducer.get(producer);

        if (products == null || products.isEmpty()) {
            return "No products found";
        }

        for (Product product : products) {
            builder.append(product)
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    public String findProductsByPriceRange(double priceFrom, double priceTo) {
        return "";
    }

}
