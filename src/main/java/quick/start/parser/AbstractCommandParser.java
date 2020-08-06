package quick.start.parser;

import quick.start.constant.CommonConstant;
import quick.start.repositorys.command.AbstractCommandForEntity;
import quick.start.repositorys.command.ExecuteCommandMeta;

/**
 * @author yuanweiquan
 */
public abstract class AbstractCommandParser {

     /**
      * 解析命令属性
      *
      * @param command
      * @return
      * @see ExecuteCommandMeta
      */
     public abstract ExecuteCommandMeta parser(AbstractCommandForEntity command);

     protected String rightSpace(String context) {
          return context.concat(CommonConstant.SPACE);
     }

}
