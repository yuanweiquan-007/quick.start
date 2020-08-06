package quick.start;

/**
 * @author yuanweiquan
 */
public interface Support<T> {

     /**
      * 是否支持
      *
      * @param type
      * @return
      */
     boolean isSupported(T type);

}
