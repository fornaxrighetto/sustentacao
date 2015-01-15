package sustentacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CadastroRelatorioMB {

	private Relatorio relatorio = new Relatorio();

	private RelatorioSB relatorioSB = new RelatorioSB();

	private List<Equipe> listaEquipe;

	private List<String> listaDescricaoEquipes = new ArrayList<String>();

	private String equipeEscolhida;

	@PostConstruct
	public void init() {
		listaEquipe = relatorioSB.findAll();
		for (Equipe equipe : listaEquipe) {
			listaDescricaoEquipes.add(equipe.getDescricao());
		}
	}

	/**
	 * Recupera os dados da tela para armazena-los no banco
	 * 
	 */
	public void salvarRelatorio() {
		for (Equipe equipe : listaEquipe) {
			if (equipe.getDescricao().equals(equipeEscolhida)) {
				this.relatorio.setDescricao(equipe);
				relatorioSB.insert(relatorio);
				msgSalvo();
			}
		}
		if (relatorio != null) {
			limparRelatorio();
		}
	}

	/**
	 * Limpa os dados do Relatorio que estão na tela
	 */
	public void limparRelatorio() {
		relatorio = new Relatorio();
	}

	/**
	 * Exibe a mensagem "Salvo com sucesso"
	 */
	public void msgSalvo() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Salvo com sucesso", ""));
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioSB getRelatorioSB() {
		return relatorioSB;
	}

	public void setRelatorioSB(RelatorioSB relatorioSB) {
		this.relatorioSB = relatorioSB;
	}

	public List<Equipe> getListaEquipe() {
		return listaEquipe;
	}

	public void setListaEquipe(List<Equipe> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}

	public List<String> getListaDescricaoEquipes() {
		return listaDescricaoEquipes;
	}

	public void setListaDescricaoEquipes(List<String> listaDescricaoEquipes) {
		this.listaDescricaoEquipes = listaDescricaoEquipes;
	}

	public String getEquipeEscolhida() {
		return equipeEscolhida;
	}

	public void setEquipeEscolhida(String equipeEscolhida) {
		this.equipeEscolhida = equipeEscolhida;
	}
}