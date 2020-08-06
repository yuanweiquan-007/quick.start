package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class ValidatorBaseType {

     protected Validator validator;

     protected ValidatorBaseType(Validator validator) {
          this.validator = validator;
     }

     public ValidatorBaseType required() {
          this.validator.currentValidateElement.setRequired(true);
          return this;
     }

     public ValidatorBaseType sometimes() {
          this.validator.currentValidateElement.setRequired(false);
          return this;
     }

     public ValidatorBaseType bytes() {
          return addValidateType(ValidateType.BYTE);
     }

     public ValidatorBaseType bigInteger() {
          return addValidateType(ValidateType.BIGINTEGER);
     }

     public ValidatorBaseType bool() {
          return addValidateType(ValidateType.BOOLEAN);
     }

     public ValidatorBaseType collection() {
          return addValidateType(ValidateType.COLLECTION);
     }

     public ValidatorBaseType date() {
          return addValidateType(ValidateType.DATE);
     }

     public ValidatorBaseType doubles() {
          return addValidateType(ValidateType.DOUBLE);
     }

     public ValidatorBaseType email() {
          return addValidateType(ValidateType.EMAIL);
     }

     public ValidatorBaseType empty() {
          return addValidateType(ValidateType.EMPTY);
     }

     public ValidatorBaseType floats() {
          return addValidateType(ValidateType.FLOAT);
     }

     public ValidatorBaseType integer() {
          return addValidateType(ValidateType.INTEGER);
     }

     public ValidatorBaseType ip() {
          return addValidateType(ValidateType.IP);
     }

     public ValidatorBaseType longs() {
          return addValidateType(ValidateType.LONG);
     }

     public ValidatorBaseType map() {
          return addValidateType(ValidateType.MAP);
     }

     public ValidatorBaseType mobile() {
          return addValidateType(ValidateType.MOBILE);
     }

     public ValidatorBaseType notEmpty() {
          return addValidateType(ValidateType.NOT_EMPTY);
     }

     public ValidatorBaseType number() {
          return addValidateType(ValidateType.NUMBER);
     }

     public ValidatorBaseType phone() {
          return addValidateType(ValidateType.PHONE);
     }

     public ValidatorBaseType shorts() {
          return addValidateType(ValidateType.SHORT);
     }

     public ValidatorBaseType string() {
          return addValidateType(ValidateType.STRING);
     }

     public ValidatorBaseType url() {
          return addValidateType(ValidateType.URL);
     }

     public ValidatorList list() {
          addValidateType(ValidateType.COLLECTION);
          return new ValidatorList(validator);
     }

     private ValidatorBaseType addValidateType(ValidateType type) {
          this.validator.currentValidateElement.addValidateType(type);
          return this;
     }

     public ValidatorBaseType set(String key) {
          return validator.set(key);
     }

     public ValidatorBaseType set(String key, Object value) {
          return validator.set(key, value);
     }

     public Validator end() {
          this.validator.addValidateElementIfNecessary();
          return this.validator;
     }
}
