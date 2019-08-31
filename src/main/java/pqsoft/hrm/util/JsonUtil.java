package pqsoft.hrm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {
  private static final ObjectMapper mapper = new CustomObjectMapper();

  @SuppressWarnings("unchecked")
  public static Map<String, Object> parse(String jsonString) {
    return parse(jsonString, Map.class);
  }

  public static <T> T parse(String jsonString, Class<T> clazz) {
    try {
      return mapper.readValue(jsonString, clazz);
    } catch (IOException e) {
      log.info(e.getMessage(), e.getCause());
      return null;
    }
  }

  public static <T> List<T> parseAsList(String jsonString, Class<T> clazz) {
    try {
      return mapper.readValue(
          jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (Exception e) {
      log.info(e.getMessage(), e.getCause());
      return Collections.emptyList();
    }
  }

  public static <T> Map<String, T> parseAsMapStringObject(String jsonString, Class<T> clazz) {
    try {
      return mapper.readValue(
          jsonString, mapper.getTypeFactory().constructMapType(Map.class, String.class, clazz));
    } catch (Exception e) {
      log.info(e.getMessage(), e.getCause());
      return Collections.emptyMap();
    }
  }

  public static <T> Map<String, List<T>> parseAsMapStringList(String jsonString, Class<T> clazz) {
    try {
      return mapper.readValue(jsonString, new TypeReference<Map<String, List<T>>>() {});
    } catch (IOException e) {
      log.info(e.getMessage(), e.getCause());
      return Collections.emptyMap();
    }
  }

  public static String stringify(Object value) {
    try {
      return mapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      log.info(e.getMessage(), e.getCause());
      return "";
    }
  }

  public static String prettyStringify(Object value) {
    try {
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    } catch (Exception e) {
      log.info(e.getMessage(), e.getCause());
      return "";
    }
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Object> transform(Object value) {
    return transform(value, Map.class);
  }

  public static <T> T transform(Object value, Class<T> clazz) {
    try {
      return mapper.convertValue(value, clazz);
    } catch (Exception e) {
      log.info(e.getMessage(), e.getCause());
      return null;
    }
  }

  public static <T> List<T> transformAsList(Object value, Class<T> clazz) {
    try {
      return mapper.convertValue(
          value, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (Exception e) {
      log.info(e.getMessage(), e.getCause());
      return null;
    }
  }

  public static <T> T copy(Object value, Class<T> clazz) {
    try {
      return mapper.readValue(mapper.writeValueAsBytes(value), clazz);
    } catch (Exception e) {
      log.info(e.getMessage(), e.getCause());
      return null;
    }
  }
}
