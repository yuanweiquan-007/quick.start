package quick.start.entity;

import lombok.Data;
import quick.start.annotation.PrimaryKey;
import quick.start.annotation.Table;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@Table("Warehouse")
public class Warehouse implements Entity {
     private Integer warehouseId; // 仓库ID
     @PrimaryKey
     private String warehouseCode; // 仓库编码
     private String warehouseName; // 仓库名称
     private String note; // 备注
     private String telephone; // 联系电话
     private String fax; // 传真
     private String address; // 地址
     private Integer enable; // 启用标记: 0，未启用；1，启用
     private Integer companyId; // 公司ID
     private String companyName; // 公司名称
     private String companyCode; // 公司编码
     private Integer isTrusteeship; // 是否托管仓
     private Integer isPush; // 是否推单
     private Integer isPushCutOff; // 是否截单
     private Integer putCutType; // '截单类型：1，时间段拦截；2，固定时间拦截'
     private Integer putCutTimeType;// 截单时间类型 ： 1,下单时间；2、付款时间；3、配货时间'
     private Date pushCutOffBeginDate; // 截单开始日期时间
     private Date pushCutOffEndDate; // 截单结束时间
     private Integer orderId; // 排序
     private String wmsApiParams; // wms api参数，xml或者json格式
     private Date putCutTimePoint;// 截单时间点
     private String provinceRegionName;// 省
     private String cityRegionName;// 市
     private String countyRegionName;// 区
     private Integer warehouseType;// 仓库类型（1.实体仓库--管实物库存 2.共享仓库--管可用库存 3.独立仓库--不可调拨 4.虚拟仓库）
     private Integer isDistributing;// default 0 comment '是否配货：0，不配货；1，配货',
     private Integer distributedPriority;// 配货优先级
     private Integer speedUpPushing;//'控制截单后，加急订单是否继续推送到WMS。0，不强推；1，强推'
     private Integer deliverAbilityLevel;
     private Integer dWRPartitioning;
     private Integer movingBin;
     private Integer negativeStock;
}
