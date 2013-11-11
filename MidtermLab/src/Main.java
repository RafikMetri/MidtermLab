import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/10/13
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private List<Contacts> contacts = new ArrayList<Contacts>();
    private HashMap<String, Contacts> map = new HashMap<String, Contacts>();

    public static void main(String[] args){
        Main main = new Main();
        main.createList();
        boolean exit = false;

        while(exit == false){
            System.out.println("Choose your action:\n1. Print persons sorted by Name\n2. Print persons sorted by Phone Number\n3. Print persons by Birth Date\n4. Get a single person by Name\n5. Quit");
            Scanner input = new Scanner(System.in);
            int entry = input.nextInt();

            switch(entry){
                case 1:
                    main.sortListAlphabetically(main.contacts);
                    main.createHashMap(main.contacts);
                    main.printMap(main.map);
                    break;
                case 2:
                    main.sortListNumber(main.contacts);
                    main.createHashMap(main.contacts);
                    main.printMap(main.map);
                    break;
                case 3:
                    main.sortListBirthDate(main.contacts);
                    main.createHashMap(main.contacts);
                    main.printMap(main.map);
                    break;
                case 4:
                    System.out.println("Enter the name that you wish to find");
                    Scanner input2 = new Scanner(System.in);
                    String name = input2.nextLine();
                    main.findName(name);
                    break;
                case 5:
                    exit = true;
            }
        }
        System.out.println("Program has ended");
    }

    public void createList(){
        Scanner read = null;
        String text = null;

        try{
            read = new Scanner(new File("Contacts.txt"));
            read.useDelimiter("$");

            do{
                text = read.nextLine();
                String name = findContactName(text);
                String num = findContactNumber(text);
                String date = findBirthDate(text);
                contacts.add(new Contacts(name, num, date));
            }while(read.hasNext());
        }
        catch(Exception e){
            System.out.println("There was a problem scanning your file");
        }
    }

    public void sortListAlphabetically(List<Contacts> list){
        Collections.sort(list);
    }

    public void sortListNumber(List<Contacts> list){
        Collections.sort(list, new ComparePhoneNumber());
    }

    public void sortListBirthDate(List<Contacts> list){
        Collections.sort(list, new CompareBirthDate());
    }

    public void printList(List<Contacts> list){
        Contacts[] array = list.toArray(new Contacts[list.size()]);

        for(int i = 0; i < list.size(); i++){
            System.out.println(array[i].toString());
        }
    }

    public void createHashMap(List<Contacts> list){
        Contacts[] array = list.toArray(new Contacts[list.size()]);

        for(int i = 0; i < array.length; i++){
            map.put(array[i].getName(), array[i]);
        }
    }

    public void printMap(HashMap<String, Contacts> map){

        for(Map.Entry<String, Contacts> entry: map.entrySet()){
            String key = entry.getKey();
            Contacts value = entry.getValue();

            System.out.println(key + ": " + value.toString());
        }
    }

    public String findContactName(String text){
        Pattern regex = Pattern.compile("[A-Z]+ [A-Z]+", Pattern.CASE_INSENSITIVE);
        Matcher search = regex.matcher(text);
        String name = null;

        do{
            try{
                name = search.group();
            }
            catch(Exception e){

            }
        }while(search.find());

        return name;
    }

    public String findContactNumber(String text){
        Pattern regex = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}", Pattern.CASE_INSENSITIVE);
        Matcher search = regex.matcher(text);
        String bob = null;


        do{

            try{
                bob = search.group();
            }
            catch(Exception e){

            }
        }while(search.find());

        return bob;
    }

    public String findBirthDate(String text){
        Pattern regex = Pattern.compile("([0-9]+/){2}[0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher search = regex.matcher(text);
        String bob = null;


        do{

            try{
                bob = search.group();
            }
            catch(Exception e){

            }
        }while(search.find());

        return bob;
    }

    public void findName(String name){
        Scanner read = null;
        String text = null;

        try{
            read = new Scanner(new File("Contacts.txt"));
            read.useDelimiter("$");
            text = read.next();
            read.close();
        }
        catch(Exception e){
            System.out.println("There was a problem scanning your file");
        }

        Pattern regex = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        Matcher search = regex.matcher(text);

        do{
            try{
                System.out.println(map.get(search.group()));
            }
            catch(Exception e){

            }
        }while(search.find());
    }
}
