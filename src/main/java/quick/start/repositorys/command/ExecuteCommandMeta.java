package quick.start.repositorys.command;

import lombok.Data;

import java.util.List;

/**
 * 基础数据
 * @author yuanweiquan
 */
@Data
public class ExecuteCommandMeta {

     /**
      * 执行语句的内容
      */
     protected String command;
     /**
      * 执行参数
      */
     protected List<Object> parames;

     private ExecuteCommandMeta(String command, List<Object> parames) {
          this.command = command;
          this.parames = parames;
     }

     public static final ExecuteCommandMeta of(String command, List<Object> parames) {
          return new ExecuteCommandMeta(command, parames);
     }

}
