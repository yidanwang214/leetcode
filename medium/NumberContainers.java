import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;

// 1. better solution: use TreeSet (Red Black Tree)
// reference: https://leetcode.com/problems/design-a-number-container-system/solutions/2322402/java-python-3-treeset-sortedlist-w-brief-explanation-and-analysis/
// Time: O(logn) for both change and find, where n is the total count of numbers in the container
// Space: O(n) for creating 2 maps
// class NumberContainers {
//     private Map<Integer, TreeSet<Integer>> numToIndices = new HashMap<>();
//     private Map<Integer, Integer> indexToNum = new HashMap<>();

//     public NumberContainers() {
//     }

//     public void change(int index, int number) {
//         if (indexToNum.containsKey(index)) {
//             int preNum = indexToNum.get(index);
//             numToIndices.get(preNum).remove(index);
//             if (numToIndices.get(preNum).isEmpty()) {
//                 numToIndices.remove(preNum); // i didn't hadnle it in my own code
//             }
//         }
//         indexToNum.put(index, number);
//         numToIndices.computeIfAbsent(number, s -> new TreeSet<>()).add(index);
//     }

//     public int find(int number) {
//         if (numToIndices.containsKey(number))
//             return numToIndices.get(number).first();
//         return -1;
//     }
// }

// 2. my attempt
// time: O(n) for inserting
// space: O(n): the count of numbers in the container
// attention 1: change can be duplicate: change(56, 31) appeared twice
// attention 2: if a number is empty set of indices, the number (the entry) should be removed from numIndexHash
// change can be duplicate: change(56, 31) appeared twice

class NumIndex {
    int minIndex;
    ArrayList<Integer> indices;

    public NumIndex() {
        this.minIndex = -1;
        this.indices = new ArrayList<Integer>();
    }

    public NumIndex(int index) {
        this.minIndex = index;
        this.indices = new ArrayList<Integer>();
        this.indices.add(index);
    }

    public int getMinIndex() {
        return this.minIndex;
    }

    public void addNewIndex(int index) {
        // update minIndex
        if (index < this.minIndex || this.minIndex == -1)
            this.minIndex = index;

        // if indices is not empty
        if (!this.indices.isEmpty()) {
            for (int i = 0; i < this.indices.size(); i++) {
                if (index < this.indices.get(i)) {
                    this.indices.add(i, index);
                    return;
                } else if (index > this.indices.get(i) && i != this.indices.size() - 1
                        && index < this.indices.get(i + 1)) {
                    this.indices.add(i + 1, index); /*
                                                     * couldn't pass test case untill i change this.indices.set(i + 1,
                                                     * index) to this.indices.add(i + 1, index), a stupid typo that made
                                                     * me debugged all day
                                                     */
                    return;
                }
            }
        }

        // if index is greater than the last element in the arraylist or if arraylist is
        // empty
        this.indices.add(index);
    }

    public void deleteIndex(int index) {
        if (index == this.minIndex) {
            this.minIndex = this.indices.size() == 1 ? -1 : this.indices.get(1);
        }
        this.indices.remove(this.indices.indexOf(index));
    }
}

class NumberContainers {
    HashMap<Integer, Integer> indexNumHash;
    HashMap<Integer, NumIndex> numIndexHash;

    public NumberContainers() {
        indexNumHash = new HashMap<Integer, Integer>();
        numIndexHash = new HashMap<Integer, NumIndex>();
    }

    public void change(int index, int number) {
        // index is not in indexNumHash
        if (!indexNumHash.containsKey(index)) {
            indexNumHash.put(index, number); // add (index, number) to indexNumHash

            // check if (number, index) in numIndexHash
            // if number not in numIndexHash
            if (!numIndexHash.containsKey(number)) {
                NumIndex numindexobj = new NumIndex(index);
                numIndexHash.put(number, numindexobj);
            } else { // if number in numIndexHash
                // numIndexHash.replace(number, numIndexHash.get(number).addNewIndex(index));
                numIndexHash.get(number).addNewIndex(index);
            }
        } else { // if index is in indexNumHash, might update the value of number
            int preNum = indexNumHash.get(index);
            int curNum = number;
            if (preNum == curNum) { // if preNum and curNum are equal, no need to update any table
                return;
            }
            // if preNum != curNum
            indexNumHash.replace(index, curNum); // 1. update indexNumHash
            // update numIndexHash by adding index to curnum's value and deleting index from
            // prenum's value
            if (!numIndexHash.containsKey(curNum)) {
                // 2. update (curNum, index) pair by creating a new NumIndex object
                NumIndex numindexobj = new NumIndex(index);
                numIndexHash.put(curNum, numindexobj); // add index to curNum's value
            } else { // if curNum is in numIndexHash
                // 2. update curNum's value
                // numIndexHash.replace(curNum, numIndexHash.get(curNum).addNewIndex(index));
                numIndexHash.get(curNum).addNewIndex(index);
            }
            // 3. update preNum's value
            // numIndexHash.replace(preNum, numIndexHash.get(preNum).deleteIndex(index));
            numIndexHash.get(preNum).deleteIndex(index);
        }
    }

    public int find(int number) {
        if (!numIndexHash.containsKey(number)) {
            return -1;
        } else {
            return numIndexHash.get(number).minIndex;
        }
    }

    /* for debug */
    public static List<Pair<Integer, Integer>> extractedNumbers(String filePath) {
        List<Pair<Integer, Integer>> numbers = new ArrayList<Pair<Integer, Integer>>();
        String regex = "\\[(\\d+),(\\d+)\\]";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = Pattern.compile(regex).matcher(line);
                while (matcher.find()) {
                    numbers.add(new Pair<Integer, Integer>(Integer.parseInt(matcher.group(1)),
                            Integer.parseInt(matcher.group(2))));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    public static int counter = 0;

    /* for debug */
    public static void main(String[] args) throws Exception {
        /*
         * Input
         * ["NumberContainers", "find", "change", "change", "change", "change", "find",
         * "change", "find"]
         * [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
         * Output
         * [null, -1, null, null, null, null, 1, null, 2]
         */
        NumberContainers nc = new NumberContainers();

        String filePath = "/Users/yidanwang/Desktop/RSP/leetcode/medium/testcase2349.txt";
        List<Pair<Integer, Integer>> extractedNumbers = extractedNumbers(filePath);

        for (Pair<Integer, Integer> pair : extractedNumbers) {
            // System.out.println("index: " + pair.getKey() + " number: " +
            // pair.getValue());
            nc.change(pair.getKey(), pair.getValue());
            counter++;
            if (counter == 52) {
                break;
            }
        }
        for (Map.Entry<Integer, Integer> entry : nc.indexNumHash.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("index: " + key + ", number: " + value);
        }

        for (Map.Entry<Integer, NumIndex> entry : nc.numIndexHash.entrySet()) {
            Integer key = entry.getKey();
            NumIndex numindex = entry.getValue();
            System.out.print("num: " + key + ", indices: ");
            for (Integer integer : numindex.indices) {
                System.out.print(integer + " -> ");
            }
            System.out.println();
        }
    }
}
