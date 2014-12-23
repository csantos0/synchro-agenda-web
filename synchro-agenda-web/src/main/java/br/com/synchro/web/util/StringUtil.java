package br.com.synchro.web.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/** 
 * StringOp.java
 * Criado em Jun 13, 2010
 * @author Ciro S. Santos
 * @version 1.0
 * 
 * Classe que fornece servicos e formatacoes de String
 */
public class StringUtil {
	
	public static boolean checkRepeatedStrings(String text){
		List<String> list = Arrays.asList(text.split("\\s+"));		 
        Set<String> uniqueWords = new HashSet<String>(list);
        for (String word : uniqueWords) {
            int count = Collections.frequency(list, word);
            if(count > 1){
            	return true;
            }
        }
        return false;
	}


	/**
	 * Pega a pilha de uma excecao
	 * 
	 * @param t excecao para pegar a pilha
	 * @return retorna toda a pilha da exception
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
	
	public static String getImagemNome(String url){		
		String split[]  = url.split("/");
		String var = split[split.length -1];		
		return var;		
	}

	/**
	 * Retorna a sigla do mes de acordo com uma data passada como parametro
	 * 
	 * @param data data a ser processada
	 * @return retorna uma string com a sigla correta
	 */
	public static String ajustaData(String data) {
		String split[] = data.split("-");
		String mes = "";

		if (split[1].equals("JAN")) {
			mes = "01";
		} else if (split[1].equalsIgnoreCase("FEV")) {
			mes = "02";
		} else if (split[1].equalsIgnoreCase("MAR")) {
			mes = "03";
		} else if (split[1].equalsIgnoreCase("ABR")) {
			mes = "04";
		} else if (split[1].equalsIgnoreCase("MAI")) {
			mes = "05";
		} else if (split[1].equalsIgnoreCase("JUN")) {
			mes = "06";
		} else if (split[1].equalsIgnoreCase("JUL")) {
			mes = "07";
		} else if (split[1].equalsIgnoreCase("AGO")) {
			mes = "08";
		} else if (split[1].equalsIgnoreCase("SET")) {
			mes = "09";
		} else if (split[1].equalsIgnoreCase("OUT")) {
			mes = "10";
		} else if (split[1].equalsIgnoreCase("NOV")) {
			mes = "11";
		} else if (split[1].equalsIgnoreCase("DEZ")) {
			mes = "12";
		}

		return split[0] + "/" + mes + "/" + split[2];
	}

	/**
	 * Verifica se a se a string passada como parametro 
	 * possui o caracter passado como parametro
	 * 
	 * @param str string a ser verificada
	 * @param c caracter a verificar
	 * @return true se o caracter existir e false se o caracter nao existir
	 */
	public static boolean existeCaracter(String str, char c) {
		boolean existe = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	/**
	 * Remove um caracter passado como parametro de uma string passada como parametro 
	 * 
	 * @param str string a ser processada
	 * @param c caracter a ser removido
	 * @return string atualiza com o caracter removido, se ele existir
	 */
	public static String removerCaracter(String str, char c) {
		String r = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != c) {
				r += str.charAt(i);
			}
		}
		return r;
	}

	/**
	 * Remove os pontos flutuantes de uma string
	 * 
	 * @param str string a ser verificada
	 * @return string processada sem os pontos flutuantes
	 */
	public static String removePontoFlutuante(String str) {
		int index = str.indexOf(".");
		if (index != -1) {
			return str.substring(0, index);
		} else {
			return str;
		}
	}

	/**
	 * Retorna um pedaco de uma string
	 * 
	 * @param str string completa a ser verificada
	 * @param pedaco pedaco da string a ser identificada
	 * @param msg mensagem a retornar se existir o pedaco
	 * @return mensagem retornada
	 */
	public static String setMessage(String str, String pedaco, String msg) {
		String split[] = str.split(" ");

		for (String var : split) {
			if (var.trim().equals(pedaco)) {
				return msg;
			}
		}
		return null;
	}

	/**
	 * Remove acentos de uma string caso exista
	 * 
	 * @param str string a ser verificada
	 * @return string sem os acentos
	 */
	public static String removerAcentos(String str) {
		return RemoverAcentos.remover(str);
	}

	/**
	 * Remove espacos de uma string
	 * 
	 * @param str string a ser processada
	 * @return string sem espacos
	 */
	public static String tiraEspaco(String str) {
		return str.replaceAll(" ", "");
	}
	
	public static String tirarAcentos(String old){
		old = Normalizer.normalize(old, Normalizer.Form.NFD);  
		old = old.replaceAll("[^\\p{ASCII}]", "");  
		return old; 
	}
	
	public static String gerarNovoNomePath(String nomeVelho, String extensao){		
		String split[] = extensao.split("\\.");
		String aux = tiraEspaco(nomeVelho);
		String aux2 = tirarAcentos(aux);
		return aux2 + "." + split[split.length -1];
	}

	/**
	 * Reedita um path de arquivo por outro
	 * 
	 * @param path path a ser alterado
	 * @return novo path
	 */
	public static String novoPath(String path) {
		String replace = path.replace("\\", "@");
		String split[] = replace.split("@");
		String nome = split[split.length - 1];
		String pasta = split[split.length - 2];
		return "/" + pasta + "/" + nome;
	}

	/**
	 * Retorna um double no valor monetario brasileiro
	 * 
	 * @param valor valor a ser convertido
	 * @return string no formato monetario
	 */
	public static String formatarValorMonetarioBr(double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt",
				"BR"));
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(valor);
	}
	
	/**
	 * Retorna um double no valor monetario brasileiro
	 * 
	 * @param valor valor a ser convertido
	 * @return string no formato monetario
	 */
	public static String formatarValorBr(double valor) {
		NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(valor);
	}

	/**
	 * Verifica se a string realmente possui um valor monetario
	 * 
	 * @param str string com um possivel valor monetario
	 * @return true se string possuir um valor monetario e false se nao
	 */
	public static boolean validaValorMonetario(String str) {
		String regex = "[0-9]+([,][0-9]{2})?";
		if (str.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Ajusta valor arredondado
	 * 
	 * @param str string a ser analisada
	 * @return string formatada
	 */
	public static String ajustaValorArredondado(String str) {
		String var = str.substring(3);
		return var;
	}

	/**
	 * Converte uma string para double
	 * 
	 * @param str string a ser convertida
	 * @return valor convertido
	 */
	public static double stringToDouble(String str) {
		boolean existe = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ',') {
				existe = true;
				break;
			}
		}

		if (existe == false) {
			return Double.parseDouble(str);
		} else {
			String replace = str.replace(",", ".");
			return Double.parseDouble(replace);
		}
	}

	/**
	 * Completa string com zeros
	 * @param str string a ser convetida
	 * @return string convertida
	 */
	public static String completaComZeros(String str) {
		boolean existe = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ',') {
				existe = true;
				break;
			}
		}

		if (existe == false) {
			StringBuilder sb = new StringBuilder();
			sb.append(str);
			sb.append(",00");
			return sb.toString();
		} else {
			return str;
		}
	}

	/**
	 * Completa uma string com algum valor a esquerda ou a direita
	 * 
	 * @param str string a ser formatada
	 * @param letra caracter a ser adicionado
	 * @param tamanho quantidade de caracteres a serem adicionados
	 * @param direcao direcao esquerda ou direita a ser adicionada
	 * @return string formatada
	 */
	public static String completaStr(String str, String letra, int tamanho,
			int direcao) {

		if (str == null || str.trim().equals("")) {
			str = "";
		}

		while (str.contains(" ")) {
			str = str.replaceAll(" ", " ").trim();
		}

		str = str.replaceAll("[./-]", "");
		StringBuffer sb = new StringBuffer(str);

		if (direcao == 1) { // a Esquerda
			for (int i = sb.length(); i < tamanho; i++) {
				sb.insert(0, letra);
			}
		} else if (direcao == 2) {// a Direita
			for (int i = sb.length(); i < tamanho; i++) {
				sb.append(letra);
			}
		}
		return sb.toString();
	}
}

/** 
 * StringOp.java
 * Criado em Jun 13, 2010
 * @author Ciro S. Santos
 * @version 1.0
 *
 * Classe interna para remover acentos de uma String
 */
class RemoverAcentos {

	/** Caracteres acentuados **/
	static String acentuado = "";
	
	/** Caracteres sem acentos **/
	static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
	
	/** Tabela onde sera armazenado os caraceteres **/
	static char[] tabela;

	/**
	 * Bloco static para inicializar a tabela
	 */
	static {
		tabela = new char[256];
		for (int i = 0; i < tabela.length; ++i) {
			tabela[i] = (char) i;
		}
		for (int i = 0; i < acentuado.length(); ++i) {
			tabela[acentuado.charAt(i)] = semAcento.charAt(i);
		}
	}

	/**
	 * Remove os acentos de uma string
	 * 
	 * @param s string a ser formatada
	 * @return string formatada
	 */
	public static String remover(final String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if (ch < 256) {
				sb.append(tabela[ch]);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
