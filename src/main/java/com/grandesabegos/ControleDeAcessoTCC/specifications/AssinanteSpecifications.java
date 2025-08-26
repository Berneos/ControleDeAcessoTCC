package com.grandesabegos.ControleDeAcessoTCC.specifications;

import java.time.Instant;

import org.springframework.data.jpa.domain.Specification;

import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;

public class AssinanteSpecifications {

    public static Specification<Assinante> nomeContem(String nome) {
        return (root, query, cb) ->
                nome == null ? null : cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Assinante> ativoIgual(Boolean ativo) {
        return (root, query, cb) ->
                ativo == null ? null : cb.equal(root.get("ativo"), ativo);
    }

    public static Specification<Assinante> planoIgual(String planoNome) {
        return (root, query, cb) -> 
            planoNome == null ? null : 
            cb.like(cb.lower(root.get("plano").get("nome")), "%" + planoNome.toLowerCase() + "%");
    }


    public static Specification<Assinante> dataVencimentoEntre(Instant inicio, Instant fim) {
        return (root, query, cb) -> {
            if (inicio != null && fim != null) {
                return cb.between(root.get("dataVencimento"), inicio, fim);
            } else if (inicio != null) {
                return cb.greaterThanOrEqualTo(root.get("dataVencimento"), inicio);
            } else if (fim != null) {
                return cb.lessThanOrEqualTo(root.get("dataVencimento"), fim);
            } else {
                return null;
            }
        };
    }
}
