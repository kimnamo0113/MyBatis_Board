package kr.yi.board.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUsingURI extends HttpServlet {
	private HashMap<String,CommandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile"); //WEB-INF/commandHandler.properties
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		try(FileReader fis = new FileReader(configFilePath)){
			prop.load(fis);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next(); //key를 추출 : /simple.do
			String handlerClassName=prop.getProperty(command); //key에 해당하는 값 : mvc.simple.SimpleHandler
			Class<?> handlerClass;
			try {
				handlerClass = Class.forName(handlerClassName); //string되어있는 클래스를 class화 시킴
				CommandHandler handlerInstance=(CommandHandler)handlerClass.newInstance(); //new해주는과정
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		
	}
	private void process(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String command = req.getRequestURI(); //주소가 넘어옴 : /MVCProject/simple.do
		if(command.indexOf(req.getContextPath())==0) { // /MVCProject
			command = command.substring(req.getContextPath().length()); // /simple.do
		}
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler==null) {
			handler=new NullHandler();
		}
		String viewPage=null;
		
		try {
			viewPage = handler.process(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(viewPage!=null) {
			RequestDispatcher dispathcher=req.getRequestDispatcher(viewPage);
			dispathcher.forward(req, res);
		}
	}
	
}
