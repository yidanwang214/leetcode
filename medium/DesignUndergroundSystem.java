public class DesignUndergroundSystem {
    /*
     * pseudo:
     * create 2 HashMaps, one is Customer<Interger, Pair<String, Integer>> which
     * stores customer ID and (station, time) pair, another is Average<String,
     * Pair<Integer, Integer>> whcih stores the (startStation, endStation) and
     * unique (time, people) associated with it
     * 
     * [checkIn] use passenger id as key in hashmap Customer, (stationName, time) as
     * value.
     * 
     * [checkOut]
     * use passenger id as key to query the hashmap, if it exist, check
     * if station name is the same, if it is, add the pair to HashMap Average by
     * adding the (startStation, endStation) and (time, people) <- time = time +
     * timeDiff; people = people + 1. Then also delete the entry of the passenger
     * from the table
     * 
     * [getAberageTime]
     * query HashMap averageTime by concatenating startStation and
     * endStation, and get the key's value (time, people), then return time/people
     * 
     * matters to be attended to:
     * check out for the same id will come after the checkin of the id, so we
     * don't need to consider whether a id exists in customer table when we
     * checkout, and all id needs to be inserted into customer for checkin of id
     */
    HashMap<Integer, Pair<String, Integer>> customer;
    HashMap<String, Pair<Integer, Integer>> time;

    public UndergroundSystem() {
        customer = new HashMap<Integer, Pair<String, Integer>>();
        time = new HashMap<String, Pair<Integer, Integer>>();
    }

    public void checkIn(int id, String stationName, int t) {
        Pair<String, Integer> value = new Pair<String, Integer>(stationName, t);
        customer.put(id, value);
    }

    public void checkOut(int id, String stationName, int t) {
        if (customer.containsKey(id)) {
            Pair<String, Integer> value = customer.get(id);
            String startStation = value.getKey();
            Integer startTime = value.getValue();
            String route = startStation + "-" + stationName;
            Integer timeDiff = t - startTime;
            // if the route is not in Time table, insert
            if (!time.containsKey(route)) {
                Pair<Integer, Integer> timePeople = new Pair<>(timeDiff, 1);
                time.put(route, timePeople);
            } else { // if the route is in Time table, update time and people
                Pair<Integer, Integer> timePeople = time.get(route);
                timeDiff += timePeople.getKey();
                int people = timePeople.getValue() + 1;
                timePeople = new Pair<>(timeDiff, people);
                time.put(route, timePeople);
                System.out.println("Route: " + time.get(route) + ", time: " + time.get(route).getKey() + ", people: "
                        + time.get(route).getValue());
            }
            // delete the customer's entry from customer table
            customer.remove(id);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        Pair<Integer, Integer> timePeople = time.get(route);
        return (double) timePeople.getKey() / (double) timePeople.getValue();
    }

    public static void main(String[] args) {
        // works on LeetCode
        Pair<String, Integer> value = new Pair<>("Paris", 1);
        System.out.println(value.getKey());
        System.out.println(value.getValue());

    }
}
