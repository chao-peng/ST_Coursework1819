package st;

import java.util.ArrayList;
import java.util.HashSet;

public class OptionMap {
	public static final int INTEGER = 1;
	public static final int BOOLEAN = 2;
	public static final int STRING = 3;
	public static final int CHAR = 4;
	
    private ArrayList<Option> options;
    private HashSet<Option> uniqueOptions;
    
    public OptionMap() {
    	options = new ArrayList<Option>();
    	uniqueOptions = new HashSet<Option>();
    }
    
    public void store(String name, String shortcut, int type) {
    	Option option = new Option(name, shortcut, type);
    	if (!isOptionValid(option)) {
    		throw new RuntimeException();
    	}
    	if (isOptionUnique(option)) {
    		options.add(option);
    		uniqueOptions.add(option);
    	} else {
    		options.remove(option);
    		options.add(option);
    	}
    }
    
    public String getValue(String pattern) {
    	for (Option option : options) {
    		if (option.getName().equals(pattern)) {
    			return option.getValue();
    		}
    	}
    	for (Option option : options) {
    		if (option.getShortcut().equals(pattern)) {
    			return option.getValue();
    		}
    	}
    	return "";
    }
    
    public int getType(String pattern) {
    	for (Option option : options) {
    		if (option.getName().equals(pattern)) {
    			return option.getType();
    		}
    	}
    	for (Option option : options) {
    		if (option.getShortcut().equals(pattern)) {
    			return option.getType();
    		}
    	}
    	return 0;
    }
    
    public void setValueWithOptionName(String name, String value) {
    	for (Option option : options) {
    		if (option.getName().equals(name)) {
    			option.setValue(value);
    		}
    	}
    }
    
    public void setValueWithOptioShortcut(String shortcut, String value) {
    	for (Option option : options) {
    		if (option.getShortcut().equals(shortcut)) {
    			option.setValue(value);
    		}
    	}
    }
    
    private boolean isOptionValid(Option o) {
    	if (o.getName() == null) {
    		return false;
    	}
    	if (o.getName().isEmpty()) {
    		return false;
    	}
    	if (!o.getName().matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
    		return false;
    	}
    	if (o.getShortcut() == null) {
    		return false;
    	} else {
    		if (!o.getShortcut().isEmpty()) {
    			if (!o.getShortcut().matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
    	    		return false;
    	    	}
    		}
    	}
    	if (o.getType() < INTEGER || o.getType() > CHAR) {
    		return false;
    	}
    	return true;
    }
    
    private Boolean isOptionUnique(Option o) {
    	if (uniqueOptions.contains(o)) {
    		return Boolean.FALSE;
    	} else {
    		return Boolean.TRUE;
    	}
    }
	
	@Override
	public String toString() {
		String result = "OptionMap [options=\n";
		for (Option option: options) {
			result = result + "\t{name=" + option.getName() + ", shortcut=" + option.getShortcut() + ", type=" + option.getType() + ", value=" + option.getValue() + "}\n";
		}
		result = result + "]";
		return result;
	}

	class Option {
		private String name;
		private String shortcut;
		private String value;
		int type;
		
		public Option(String name, Integer type) {
			super();
			this.name = name;
			this.shortcut = "";
			this.value = "";
			this.type = type;
		}
		
		public Option(String name, String shortcut, Integer type) {
			super();
			this.name = name;
			this.shortcut = shortcut;
			this.value = "";
			this.type = type;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getShortcut() {
			return shortcut;
		}
		
		public void setShortcut(String shortcut) {
			this.shortcut = shortcut;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
		public Integer getType() {
			return type;
		}
		
		public void setType(Integer type) {
			this.type = type;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Option other = (Option) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance())) {
				return false;
			}
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			} else if (!name.equals(other.name)) {
				return false;
			}
			return true;
		}

		private OptionMap getEnclosingInstance() {
			return OptionMap.this;
		}        
	}
}
