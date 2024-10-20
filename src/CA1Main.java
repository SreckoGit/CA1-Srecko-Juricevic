import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//My GitHub repo:
//https://github.com/SreckoGit/CA1-Srecko-Juricevic


public class CA1Main {

   public static void main(String[] args) {

      //creating objects from classes of file-handling class and List to hold
      //customer objects returned from CA1CustomerParser.parseCA1Customer() method
      CA1FileHandler fileHandler = new CA1FileHandler();
      List<CA1Customer> customers = new ArrayList<>();

      // Read lines from file and handle IOException
      try {
         // Read all lines from the file passed as an argument into the List
         List<String> lines = fileHandler.readLinesFromCA1File("customers.txt");

         // using while loop, starting at index 0, and incrementing it each time it reads 4 lines by 4 at the
         // bottom. at first run indexes 0,1,2,3 are passed, second loop run line index is incremented by 4
         // and
         //indexes 4,5,6,7 are read, lineIndex increments by 4 again with +=4, and 8,9,10,11 are read
         int lineIndex = 0;
         while (lineIndex < lines.size()) {
            try {
               // Read the next 4 lines as customer data, and pass it to variables,
               //the method get() returns a string we had at location, and it's dynamically calculated,
               //fields are declared inside loop, cleared each time when out of scope and created again
               String firstNameSecondName = lines.get(lineIndex);
               String totalPurchase = lines.get(lineIndex + 1);
               String customerClass = lines.get(lineIndex + 2);
               String lastPurchase = lines.get(lineIndex + 3);

               //for each iteration of the loop,
               //a new list is created, Strings added and when iteration is done it disappears
               // as a local variable, because it's declared inside the loop not outside,
               List<String> customerData = Arrays.asList(
                  firstNameSecondName,
                  totalPurchase,
                  customerClass,
                  lastPurchase
               );

               // Parse the customer data and add to list, finalValue is calculated in constructor call
               CA1Customer customer = parseCA1Customer(customerData);
               customers.add(customer);

            } catch (IllegalArgumentException e) {
               System.err.println("Main: Error parsing customer data: " + e.getMessage());
            }

            // Move to the next set of 4 lines
            lineIndex += 4;
         }//end of while loop
      } catch (IOException e) {
         System.err.println("An error occurred while reading the file: " + e.getMessage());
      }
      // Display all customers for my own validation, I have overridden the toString() method in CA1Customer
      for (CA1Customer customer : customers) {
         System.out.println(customer);
      }

      //Writing the customers new info
      try{
         fileHandler.writeLinesToCA1File("customerdiscount.txt", customers);
      } catch (IOException e) {
         System.err.println("An error occurred while writing the file: " + e.getMessage());
         e.printStackTrace();
      }


   }//-------------------end main------------------------------



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

}//end class
