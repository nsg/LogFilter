package cc.nsg.bukkit.LogFilter;

import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class FilterOverride implements Filter {

	List<String> list;
	
	public FilterOverride(List<String> list) {
		this.list = list;
	}
	
	@Override
	public boolean isLoggable(LogRecord arg0) {
		for(String s : list) {
			if (arg0.getMessage().matches(".*" + s + ".*")) {
				//System.out.println("Blocked: " + arg0.getLoggerName());
				return false;
			}
		}
		
		return true;
	}

}
