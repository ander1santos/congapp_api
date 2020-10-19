package org.cong.congapp.dto.input;

import java.time.LocalDateTime;
import java.util.Optional;

import org.cong.congapp.exception.RegistroNotFoundException;
import org.cong.congapp.model.Modalidade;
import org.cong.congapp.model.Morador;
import org.cong.congapp.model.Pregacao;
import org.cong.congapp.model.Publicador;
import org.cong.congapp.model.TerritorioPropriedade;
import org.cong.congapp.model.TipoContato;
import org.cong.congapp.repository.ModalidadeRepository;
import org.cong.congapp.repository.MoradorRepository;
import org.cong.congapp.repository.PublicadorRepository;
import org.cong.congapp.repository.TerritorioPropriedadeRepository;
import org.cong.congapp.repository.TipoContatoRepository;

public class PregacaoEntradaDto {
	
	private Long id;
	private LocalDateTime data = LocalDateTime.now();	
	private Long territorioPrincipalId;
	private String numeroPropriedade;
	private Long publicador1Id;
	private Long publicador2Id;	
	private String nomeMorador;
	private Long tipoContatoId;
	private Long modalidadeId;
	private Long dirigenteId;
	private String detalhes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Long getTerritorioPrincipalId() {
		return territorioPrincipalId;
	}
	public void setTerritorioPrincipalId(Long territorioPrincipalId) {
		this.territorioPrincipalId = territorioPrincipalId;
	}
	public String getNumeroPropriedade() {
		return numeroPropriedade;
	}
	public void setNumeroPropriedade(String numeroPropriedade) {
		this.numeroPropriedade = numeroPropriedade;
	}
	public Long getPublicador1Id() {
		return publicador1Id;
	}
	public void setPublicador1Id(Long publicador1Id) {
		this.publicador1Id = publicador1Id;
	}
	public Long getPublicador2Id() {
		return publicador2Id;
	}
	public void setPublicador2Id(Long publicador2Id) {
		this.publicador2Id = publicador2Id;
	}
	public String getNomeMorador() {
		return nomeMorador;
	}
	public void setNomeMorador(String nomeMorador) {
		this.nomeMorador = nomeMorador;
	}
	public Long getTipoContatoId() {
		return tipoContatoId;
	}
	public void setTipoContatoId(Long tipoContatoId) {
		this.tipoContatoId = tipoContatoId;
	}
	public Long getModalidadeId() {
		return modalidadeId;
	}
	public void setModalidadeId(Long modalidadeId) {
		this.modalidadeId = modalidadeId;
	}
	public Long getDirigenteId() {
		return dirigenteId;
	}
	public void setDirigenteId(Long dirigenteId) {
		this.dirigenteId = dirigenteId;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
	public Pregacao build(TerritorioPropriedadeRepository propriedadeRepository,
			PublicadorRepository publicadorRepository,
			MoradorRepository moradorRepository,
			ModalidadeRepository modalidadeRepository,
			TipoContatoRepository tipoContatoRepository) 
		throws RegistroNotFoundException {
		
		LocalDateTime data = LocalDateTime.now();
		
		TerritorioPropriedade propriedade = propriedadeRepository.findByTerritorioPrincipalIdAndNumeroPropriedade(
				this.territorioPrincipalId,this.numeroPropriedade)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar a propriedade: " + this.numeroPropriedade));
		
		Publicador publicador1 = publicadorRepository.findById(this.publicador1Id)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o publicador com id: " + this.publicador1Id));
		
		Optional<Publicador> publicador2 = publicadorRepository.findById(this.publicador2Id);
		
		TipoContato tipoContato = tipoContatoRepository.findById(this.tipoContatoId)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o Tipo Contato com id: " + this.tipoContatoId));
		
		Morador morador = moradorRepository.findByNomeAndPropriedade(this.nomeMorador,propriedade)
				.orElse(new Morador(this.nomeMorador,tipoContato,propriedade));
		morador.setTipoMorador(tipoContato);
		
		System.out.println("Id morador: " + morador.getId());
		
		Morador moradorGravado = moradorRepository.save(morador);
		
		Modalidade modalidade = modalidadeRepository.findById(this.modalidadeId)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar a Modalidade com id: " + this.modalidadeId));
		
		Optional<Publicador> dirigente = publicadorRepository.findById(this.dirigenteId);
		
		return new Pregacao(data,
				propriedade,
				publicador1,
				publicador2.orElse(null),
				moradorGravado,
				modalidade,
				dirigente.orElse(null),
				this.detalhes,
				null
				);
		
	}
}
