package parsers.json;

public class JsonNode {
  public Object object;

  public JsonNode(Object object) {
    this.object = object;
  }

  public void set(Object object) {
    this.object = object;
  }

  public boolean isNull() {
    return (object == null);
  }

  public boolean isString() {
    if (object == null) {
      return false;
    }
    return (object instanceof String);
  }

  public boolean isLong() {
    if (object == null) {
      return false;
    }
    if (object instanceof Long) {
      return true;
    }
    return false;
  }

  public boolean isDouble() {
    if (object == null) {
      return false;
    }
    if (object instanceof Double) {
      return true;
    }
    return false;
  }

  public boolean isBoolean() {
    if (object == null) {
      return false;
    }
    return (object instanceof Boolean);
  }

  public boolean isJsonObject() {
    return false;
  }

  public boolean isJsonArray() {
    return false;
  }

  public String asString() {
    if (object == null) {
      return "null";
    }
    if (object instanceof JsonObject) {
      StringBuffer buffer = new StringBuffer();
      ((JsonObject) object).toStringBuffer(buffer);
      return buffer.toString();
    }
    if (object instanceof JsonArray) {
      StringBuffer buffer = new StringBuffer();
      ((JsonArray) object).toStringBuffer(buffer);
      return buffer.toString();
    }
    if (object instanceof String) {
      return (String) object;
    }
    if (object instanceof Long) {
      return ((Long) object).toString();
    }
    if (object instanceof Double) {
      return ((Double) object).toString();
    }
    if (object instanceof Boolean) {
      return ((Boolean) object).toString();
    }
    return null;
  }

  public Long asLong() {
    if (object instanceof Long) {
      return (Long) object;
    }
    return null;
  }

  public Double asDouble() {
    if (object instanceof Double) {
      return (Double) object;
    }
    return null;
  }

  public Boolean asBoolean() {
    if (object instanceof Boolean) {
      return (Boolean) object;
    }
    return null;
  }

  public JsonObject asJsonObject() {
    return null;
  }

  public JsonArray asJsonArray() {
    return null;
  }

  public void toStringBuffer(StringBuffer buffer) {
    if (object == null) {
      buffer.append("null");
    }
    if (object instanceof JsonObject) {
      ((JsonObject) object).toStringBuffer(buffer);
      return;
    }
    if (object instanceof JsonArray) {
      ((JsonArray) object).toStringBuffer(buffer);
      return;
    }
    if (object instanceof String) {
      buffer.append("\"" + object + "\"");
      return;
    }
    if (object instanceof Long) {
      buffer.append(((Long) object).toString());
      return;
    }
    if (object instanceof Double) {
      buffer.append(((Double) object).toString());
      return;
    }
    if (object instanceof Boolean) {
      if ((Boolean) object) {
        buffer.append("true");
      } else {
        buffer.append("false");
      }
    }
  }
}
