package quick.start.entity;

import lombok.Data;
import quick.start.annotation.Generated;
import quick.start.annotation.PrimaryKey;
import quick.start.annotation.Table;

@Data
@Table("Orders")
public class Order implements Entity {
     @Generated
     private Integer orderId;
     @PrimaryKey
     private String orderCode;
     private String remark;

}
