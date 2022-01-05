package quick.start.exception;

/**
 * 查询异常
 * @author yuanweiquan
 */
public class QueryException extends RuntimeException {

     public QueryException(String message) {
          super(message);
     }

     public QueryException(Throwable cause) {
          super(cause);
     }
}
