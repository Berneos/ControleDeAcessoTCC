package com.grandesabegos.ControleDeAcessoTCC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.dto.EstudanteFilterDTO;
import com.grandesabegos.ControleDeAcessoTCC.dto.EstudanteMinDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.repositories.EstudanteRepository;
import com.grandesabegos.ControleDeAcessoTCC.specifications.EstudanteSpecification;

@Service
public class EstudanteService extends PessoaService<Estudante> {

	
	
	 @Autowired
	    public EstudanteService(EstudanteRepository repo) {
	        this.repository = repo;
	    }
	
	    // regras específicas do funcionário

	 public Page<EstudanteMinDTO> filtrarEstudantes(EstudanteFilterDTO filtro, Pageable pageable) {
	        Specification<Estudante> spec = Specification
	                .where(EstudanteSpecification.nomeContem(filtro.getNome()))
	                .and(EstudanteSpecification.cpfIgual(filtro.getCpf()))
	                .and(EstudanteSpecification.ativoIgual(filtro.getAtivo()))
	                .and(EstudanteSpecification.empresaIdIgual(filtro.getEmpresaId()))
	                .and(EstudanteSpecification.responsavelNomeContem(filtro.getResponsavelNome()))
	                .and(EstudanteSpecification.dataCadastroEntre(filtro.getDataInicio(), filtro.getDataFim()));

	        return repository.findAll(spec, pageable).map(EstudanteMinDTO::new);
	    }
	 
	 
}

