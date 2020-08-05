package quick.start;

import org.junit.Test;
import quick.start.validator.Validator;

public class TestValidation {

     @Test
     public void test() throws Exception {
          String xml = "<response>\n" +
                  "  <code>200</code>\n" +
                  "  <flag>success</flag>\n" +
                  "  <message>订单成功!</message>\n" +
                  "  <orders>\n" +
                  "    <order>\n" +
                  "      <orderCode></orderCode>\n" +
                  "      <createTime>2020-07-22 17:47:41</createTime>\n" +
                  "    </order>\n" +
                  "    <order>\n" +
                  "      <orderCode>87654321</orderCode>\n" +
                  "      <createTime>2020-07-22 17:48:13</createTime>\n" +
                  "    </order>\n" +
                  "  </orders>\n" +
                  "</response>";

          Validator validator = Validator.ofXml(xml)
                  .set("version", "1.0").required().integer()
                  .set("payTime", "2016-11-11").date()
                  .set("remark", "").string().notEmpty()
                  .set("money", "545T").doubles()
                  .set("email", "777@qq").email()
                  .set("id", "1").empty()
                  .set("host", "1111").ip()
                  .set("mobile", "18141448961").mobile()
                  .set("phone", "0792-1548562").phone()
                  .set("/response/code").number().notEmpty()
                  .set("/response/message").string().notEmpty()
                  .set("/response/flag").string().notEmpty()
                  .set("/response/orders/order").list().forEach(childVal -> {
                       childVal.set("orderCode").string().notEmpty()
                               .set("createTime").date().required()
                               .end();
                  })
                  .end();

          if (!validator.isValidate()) {
               for (String errorMessage : validator.getErrorMessage()) {
                    System.out.println(errorMessage);
               }
          } else {
               System.out.println("验证成功");
          }
     }

}
