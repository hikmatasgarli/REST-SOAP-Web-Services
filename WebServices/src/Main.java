import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> userList = new ArrayList<>();
        userList.add("Hello");
        userList.add("World");
        userList.add("Mamed");


        for(int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }

        Iterator<String> iterator = userList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }




    }
}
