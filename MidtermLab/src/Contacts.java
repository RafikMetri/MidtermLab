/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/10/13
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Contacts implements Comparable{
    private String name, phoneNumber, birthDate;

    public Contacts(String _name, String _phoneNumber, String _birthDate){
        name = _name;
        phoneNumber = _phoneNumber;
        birthDate = _birthDate;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public int compareTo(Object o) {
        Contacts contacts = (Contacts) o;
        int shorterNameLength = 0;
        if(this.name.length() < contacts.name.length())
            shorterNameLength = this.name.length();
        else
            shorterNameLength = contacts.name.length();

        char thisLetter;
        char compareLetter;
        boolean exit = false;
        int num = 0;

        for(int i = 0; i < this.name.length()-9; i++){
            thisLetter = this.name.charAt(i);
            compareLetter = contacts.name.charAt(i);

            if(thisLetter > compareLetter){
                num = -1;
                exit = true;
            }
            else if(thisLetter < compareLetter){
                num = 1;
                exit = true;
            }
            else if(i > shorterNameLength){
                num = 0;
                exit = true;
            }
        }
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacts contacts = (Contacts) o;

        if (birthDate != null ? !birthDate.equals(contacts.birthDate) : contacts.birthDate != null) return false;
        if (name != null ? !name.equals(contacts.name) : contacts.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(contacts.phoneNumber) : contacts.phoneNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return name + "\t" + phoneNumber + "\t" + birthDate;
    }
}