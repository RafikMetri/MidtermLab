import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/10/13
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComparePhoneNumber implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Contacts contact1 = (Contacts)o1;
        Contacts contact2 = (Contacts)o2;

        return contact1.getName().compareTo(contact2.getName());
    }
}
