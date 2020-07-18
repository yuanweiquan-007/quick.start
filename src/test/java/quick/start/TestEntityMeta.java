package quick.start;

import org.junit.Assert;
import quick.start.annotation.PrimaryKey;
import quick.start.annotation.Table;
import quick.start.entity.Entity;
import quick.start.entity.EntityMeta;
import lombok.Data;
import org.junit.Test;

public class TestEntityMeta {

     @Test
     public void test() {
          EntityMeta<Color> meta = EntityMeta.of(Color.class);
          Assert.assertEquals(meta.getPrimaryKey(), "colorId");
          Assert.assertEquals(meta.getTableName(), "color");
     }

     @Data
     @Table("color")
     @PrimaryKey("colorId")
     class Color implements Entity {
          private String name;
     }

}
