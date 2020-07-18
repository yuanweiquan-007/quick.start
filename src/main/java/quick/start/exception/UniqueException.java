package quick.start.exception;

/**
 * 唯一约束异常
 */
public class UniqueException extends RuntimeException {

     public UniqueException(String message) {
          super(message);
     }

}
