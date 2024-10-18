package T1_pos;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class POSClientGUI {
    private static POSInterface pos;
    private static double totalBill = 0.0;
    private static Map<String, Integer> cart = new HashMap<>();  // Cart to hold product and quantity

    public static void main(String[] args) {
        try {
            // Connect to the RMI registry and look up the POS service
            Registry registry = LocateRegistry.getRegistry("localhost", 7444);
            pos = (POSInterface) registry.lookup("POS");

            // Create the UI
            JFrame frame = new JFrame("Point of Sale System");
            frame.setSize(500, 450);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);

            // Fetch products for dropdown
            List<String> productList = pos.getProductList();
            JComboBox<String> productDropdown = new JComboBox<>(productList.toArray(new String[0]));
            productDropdown.setBounds(150, 30, 200, 30);
            frame.add(productDropdown);

            // Quantity input field
            JLabel quantityLabel = new JLabel("Quantity:");
            quantityLabel.setBounds(30, 80, 100, 30);
            JTextField quantityField = new JTextField();
            quantityField.setBounds(150, 80, 200, 30);
            frame.add(quantityLabel);
            frame.add(quantityField);

            // Stock availability label
            JLabel stockLabel = new JLabel("Available stock: ");
            stockLabel.setBounds(30, 120, 200, 30);
            frame.add(stockLabel);

            // Button to add the product to cart
            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.setBounds(150, 170, 200, 30);
            frame.add(addToCartButton);

            // Cart display area
            JTextArea cartArea = new JTextArea();
            cartArea.setBounds(30, 210, 400, 80);
            cartArea.setEditable(false);
            frame.add(cartArea);

            // Button to buy all items in the cart
            JButton buyButton = new JButton("Buy");
            buyButton.setBounds(150, 310, 200, 30);
            frame.add(buyButton);

            // Output label for transaction status
            JLabel resultLabel = new JLabel("");
            resultLabel.setBounds(30, 350, 350, 30);
            frame.add(resultLabel);

            // Total bill label
            JLabel totalBillLabel = new JLabel("Total Bill: $0.0");
            totalBillLabel.setBounds(30, 380, 350, 30);
            frame.add(totalBillLabel);

            // Add action listener to display stock when product is selected
            productDropdown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedProduct = (String) productDropdown.getSelectedItem();
                    try {
                        String productDetails = pos.getProductDetails(selectedProduct);
                        String[] details = productDetails.split(", Quantity: ");
                        stockLabel.setText("Available stock: " + details[1]);
                    } catch (Exception ex) {
                        stockLabel.setText("Error fetching stock.");
                    }
                }
            });

            // Add action listener for the "Add to Cart" button
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedProduct = (String) productDropdown.getSelectedItem();
                    String quantityText = quantityField.getText();
                    if (selectedProduct != null && !quantityText.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(quantityText);
                            // Add product to cart
                            cart.put(selectedProduct, cart.getOrDefault(selectedProduct, 0) + quantity);
                            updateCartArea(cartArea);
                            resultLabel.setText("Product added to cart.");
                        } catch (Exception ex) {
                            resultLabel.setText("Error: " + ex.getMessage());
                        }
                    } else {
                        resultLabel.setText("Please select a product and enter quantity.");
                    }
                }
            });

            // Add action listener for the "Buy" button
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!cart.isEmpty()) {
                        double transactionTotal = 0.0;
                        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                            String productName = entry.getKey();
                            int quantity = entry.getValue();
                            try {
                                String result = pos.sellProduct(productName, quantity);
                                // If the sale is successful, add the total price to transaction total
                                if (!result.contains("Insufficient stock")) {
                                    String[] resultParts = result.split("Total price: \\$");
                                    double price = Double.parseDouble(resultParts[1].split("\\.")[0]);
                                    transactionTotal += price;
                                    resultLabel.setText("Products sold successfully.");
                                } else {
                                    resultLabel.setText("Failed: " + result);
                                    return;
                                }
                            } catch (Exception ex) {
                                resultLabel.setText("Error: " + ex.getMessage());
                                return;
                            }
                        }
                        // Update total bill and reset cart
                        totalBill += transactionTotal;
                        totalBillLabel.setText("Total Bill: $" + totalBill);
                        cart.clear();
                        updateCartArea(cartArea);
                    } else {
                        resultLabel.setText("Cart is empty. Add products to cart.");
                    }
                }
            });

            // Show the frame
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to update the cart display area
    private static void updateCartArea(JTextArea cartArea) {
        StringBuilder cartContents = new StringBuilder();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            cartContents.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        cartArea.setText(cartContents.toString());
    }
}

