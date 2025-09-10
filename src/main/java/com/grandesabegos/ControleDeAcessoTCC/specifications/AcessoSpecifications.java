package com.grandesabegos.ControleDeAcessoTCC.specifications;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.springframework.data.jpa.domain.Specification;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;

public class AcessoSpecifications {

    public static Specification<Acesso> empresaIdIgual(Long empresaId) {
        return (root, query, cb) ->
            empresaId == null ? null : cb.equal(root.get("empresa").get("id"), empresaId);
    }

    public static Specification<Acesso> pessoaIdIgual(Long pessoaId) {
        return (root, query, cb) ->
            pessoaId == null ? null : cb.equal(root.get("pessoa").get("id"), pessoaId);
    }

    public static Specification<Acesso> usuarioIdIgual(Long usuarioId) {
        return (root, query, cb) ->
            usuarioId == null ? null : cb.equal(root.get("usuario").get("id"), usuarioId);
    }

    public static Specification<Acesso> catracaIdIgual(Long catracaId) {
        return (root, query, cb) ->
            catracaId == null ? null : cb.equal(root.get("catraca").get("id"), catracaId);
    }

    public static Specification<Acesso> dataEntre(LocalDate inicio, LocalDate fim) {
        return (root, query, cb) -> {
            if (inicio != null && fim != null) {
                return cb.between(
                    root.get("dataAcesso"),
                    inicio.atStartOfDay().toInstant(ZoneOffset.UTC),
                    fim.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC)
                );
            } else if (inicio != null) {
                return cb.greaterThanOrEqualTo(
                    root.get("dataAcesso"),
                    inicio.atStartOfDay().toInstant(ZoneOffset.UTC)
                );
            } else if (fim != null) {
                return cb.lessThan(
                    root.get("dataAcesso"),
                    fim.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC)
                );
            } else {
                return null;
            }
        };
    }
}
