package quick.start.util;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.util.ObjectUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XmlUtils {

     public static String fromMap(Map<String, Object> data, String root, String encoding) {
          return fromMap(data, root, encoding, null);
     }

     public static String fromMap(Map<String, Object> data, String root, String encoding, Consumer<Document> fixer) {
          Document document = DocumentHelper.createDocument();
          Element element = document.addElement(root);
          parseMap(data).forEach(element::add);
          if (fixer != null) {
               fixer.accept(document);
          }

          OutputFormat format = OutputFormat.createPrettyPrint();
          format.setEncoding(encoding);

          StringWriter writer = new StringWriter();
          XMLWriter xmlWriter = new XMLWriter(writer, format);
          try {
               xmlWriter.write(document);
               xmlWriter.flush();
               xmlWriter.close();
          } catch (IOException e) {
               throw new IllegalStateException("XML uncreatable", e);
          }
          return writer.toString();
     }

     public static Map<String, Object> toMap(String text) throws DocumentException {
          Document document = DocumentHelper.parseText(text);
          Element root = document.getRootElement();
          Map<String, Object> data = (Map<String, Object>) parseElement(root);
          Map<String, Object> result = new HashMap<>();
          if (data != null) {
               result.put(root.getName(), data);
          }
          return result;
     }

     public static String fromObject(Object object, String encoding) throws JAXBException {
          JAXBContext context = JAXBContext.newInstance(object.getClass());
          Marshaller marshaller = context.createMarshaller();
          marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
          marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
          StringWriter writer = new StringWriter();
          marshaller.marshal(object, writer);
          return writer.toString();
     }

     public static <T> T toObject(String text, Class<T> clazz) throws JAXBException {
          JAXBContext context = JAXBContext.newInstance(clazz);
          Unmarshaller unmarshaller = context.createUnmarshaller();
          return (T) unmarshaller.unmarshal(new StringReader(text));
     }

     private static List<Element> parseMap(Map<String, Object> map) {
          if (ObjectUtils.isEmpty(map)) {
               return Collections.emptyList();
          }

          return map.entrySet().stream()
                  .flatMap(entry -> {
                       String key = entry.getKey();
                       Object value = entry.getValue();

                       if (value instanceof Map) {
                            List<Element> tmp = parseMap((Map<String, Object>) value);
                            if (tmp.isEmpty()) {
                                 return Stream.empty();
                            }
                            Element element = DocumentHelper.createElement(key);
                            element.setContent(tmp);
                            return Stream.of(element);
                       }

                       if (value instanceof Collection) {
                            return ((Collection<Map<String, Object>>) value).stream().map(item -> {
                                 Element x = DocumentHelper.createElement(key);
                                 x.setContent(parseMap(item));
                                 return x;
                            });
                       }

                       Element element = DocumentHelper.createElement(key);
                       element.setText(String.valueOf(value));
                       return Stream.of(element);
                  })
                  .collect(Collectors.toList());
     }

     private static Object parseElement(Element root) {
          List<Element> elements = root.elements();
          int size = elements.size();
          if (size == 0) {
               return root.getText();
          }

          Map<String, Object> result = new HashMap<>();
          if (size == 1) {
               Element element = elements.get(0);
               result.put(element.getName(), parseElement(element));
          } else {
               Map<String, Element> tmp = new HashMap<>();
               elements.forEach(x -> tmp.put(x.getName(), x));
               tmp.forEach((name, element) -> {
                    Namespace namespace = element.getNamespace();
                    List<Element> children = root.elements(new QName(name, namespace));
                    if (children.size() > 1) {
                         result.put(name, children.stream().map(XmlUtils::parseElement).collect(Collectors.toList()));
                    } else {
                         result.put(name, parseElement(children.get(0)));
                    }
               });
          }
          return result;
     }

}
