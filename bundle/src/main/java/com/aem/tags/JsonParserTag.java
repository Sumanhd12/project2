package com.aem.tags;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function library to handle JSON in JSPs.
 */
public final class JsonParserTag {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(JsonParserTag.class);

    private JsonParserTag() {
    }

    /**
     * Function to parse JSON from a string representation into a map of key value pairs.
     *
     * @param json The JSON string to parse data from.
     * @return Map<String, String> The mapping of key value pairs parsed from the JSON.
     */
    public static Map<String, String> parseJson(final String json) {
        LOG.debug("Input JSON: " + json);
        final Map<String, String> map = new HashMap<String, String>();

        // Deal with empty strings being passed in.
        if (json.length() == 0) {
            LOG.debug("Empty string given, return empty map");
            return map;
        }

        try {
            // Loop through all the keys within the JSON, putting them in our map.
            final JSONObject jsonObject = new JSONObject(json);
            final Iterator<String> keys = jsonObject.keys();
            String key;
            String value;
            while (keys.hasNext()) {
                key = (String) keys.next();
                value = jsonObject.get(key).toString();
                map.put(key, value);
            }
        } catch (JSONException jsonException) {
            LOG.error("Problem whilst parsing JSON: " + json, jsonException);
        }
        LOG.debug("Output map: " + map);

        return map;
    }
}