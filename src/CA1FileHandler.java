import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CA1FileHandler {

   // Method for reading all lines from the file passed on and returning as a list,
   //"throws" keyword informs the calling method to handle the exception it may throw
   //an exception that needs to be handled with try catch
   public List<String> readLinesFromCA1File(String filePath) throws IOException {

      List<String> lines = new ArrayList<>();
      //creating the buffered reader with the filename given, filePath accepts the value when called
      //try-with-resources, no catch needed as method signature already throws IOException that can happen
      // within
      // this try block
      try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         //doing a standard check to see if we reached the end of file,
         //"line =" part is taking information first before checking,
         // info is being checked in same row with != null,
         // and if not null, it's added to the "lines" list.
         while ((line = br.readLine()) != null) {
            //if not end of file, add it to the list of String type
            lines.add(line);
         }
      }
      //we have read all the lines and return the list
      return lines;
   }


   //method that accepts path and a list to be written to the designated file
   public void writeLinesToCA1File(String filePath, List<CA1Customer> customers)throws IOException {

      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {

         for (CA1Customer customer : customers) {
            bufferedWriter.write(customer.getFirstName()+" "+ customer.getSecondName()+ "\n" + customer.getFinalValue()+ "\n\n");
         }

      }
   }
}