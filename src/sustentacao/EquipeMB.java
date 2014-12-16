package sustentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class EquipeMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Equipe> equipe = new ArrayList<Equipe>();

	private List<String> listaEquipe;

	public EquipeMB() {
		listaEquipe = new ArrayList<String>();

		listaEquipe.add("HUDSON");
		listaEquipe.add("QUANTUM");
		listaEquipe.add("SINISTRO");
	}

	public List<Equipe> getEquipe() {
		return equipe;
	}

	public void setEquipe(List<Equipe> equipe) {
		this.equipe = equipe;
	}

	public List<String> getListaEquipe() {
		return listaEquipe;
	}

	public void setListaEquipe(List<String> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}
}