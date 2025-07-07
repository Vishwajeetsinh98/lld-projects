package character;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

    private static final Map<String, CharacterType> ourCharacterTypes = new HashMap<>();

    public static CharacterType getCharacterType(String fontName, int fontSize, String fontColor) {

        String key = getKey(fontName, fontSize, fontColor);
        if (!ourCharacterTypes.containsKey(key))
            ourCharacterTypes.put(key,
                                            new CharacterType(fontName, fontSize, fontColor));
        return ourCharacterTypes.get(key);
    }

    private static String getKey(String fontName, int fontSize, String fontColor) {
        return fontName + "-" + fontSize + "-" + fontColor;
    }

    public static int getTotalCharacterTypes() {
        return ourCharacterTypes.size();
    }

}