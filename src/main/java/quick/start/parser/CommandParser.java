package quick.start.parser;

import quick.start.repositorys.command.CommandForEntity;
import quick.start.repositorys.command.ExecuteCommandMeta;

public abstract class CommandParser {

     public abstract ExecuteCommandMeta parser(CommandForEntity command);

}
