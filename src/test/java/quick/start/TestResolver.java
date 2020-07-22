package quick.start;

import org.junit.Test;
import quick.start.util.Resolver;

import java.util.List;
import java.util.Map;

public class TestResolver {

     @Test
     public void resolverJson() throws Exception {
          String json = "{\n" +
                  "  \"name\":\"quick.start\",\n" +
                  "  \"version\":\"1.0\",\n" +
                  "  \"details\":[\n" +
                  "    {\n" +
                  "      \"key\":\"key1\",\n" +
                  "      \"value\":\"value1\"\n" +
                  "    },\n" +
                  "    {\n" +
                  "      \"key\":\"ke2\",\n" +
                  "      \"value\":\"value2\"\n" +
                  "    }\n" +
                  "  ]\n" +
                  "}";
          Resolver resolver = Resolver.ofJson(json);
          //解析字符串
          String name = resolver.get("name");
          System.out.println(name);

          //解析数组
          List<Map<String, Object>> list = resolver.getList("/details");
          list.forEach(x -> {
               System.out.println(x);
          });
     }

     @Test
     public void resolverXml() throws Exception {
          String xml = "<response>\n" +
                  "  <code>200</code>\n" +
                  "  <flag>success</flag>\n" +
                  "  <message>订单成功!</message>\n" +
                  "  <orders>\n" +
                  "    <order>\n" +
                  "      <orderCode>12345678</orderCode>\n" +
                  "      <createTime>2020-07-22 17:47:46</createTime>\n" +
                  "    </order>\n" +
                  "    <order>\n" +
                  "      <orderCode>87654321</orderCode>\n" +
                  "      <createTime>2020-07-22 17:48:13</createTime>\n" +
                  "    </order>\n" +
                  "  </orders>\n" +
                  "</response>";
          Resolver resolver = Resolver.ofXml(xml);
          //解析字符串
          String code = resolver.get("/response/code");
          System.out.println(code);

          //解析数组
          List<Map<String, Object>> list = resolver.getList("/response/orders/order");
          list.forEach(x -> {
               System.out.println(x);
          });
     }

}
