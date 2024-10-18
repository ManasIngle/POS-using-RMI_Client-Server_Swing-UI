# Java POS-using-RMI_Client-Server_Swing-UI


This Java RMI-based **Point of Sale (POS)** system allows users to select products from a list, add them to a cart, and make purchases via a **Swing GUI**. The server manages **real-time inventory**, updates stock after purchases, and calculates the total bill. 

## Project Overview

This project is a simple Point of Sale (POS) system developed using **Java RMI** (Remote Method Invocation) and a **Swing-based graphical user interface (GUI)**. The system allows users to select products, add them to a cart, and make purchases, all while managing inventory in real-time on the server side. It demonstrates the implementation of a distributed **client-server architecture** with real-time communication between the client and server.

## Features Implemented

### Client-Server Architecture with Java RMI
- The system is designed using **Java RMI**, where the client communicates with the server remotely.
- The server manages product inventory, provides product details, handles purchases, and updates stock.
- The client interacts with the server via the **RMI registry** to perform various POS-related tasks.

### Swing GUI for Client
A graphical user interface is built using **Java Swing**, providing an interactive and user-friendly experience for the customer. The interface includes:
- A **dropdown list** to select products from the available inventory.
- A **quantity input field** to specify the number of units to purchase.
- **Stock availability display** to inform users of current stock levels.
- **Cart display** that shows the items added before the final purchase.
- **Add to Cart button** and **Buy button** for handling purchases.
- A **total bill display** showing the cumulative total for all purchases.

### Product List and Inventory Management
- A predefined list of products (e.g., Chips, Cold Drinks, Ice Cream) is stored on the server, each with its name, price, and available quantity (stock).
- Users can view product details (including available stock) before adding items to the cart.

### Cart Functionality
- Users can add multiple items to the cart before making a purchase.
- The cart stores selected products and quantities, allowing users to review items before finalizing the transaction.
- The cart can hold different product types, and its contents are displayed dynamically in the GUI.

### Purchasing and Stock Update
Once the user is satisfied with the items in the cart, clicking the **Buy** button will:
- Validate the availability of stock for each item.
- Reduce the stock on the server after the purchase is made.
- Display the total bill for the transaction.
- If the stock is insufficient, an error message is displayed.

### Real-time Inventory Management
- The server handles all inventory management in real-time:
  - It checks if there’s enough stock before processing each sale.
  - Stock levels are updated immediately after a successful purchase.

### Error Handling
- The system gracefully handles errors such as insufficient stock or invalid input.
- Messages are displayed on the client GUI to inform users of any issues during the transaction process.

## Instructions to Run the Java RMI-Based POS System

Follow the steps below to compile and run the Java RMI-based POS system with client-server architecture and a graphical user interface (GUI):

---

### Step 1: Compile All Java Files

First, compile all the necessary Java files using the `javac` command to prepare the server, client, and interface files for execution.

```bash
javac POSInterface.java Product.java POSImpl.java POSServer.java POSClientGUI.java
```

### Step 2: Start the RMI Registry

Before running the server, you need to start the RMI registry. The RMI registry allows the client to look up remote objects on the server. This should be run in a separate terminal window.
```bash
rmiregistry
```
Note: Make sure to keep the registry running in the background while you execute the other steps.


### Step 3: Run the POS Server

Once the RMI registry is running, start the server which handles inventory management, product listing, and sales processing.

```bash
java POSServer
```
-This starts the server and makes it ready to handle requests from the client.

### Step 4: Run the POS Client with GUI

In a new terminal window, run the client application to launch the GUI (built using Java Swing) for the Point of Sale system.

```bash
java POSClientGUI.java
```
-This opens the graphical interface where users can interact with the POS system, select products, add them to the cart, and make purchases.
-The client connects to the server via RMI to retrieve product details, check stock, and process transactions.

## System Overview

- The server manages the product inventory and handles requests from the client. It also updates product stock levels after each successful sale.
- The client allows users to browse available products, add them to a cart, and purchase them. It communicates with the server to validate stock availability and get product details.

## Future Enhancements

This system can be easily extended with more advanced features such as:
- **User authentication**
- **Discount calculations**
- **Reporting**
- Integration with a **database** for persistent storage of product data.

## Technologies Used

- **Java RMI**: For remote communication between the client and server.
- **Java Swing**: For building the graphical user interface on the client side.
- **Object-Oriented Programming (OOP)**: For managing product information and transaction logic.

## Licensing Information

This project is licensed under the **MIT License**. For more details, please refer to the LICENSE file in the repository.
