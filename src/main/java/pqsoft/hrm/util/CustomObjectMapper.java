package pqsoft.hrm.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {
  private static final long serialVersionUID = -313839197258513284L;

  public CustomObjectMapper() {
    super.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
}
