import java.util.List;

public class CA1CustomerParser {


   //    --Method to create a Customer object from 4 lines of text. All lines read are broken down in groups of
   // 4 lines in main, and passed to parseCustomer class. In this method attempt to parse information from
   // passed list of string is made.
   //    --All known exceptions thrown by methods are caught, and we are throwing
   // our own in case any of them fail along with a message, that easily shows us what failed.
   //    --the method signature does not contain throws statement as IllegalArgumentException is unchecked
   // exception and can be thrown inside method without declaration in signature
   public static CA1Customer parseCA1Customer(List<String> customerData) {
      // -these remain local variables inside method as they are only relevant
      // in object creation not to the class:
      //firstName, secondName, totalPurchase, customerClass, lastPurchaseYear



      if (customerData.size() != 4) {
         throw new IllegalArgumentException("Invalid customer data, expected 4 lines.");
      }

      //first dealing with first line that contains name and surname split by space
      String[] names = customerData.get(0).split(" ");
      if (names.length != 2) {
         throw new IllegalArgumentException("First line in list is not split by ' '");
      }
      //if the first line was split in two
      String firstName = names[0];
      String secondName = names[1];


      // Line 2 - Total Purchase, calling parseDouble of Double Wrapper class to convert position 1 in
      // ArrayList
      double totalPurchase;
      //parseDouble might throw an unchecked exception, so far the data was manipulated as string, and now I
      // need to
      // convert it for object creation
      try {
         totalPurchase = Double.parseDouble(customerData.get(1));
         //catching a checked exception that can be possibly caused by the parseDouble
      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("Invalid total purchase value.");
      }



      // line 3, Class of customer has to be between 1 adn 3
      int customerClass;

      //parseInt might throw NumberFormat Exception
      try {
         customerClass = Integer.parseInt(customerData.get(2));
         if (customerClass < 1 || customerClass > 3) {
            throw new IllegalArgumentException("Customer class must be between 1 and 3.");
         }
      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("Invalid customer class value.");
      }



      //line 4 Last purchase check, parse int can throw  an unchecked exception
      int lastPurchaseYear;
      try {
         lastPurchaseYear = Integer.parseInt(customerData.get(3));
      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("Invalid last purchase year.");
      }

      // if we are here, validation for all the variables has passed and we can create and return new obj
      return new CA1Customer(firstName, secondName, totalPurchase, customerClass, lastPurchaseYear);
   }
}
