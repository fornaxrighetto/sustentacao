package sustentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Classe responsavel pela persistencia dos dados no banco
 * 
 * @author Lucas Henrique de Sousa
 * 
 * @version 1.0
 */
public class RelatorioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Pesquisa no banco de acordo com filtro informado
	 * 
	 * @param dataInicio
	 *            primeiro filtro de pesquisa
	 * 
	 * @param dataFim
	 *            segundo filtro de pesquisa
	 * 
	 * @return lista de Relatorio
	 */
	@SuppressWarnings("unchecked")
	public List<Relatorio> find(Date dataInicio, Date dataFim,
			List<String> equipes) {
		List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();

		try {
			EntityManager em = JPAUtil.getEntityManager();
			Session session = em.unwrap(Session.class);

			Criteria criteria = session.createCriteria(Relatorio.class, "r");
			criteria.add(Restrictions.between("r.data", dataInicio, dataFim));
			criteria.setFetchMode("r.equipes", FetchMode.JOIN);
			criteria.createAlias("r.equipes", "equipe");
			criteria.add(Restrictions.in("equipe.descricao", equipes));

			listaRelatorio = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRelatorio;
	}

	/**
	 * Recupera os dados para serem armazenados no banco
	 * 
	 * @param dados
	 *            inseridos na tela pelo usuario
	 */
	public void insert(Relatorio relatorio, Equipe equipe) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(relatorio);
			em.persist(equipe);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipe> findAll(){
		List<Equipe> listaEquipes = new ArrayList<Equipe>();
		
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("select e from Equipe e");
		
		listaEquipes = query.getResultList();
		return listaEquipes;
	}
}