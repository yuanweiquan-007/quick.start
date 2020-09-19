package quick.start.collection;

import lombok.Getter;

import java.io.Serializable;

/**
 * 不可变三值元组
 *
 * @author yuanweiquan
 */
@Getter
public class Triple<L, M, R> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 左值
     */
    private final L left;

    /**
     * 中值
     */
    private final M middle;

    /**
     * 右值
     */
    private final R right;

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
     * @param <L>    泛型
     * @param <M>    泛型
     * @param <R>    泛型
     * @param left   左值
     * @param middle 中值
     * @param right  右值
     * @return 返回Triple对象
     */
    public static <L, M, R> Triple<L, M, R> of(final L left, final M middle, final R right) {
        return new Triple<>(left, middle, right);
    }

}
