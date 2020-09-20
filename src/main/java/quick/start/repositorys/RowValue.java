package quick.start.repositorys;

import lombok.Getter;

/**
 * update的时候，如果需要使用表达式时使用
 *
 * @author yuanweiquan
 */
@Getter
public class RowValue {

    private String rowValue;

    private RowValue(String rowValue) {
        this.rowValue = rowValue;
    }

    public static final RowValue of(String rowValue) {
        return new RowValue(rowValue);
    }

}
