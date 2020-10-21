package org.cong.congapp.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.cong.congapp.dto.input.DesignacaoPregacaoEntradaDto;
import org.cong.congapp.dto.input.PregacaoEntradaDto;
import org.cong.congapp.dto.output.DesignacaoPregacaoSaidaDto;
import org.cong.congapp.dto.output.ModalidadeSaidaDto;
import org.cong.congapp.dto.output.MoradorSaidaDto;
import org.cong.congapp.dto.output.PregacaoSaidaDto;
import org.cong.congapp.dto.output.TerritorioDesignadoSaidaDto;
import org.cong.congapp.dto.output.TipoContatoSaidaDto;
import org.cong.congapp.exception.RegistroNotFoundException;
import org.cong.congapp.model.DesignacaoPregacao;
import org.cong.congapp.model.Pregacao;
import org.cong.congapp.model.Publicador;
import org.cong.congapp.model.TerritorioPropriedade;
import org.cong.congapp.repository.DesignacaoPregacaoRepository;
import org.cong.congapp.repository.DesignacaoTerritorioRepository;
import org.cong.congapp.repository.ModalidadeRepository;
import org.cong.congapp.repository.MoradorRepository;
import org.cong.congapp.repository.PregacaoRepository;
import org.cong.congapp.repository.PublicadorRepository;
import org.cong.congapp.repository.TerritorioPrincipalRepository;
import org.cong.congapp.repository.TerritorioPropriedadeRepository;
import org.cong.congapp.repository.TipoContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pregacao")
public class PregacaoController {
	
	@Autowired
	TerritorioPrincipalRepository territorioRepository;
	
	@Autowired
	TerritorioPropriedadeRepository propriedadeRepository;
	
	@Autowired
	PublicadorRepository publicadorRepository;
	
	@Autowired
	PregacaoRepository pregacaoRepository;
	
	@Autowired
	DesignacaoTerritorioRepository desigTerritRepository;
	
	@Autowired
	DesignacaoPregacaoRepository desigPregRepository;
	
	@Autowired
	ModalidadeRepository modalidadeRepository;
	
	@Autowired
	TipoContatoRepository tipoContatoRepository;
	
	@Autowired
	MoradorRepository moradorRepository;
	
	@GetMapping("/territorios-designacao")
	public List<TerritorioDesignadoSaidaDto> listarTerritoriosPorData(){
		return TerritorioDesignadoSaidaDto.listarTerritorios(desigTerritRepository.findByData(LocalDate.now()));
	}
	
	@GetMapping("/designacoes")
	public List<DesignacaoPregacaoSaidaDto> listarDesignacaoes(){
		return DesignacaoPregacaoSaidaDto.listarDesignacoes(desigPregRepository.findByData(LocalDate.now()));
	}
	
	@PostMapping("/designar-pregacao")
	public ResponseEntity<DesignacaoPregacaoSaidaDto> criarDesignacaoPregacao(@Valid @RequestBody DesignacaoPregacaoEntradaDto entrada) 
			throws RegistroNotFoundException{
		
		DesignacaoPregacao designacao = entrada.build(desigTerritRepository, propriedadeRepository,publicadorRepository);
		
		designacao.setId(null);
		
		designacao = desigPregRepository.save(designacao);
		
		return ResponseEntity.ok(new DesignacaoPregacaoSaidaDto(designacao));
	}
	
	@DeleteMapping("/remover-designacao/{id}")
	public Map<String, Boolean> removerDesignacaoPregacao(@PathVariable(value = "id") Long id)
		throws RegistroNotFoundException{
		
		DesignacaoPregacao desigPregacao = desigPregRepository.findById(id)
				.orElseThrow(() -> new RegistroNotFoundException("Designacao de Pregacao nao encontrado com o id: " + id));
		
		desigPregRepository.delete(desigPregacao);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("removido",Boolean.TRUE);
		return response;
	}
	
	@GetMapping("/atividade/{publicadorId}")
	public DesignacaoPregacaoSaidaDto obterAtividade(@PathVariable(value = "publicadorId") Long publicadorId) 
			throws RegistroNotFoundException{
		
		Publicador publicador = publicadorRepository.findById(publicadorId)
				.orElseThrow(() -> new RegistroNotFoundException("Publicador nao encontrado com o id: " + publicadorId));
		
		
		DesignacaoPregacao designacao = desigPregRepository.findByPublicadorAndData(publicador,LocalDate.now())
				.orElseThrow(() -> new RegistroNotFoundException("Designacao nao encontrada para o publicador: " + publicador.getNome()));
		
		return new DesignacaoPregacaoSaidaDto(designacao);
	}
	
	@GetMapping("/pregacoes/{page}/{count}/{principalId}/{nroPropriedade}")
	public Page<PregacaoSaidaDto> listarPregacao(
			@PathVariable(value="page") int page,
			@PathVariable(value="count") int count,
			@PathVariable(value = "principalId") Long principalId, 
			@PathVariable(value = "nroPropriedade") String nroPropriedade){
		
		Pageable pages = PageRequest.of(page, count,Sort.Direction.DESC,"data","id");
		
		Page<Pregacao> pregacoes;
		
		if(nroPropriedade != null && !nroPropriedade.equals("todosimoveis")) {
			pregacoes = pregacaoRepository.findByTerritorioPropriedadeNumeroPropriedade(nroPropriedade,pages);
		}
		else {
			pregacoes = pregacaoRepository.findByTerritorioPropriedadeTerritorioPrincipalId(principalId,pages);
		}
		
		return PregacaoSaidaDto.paginarPregacao(pregacoes);
	}
	
	@PostMapping("/incluir")
	public ResponseEntity<PregacaoSaidaDto> criarPregacao(@Valid @RequestBody PregacaoEntradaDto entrada) 
			throws RegistroNotFoundException{
		
		Pregacao pregacao = entrada.build(propriedadeRepository,publicadorRepository,moradorRepository,modalidadeRepository,tipoContatoRepository);
		
		pregacao.setId(null);
		
		pregacao = pregacaoRepository.save(pregacao);
		
		return ResponseEntity.ok(new PregacaoSaidaDto(pregacao));
	}
	
	@GetMapping("/modalidades")
	public List<ModalidadeSaidaDto> listarModalidade(){
		return ModalidadeSaidaDto.listarModalidade(modalidadeRepository.findAll());
	}
	
	@GetMapping("/tipos-contato")
	public List<TipoContatoSaidaDto> listarTipoContato(){
		return TipoContatoSaidaDto.listarTipoContato(tipoContatoRepository.findAll());
	}
	
	@GetMapping("/moradores/{logrSimples}/{nroPropriedade}")
	public List<MoradorSaidaDto> obterMoradores(@PathVariable(value = "logrSimples") String logrSimples, 
			@PathVariable(value = "nroPropriedade") String nroPropriedade) 
			throws RegistroNotFoundException{
		
		TerritorioPropriedade propriedade = propriedadeRepository.findByTerritorioPrincipalLogrSimplesAndNumeroPropriedade(logrSimples,nroPropriedade)
				.orElseThrow(() -> new RegistroNotFoundException("Propriedade nao encontrada: " + nroPropriedade));
	
		return MoradorSaidaDto.listarMoradores(moradorRepository.findByPropriedade(propriedade));
	}
}
