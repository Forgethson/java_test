import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/14
 */
@SuppressWarnings({"all"})
public class Map_ {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("wang", 12);
        map.put("li", 14);
        map.put("liu", 13);

        Set keyset = map.keySet();
        Collection valueset = map.values();
        System.out.println(valueset.getClass());

        Set entrySet = map.entrySet();
        for (Object entry : entrySet) {
            System.out.println(entry);
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m);
        }


    }
}
