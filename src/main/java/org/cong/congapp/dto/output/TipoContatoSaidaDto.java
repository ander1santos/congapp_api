package org.cong.congapp.dto.output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.TipoContato;

public class TipoContatoSaidaDto {

	private Long id;
	private String descricao;
	private boolean restricaoContato = false;
	private int mesesRestricao = 0;
	
	public TipoContatoSaidaDto(TipoContato tipo) {
		this.id = tipo.getId();
		this.descricao = tipo.getDescricao();
		this.restricaoContato = tipo.isRestricaoContato();
		this.mesesRestricao = tipo.getMesesRestricao();
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
	public boolean isRestricaoContato() {
		return restricaoContato;
	}
	public void setRestricaoContato(boolean restricaoContato) {
		this.restricaoContato = restricaoContato;
	}
	public int getMesesRestricao() {
		return mesesRestricao;
	}
	public void setMesesRestricao(int mesesRestricao) {
		this.mesesRestricao = mesesRestricao;
	}
	
	public static List<TipoContatoSaidaDto> listarTipoContato(Iterable<TipoContato> tiposcontato){
		List<TipoContato> lista = new ArrayList<>();
		tiposcontato.forEach(lista::add);
		return lista.stream()
				.map(TipoContatoSaidaDto::new)
				.collect(Collectors.toList());
	}
}
