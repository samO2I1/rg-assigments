import java.util.*;

public class Singleton {
   
    private static Singleton instance;
    
    
    private Singleton() {
        // Constructor code
    }
    
    
    public static Singleton getInstance() {
        
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    
    public void showMessage() {
        System.out.println("Hello");
    }
}


