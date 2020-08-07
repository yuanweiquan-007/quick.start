package quick.start.collection;

/**
 * 不可变三值元组
 *
 * @author yuanweiquan
 */
public class Triple<L, M, R> {

     private static final long serialVersionUID = 1L;

     /**
      * 左值
      */
     public final L left;

     /**
      * 中值
      */
     public final M middle;

     /**
      * 右值
      */
     public final R right;

     /**
      * 构造
      *
      * @param left   左值
      * @param middle 中值
      * @param right  右值
      */
     private Triple(final L left, final M middle, final R right) {
          this.left = left;
          this.middle = middle;
          this.right = right;
     }

     /**
      * 静态构造
      *
      * @param left   左值
      * @param middle 中值
      * @param right  右值
      */
     public static <L, M, R> Triple<L, M, R> of(final L left, final M middle, final R right) {
          return new Triple<>(left, middle, right);
     }

     public L getLeft() {
          return left;
     }

     public M getMiddle() {
          return middle;
     }

     public R getRight() {
          return right;
     }

}
