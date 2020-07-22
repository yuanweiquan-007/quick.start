package quick.start.entity;

import lombok.Data;
import quick.start.annotation.Generated;
import quick.start.annotation.PrimaryKey;
import quick.start.annotation.Table;

import java.util.Date;

@Data
@Table("Orders")
public class Order implements Entity {
     @Generated
     private Integer orderId;
     @PrimaryKey
     private String orderCode;
     private Date createTime;
     private Integer status;
     private String remark;
}
