import java.util.ArrayList;
import java.util.List;

public class FindWordsContainingCharacter {
    // time: O(n)
    // space: O(n * max(length of words))
    public List<Integer> findWordsContaining(String[] words, char x) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1)
                res.add(i);
        }
        return res;
    }
}
