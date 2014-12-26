package sustentacao;

public enum EquipesEnum {
	HUDSON ((long) 1),
	QUANTUM ((long) 2),
	SINISTRO ((long) 3),
	TESTE((long) 4);
	
	private Long codigo;
	
	private EquipesEnum(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}
	
}
