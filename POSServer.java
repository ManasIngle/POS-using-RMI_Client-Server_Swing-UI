package T1_pos;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class POSServer {
    public static void main(String[] args) {
        try {
            POSImpl pos = new POSImpl();
            Registry registry = LocateRegistry.createRegistry(7444);  // Default RMI port
            registry.bind("POS", pos);
            System.out.println("POS server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
