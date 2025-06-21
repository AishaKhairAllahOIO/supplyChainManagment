public class Main {
    public static void main(String[] args) {
        ProductWarehouse warehouse = new ProductWarehouse();

        warehouse.addProduct("Laptop", 1500.0, 10);
        warehouse.addProduct("Smartphone", 800.0, 25);
        warehouse.addProduct("Headphones", 150.0, 50);
        warehouse.addProduct("Monitor", 300.0, 20);
        warehouse.addProduct("Keyboard", 100.0, 40);
        warehouse.addProduct("Mouse", 50.0, 100);

        printInOrder(warehouse.getRoot());

        ProductInformation found = warehouse.searchProduct(3);
        if (found != null) System.out.println("Found: " + found);
        else System.out.println("Product not found");

        ProductInformation notFound = warehouse.searchProduct(10);
        if (notFound != null) System.out.println("Found: " + notFound);
        else System.out.println("Product not found");

        warehouse.updateProductPrice(1, 1450.0);
        warehouse.updateProductQuantity(6, 120);

        printInOrder(warehouse.getRoot());

        warehouse.deleteProduct(3);
        warehouse.deleteProduct(2);

        printInOrder(warehouse.getRoot());

        boolean deletedNonExistent = warehouse.deleteProduct(100);
        System.out.println("Delete non-existent product: " + deletedNonExistent);
    }

    private static void printInOrder(ProductNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.product);
            printInOrder(node.right);
        }
    }
}
