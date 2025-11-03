package com.grandesabegos.ControleDeAcessoTCC.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;
import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;
import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;
import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;
import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;
import com.grandesabegos.ControleDeAcessoTCC.entities.Setor;
import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;
import com.grandesabegos.ControleDeAcessoTCC.repositories.AcessoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.AssinanteRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.CargoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.CatracaRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.EstudanteRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.FuncionarioRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PessoaPadraoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PlanoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.ResponsavelRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.SetorRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private PessoaPadraoRepository pessoaPadraoRepository; // para pessoas genéricas

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private CargoRepository cargoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PlanoRepository planoRepository;
    
    @Autowired
    private AssinanteRepository assinanteRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // importante para criptografar a senha
    
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private CatracaRepository catracaRepository;

    @Autowired
    private AcessoRepository acessoRepository;
    
    @Override
    public void run(String... args) throws Exception {

        Instant now = Instant.now();

        // --- INSTITUIÇÕES ---
        Instituicao i1 = new Instituicao(null, "Escola Estadual João Silva", "12345678000101", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.Escola);
        Instituicao i2 = new Instituicao(null, "Academia PowerFit", "98765432000199", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.Academia);
        Instituicao i3 = new Instituicao(null, "Empresa Tech Solutions", "11223344000166", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.Empresa);
        Instituicao i4 = new Instituicao(null, "Edifício Comercial Alpha Tower", "55667788000144", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.EdificioComercial);

        instituicaoRepository.saveAll(Set.of(i1, i2, i3, i4));

        // --- SETORES ---
        Setor setorTI = new Setor(null, "Tecnologia da Informação", new HashSet<>(), new HashSet<>());
        Setor setorRH = new Setor(null, "Recursos Humanos", new HashSet<>(), new HashSet<>());
        Setor setorSeg = new Setor(null, "Segurança", new HashSet<>(), new HashSet<>());

        setorRepository.saveAll(Set.of(setorTI, setorRH, setorSeg));

        // --- CARGOS ---
        Cargo cargoDev = new Cargo(null, "Desenvolvedor");
        Cargo cargoSuporte = new Cargo(null, "Suporte Técnico");
        Cargo cargoAnalistaRH = new Cargo(null, "Analista de RH");
        Cargo cargoRecrutador = new Cargo(null, "Recrutador");
        Cargo cargoVigilante = new Cargo(null, "Vigilante");
        Cargo cargoControladorAcesso = new Cargo(null, "Controlador de Acesso");

        cargoRepository.saveAll(Set.of(cargoDev, cargoSuporte, cargoAnalistaRH, cargoRecrutador, cargoVigilante, cargoControladorAcesso));

        // --- PESSOAS GENÉRICAS ---
        Pessoa p1 = new Pessoa(null, "Carlos da Silva", "11122233344", "11999999999", true, now,
                i1, "Rua das Flores, 123", null, "abugbogweobwgawgoabuwgaobuçwgaboçgwabou");
        Pessoa p2 = new Pessoa(null, "Maria Oliveira", "55566677788", "11988888888", true, now,
                i2, "Av. Central, 456", null, "aggbowçboiuwagbouiwgabuoiwga");

        pessoaPadraoRepository.saveAll(Set.of(p1, p2));

        // --- FUNCIONÁRIOS ---
        Funcionario f1 = new Funcionario(null, "João Souza", "99988877766", "11977777777", true, now,
                i3, "Rua Alpha, 999", null, "agiongoawniwanogiwgnioawganio");
        f1.setSalario(3500.0);
        f1.setSetor(setorTI);
        f1.setCargo(cargoDev);

        Funcionario f2 = new Funcionario(null, "Ana Lima", "22233344455", "11966666666", true, now,
                i3, "Av. Beta, 111", null, "wgãnpwniggwnaiwngawgoin");
        f2.setSalario(4200.0);
        f2.setSetor(setorRH);
        f2.setCargo(cargoAnalistaRH);

        Funcionario f3 = new Funcionario(null, "Pedro Santos", "33344455566", "11955555555", true, now,
                i3, "Rua Gama, 12", null, "GNWIANIWGAPNIOWAGNIPOWGAINPOWAG");
        f3.setSalario(2800.0);
        f3.setSetor(setorSeg);
        f3.setCargo(cargoVigilante);

        funcionarioRepository.saveAll(Set.of(f1, f2, f3));

        // --- ESTUDANTES ---
        Estudante e1 = new Estudante(null, "Lucas Pereira", "44455566677", "11944444444", true, now,
                i1, "Rua das Oliveiras, 50", null, "ONGAONIWAGOINWAGNIOAGWIONWGA");

        
        Responsavel r1 = new Responsavel("Jubileu", "11949357107", "jubileu@gmail.com", e1);
        e1.setResponsavel(r1);
        responsavelRepository.saveAll(Set.of(r1));
        estudanteRepository.saveAll(Set.of(e1));

     // --- USUÁRIOS ---
        Usuario u1 = new Usuario(
                null,
                "Administrador Sistema",
                "11111111111",
                "11911111111",
                true,
                now,
                i3, // vinculado à empresa Tech Solutions
                "admin",
                "admin@empresa.com",
                passwordEncoder.encode("123456") // senha criptografada
        );
        
        Usuario master = new Usuario(
                null,
                "Master Sistema",
                "00011122233",
                "11900011122",
                true, // isAdmin = true (também é admin)
                now,
                i3,
                "master",
                "master@sistema.com",
                passwordEncoder.encode("123456")
        );
        master.setIsMaster(true); // 🔑 usuário 


        Usuario u2 = new Usuario(
                null,
                "Usuário Comum",
                "22222222222",
                "11922222222",
                false,
                now,
                i1, // vinculado à Escola Estadual
                "user",
                "user@escola.com",
                passwordEncoder.encode("123456")
        );

        usuarioRepository.saveAll(Set.of(master,u1, u2));
        
        
        Plano planoBasico = new Plano(null, "Básico", 99.90, "Acesso livre durante horário comercial", new HashSet<>(), i2);
        Plano planoPremium = new Plano(null, "Premium", 199.90, "Acesso 24h + aulas especiais", new HashSet<>(), i2);
        Plano planoAnual = new Plano(null, "Anual", 999.00, "Plano anual com desconto", new HashSet<>(), i2);
        
        planoRepository.saveAll(Set.of(planoBasico, planoPremium, planoAnual));
        
        
        Assinante a1 = new Assinante(null, "Rafael Costa", "77788899900", "11933334444", true,
                now, i2, "Rua do Treino, 321", null, "BIO123456");
        a1.setPlano(planoBasico);
        a1.setDataVencimento(now.plus(30, ChronoUnit.DAYS)); // 30 dias

        Assinante a2 = new Assinante(null, "Juliana Mendes", "11144477722", "11922223333", true,
                now, i2, "Av. Saúde, 654", null, "BIO654321");
        a2.setPlano(planoPremium);
        a2.setDataVencimento(now.plus(60, ChronoUnit.DAYS)); // 60 dias

        Assinante a3 = new Assinante(null, "Paulo Henrique", "55566677788", "11955556666", false,
                now, i2, "Rua Fechada, 12", null, "BIO987654");
        a3.setPlano(planoAnual);
        a3.setDataVencimento(now.minus(15, ChronoUnit.DAYS)); // 

        assinanteRepository.saveAll(Set.of(a1, a2, a3));

     // --- CATRACAS ---
        Catraca c1 = new Catraca(null, "Entrada Principal Escola", i1);
        Catraca c2 = new Catraca(null, "Academia - Portão 1", i2);
        Catraca c3 = new Catraca(null, "Tech Solutions - Recepção", i3);
        Catraca c4 = new Catraca(null, "Alpha Tower - Entrada", i4);

        catracaRepository.saveAll(Set.of(c1, c2, c3, c4));

        // --- ACESSOS ---
        // Pessoa genérica Carlos passando pela escola
        Acesso ac1 = new Acesso(
                null,
                i1,
                p1,      // Carlos
                u2,      // Usuário comum registrou
                c1,      // catraca da escola
                now.minus(5, ChronoUnit.DAYS)
        );

        // Pessoa genérica Maria passando pela academia
        Acesso ac2 = new Acesso(
                null,
                i2,
                p2,      // Maria
                u1,      // Administrador registrou
                c2,      // catraca da academia
                now.minus(3, ChronoUnit.DAYS)
        );

        // Funcionário João (Dev) entrando na empresa
        Acesso ac3 = new Acesso(
                null,
                i3,
                f1,      // João
                u1,
                c3,      // catraca da empresa
                now.minus(1, ChronoUnit.DAYS)
        );

        // Estudante Lucas entrando na escola hoje
        Acesso ac4 = new Acesso(
                null,
                i1,
                e1,      // Lucas
                u2,
                c1,
                now
        );

        acessoRepository.saveAll(Set.of(ac1, ac2, ac3, ac4));
        
    }
}
