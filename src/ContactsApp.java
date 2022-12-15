import java.io.*;
import java.util.Scanner;
/**
* The class ContactsApp contains methods that can be used to create, edit, 
* remove or read user created contacts.
*
* ContactsApp class can be used to making similar functions than for example
* contacts application in smartphones. The class contains methods that are 
* for creating contacts with certain informations and similarly to edit those
* contacts and removing them. The App runs in CLI and uses text-files to store
* its data. Apart from contact handling methods there is also an option for 
* closing the application.
* 
* @author Olli Nevamäki
 */

public class ContactsApp {
    public static int whatToDo;
    /**
    * The main method is used to run the ContactsApp class and it contains
    * a while loop that is used to keep the application alive and running.
    * It also has the menu view for the app itself from which you can move on to
    * the apps own functions through method calls and also close the program.
     */
    public static void main(String [] args) {
        Console c = System.console();
        File text = new File("contacts.txt");
        while(true) {
            System.out.print(
            "Press '1' to add new contact." +'\n'+
            "Press '2' to edit existing contact." +'\n'+
            "Press '3' to remove a contact." +'\n'+
            "Press '4' to read contacts."+'\n'+
            "Press '5' to exit Contacts App"+'\n');
            whatToDo = 0;
            if(whatToDo < 1 || whatToDo > 5) {
                whatToDo = Integer.parseInt(c.readLine());
            }
            if(whatToDo == 1) {
                createContact();
            }

            if(whatToDo == 2) {
                editContact();
            }

            if(whatToDo == 3) {
                removeContact();
            }

            if(whatToDo == 4) {
                readContacts();
            }

            if(whatToDo == 5) {
                System.exit(0);
            }
        
        }
    }
    /**
    * The createContact method is used to create contacts in a permanent file.
    * 
    * createContact is a method that first uses user input to ask the required
    * contact information from the user which some are mandatory to have and
    * some are not. When the required information has been collected the method
    * appends a text file where the collected contact data is then stored for 
    * later use.
     */
    public static void createContact() {
        Console c = System.console();
        System.out.println("Personal ID:");
        String id = c.readLine();
        while(id.equals("")) {
            System.out.println("ID is mandatory:");
            id = c.readLine();
        }
            
        System.out.println("First name:");
        String firstName = c.readLine();
        while(firstName.equals("")) {
            System.out.println("First name is mandatory:");
            firstName = c.readLine();
        }

        System.out.println("Last name:");
        String lastName = c.readLine();
        while(lastName.equals("")) {
            System.out.println("Last name is mandatory:");
            lastName = c.readLine();
        }
            

        System.out.println("Phone number:");
        String phonenum = c.readLine();
        while(phonenum.equals("")) {
            System.out.println("Phone number is mandatory:");
            phonenum = c.readLine();
        }
            

        System.out.println("Address:");
        String address = c.readLine();
            
        System.out.println("e-mail:");
        String email = c.readLine();

        try {
            Writer write = new FileWriter("contacts.txt", true);
            write.write(id + ",");
            write.write(firstName + ",");
            write.write(lastName + ",");
            write.write(phonenum + ",");
            write.write(address + ",");
            write.write(email);
            write.write('\n');
            write.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
    * The editContact method is used to search and edit contacts.
    *  
    * editContact method first asks the user to enter three mandatory search 
    * keywords which it uses to locate the line from the text file where the 
    * contacts are stored that contains the searched three keywords. After 
    * locating the searched contact, the method asks the user for substitutive
    * information which it uses to create an updated contact with new data by
    * removing the old contact from the new contacts way.
    * The method uses a temporary file to gather the old information and pass it
    * on to the original text file with added new contact information.
    */
    public static void editContact() {
        Console c = System.console();
        System.out.println("Search a contact to edit:");
        System.out.println("keyword 1:");
        String keyword1 = c.readLine();
        while(keyword1.equals("")) {
            System.out.println("keyword required!");
            keyword1 = c.readLine();
        }
        System.out.println("keyword 2:");
        String keyword2 = c.readLine();
        while(keyword2.equals("")) {
            System.out.println("keyword required!");
            keyword2 = c.readLine();
        }
        System.out.println("keyword 3:");
        String keyword3 = c.readLine();
        while(keyword3.equals("")) {
            System.out.println("keyword required!");
            keyword3 = c.readLine();
        }
        
        System.out.println("Insert new contact information." + '\n');
        System.out.println("Personal ID:");
        String id = c.readLine();
        while(id.equals("")) {
            System.out.println("ID is mandatory:");
            id = c.readLine();
        }
            
        System.out.println("First name:");
        String firstName = c.readLine();
        while(firstName.equals("")) {
            System.out.println("First name is mandatory:");
            firstName = c.readLine();
        }

        System.out.println("Last name:");
        String lastName = c.readLine();
        while(lastName.equals("")) {
            System.out.println("Last name is mandatory:");
            lastName = c.readLine();
        }
            

        System.out.println("Phone number:");
        String phonenum = c.readLine();
        while(phonenum.equals("")) {
            System.out.println("Phone number is mandatory:");
            phonenum = c.readLine();
        }
            

        System.out.println("Address:");
        String address = c.readLine();
            
        System.out.println("e-mail:");
        String email = c.readLine();

        try{
            File inputFile = new File("contacts.txt");
            File tempFile = new File("contactsTemp.txt");
            BufferedReader reader=new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer=new BufferedWriter(new FileWriter(tempFile));            
            String line;
            while((line = reader.readLine()) != null) {
                if(line.contains(keyword1) && line.contains(keyword2) 
                && line.contains(keyword3)) continue;
                writer.write(line + System.getProperty("line.separator"));
            }
            writer.write(id+","+firstName+","+lastName+","+phonenum+","+
            address+","+email+'\n');
            writer.close();
            reader.close();
            inputFile.delete();
            boolean successfull = tempFile.renameTo(inputFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
    * The removeContact method is used for removing stored contacts.
    *
    * removeContact method as well as the editing method,first asks the user
    * for keywords that are used for searching the precise contact.
    * After that the method reads the text file where the contacts are stored 
    * and deletes the contact line from the text file. 
    * The method uses the same function as the editContact method which where it
    * creates a temporary file where it gathers the stored information but in 
    * this case it leaves the selected information out when it rewrites itself
    * as the new storage file for contacts.
     */
    public static void removeContact() {
        Console c = System.console();
        System.out.println("Search a contact to remove:");
        System.out.println("keyword 1:");
        String keyword1 = c.readLine();
        while(keyword1.equals("")) {
            System.out.println("keyword required!");
            keyword1 = c.readLine();
        }
        System.out.println("keyword 2:");
        String keyword2 = c.readLine();
        while(keyword2.equals("")) {
            System.out.println("keyword required!");
            keyword2 = c.readLine();
        }
        System.out.println("keyword 3:");
        String keyword3 = c.readLine();
        while(keyword3.equals("")) {
            System.out.println("keyword required!");
            keyword3 = c.readLine();
        }

        try{
            File inputFile = new File("contacts.txt");
            File tempFile = new File("contactsTemp.txt");
            BufferedReader reader=new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer=new BufferedWriter(new FileWriter(tempFile));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.contains(keyword1) && line.contains(keyword2) 
                && line.contains(keyword3)) continue;
                writer.write(line + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            boolean successfull = tempFile.renameTo(inputFile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
    * The readContacts method is used to read stored contacts.
    * 
    * readContacts method uses Scanner to read the contacts text file where the
    * contacts are stored and then line by line prints them out for the user to 
    * inspect.
     */
    public static void readContacts() {
        Console c = System.console();
        try{
            Scanner scan = new Scanner(new File("contacts.txt"));
            String readFile = "";
            while(scan.hasNextLine()) {
                readFile = scan.nextLine();
                System.out.println(readFile);
                System.out.println();
            }
            scan.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}