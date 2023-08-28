package parsers.json;

import java.util.ArrayList;

public class JsonArray extends JsonNode {

  public JsonArray() {
    super(new ArrayList<JsonNode>());
  }

  public boolean isJsonArray() {
    return true;
  }

  public JsonArray asJsonArray() {
    return this;
  }

  public int size() {
    return ((ArrayList<JsonNode>) object).size();
  }

  public JsonNode append(JsonNode node) {
    ((ArrayList<JsonNode>) object).add(node);
    return node;
  }

  public JsonNode insert(int index, JsonNode node) {
    ((ArrayList<JsonNode>) object).add(index, node);
    return node;
  }

  public JsonNode set(int index, JsonNode node) {
    try {
      ((ArrayList<JsonNode>) object).set(index, node);
      return node;
    } catch(Exception exception) {
      return null;
    }
  }

  public JsonNode get(int index) {
    try {
      return ((ArrayList<JsonNode>) object).get(index);
    } catch(Exception exception) {
      return null;
    }
  }

  public JsonNode remove(int index) {
    try {
      return ((ArrayList<JsonNode>) object).remove(index);
    } catch(Exception exception) {
      return null;
    }
  }

  public void clear() {
    try {
      ((ArrayList<JsonNode>) object).clear();
    } catch(Exception exception) {
    }
  }

  public String asString() {
    StringBuffer buffer = new StringBuffer();
    toStringBuffer(buffer);
    return buffer.toString();
  }

  public void toStringBuffer(StringBuffer buffer) {
    buffer.append("[");
    for (int i = 0; i < size(); i++) {
      if (i++ > 0) {
        buffer.append(",");
      }
      get(i).toStringBuffer(buffer);
    }
    buffer.append("]");
  }
}
