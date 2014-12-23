package br.com.synchro.web.exception;

/**
 * 
 * RestException.java
 * Criado em Sep 29, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
public class RestException extends Exception {

	/** Numero serial da classe **/
	private static final long serialVersionUID = 4597624410836835698L;
	
	/** Codigo da excecao **/
	private String code;

	/** Mensagem generica da excecao **/
	private String message;
	
	/** Pilha completa da excecao **/
	private String stack;
	
	/** Nome do metodo que lancou a excecao **/
	private String methodName;
	
	/**
	 * Construtor que captura o nome do metodo, a descricao da mensagem e a pilha do erro.
	 * 
	 * @param method Name nome do metodo
	 * @param message mensagem generica da excecao 
	 * @param stack pilha completa da excecao
	 */
	public RestException(String methodName, String message, String stack){
		this.message = message;
		this.stack = stack;
		this.methodName = methodName;
	}

	/**
	 * Contrutor padrao que invoca a excecao da classe Exception
	 */
	public RestException() {
		super();
	}

	/**
	 * Construtor que adiciona a mensagem da excecao lancada
	 * 
	 * @param message mensagem generica da excecao lancada
	 */
	public RestException(String message) {
		this.message = message;
	}
	
	/**
	 * Construtor que adiciona a mensagem e o codigo da excecao lancada
	 * 
	 * @param code codigo da excecao lancada
	 * @param message mensagem generica da excecao lancada
	 */
	public RestException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Construtor que chama o construtor da superclasse  passando o
	 * objeto Throwable e adiciona a mensagem da excecao lancada
	 * 
	 * @param message mensagem generica da excecao lancada
	 * @param throwable objeto que representa um Throwable
	 */
	public RestException(String message, Throwable throwable) {
		super(throwable);
		this.message = message;		
	}

	/**
	 * Construtor que chama o construtor da superclasse  passando o
	 * objeto Exception e adiciona a mensagem da excecao lancada
	 * 
	 * @param message mensagem generica da excecao lancada
	 * @param exception objeto que representa um Exception
	 */
	public RestException(String message, Exception exception) {
		super(exception);
		this.message = message;
	}
	
	/**
	 * Construtor que chama o construtor da superclasse  passando o
	 * objeto Throwable e adiciona a mensagem e o codigo da excecao lancada
	 * 
	 * @param message mensagem generica da excecao lancada
	 * @param throwable objeto que representa um Throwable
	 * @param code codigo da excecao lancada
	 */
	public RestException(String message, Throwable throwable, String code) {
		super(throwable);
		this.message = message;
		this.code = code;
	}

	/**
	 * Construtor que chama o construtor da superclasse  passando o
	 * objeto Exception e adiciona a mensagem e o codigo da excecao lancada
	 * 
	 * @param message mensagem generica da excecao lancada
	 * @param exception objeto que representa um Exception
	 * @param code codigo da excecao lancada
	 */
	public RestException(String message, Exception exception, String code) {
		super(exception);
		this.message = message;
		this.code = code;
	}	
	
	/**
	 * Construtor que chama o construtor da superclasse  passando o
	 * objeto Throwable
	 * 
	 * @param throwable objeto que representa um Throwable
	 */
	public RestException(Throwable throwable) {
		super(throwable);
		this.message = throwable.getMessage();
	}

	/**
	 * Construtor que chama o construtor da superclasse  passando o
	 * objeto Exception
	 * 
	 * @param exception objeto que representa um Exception
	 */
	public RestException(Exception exception) {
		super(exception);
		this.message = exception.getMessage();
	}

	/**
	 * Getter para o codigo da exception
	 * @return codigo da exception
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter para o codigo da exception
	 * @param code codigo da exception
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter para a mensagem da exception
	 * @return mensagem da exception
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter para a mensagem da exception
	 * @param message mensagem da exception
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter para a pilha da exception
	 * @return pilha da exception
	 */
	public String getStack() {
		return stack;
	}

	/**
	 * Setter para a pilha da exception
	 * @param stack pilha da exception
	 */
	public void setStack(String stack) {
		this.stack = stack;
	}

	/**
	 * Getter para o nome do metodo que gerou a exception
	 * @return nome do metodo que gerou a exception
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Setter para o nome do metodo que gerou a exception
	 * @param methodName nome do metodo que gerou a exception
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}	
}
