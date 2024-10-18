public class CA1Customer {

   private String firstName;
   private String secondName;
   private double totalPurchase;
   private int customerClass;
   private int lastPurchaseYear;
   private double finalValue;

   // Constructor, initializes the fields and calculates discount
   public CA1Customer(String firstName, String secondName, double totalPurchase, int customerClass,
                      int lastPurchaseYear) {
      this.firstName = firstName;
      this.secondName = secondName;
      this.totalPurchase = totalPurchase;
      this.customerClass = customerClass;
      this.lastPurchaseYear = lastPurchaseYear;
      //method call within constructor as all the checks were done before calling constructor,
      //calculates discount and my finalValue
      calculateDiscount();

   }

   //---GENERATED GETTERS AND SETTERS-------

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getSecondName() {
      return secondName;
   }

   public void setSecondName(String secondName) {
      this.secondName = secondName;
   }

   public double getTotalPurchase() {
      return totalPurchase;
   }

   public void setTotalPurchase(double totalPurchase) {
      this.totalPurchase = totalPurchase;
   }

   public int getCustomerClass() {
      return customerClass;
   }

   public void setCustomerClass(int customerClass) {
      this.customerClass = customerClass;
   }

   public int getLastPurchaseYear() {
      return lastPurchaseYear;
   }

   public void setLastPurchaseYear(int lastPurchaseYear) {
      this.lastPurchaseYear = lastPurchaseYear;
   }

   public double getFinalValue() {
      return finalValue;
   }

   public void setFinalValue(double finalValue) {
      this.finalValue = finalValue;
   }