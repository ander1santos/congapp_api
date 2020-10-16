package org.cong.congapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.cong.congapp.dto.input.DesignacaoTerritorioEntradaDto;
import org.cong.congapp.dto.output.PropriedadesSaidaDto;
import org.cong.congapp.dto.output.TerritorioDesignadoSaidaDto;
import org.cong.congapp.dto.output.TerritorioListaSaidaDto;
import org.cong.congapp.exception.RegistroNotFoundException;
import org.cong.congapp.model.DesignacaoTerritorio;
import org.cong.congapp.model.TerritorioPrincipal;
import org.cong.congapp.model.TerritorioPropriedade;
import org.cong.congapp.repository.DesignacaoTerritorioRepository;
import org.cong.congapp.repository.PregacaoRepository;
import org.cong.congapp.repository.PublicadorRepository;
import org.cong.congapp.repository.TerritorioPrincipalRepository;
import org.cong.congapp.repository.TerritorioPropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/territorio")
public class TerritorioController {

	@Autowired
	TerritorioPrincipalRepository territorioRepository;
	
	@Autowired
	TerritorioPropriedadeRepository propriedadeRepository;
		
	@Autowired
	DesignacaoTerritorioRepository desigTerritRepository;
	
	@Autowired
	PublicadorRepository publicadorRepository;
	
	@Autowired
	PregacaoRepository pregacaoRepository;
	
	@GetMapping("/lista-territorio")
	public List<TerritorioListaSaidaDto> listarTerritorio(){
		return TerritorioListaSaidaDto.listarTerritorio(territorioRepository.findAll());
	}
	
	@GetMapping("/lista-propriedade-territorio/{id}")
	public List<PropriedadesSaidaDto> listarPropriedadeTerritorio(@PathVariable(value = "id") Long id) 
			throws RegistroNotFoundException{
		
		TerritorioPrincipal principal = territorioRepository.findById(id)
				.orElseThrow(() -> new RegistroNotFoundException("Territorio sem cadastro com id: " + id));
		
		List<TerritorioPropriedade> territPropr =  propriedadeRepository.findByTerritorioPrincipal(principal);
		
		return PropriedadesSaidaDto.listarPropriedades(territPropr);
	}
	
	@GetMapping(value="/territorios-selecionados/{page}/{count}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<TerritorioDesignadoSaidaDto> listarTerritoriosDesignados(
			@PathVariable(value="page") int page,@PathVariable(value="count") int count){
		
		Pageable pages = PageRequest.of(page, count,Sort.Direction.DESC,"data","id");
		
		Page<DesignacaoTerritorio> lista = desigTerritRepository.findAll(pages);
				
		return TerritorioDesignadoSaidaDto.paginarTerritorios(lista,pregacaoRepository);
	}
	
	@PostMapping("/designar-territorio")
	public ResponseEntity<TerritorioDesignadoSaidaDto> criarDesignacaoTerritorio(@Valid @RequestBody DesignacaoTerritorioEntradaDto entrada) 
			throws RegistroNotFoundException{
		
		DesignacaoTerritorio designacao = entrada.build(territorioRepository, propriedadeRepository, publicadorRepository);
		
		designacao.setId(null);
		
		designacao = desigTerritRepository.save(designacao);
		
		return ResponseEntity.ok(new TerritorioDesignadoSaidaDto(designacao));
	}
	
	@DeleteMapping("/remover-designacao/{id}")
	public Map<String, Boolean> removerDesignacaoTerritorio(@PathVariable(value = "id") Long id)
		throws RegistroNotFoundException{
		
		DesignacaoTerritorio designacao = desigTerritRepository.findById(id)
				.orElseThrow(() -> new RegistroNotFoundException("Designacao de Territorio nao encontrado com o id: " + id));
		
		desigTerritRepository.delete(designacao);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("removido",Boolean.TRUE);
		return response;
	}
	
}
