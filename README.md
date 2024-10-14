# POS-using-RMI_Client-Server_Swing-UI
This Java RMI-based POS system allows users to select products from a list, add them to a cart, and make purchases via a Swing GUI. The server manages real-time inventory, updates stock after purchases, and calculates the total bill. Features include cart functionality and stock validation.


This project is a simple Point of Sale (POS) system developed using Java RMI (Remote Method Invocation) and a Swing-based graphical user interface (GUI). The system allows users to select products, add them to a cart, and make purchases, all while managing inventory in real-time on the server side. It demonstrates the implementation of a distributed client-server architecture with real-time communication between the client and server.

Features Implemented:
1. Client-Server Architecture with Java RMI:   
  The system is designed using Java RMI, where the client communicates with the server remotely.
  The server manages product inventory, provides product details, handles purchases, and updates stock.
  The client interacts with the server via the RMI registry to perform various POS-related tasks.

2. Swing GUI for Client
  A graphical user interface is built using Java Swing, providing an interactive and user-friendly experience for the customer.
  The interface includes:
  A dropdown list to select products from the available inventory.
  A quantity input field to specify the number of units to purchase.
  Stock availability display to inform users of current stock levels.
  Cart display that shows the items added before the final purchase.
  Add to Cart button and Buy button for handling purchases.
  A total bill display showing the cumulative total for all purchases.

3. Product List and Inventory Management
  A predefined list of products (e.g., Chips, Cold Drinks, Ice Cream) is stored on the server, each with its name, price, and available quantity (stock).
  Users can view product details (including available stock) before adding items to the cart.

4. Cart Functionality
  Users can add multiple items to the cart before making a purchase.
  The cart stores selected products and quantities, allowing users to review items before finalizing the transaction.
  The cart can hold different product types, and its contents are displayed dynamically in the GUI.

5. Purchasing and Stock Update
  Once the user is satisfied with the items in the cart, clicking the "Buy" button will:
  Validate the availability of stock for each item.
  Reduce the stock on the server after the purchase is made.
  Display the total bill for the transaction.
  If the stock is insufficient, an error message is displayed.

6. Real-time Inventory Management
  The server handles all inventory management in real-time:
  It checks if there’s enough stock before processing each sale.
  Stock levels are updated immediately after a successful purchase.

7. Error Handling
  The system gracefully handles errors such as insufficient stock or invalid input.
  Messages are displayed on the client GUI to inform users of any issues during the transaction process.

System Overview:
  1. The server manages the product inventory and handles requests from the client. It also updates product stock levels after each successful sale.
  2. The client allows users to browse available products, add them to a cart, and purchase them. It communicates with the server to validate stock availability and get product details.

This system can be easily extended with more advanced features such as user authentication, discount calculations, reporting, and integration with a database for persistent storage of product data.

**Technologies Used:**
1. Java RMI: For remote communication between the client and server.
2. Java Swing: For building the graphical user interface on the client side.
3. Object-Oriented Programming (OOP): For managing product information and transaction logic.
