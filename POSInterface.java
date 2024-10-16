package T1_pos;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface POSInterface extends Remote {
    String addProduct(String name, double price, int quantity) throws RemoteException;
    String sellProduct(String name, int quantity) throws RemoteException;
    String getProductDetails(String name) throws RemoteException;
    List<String> getProductList() throws RemoteException;  // Fetch product list
}
