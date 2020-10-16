package org.cong.congapp.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.cong.congapp.dto.input.DesignacaoPregacaoEntradaDto;
import org.cong.congapp.dto.output.DesignacaoPregacaoSaidaDto;
import org.cong.congapp.dto.output.TerritorioDesignadoSaidaDto;
import org.cong.congapp.exception.RegistroNotFoundException;
import org.cong.congapp.model.DesignacaoPregacao;
import org.cong.congapp.repository.DesignacaoPregacaoRepository;
import org.cong.congapp.repository.DesignacaoTerritorioRepository;
import org.cong.congapp.repository.PregacaoRepository;
import org.cong.congapp.repository.PublicadorRepository;
import org.cong.congapp.repository.TerritorioPrincipalRepository;
import org.cong.congapp.repository.TerritorioPropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@DeleteMapping("/remover-desig-pregacao/{id}")
	public Map<String, Boolean> removerDesignacaoPregacao(@PathVariable(value = "id") Long id)
		throws RegistroNotFoundException{
		
		DesignacaoPregacao desigPregacao = desigPregRepository.findById(id)
				.orElseThrow(() -> new RegistroNotFoundException("Designacao de Pregacao nao encontrado com o id: " + id));
		
		desigPregRepository.delete(desigPregacao);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("removido",Boolean.TRUE);
		return response;
	}
}
