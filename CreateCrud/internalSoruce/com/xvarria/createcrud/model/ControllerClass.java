package com.xvarria.createcrud.model;

import java.util.ArrayList;
import java.util.List;

public class ControllerClass {
	String packageBase;
	String constantClasseName;
	String baseName;
	
	private String controllerName;
	private String serviceNameVariable;
	private String serviceNameDef;
	private List <MethodClass> methods = new ArrayList<>();
			
	public ControllerClass(String baseName, String packageBase, String constantClasseName) {
		super();
		this.packageBase = packageBase.replace("/", ".");
		this.controllerName = baseName + "Controller";
		this.serviceNameVariable = baseName.toLowerCase() + "Service"; //TODO CamelCaseNotation
		this.serviceNameDef = baseName + "Service";
		this.constantClasseName = constantClasseName;
		//Create Method by Action
		this.methods.add(new MethodClass(constantClasseName, baseName, Actions.LIST.toString(), WebMethods.GET.toString(), "VIEW_FROM_HERE")); 
		
	}

	public List<String> renderedClass () {
		List<String> lines = new ArrayList<>(); 
		lines.add("package " + packageBase + "*;");
		lines.add("import static "+ packageBase +"model.constant.PasConstants." + constantClasseName + ";");
		lines.add("");
		lines.add("import "+ packageBase +"*;");
		lines.add("");
		lines.add("@Controller");
		lines.add("public class "+ controllerName + " extends BasicController {");
		lines.add("       private static Log log = LogFactory.getLog("+controllerName+".class);");
		lines.add("");
		lines.add("       @Autowired");
		lines.add("       private "+serviceNameDef+" "+serviceNameVariable+"");
		lines.add("");
		for (MethodClass method : this.methods) {
			lines.addAll(method.renderedClass());
		}
		lines.add("");
		lines.add("}");
		
		return lines;
	}
}
