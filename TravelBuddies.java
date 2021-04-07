import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use of Map sorted by value using java 8 streaming api
 *
 * {adidas=[sanfran, phoenix, dallas],
 *  reebok=[newyork, saltlake, washington, sanfran],
 *  puma=[sanfran, newyork, dallas]}
 *
 *
 *  sourceUser = adidas
 *  travelBuddies = reebok,puma since city-matching percent > 50%
 *
 *
 */


public class TravelBuddies {

    private static void findTravelBuddies(Map<String,List<String>> usercities,String sourceUser, double matchLimit)
    {
        //ETL : store the users city wise
        Map<String, List<String>> cityUsers = new HashMap<>();
        Map<String,Integer> usercount = new HashMap<>();
        Map<String, Double> output = new HashMap<>();

        for(String user: usercities.keySet())
        {
            for(String city: usercities.get(user))
            {
                List users;
                if(cityUsers.get(city)!=null)
                {
                    users= cityUsers.get(city);
                }
                else
                {
                    users = new ArrayList<String>();
                }
                users.add(user);
                cityUsers.put(city, users);
            }
        }

        System.out.println(usercities);

        for(String user: usercities.keySet())
        {
            usercount.put(user,0);
        }

        //scan on basis of source user cities and increse others users matching city count
        List<String> sourceList = usercities.get(sourceUser);
        for(String city: sourceList)
        {
            for(String user: cityUsers.get(city))
            {
                usercount.put(user,usercount.get(user)+1);
            }
        }

        System.out.println("Matching-city count per user");
        System.out.println(usercount);

        //scan and filter the buddies based on match Limit
        for(String user: usercount.keySet())
        {
            double matchPercent = (double)(usercount.get(user))/(double)(usercities.get(user).size());
            if(matchPercent>=matchLimit && !user.equals(sourceUser))
            {
                output.put(user,matchPercent);
            }
        }

        //System.out.println(output);

        Map<String, Double> sorted = new HashMap<>();

        output.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));


        System.out.println(sorted);

    }

    public static void main(String args[])
    {
        double matchLimit = 0.5;

        Map<String, List<String>> userCities = new HashMap<>();

        String user = "puma";
        List<String> cities = Arrays.asList( "sanfran", "newyork", "dallas");
        userCities.put(user,cities);

        user = "adidas";
        cities = Arrays.asList( "sanfran", "phoenix", "dallas");
        userCities.put(user,cities);

        user = "reebok";
        cities = Arrays.asList( "newyork", "saltlake","washington", "sanfran");
        userCities.put(user,cities);

        findTravelBuddies(userCities,"puma",matchLimit);
    }
}
