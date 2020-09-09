package quick.start.validator;

/**
 * @author yuanweiquan
 */
public interface Validatable {

     /**
      * 验证
      *
      * @param value 值
      * @return 是否验证成功
      */
     boolean validate(Object value);

}
