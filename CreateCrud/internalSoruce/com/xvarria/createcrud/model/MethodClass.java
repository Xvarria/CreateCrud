package com.xvarria.createcrud.model;

import java.util.ArrayList;
import java.util.List;

public class MethodClass {
	String constantClasseName;
	String action;
	String baseName;
	String method;
	String viewConstant;
	private String formClass;
	private String methodName;
	private String url;
	
	public MethodClass(String constantClasseName, String baseName, String action, String method, String viewConstant) {
		super();
		this.constantClasseName = constantClasseName;
		this.baseName = baseName;
		this.method = method;
		this.viewConstant = viewConstant;
		this.methodName = action.toLowerCase() +  baseName + method.toLowerCase();
		this.url = baseName + action.toLowerCase() + ".do";		
		this.formClass = baseName + action.toLowerCase() + "Command";
		
				
		
	}

	public List<String> renderedClass () {

		List<String> lines = new ArrayList<>(); 
		if (Actions.LIST.toString().equals(action)) {
			lines.add("@RequestMapping(value=\"/**/web/"+url+".do\", method = RequestMethod."+method+")");
			lines.add("public ModelAndView "+methodName+"(HttpServletRequest request, HttpServletResponse response) throws Exception {");
			lines.add("        ModelAndView nextMv = null;");
			lines.add("       "+ formClass+" command = new "+formClass+"();");
			lines.add("       nextMv = this.nextModelView("+viewConstant+", command, Actions."+action+");");
			lines.add("       return nextMv;");
			lines.add("}");			
		}		
		return lines;
	}
	
	
}
