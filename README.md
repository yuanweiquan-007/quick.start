# quick start
> 一个基于Java快速开发框架，支持mysql、xml、json等的基本操作。



### 依赖框架

- Spring 5.1.5.RELEASE
- Mysql 5.1.26
- Dom4j 1.6.1
- Jackson 2.9.9
- Lombok 1.18.10
- Jdk 1.8



### 默认Mysql功能

| 方法                                                         | 说明                                       |
| ------------------------------------------------------------ | ------------------------------------------ |
| boolean has(Serializable id)                                 | 根据主键检查记录是否存在                   |
| E findById(Serializable id)                                  | 根据主键查找对象                           |
| List<E> findByIds(Collection<? extends Serializable> ids)    | 根据主键批量查找对象                       |
| List<E> find()                                               | 查询所有的记录                             |
| List<E> findByColumn(String column, Serializable value)      | 根据指定字段查询记录                       |
| List<E> findByColumn(String column, Collection<? extends Serializable> values) | 根据指定字段查询记录                       |
| List<E> find(Conditions conditions)                          | 根据条件查询，条件的具体用法请看下面的案例 |
| Paginator<E> findByPage(Conditions conditions, Integer pageSize, Integer pageNumber) | 分页查询                                   |
| Integer delete(Serializable id)                              | 根据主键删除                               |
| Integer delete(List<? extends Serializable> ids)             | 根据主键删除                               |
| Integer delete(String column, Collection<? extends Serializable> values) | 根据字段删除                               |
| Integer insert(E entity)                                     | 保存对象                                   |
| Integer insert(List<E> entitys)                              | 批量保存对象                               |
| Integer update(E entity)                                     | 修改对象（根据主键修改）                   |
| Integer update(String id, String key, Object value)          | 修改                                       |
| Integer update(List<? extends Serializable> ids, Map<String, Object> data) | 批量修改                                   |
| Integer update(String id, Map<String, Object> data)          | 修改                                       |
| Integer update(List<? extends Serializable> ids, String key, Object value) | 修改                                       |



### 如何使用

##### 1、添加Maven依赖

```xml
<dependency>
  <groupId>io.github.yuanweiquan-007</groupId>
  <artifactId>quick.start</artifactId>
  <version>1.0</version>
</dependency>
```

##### 2、注入jdbcTemplate

```java
@Bean
public JdbcTemplate jdbcTemplate() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
    return jdbcTemplate;
}
```

也可以通过xml配置文件的方式注入

##### 3、定义对象

```java
@Data
@Table("Orders")//定义表名
public class Order implements Entity {
     @Generated//设置自增属性
     private Integer orderId;
     @PrimaryKey//定义主键
     private String orderCode;
  	 @Column("remarks")//字段和属性不一致时
     private String remark;
}
```

##### 4、定义Repository

```java
@Repository
public class OrderRepository extends JdbcRepository<Order> {
	//nothing to do
}
```

##### 5、增删改查操作

```java
//新增
Order order = new Order();
order.setOrderCode(String.valueOf(System.currentTimeMillis()));
order.setRemark(LocalDateTime.now().toString());
orderRepository.insert(order);

//修改
order.setRemard("remark");
orderRepository.update(order);
  
//查询
orderRepository.findById("1593238076676"));
  
//删除
orderRepository.delete("1593238076676");
```

##### 其他

如果需要执行复杂的sql，可以在Repository中通过内置的**jdbcTemplate**对象来执行对应的sql。

```java
this.jdbcTemplate.query(sql, args);
```

如果要指定数据源，可以重写方法

```java
/**
* 如果要指定JdbcTemplate，可以通过此方法修改
*
* @param jdbcTemplate
*/
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}
```

##### Conditions条件使用

```java
Conditions conditions = new Conditions()
  .equal("orderCode", "1593238616437")//等于
  .lessThenOrEqual("orderId", 13)//小于
  .greaterThen("orderId", 10)//大于
  .desc("orderId")//降序
  .asc("orderCode")//升序
  .limit(10);//limit-分页
orderRepository.find(conditions).forEach(x -> {
  logger.info("{}", x.toString());
});
```



### 解析JSON

```
String json = "{\n" +
        "  \"name\":\"quick.start\",\n" +
        "  \"version\":\"1.0\",\n" +
        "  \"details\":[\n" +
        "    {\n" +
        "      \"key\":\"key1\",\n" +
        "      \"value\":\"value1\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"key\":\"ke2\",\n" +
        "      \"value\":\"value2\"\n" +
        "    }\n" +
        "  ]\n" +
        "}";
Resolver resolver = Resolver.ofJson(json);
//解析字符串
String name = resolver.get("name");
System.out.println(name);

//解析数组
List<Map<String, Object>> list = resolver.getList("/details");
list.forEach(x -> {
     System.out.println(x);
});
```



### 解析XML

```
String xml = "<response>\n" +
        "  <code>200</code>\n" +
        "  <flag>success</flag>\n" +
        "  <message>成功</message>\n" +
        "  <orders>\n" +
        "    <order>\n" +
        "      <orderCode>12345678</orderCode>\n" +
        "      <createTime>2020-07-22 17:47:46</createTime>\n" +
        "    </order>\n" +
        "    <order>\n" +
        "      <orderCode>87654321</orderCode>\n" +
        "      <createTime>2020-07-22 17:48:13</createTime>\n" +
        "    </order>\n" +
        "  </orders>\n" +
        "</response>";
Resolver resolver = Resolver.ofXml(xml);
//解析字符串
String code = resolver.get("/response/code");
System.out.println(code);

//解析数组
List<Map<String, Object>> list = resolver.getList("/response/orders/order");
list.forEach(x -> {
     System.out.println(x);
});
```

