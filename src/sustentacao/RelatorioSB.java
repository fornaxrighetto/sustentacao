package sustentacao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Classe responsavel pela Regra de Negocio
 * 
 * @author Lucas Henrique de Sousa
 * 
 * @version 1.0
 */
public class RelatorioSB implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Pesquisa no banco de acordo com filtro informado
	 * 
	 * @param dataInicio primeiro filtro de pesquisa
	 * 
	 * @param dataFim segundo filtro de pesquisa
	 * 
	 * @return lista de Relatorio
	 */
	public List<Relatorio> find(Date dataInicio, Date dataFim, List<String> equipes) {
		RelatorioDAO dao = new RelatorioDAO();
		return dao.find(dataInicio, dataFim, equipes);
	}

	/**
	 * Recupera os dados para serem armazenados no banco
	 * 
	 * @param dados inseridos na tela pelo usuario
	 */
	public void insert(Relatorio relatorio) {
		RelatorioDAO dao = new RelatorioDAO();
		dao.insert(relatorio);
	}
	
	public List<Equipe> findAll(){
		RelatorioDAO dao = new RelatorioDAO();
		return dao.findAll();
	}
}