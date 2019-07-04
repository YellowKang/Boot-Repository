import java.util.ArrayList;
import java.util.List;

public class TestAs {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        String[] strings = list.toArray(new String[list.size()]);
        for (String string : strings) {
            System.out.println(strings.length);
            System.out.println(string);
        }

    }

}
