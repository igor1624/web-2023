package parsers.json;

public class JsonParser {
  private static boolean debug = false;
  private static int inputIndex;

  public static JsonNode parse(String input) {
    inputIndex = 0;
    return parseJsonNode(input);
  }

  private static JsonNode parseJsonNode(String input) {
    skipWhitespaces(input);
    JsonNode node = parseJsonValue(input);
    skipWhitespaces(input);
    return node;
  }

  private static JsonNode parseJsonValue(String input) {
    JsonNode node = parseJsonObject(input);
    if (node != null) {
      return node;
    }
    node = parseJsonArray(input);
    if (node != null) {
      return node;
    }
    String s = parseString(input);
    if (s != null) {
      return new JsonNode(s);
    }
    Object o = parseNumber(input);
    if (o != null) {
      return new JsonNode(o);
    }
    String name = parseName(input);
    assert name != null;
    if (name.equals("true")) {
      return new JsonNode(Boolean.TRUE);
    }
    if (name.equals("false")) {
      return new JsonNode(Boolean.FALSE);
    }
    if (name.equals("null")) {
      return new JsonNode(null);
    }
    return null;
  }

  private static JsonObject parseJsonObject(String input) {
    if (inputIndex >= input.length()) {
      return null;
    }
    if (input.charAt(inputIndex) != '{') {
      return null;
    }
    // skip {
    inputIndex++;
    JsonObject object = new JsonObject();
    skipWhitespaces(input);
    if (input.charAt(inputIndex) == '}') {
      inputIndex++;
      return object;
    }
    // parse members
    while (true) {
      parseJsonObjectMember(input, object);
      if (inputIndex >= input.length()) {
        // error
        break;
      }
      if (input.charAt(inputIndex) == ',') {
        inputIndex++;
        continue;
      }
      break;
    }
    skipWhitespaces(input);
    if (input.charAt(inputIndex) == '}') {
      inputIndex++;
    }
    return object;
  }

  private static void parseJsonObjectMember(String input, JsonObject object) {
    skipWhitespaces(input);
    String name = parseString(input);
    if (debug) {
      System.out.println(name);
    }
    skipWhitespaces(input);
    if (input.charAt(inputIndex) == ':') {
      inputIndex++;
      JsonNode node = parseJsonNode(input);
      object.set(name, node);
    }
  }

  private static JsonArray parseJsonArray(String input) {
    if (inputIndex >= input.length()) {
      return null;
    }
    if (input.charAt(inputIndex) != '[') {
      return null;
    }
    inputIndex++;
    JsonArray array = new JsonArray();
    skipWhitespaces(input);
    while (inputIndex < input.length()) {
      if (input.charAt(inputIndex) == ']') {
        break;
      }
      JsonNode node = parseJsonNode(input);
      array.append(node);
      if (input.charAt(inputIndex) != ',') {
        break;
      }
      inputIndex++;
    }
    inputIndex++;
    return array;
  }

  private static String parseString(String input) {
    if (inputIndex >= input.length()) {
      return null;
    }
    if (input.charAt(inputIndex) != '"') {
      return null;
    }
    inputIndex++;
    while (true) {
      int i = input.indexOf('"', inputIndex);
      if (i < 0) {
        input = input.substring(inputIndex);
        inputIndex = input.length();
        return input;
      }
      if (input.charAt(i - 1) == '\\') {
        inputIndex = i + 1;
        continue;
      }
      input = input.substring(inputIndex, i);
      inputIndex = i + 1;
      return input;
    }
  }

  private static Object parseNumber(String input) {
    // TODO
    if (inputIndex >= input.length()) {
      return null;
    }
    char c = input.charAt(inputIndex);
    if ((c >= '0') && (c <= '9')) {
    } else if (c == '-') {
    } else {
      return null;
    }
    String s = "" + c;
    inputIndex++;
    while (inputIndex < input.length()) {
      c = input.charAt(inputIndex);
      if ((c >= '0') && (c <= '9')) {
      } else if (c == '.') {
      } else {
        break;
      }
      s += c;
      inputIndex++;
    }

    try {
      return Long.parseLong(s);
    } catch (Exception exception) {

    }

    try {
      return Double.parseDouble(s);
    } catch (Exception exception) {
    }

    return null;
  }

  private static String parseName(String input) {
    if (inputIndex >= input.length()) {
      return null;
    }
    char c = input.charAt(inputIndex);
    if ((c >= 'A') && (c <= 'Z')) {
    } else if ((c >= 'a') && (c <= 'z')) {
    } else if (c == '_') {
    } else {
      return null;
    }
    String s = "";
    while (inputIndex < input.length()) {
      c = input.charAt(inputIndex);
      if ((c >= 'A') && (c <= 'Z')) {
      } else if ((c >= 'a') && (c <= 'z')) {
      } else if (c == '_') {
      } else {
        break;
      }
      s += c;
      inputIndex++;
    }
    return s;
  }

  private static void skipWhitespaces(String input) {
    while (inputIndex < input.length()) {
      switch (input.charAt(inputIndex)) {
        case (char) 9:
        case (char) 10:
        case (char) 13:
        case (char) 32:
          inputIndex++;
          continue;
      }
      break;
    }
  }

  public static String asString(JsonObject object) {
    StringBuffer buffer = new StringBuffer();
    buffer.append("{");
    toStringBuffer(buffer, object, 0);
    buffer.append("\n}\n");
    return buffer.toString();
  }

  public static String asString(JsonArray array) {
    StringBuffer buffer = new StringBuffer();
    buffer.append("[");
    toStringBuffer(buffer, array, 0);
    buffer.append("\n]\n");
    return buffer.toString();
  }

  public static void toStringBuffer(StringBuffer buffer, JsonObject object, int level) {
    String[] keys = object.getKeys();
    for (int i0 = 0; i0 < keys.length; i0++) {
      JsonNode node = object.get(keys[i0]);
      lineToStringBuffer(buffer, level);
      buffer.append("\"" + keys[i0] + "\": ");

      toStringBuffer(buffer, node, level);
      if (i0 < keys.length - 1) {
        buffer.append(",");
      }
    }
  }

  public static void toStringBuffer(StringBuffer buffer, JsonArray array, int level) {
    for (int i0 = 0; i0 < array.size(); i0++) {
      lineToStringBuffer(buffer, level);
      JsonNode node = array.get(i0);
      toStringBuffer(buffer, node, level + 1);
      if (i0 < array.size() - 1) {
        buffer.append(",");
      }
    }
  }

  public static void toStringBuffer(StringBuffer buffer, JsonNode node, int level) {
    if (node.isNull()) {
      buffer.append("null");
      return;
    }
    if (node.isJsonObject()) {
      buffer.append("{");
      toStringBuffer(buffer, node.asJsonObject(), level + 1);
      lineToStringBuffer(buffer, level);
      buffer.append("}");
      return;
    }
    if (node.isJsonArray()) {
      buffer.append("[");
      if (node.asJsonArray().size() > 0) {
        toStringBuffer(buffer, node.asJsonArray(), level + 1);
        lineToStringBuffer(buffer, level);
      }
      buffer.append("]");
      return;
    }
    if (node.object instanceof String) {
      buffer.append('"' + node.asString() + '"');
    } else {
      buffer.append(node.asString());
    }
  }

  public static void lineToStringBuffer(StringBuffer buffer, int level) {
    buffer.append("\n");
    for (int i1 = 0; i1 < level; i1++) {
      buffer.append(" ");
    }
  }
}
