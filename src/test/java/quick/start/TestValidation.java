package quick.start;

import org.junit.Test;
import quick.start.validator.Validator;

public class TestValidation {

     @Test
     public void test() {
          Validator validator = Validator.of()
                  .set("version", "1.0").required().integer()
                  .set("payTime", "2016-11-11")
                  .set("remark", "").string().notEmpty()
                  .set("money", "545T").doubles()
                  .set("email", "777@qq").email()
                  .set("id", "1").empty()
                  .set("host", "1111").ip()
                  .set("mobile", "18141448961").mobile()
                  .set("phone", "0792-1548562").phone()
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
