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

   //---GENERATED GETTERS AND SETTERS THAT I KEPT/USED-------

   public String getFirstName() {
      return firstName;
   }

   public String getSecondName() {
      return secondName;
   }

   public double getFinalValue() {
      return finalValue;
   }



   //This method is called by the constructor after the fields have been initialized ,
   //it calculates needed values and initializes the total finalValue field, later printed to the file
   private void calculateDiscount(){
      int currentYear = 2024;
      double discountPercentage = 0.0;

      //------------CLASS 1----------------
      if (customerClass == 1) {
         if (lastPurchaseYear == currentYear) {
            discountPercentage = 30.0;    //Class = 1 and Last Purchase in 2024

         } else if ( lastPurchaseYear < currentYear &&  lastPurchaseYear >= (currentYear - 5)) {
            discountPercentage = 20.0;  //Class = 1 and Last Purchase before than 2024 , within 5y

         } else if (lastPurchaseYear < (currentYear - 5)) {
            discountPercentage = 10.0;  //Class 1, no purchase in the last 5 years
         }

         //------------CLASS 2----------------
      } else if (customerClass == 2) {
         if (lastPurchaseYear == currentYear) {
            discountPercentage = 15.0;  //Class 2, purchase in 2024

         } else if (lastPurchaseYear < currentYear && lastPurchaseYear >= (currentYear - 5)) {
            discountPercentage = 13.0;  // Class 2, purchase before 2024 but within 5 years

         } else if (lastPurchaseYear < (currentYear - 5)) {
            discountPercentage = 5.0;   // Class 2, no purchase in the last 5 years
         }

         //------------CLASS 3----------------
      } else if (customerClass == 3) {
         if (lastPurchaseYear == currentYear) {
            discountPercentage = 3.0;   // Class 3, purchase in 2024

         } else if (lastPurchaseYear < currentYear) {
            discountPercentage = 0.0;   //Class 3, no discount for purchases before 2024
         }
      }
      //calculating how much should we subtract from the total purchase amount
      double discountAmount = totalPurchase * discountPercentage/100;
      //initializing the field
      this.finalValue = totalPurchase - discountAmount;

   }//calculate discount end


   //OVERRIDING THE TO-STRING METHOD
   @Override
   public String toString() {
      return "Customer{" +
         "firstName='" + firstName + '\'' +
         ", secondName='" + secondName + '\'' +
         ", totalPurchase=" + totalPurchase +
         ", customerClass=" + customerClass +
         ", lastPurchaseYear=" + lastPurchaseYear +
         ", final value after discount=" + finalValue +
         '}';
   }
}