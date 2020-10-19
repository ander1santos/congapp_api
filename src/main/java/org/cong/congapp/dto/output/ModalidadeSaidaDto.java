package org.cong.congapp.dto.output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.Modalidade;

public class ModalidadeSaidaDto {

	private Long id;	
	private String descricao;
	
	public ModalidadeSaidaDto(Modalidade modalidade) {
		this.id = modalidade.getId();
		this.descricao = modalidade.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static List<ModalidadeSaidaDto> listarModalidade(Iterable<Modalidade> modalidades){
		List<Modalidade> listaModalidades = new ArrayList<>();
		modalidades.forEach(listaModalidades::add);
		return listaModalidades.stream()
				.map(ModalidadeSaidaDto::new)
				.collect(Collectors.toList());
	}
}
