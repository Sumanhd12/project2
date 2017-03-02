
package com.aem.tags;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Function library to handle JSON in JSPs.
 */
public final class JsonArrayParserTag {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(JsonArrayParserTag.class);

    private JsonArrayParserTag() {
    }

  
    public static Map<Integer, Map<String, String>> parseJson(final String json) throws JspException {
        LOG.debug("Input JSON: " + json);
        final Map<Integer, Map<String, String>> map = new HashMap<Integer, Map<String, String>>();

        // Deal with empty strings being passed in.
        if (json.length() == 0) {
            LOG.debug("Empty string given, return empty map");
            return map;
        }

        try {
            // Loop through all the keys within the JSON, putting them in our map.
            final JSONArray jsonArray = new JSONArray(json);
            int i = 0;
            while (i < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(i);
                Map<String, String> values = new HashMap<String, String>();
                final Iterator<String> keys = object.keys();
                String key;
                String value;
                while (keys.hasNext()) {
                    key = (String) keys.next();
                    value = object.get(key).toString();
                    values.put(key, value);
                }
                map.put(i, values);
                i++;
            }
        } catch (JSONException jsonException) {
            LOG.error("Problem whilst parsing JSON: " + json, jsonException);
            throw new JspException("Problem whilst parsing the JSON", jsonException);
        }
        LOG.debug("Output map: " + map);

        return map;
    }
}