package quick.start.repositorys.support;

/**
 * @author yuanweiquan
 */
public interface ColumnSupport {

     /**
      * 列
      * 指定字段
      *
      * @param columns 列
      * @return {@link ColumnSupport}
      */
     public ColumnSupport columns(String... columns);

}
