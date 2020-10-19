package org.cong.congapp.dto.output;

import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.Morador;

public class MoradorSaidaDto {

	private Long id;
	private String nome;
	private String tipoContato;
	
	public MoradorSaidaDto(Morador morador) {
		this.id = morador.getId();
		this.nome = morador.getNome();
		this.tipoContato = morador.getTipoMorador() != null ? morador.getTipoMorador().getDescricao() : "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}
	
	public static List<MoradorSaidaDto> listarMoradores(List<Morador> moradores){
		return moradores.stream()
				.map(MoradorSaidaDto::new)
				.collect(Collectors.toList());
	}
}
