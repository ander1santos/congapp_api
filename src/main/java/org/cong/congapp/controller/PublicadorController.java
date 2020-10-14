package org.cong.congapp.controller;

import java.util.List;

import org.cong.congapp.dto.output.PublicadorDesignacaoSaidaDto;
import org.cong.congapp.model.Publicador;
import org.cong.congapp.repository.PublicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publicador")
public class PublicadorController {

	@Autowired
	PublicadorRepository publicadorRepository;
	
	@GetMapping("/publicadores-designacao")
	public List<PublicadorDesignacaoSaidaDto> listarPublicadoresDesignacao(){
		return PublicadorDesignacaoSaidaDto.listarPublicadores((List<Publicador>) publicadorRepository.findAll()); 
	}
}
