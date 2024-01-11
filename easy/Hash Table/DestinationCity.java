import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationCity {
    /*
     * pseudo:
     * 1. create a hashmap<String, Integer> that stores (stationName,
     * indexInInnerList)
     * 2. iterate the path list, save (stationNamem, index) pair in HashMap is it
     * hasn't been seen before, otherwise delete the pair
     * 3. aftrer iteration, the HashMap should have 2 pairs in it, we check each of
     * them, the pair whosev value is 1 will be destiantion
     * Time: O(n)
     * Space: O(n)
     */
    public String destCity(List<List<String>> paths) {
        HashMap<String, Integer> stationIndex = new HashMap<>();
        for (List<String> path : paths) {
            if (!stationIndex.containsKey(path.get(0))) {
                stationIndex.put(path.get(0), 0);
            } else {
                stationIndex.remove(path.get(0));
            }
            if (!stationIndex.containsKey(path.get(1))) {
                stationIndex.put(path.get(1), 1);
            } else {
                stationIndex.remove(path.get(1));
            }
        }
        for (Map.Entry<String, Integer> entry : stationIndex.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return "";
    }

    public static void main(String[] args) {
    }
}
