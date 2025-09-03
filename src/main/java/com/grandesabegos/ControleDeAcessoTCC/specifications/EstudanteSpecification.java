package com.grandesabegos.ControleDeAcessoTCC.specifications;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import org.springframework.data.jpa.domain.Specification;

import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class EstudanteSpecification {

    public static Specification<Estudante> nomeContem(String nome) {
        return (root, query, cb) ->
            (nome == null || nome.isBlank()) ? null
                : cb.like(cb.lower(cb.trim(root.get("nome"))), "%" + nome.toLowerCase().trim() + "%");
    }

    public static Specification<Estudante> cpfIgual(String cpf) {
        return (root, query, cb) ->
            (cpf == null || cpf.isBlank()) ? null
                : cb.equal(cb.trim(root.get("cpf")), cpf.trim());
    }

    public static Specification<Estudante> ativoIgual(Boolean ativo) {
        return (root, query, cb) ->
            ativo == null ? null : cb.equal(root.get("ativo"), ativo);
    }

    public static Specification<Estudante> empresaIdIgual(Long empresaId) {
        return (root, query, cb) ->
            empresaId == null ? null : cb.equal(root.get("empresa").get("id"), empresaId);
    }

    public static Specification<Estudante> responsavelNomeContem(String responsavelNome) {
        return (root, query, cb) -> {
            if (responsavelNome == null || responsavelNome.isBlank()) return null;
            Join<Estudante, Responsavel> join = root.join("responsavel", JoinType.LEFT);
            return cb.like(cb.lower(cb.trim(join.get("nome"))), "%" + responsavelNome.toLowerCase().trim() + "%");
        };
    }

    public static Specification<Estudante> dataCadastroEntre(LocalDate inicio, LocalDate fim) {
        return (root, query, cb) -> {
            if (inicio == null && fim == null) return null;

            Instant startInstant = null;
            Instant endInstant = null;

            if (inicio != null) {
                startInstant = inicio.atStartOfDay().toInstant(ZoneOffset.UTC);
            }
            if (fim != null) {
                // fim do dia: transformar fim +1 dia às 00:00 e subtrair 1 nanos (ou usar <= nextDayStart.minusNanos(1))
                endInstant = fim.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).minusNanos(1);
            }

            if (startInstant != null && endInstant != null) {
                return cb.between(root.get("dataCadastro"), startInstant, endInstant);
            } else if (startInstant != null) {
                return cb.greaterThanOrEqualTo(root.get("dataCadastro"), startInstant);
            } else { // só endInstant
                return cb.lessThanOrEqualTo(root.get("dataCadastro"), endInstant);
            }
        };
    }
}
