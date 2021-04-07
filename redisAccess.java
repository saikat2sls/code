import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSetCache;
import org.redisson.api.RSetMultimapCache;
import org.redisson.api.RedissonClient;

public class redisAccess {

    public static void main(String[] args) throws IOException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();


        RList<String> list = redisson.getList("saikatList");
        list.add("data");
        list.add("data2");
        list.add("data3");
        list.addAfter("3","break");
        list.addBefore("data","new");
        //list.expireKey("2", 10, TimeUnit.MINUTES);

        RSetCache<String> setCache = redisson.getSetCache("myCache");

        // with ttl = 20 seconds
        boolean isAdded = setCache.add("1", 120, TimeUnit.SECONDS);
        // store value permanently
        setCache.add("2");

        Boolean isPresent = setCache.contains("1");
        System.out.println(isPresent);

        RBucket<String> bucket = redisson.getBucket("myBucket");
        bucket.set("123");
        boolean isUpdated = bucket.compareAndSet("123", "4934");
        String prevObject = bucket.getAndSet("321");
        boolean isSet = bucket.trySet("901");
        long objectSize = bucket.size();

        // set with expiration
        bucket.set("value", 20, TimeUnit.SECONDS);
        bucket.set("nextValue", 10, TimeUnit.SECONDS);

        redisson.shutdown();
    }

}