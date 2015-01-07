package sustentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Classe responsavel pela comunicacao do modelo com a view
 * 
 * @author Lucas Henrique de Sousa
 * 
 * @version 1.0
 */

@ViewScoped
@ManagedBean
public class RelatorioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Equipe equipe = new Equipe();

	private RelatorioSB relatorioSB = new RelatorioSB();

	private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();

	private List<String> equipes = new ArrayList<String>();

	private String equipeEscolhida;

	private Relatorio relatorio = new Relatorio();

	private Date dataInicio;

	private Date dataFim;
	
	private List<Equipe> listaEquipe;

	private List<String> listaDescricaoEquipes = new ArrayList<String>();

	@PostConstruct
	public void init() {
		listaEquipe = relatorioSB.findAll();
		for (Equipe equipe : listaEquipe) {
			listaDescricaoEquipes.add(equipe.getDescricao());
		}
	}

	/**
	 * Pesquisa no banco de acordo com filtro informado
	 * 
	 * @return lista de Relatorio
	 */
	public List<Relatorio> buscarRelatorio() {
		listaRelatorio = relatorioSB.find(dataInicio, dataFim, equipes);
		return listaRelatorio;
	}

	/**
	 * Recupera os dados da tela para armazena-los no banco
	 * 
	 */
	public void salvarRelatorio() {
		for (Equipe equipe : listaEquipe) {
			if (equipe.getDescricao().equals(equipeEscolhida)) {
//				this.relatorio.setEquipe(equipe);
				relatorioSB.insert(relatorio, equipe);
				msgSalvo();
			}
		}
		

		if (relatorio != null) {
			limparRelatorio();
		}
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
	
	/**
	 * Exibe a mensagem "Salvo com sucesso"
	 */
	public void msgNaoSalvo() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Falha ao salvar", ""));
	}

	/**
	 * Limpa os dados do Relatorio que estão na tela
	 */
	public void limparRelatorio() {
		relatorio = new Relatorio();
	}

	public String mostrarGrafico() {
		// Abrir o grafico em uma nova janela

		// JButton jb = new JButton();
		//
		// jb.addActionListener(new ActionListener() {
		//
		// public void actionPerformed(ActionEvent e) {
		// JFrame teste = new JFrame();
		// teste.setVisible(true);
		// }
		// });
		return "grafico";
	}

	/**
	 * @return listaRelatorio
	 */
	public List<Relatorio> getListaRelatorio() {
		return listaRelatorio;
	}

	/**
	 * @param listaRelatorio
	 */
	public void setListaRelatorio(List<Relatorio> listaRelatorio) {
		this.listaRelatorio = listaRelatorio;
	}

	/**
	 * @return relatorio
	 */
	public Relatorio getRelatorio() {
		return relatorio;
	}

	/**
	 * @param relatorio
	 */
	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	/**
	 * @return dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public RelatorioSB getRelatorioSB() {
		return relatorioSB;
	}

	public void setRelatorioSB(RelatorioSB relatorioSB) {
		this.relatorioSB = relatorioSB;
	}

	public List<String> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<String> equipes) {
		this.equipes = equipes;
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