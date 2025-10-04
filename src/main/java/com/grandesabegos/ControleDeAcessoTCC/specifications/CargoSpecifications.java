package com.grandesabegos.ControleDeAcessoTCC.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;

public class CargoSpecifications {

    public static Specification<Cargo> nomeContem(String nome) {
        return (root, query, builder) ->
            nome == null ? null : builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Cargo> setorIdIgual(Long setorId) {
        return (root, query, builder) ->
            setorId == null ? null : builder.equal(root.get("setor").get("id"), setorId);
    }
}
