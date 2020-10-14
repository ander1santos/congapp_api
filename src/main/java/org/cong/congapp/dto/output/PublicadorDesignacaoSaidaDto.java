package org.cong.congapp.dto.output;

import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.Publicador;

public class PublicadorDesignacaoSaidaDto {

	private Long id;	
	private String nome;	
	private boolean privilegioTelaDesignacao;
	private String grupo;
	
	public PublicadorDesignacaoSaidaDto(Publicador publicador) {
		this.id = publicador.getId();	
		this.nome = publicador.getNome();	
		this.privilegioTelaDesignacao = publicador.getPrivilegio().isTelaPregacaoDesignacao();
		this.grupo = publicador.getGrupo();
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

	public boolean isPrivilegioTelaDesignacao() {
		return privilegioTelaDesignacao;
	}

	public void setPrivilegioTelaDesignacao(boolean privilegioTelaDesignacao) {
		this.privilegioTelaDesignacao = privilegioTelaDesignacao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public static List<PublicadorDesignacaoSaidaDto> listarPublicadores(List<Publicador> publicadores){
		return publicadores.stream()
				.map(PublicadorDesignacaoSaidaDto::new)
				.collect(Collectors.toList());
	}
}
