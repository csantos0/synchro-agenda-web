package br.com.synchro.web.domain;

public class Cep {

	private String bairro;
	
	private String logradouro;
	
	private String cep;
	
	private String uf;
	
	private String localidade;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	@Override
	public String toString() {
		return "Cep [bairro=" + bairro + ", logradouro=" + logradouro
				+ ", cep=" + cep + ", uf=" + uf + ", localidade=" + localidade
				+ "]";
	}
	
	
}
