package br.com.synchro.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 
 * FacesUtil.java
 * Criado em Set 11, 2009
 * @author Ciro S. Santos
 * @version 1.0
 *
 * Classe que fornece servicos do JSF
 */
public class FacesUtil {

	/**
	 * Adiciona uma mensagem de erro ao JSF
	 * 
	 * @param msg mensagem a ser adicionada
	 */
	public static void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}

	/**
	 * Adiciona uma mensagem de sucesso a JSF
	 * 
	 * @param msg mensagem a ser adicionada
	 */
	public static void addSuccessMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}

	/**
	 * Adiciona uma mensagem de aviso ao JSF
	 * 
	 * @param msg mensagem a ser adicionada
	 */
	public static void addWarningMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}

	/**
	 * Retorna um Servlet Context
	 * @return servet context
	 */
	public static ServletContext getServletContext() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		return (ServletContext) external.getContext();
	}

	/**
	 * Retorna um HttpSession 
	 * @return http session
	 */
	public static HttpSession getSession() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		return (HttpSession) external.getSession(false);
	}

	/**
	 * Retorna um HttpServletRequest
	 * 
	 * @return http servlet request
	 */
	public static HttpServletRequest getRequest() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		return (HttpServletRequest) external.getRequest();
	}

	/**
	 * Retorna um HttpServletResponse
	 * 
	 * @return http servlet response
	 */
	public static HttpServletResponse getResponse() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		return (HttpServletResponse) external.getResponse();
	}

	/**
	 * Retorna um mapa com todos os parametros de request
	 * 
	 * @return map de strings com os parametros de request
	 */
	public static Map<String, String> getRequestParametersMap() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		return external.getRequestParameterMap();
	}
	
	/**
	 * Retorna um sessionMap
	 * 
	 * @return session mao
	 */
	public static Map<String, Object> getSessionMap() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		return external.getSessionMap();		
	}

	/**
	 * Realiza um dispatcher com a string passada como parametro
	 * 
	 * @param args string a ser executada
	 */
	public static void doDispatcher(String args) {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(args);
			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Renderiza o response
	 */
	public static void renderResponse() {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.renderResponse();
		fc.responseComplete();
	}
	
	public static ExternalContext getExternalContext(){
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext();
	}

	/**
	 * Retorna um objeto ResourceBundle das mensagens configuradas no arquivo
	 * 
	 * @return resource bundle das mensagens
	 */
	public static ResourceBundle getMsg() {
		return ResourceBundle.getBundle("ResourceBundle");
	}
	
	/**
	 * Retorna o caminho da pasta passada como parametro
	 * 
	 * @param folderName nome da pasta dentro do servidor
	 * @return caminho da pasta
	 */
	public static String getFolderFromContext(String folderName) {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) external.getContext();
        return context.getRealPath("/" + folderName + "/") + "/";       
    } 
	
	public static Properties getFtpProperties(){
		Properties props = new Properties();
		try{
			props.load(new FacesUtil().getStream());
		}catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	private InputStream getStream(){
		InputStream in = getClass().getClassLoader().getResourceAsStream("ftp.properties");
		return in;
	}
}
