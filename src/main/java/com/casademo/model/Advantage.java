package main.java.com.casademo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    Advantage class is used to hold advantage mapping of plantoons.
    Key has advantage on values.
 */
public class Advantage {
    public static final Map<String, Set<String>> advantageMap;

    static {
        advantageMap = new HashMap<>();
        advantageMap.put("Militia", Set.of("Spearmen", "LightCavalry"));
        advantageMap.put("Spearmen", Set.of("LightCavalry", "HeavyCavalry"));
        advantageMap.put("LightCavalry", Set.of("FootArcher", "CavalryArcher"));
        advantageMap.put("HeavyCavalry", Set.of("Militia", "FootArcher", "LightCavalry"));
        advantageMap.put("CavalryArcher", Set.of("Spearmen", "HeavyCavalry"));
        advantageMap.put("FootArcher", Set.of("Militia", "CavalryArcher"));
    }
}
