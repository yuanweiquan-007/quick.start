package quick.start.exception;

/**
 * 唯一约束异常
 * @author yuanweiquan
 */
public class UniqueException extends RuntimeException {

     public UniqueException(String message) {
          super(message);
     }

}
