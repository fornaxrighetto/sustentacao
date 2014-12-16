package sustentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
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
	 * @param dataInicio primeiro filtro de pesquisa
	 * 
	 * @param dataFim segundo filtro de pesquisa
	 * 
	 * @return lista de Relatorio
	 */
	@SuppressWarnings("unchecked")
	public List<Relatorio> findDate(Date dataInicio, Date dataFim,	String equipes) {
		List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();

		try {
			// EntityManager em = JPAUtil.getEntityManager();
			// Query query =
			// em.createQuery(" select rel from Relatorio rel where rel.data >= :dataInicio and  rel.data <= :dataFim and rel.descricaoEquipe in (:equipes) ");
			//
			// query.setParameter("dataInicio", dataInicio);
			// query.setParameter("dataFim", dataFim);
			// query.setParameter("equipe", equipe);
			//
			// listaRelatorio = query.getResultList();

			EntityManager em = JPAUtil.getEntityManager();
			Session session = em.unwrap(Session.class);

			Criteria criteria = session.createCriteria(Relatorio.class, "r");
			criteria.add(Restrictions.between("r.data", dataInicio, dataFim));
			// criteria.createCriteria("descricaoEquipe").add(Restrictions.idEq(equipes))criteria;
			criteria.add(Restrictions.like("r.equipe", equipes));

			listaRelatorio = criteria.list();
		} catch (Exception e) {
			System.out.println(e);
		}
		return listaRelatorio;
	}

	/**
	 * Recupera os dados para serem armazenados no banco
	 * 
	 * @param dados
	 *            inseridos na tela pelo usuario
	 */
	public void insert(Relatorio relatorio) {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.persist(relatorio);
		em.getTransaction().commit();
		em.close();
	}
}