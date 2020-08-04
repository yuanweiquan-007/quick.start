package quick.start.validator;

import java.math.BigInteger;

public class BigIntegerValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, BigInteger.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.BIGINTEGER.equals(type);
     }
}
