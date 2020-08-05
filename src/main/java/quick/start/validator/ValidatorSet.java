package quick.start.validator;

public class ValidatorSet {

     private Validator validator;

     protected ValidatorSet(Validator validator) {
          this.validator = validator;
     }

     public ValidatorSet required() {
          this.validator.currentValidateElement.setRequired(true);
          return this;
     }

     public ValidatorSet sometimes() {
          this.validator.currentValidateElement.setRequired(false);
          return this;
     }

     public ValidatorSet bytes() {
          return addValidateType(ValidateType.BYTE);
     }

     public ValidatorSet bigInteger() {
          return addValidateType(ValidateType.BIGINTEGER);
     }

     public ValidatorSet bool() {
          return addValidateType(ValidateType.BOOLEAN);
     }

     public ValidatorSet collection() {
          return addValidateType(ValidateType.COLLECTION);
     }

     public ValidatorSet date() {
          return addValidateType(ValidateType.DATE);
     }

     public ValidatorSet doubles() {
          return addValidateType(ValidateType.DOUBLE);
     }

     public ValidatorSet email() {
          return addValidateType(ValidateType.EMAIL);
     }

     public ValidatorSet empty() {
          return addValidateType(ValidateType.EMPTY);
     }

     public ValidatorSet floats() {
          return addValidateType(ValidateType.FLOAT);
     }

     public ValidatorSet integer() {
          return addValidateType(ValidateType.INTEGER);
     }

     public ValidatorSet ip() {
          return addValidateType(ValidateType.IP);
     }

     public ValidatorSet longs() {
          return addValidateType(ValidateType.LONG);
     }

     public ValidatorSet map() {
          return addValidateType(ValidateType.MAP);
     }

     public ValidatorSet mobile() {
          return addValidateType(ValidateType.MOBILE);
     }

     public ValidatorSet notEmpty() {
          return addValidateType(ValidateType.NOT_EMPTY);
     }

     public ValidatorSet number() {
          return addValidateType(ValidateType.NUMBER);
     }

     public ValidatorSet phone() {
          return addValidateType(ValidateType.PHONE);
     }

     public ValidatorSet shorts() {
          return addValidateType(ValidateType.SHORT);
     }

     public ValidatorSet string() {
          return addValidateType(ValidateType.STRING);
     }

     public ValidatorSet url() {
          return addValidateType(ValidateType.URL);
     }

     private ValidatorSet addValidateType(ValidateType type) {
          this.validator.currentValidateElement.addValidateType(type);
          return this;
     }

     public ValidatorSet set(String key) {
          return validator.set(key);
     }

     public ValidatorSet set(String key, Object value) {
          return validator.set(key, value);
     }

     public Validator end() {
          this.validator.addValidateElementIfNecessary();
          return this.validator;
     }
}
