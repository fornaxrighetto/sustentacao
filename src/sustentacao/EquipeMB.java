package sustentacao;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class EquipeMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Equipe equipe = new Equipe();
	

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	 
}