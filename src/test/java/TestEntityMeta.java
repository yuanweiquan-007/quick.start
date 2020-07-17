import com.yuanwiequan.common.annotation.PrimaryKey;
import com.yuanwiequan.common.annotation.Table;
import com.yuanwiequan.common.entity.Entity;
import com.yuanwiequan.common.entity.EntityMeta;
import lombok.Data;
import org.junit.Test;

public class TestEntityMeta {

     @Test
     public void test() {
          EntityMeta<Color> meta = EntityMeta.of(Color.class);
          System.out.println(meta);
     }

     @Data
     @Table("color")
     @PrimaryKey("colorId")
     class Color implements Entity {
          private String name;
     }

}
