public class OMS {
    static String orderId;
    static String customerName;
    static String customerPhone;
    static double orderAmount;
    static double tempDiscount;
    static String paymentType;
    
    public static void main(String[] args) {
        start("ORD001", "John", "5551234567", 200.0, "CARD");
    }
    
    static void start(String a, String b, String c, double d, String e) {
        step2(a, b, c, d, e);
    }
    
    static void step2(String a, String b, String c, double d, String e) {
        step3(a, b, c, d, e);
    }
    
    static void step3(String a, String b, String c, double d, String e) {
        orderId = a; customerName = b; customerPhone = c;
        orderAmount = d; paymentType = e;
        process();
    }
    
    static void process() {
        if (orderAmount > 100) {
            tempDiscount = 0.1;
            orderAmount = orderAmount * 0.9;
        }
        
        if (paymentType.equals("CARD")) {
            orderAmount = orderAmount + 5;
        } else if (paymentType.equals("CASH")) {
            orderAmount = orderAmount;
        }
        
        String phoneDigits = customerPhone.replaceAll("\\D", "");
        
        System.out.println("Order: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Phone: " + phoneDigits);
        System.out.println("Total: " + orderAmount);
        
        middle();
        save();
    }
    
    static void middle() { validate(); }
    static void validate() { }
    static void save() { System.out.println("Saved to DB"); }
}

class Vehicle { void startEngine() {} }
class Bicycle extends Vehicle { }
class Truck extends Vehicle { }
class TruckHandler { void processTruck(Truck t) {} }














#SP-1 testing new jira space connections

