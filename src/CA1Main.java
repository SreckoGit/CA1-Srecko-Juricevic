import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
               CA1Customer customer = CA1CustomerParser.parseCA1Customer(customerData);
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


   }//end main


}//end class
