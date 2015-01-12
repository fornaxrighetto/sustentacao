package sustentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();

	private RelatorioSB relatorioSB = new RelatorioSB();

	private Equipe equipe = new Equipe();

	private List<String> equipes = new ArrayList<String>();

	private List<Equipe> listaEquipe;

	private List<String> listaDescricaoEquipes = new ArrayList<String>();

	private String equipeEscolhida;

	private Date dataInicio;

	private Date dataFim;

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

	// /**
	// * Exibe a mensagem "Salvo com sucesso"
	// */
	// public void msgNaoSalvo() {
	// FacesContext.getCurrentInstance().addMessage(
	// null,
	// new FacesMessage(FacesMessage.SEVERITY_INFO,
	// "Falha ao salvar", ""));
	// }

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

	public List<Relatorio> getListaRelatorio() {
		return listaRelatorio;
	}

	public void setListaRelatorio(List<Relatorio> listaRelatorio) {
		this.listaRelatorio = listaRelatorio;
	}

	public RelatorioSB getRelatorioSB() {
		return relatorioSB;
	}

	public void setRelatorioSB(RelatorioSB relatorioSB) {
		this.relatorioSB = relatorioSB;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<String> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<String> equipes) {
		this.equipes = equipes;
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}