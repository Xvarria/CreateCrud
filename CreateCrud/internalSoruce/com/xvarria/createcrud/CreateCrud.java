package com.xvarria.createcrud;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class CreateCrud {
	private static final String NAME="Medidor";
	
	private static final String PACKAGE_BASE = "com/gea/web/";
	private static final String BASE_DIRECTORY = "/home/mchavarria/proyectos/CreateCrud/target/";
	private static final String GENERAL_EXCEPTION = "GeaWebException";
	//private static final String ENV_WEB_DIRECTORY = "env/web/WEB-INF/classes";
	//private static final String SOURCE_PACKAGE = "src/";
	
	private static final String CONTROLLER_PACKAGE = "controller/";
	private static final String VALIDATOR_PACKAGE = "validator/";
	private static final String FORM_PACKAGE = "form/";
	private static final String SNIPPETS_PACKAGE = "snnipets/";
	private static final String SERVICE_PACKAGE = "service/";
	private static final String DAO_PACKAGE = "dao/";
	private static final String IMPL_PACKAGE = "impl/";
	
	
	//private static final String BASE_DIRECTORY_TEMPLATE = "c:/projects/CreateCrud/resources/templates/";
	private static final String CONTROLLER_PATH = BASE_DIRECTORY + PACKAGE_BASE + CONTROLLER_PACKAGE;
	private static final String VALIDATOR_PATH = BASE_DIRECTORY + PACKAGE_BASE + VALIDATOR_PACKAGE;
	private static final String FORM_PATH = BASE_DIRECTORY + PACKAGE_BASE + FORM_PACKAGE;
	private static final String SNIPPETS_PATH = BASE_DIRECTORY + SNIPPETS_PACKAGE;
	private static final String SERVICE_PATH = BASE_DIRECTORY + PACKAGE_BASE + SERVICE_PACKAGE;
	private static final String SERVICE_IMPL_PATH = SERVICE_PATH + IMPL_PACKAGE;
	private static final String DAO_PATH = BASE_DIRECTORY + PACKAGE_BASE + DAO_PACKAGE;
	private static final String DAO_IMPL_PATH = DAO_PATH + IMPL_PACKAGE;

	
	private static void loadModel(VelocityContext vc) {
		vc.put("constantName", "constant.GeaWebConstants");
		vc.put("basePackage", "com.gea.web");
		vc.put("EntityName", "Medidor");
		vc.put("entityName", "medidor");
		vc.put("ENTITY_NAME", "MEDIDOR");
		vc.put("ExceptionClass", GENERAL_EXCEPTION);
		vc.put("ProjectName", "GEA");
		vc.put("d", "$");
		vc.put("s", "#");
	}
	
	public static void main(String[] args) throws IOException {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
         
        createFiles(ve);
	}

	
	public static Map<String, String> createFiles(VelocityEngine ve) {
		Map<String, String> files = new HashMap<>();
		createController(ve); //Create Controller
		createValidator(ve);
		createForm(ve);
		createSnippets(ve);//Create Constants and tiles snippet
		//Create property Snipped
		createService(ve);
		//Create Service Int and class
		createDao(ve);//Create DAO Interface and class
		//Create JS / JSP for list
		//Create JS / JSP for form
		return files;
	}


	private static void createController(VelocityEngine ve) {
		String controllerName=NAME + "Controller";
		String controllerFileName = CONTROLLER_PATH + controllerName + ".java";
		
		renderFile(ve, controllerFileName, CONTROLLER_PATH, "/templates/ControllerClass.vm");
		System.out.println("Controller created -> "  + controllerFileName);
		
		String name="ControllerException";
		String path= BASE_DIRECTORY+PACKAGE_BASE+"/model/exception/";
		String fileName=path+name+".java";
		renderFile(ve, fileName, path, "templates/ControllerException.vm");
		System.out.println(name+" created -> "  + fileName);
		
		name=GENERAL_EXCEPTION;
		path= BASE_DIRECTORY+PACKAGE_BASE+"/model/exception/";
		fileName=path+name+".java";
		renderFile(ve, fileName, path, "templates/GeneralException.vm");
		System.out.println(name+" created -> "  + fileName);		
	}
	
	private static void createValidator(VelocityEngine ve) {
		String validatorName=NAME + "Validator";
		String validatorFileName = VALIDATOR_PATH + validatorName + ".java";
		
		renderFile(ve, validatorFileName, VALIDATOR_PATH, "templates/ValidatorClass.vm");
		System.out.println("Validator created -> "  + validatorFileName);
	}
	
	private static void createForm(VelocityEngine ve) {
		String formName=NAME + "Form";
		String formFileName = FORM_PATH + formName + ".java";
		
		renderFile(ve, formFileName, FORM_PATH, "templates/FormClass.vm");
		System.out.println("Form created -> "  + formFileName);
	}
	
	private static void createSnippets(VelocityEngine ve) {
		String outputBase="SnippetConstants";
		String constantSnippetFile = SNIPPETS_PATH + outputBase + ".java";		
		renderFile(ve, constantSnippetFile, SNIPPETS_PATH, "templates/SnippedConstants.vm");
		System.out.println("Constants created -> "  + constantSnippetFile);
		
		outputBase="SnippetTiles";
		String tilesSnippetFile = SNIPPETS_PATH + outputBase + ".xml";
		renderFile(ve, tilesSnippetFile, SNIPPETS_PATH, "templates/SnippedTiles.vm");
		System.out.println("Tiles created -> "  + tilesSnippetFile);
		
		outputBase="SnippetProperties";
		String propertiesSnippetFile = SNIPPETS_PATH + outputBase + ".properties";
		renderFile(ve, propertiesSnippetFile, SNIPPETS_PATH, "templates/SnippedProperties.vm");
		System.out.println("Properties created -> "  + propertiesSnippetFile);		
	}
	
	private static void createService(VelocityEngine ve) {
		String outputBase=NAME+"BO";
		String serviceInterfaceFile = SERVICE_PATH + outputBase + ".java";		
		renderFile(ve, serviceInterfaceFile, SERVICE_PATH, "templates/ServiceInterface.vm");
		System.out.println("Service Interface created -> "  + serviceInterfaceFile);
		
		outputBase=NAME+"BOImpl";
		String serviceClassFile = SERVICE_IMPL_PATH + outputBase + ".java";		
		renderFile(ve, serviceClassFile, SERVICE_IMPL_PATH, "templates/ServiceClass.vm");
		System.out.println("Service Class created -> "  + serviceClassFile);
	}
	
	private static void createDao(VelocityEngine ve) {
		String outputBase=NAME+"DAO";
		String daoInterfaceFile = DAO_PATH + outputBase + ".java";		
		renderFile(ve, daoInterfaceFile, DAO_PATH, "templates/DAOInterface.vm");
		System.out.println("DAO Interface created -> "  + daoInterfaceFile);
		
		outputBase=NAME+"DAOImpl";
		String daoClassFile = DAO_IMPL_PATH + outputBase + ".java";		
		renderFile(ve, daoClassFile, DAO_IMPL_PATH, "templates/DAOClass.vm");
		System.out.println("DAO Class created -> "  + daoClassFile);

		outputBase="AbstractDAO";
		String abstractDaoFile = DAO_PATH + outputBase + ".java";		
		renderFile(ve, abstractDaoFile, DAO_PATH, "templates/DAOAbstract.vm");
		System.out.println("DAO Abstract created -> "  + abstractDaoFile);
	}

	private static void renderFile(VelocityEngine ve, String fileName, String dirPath, String template) {
		try {
	        Template controllerTemplate = ve.getTemplate(template);
	        
	        VelocityContext vc = new VelocityContext();
	        loadModel(vc);
	        
	        StringWriter sw = new StringWriter();
	        controllerTemplate.merge(vc, sw);

	        new File(dirPath).mkdirs();
			Path file = Paths.get(fileName);
			Files.write(file,Arrays.asList(sw.toString()),Charset.forName("UTF-8"));
			
	        System.out.println();		
		} catch (IOException e) {
			e.printStackTrace(); //DO Something
		}
	}



}
