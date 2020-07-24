package quick.start.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

     private final static ObjectMapper MAPPER = new ObjectMapper()
             .setVisibility(PropertyAccessor.FIELD, Visibility.ANY).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

     public static String fromMap(Map<String, Object> data) {
          return from(data);
     }

     public static String from(Object data) {
          try {
               return MAPPER.writeValueAsString(data);
          } catch (JsonProcessingException e) {
               return null;
          }
     }

     public static Map<String, Object> toMap(String text) throws IOException {
          return to(text, new TypeReference<Map<String, Object>>() {
          });
     }

     public static List<Map<String, Object>> toMapList(String text) throws IOException {
          return to(text, new TypeReference<List<Map<String, Object>>>() {
          });
     }

     public static Map<String, Map<String, Object>> toMapKeyed(String text) throws IOException {
          return to(text, new TypeReference<Map<String, Map<String, Object>>>() {
          });
     }

     public static Map<String, List<Map<String, Object>>> toMapGrouped(String text) throws IOException {
          return to(text, new TypeReference<Map<String, List<Map<String, Object>>>>() {
          });
     }

     public static <E> E to(String text, TypeReference<E> reference) throws IOException {
          return MAPPER.readValue(text, reference);
     }

     public static <E> E to(String text, Class<E> clazz) {
          try {
               return MAPPER.readValue(text, clazz);
          } catch (IOException e) {
               return null;
          }
     }

     public static boolean is(String text) {
          try {
               return MAPPER.readTree(text) != null;
          } catch (IOException e) {
               return false;
          }
     }

}
