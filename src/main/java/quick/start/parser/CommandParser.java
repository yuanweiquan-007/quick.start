package quick.start.parser;

import quick.start.repository.command.CommandForEntity;
import quick.start.repository.command.ExecuteCommandMeta;

public abstract class CommandParser {

     public abstract ExecuteCommandMeta parser(CommandForEntity command);

}
