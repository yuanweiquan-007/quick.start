package quick.start;

/**
 * @author yuanweiquan
 */
public interface Support<T> {

     /**
      * 是否支持
      *
      * @param type 类型
      * @return 是否支持
      */
     boolean isSupported(T type);

}
