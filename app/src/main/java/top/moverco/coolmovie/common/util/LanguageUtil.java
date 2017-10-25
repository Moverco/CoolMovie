package top.moverco.coolmovie.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Moverco.
 * <p>
 * ISO 639-1 https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 * Chinese:zh  English:en French:fr German:de
 * ISO 3166-1 https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
 */

public class LanguageUtil {
    public static final String Chinese = "Chinese";
    public static final String English = "English";
    public static final String French = "French";
    public static final String German = "German";


    private Map<String, String> languageMap = new HashMap<>();

    {
        languageMap.put(Chinese,languageCode.Chinese_code);
        languageMap.put(English,languageCode.English_code);
        languageMap.put(French,languageCode.French_code);
        languageMap.put(German,languageCode.German_code);

    }

    public String getLanguageCode(String string) {
        return languageMap.get(string);
    }

    class languageCode{
        private final static String Chinese_code = "zh";
        private final static String English_code = "en";
        private final static String French_code = "fr";
        private final static String German_code = "de";
    }
}
