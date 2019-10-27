package objects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Status {
    GREEN("green"),
    YELLOW("yellow"),
    RED("red");

    private String name;
    private static Map<String, Status> statusMap;

    Status(String name){
        this.name = name;
    }

    public static Status fromString(String key){
        return statusMap.get(key);
    }

    static{
        Map<String, Status> map = new HashMap<>();

        for(Status status : Status.values()){
            map.put(status.name, status);
        }

        statusMap = Collections.unmodifiableMap(map);
    }
}
