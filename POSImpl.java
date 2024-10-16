package T1_pos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POSImpl extends UnicastRemoteObject implements POSInterface {
    private Map<String, Product> inventory;

    public POSImpl() throws RemoteException {
        inventory = new HashMap<>();
        // Adding some predefined products to the inventory
        inventory.put("Chips", new Product("Chips", 10.0, 50));
        inventory.put("Cold Drink", new Product("Cold Drink", 20.0, 30));
        inventory.put("Ice Cream Cone", new Product("Ice Cream Cone", 30.0, 20));
    }

    @Override
    public String addProduct(String name, double price, int quantity) throws RemoteException {
        if (inventory.containsKey(name)) {
            return "Product already exists.";
        }
        inventory.put(name, new Product(name, price, quantity));
        return "Product added successfully.";
    }

    @Override
    public String sellProduct(String name, int quantity) throws RemoteException {
        if (!inventory.containsKey(name)) {
            return "Product not found.";
        }
        Product product = inventory.get(name);
        if (product.getQuantity() < quantity) {
            return "Insufficient stock. Available quantity: " + product.getQuantity();
        }
        product.setQuantity(product.getQuantity() - quantity);
        double totalPrice = product.getPrice() * quantity;
        return "Product sold. Total price: $" + totalPrice + ". Remaining stock: " + product.getQuantity();
    }

    @Override
    public String getProductDetails(String name) throws RemoteException {
        if (!inventory.containsKey(name)) {
            return "Product not found.";
        }
        Product product = inventory.get(name);
        return "Product: " + product.getName() + ", Price: $" + product.getPrice() + ", Quantity: " + product.getQuantity();
    }

    @Override
    public List<String> getProductList() throws RemoteException {
        return new ArrayList<>(inventory.keySet());
    }
}
