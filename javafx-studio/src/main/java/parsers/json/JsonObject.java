package parsers.json;

import java.util.HashMap;
import java.util.Set;

public class JsonObject extends JsonNode {

  public JsonObject() {
    super(new HashMap<String, JsonNode>());
  }

  public boolean isJsonObject() {
    return true;
  }

  public JsonObject asJsonObject() {
    return this;
  }

  public String[] getKeys() {
    Set<String> keys = ((HashMap<String, JsonNode>) object).keySet();
    return keys.toArray(new String[0]);
  }

  public void set(String name, JsonNode node) {
    ((HashMap<String, JsonNode>) object).put(name, node);
  }

  public void set(String name, String value) {
    JsonNode node = new JsonNode(value);
    ((HashMap<String, JsonNode>) object).put(name, node);
  }

  public void set(String name, Long value) {
    JsonNode node = new JsonNode(value);
    ((HashMap<String, JsonNode>) object).put(name, node);
  }

  public void set(String name, Double value) {
    JsonNode node = new JsonNode(value);
    ((HashMap<String, JsonNode>) object).put(name, node);
  }

  public void set(String name, Boolean value) {
    JsonNode node = new JsonNode(value);
    ((HashMap<String, JsonNode>) object).put(name, node);
  }

  public JsonNode get(String name) {
    return ((HashMap<String, JsonNode>) object).get(name);
  }

  public String asString() {
    StringBuffer buffer = new StringBuffer();
    toStringBuffer(buffer);
    return buffer.toString();
  }

  public void toStringBuffer(StringBuffer buffer) {
    buffer.append("{");
    Set<String> keys = ((HashMap<String, JsonNode>) object).keySet();
    int i = 0;
    for (String key : keys) {
      if (i++ > 0) {
        buffer.append(",");
      }
      buffer.append("\"").append(key).append("\"").append(":");
      get(key).toStringBuffer(buffer);
    }
    buffer.append("}");
  }
}
