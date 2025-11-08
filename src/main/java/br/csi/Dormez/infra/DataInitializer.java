package br.csi.Dormez.infra;

import br.csi.Dormez.model.Usuario;
import br.csi.Dormez.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    /**
     * Inicializa o banco de dados com um usuário padrão para testes.
     * O usuário será criado apenas se ainda não existir.
     */
    @Bean
    public CommandLineRunner initUsuarios(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.findByLogin("admin@admin.com") == null) {
                Usuario admin = new Usuario();
                admin.setLogin("admin@admin.com");
                admin.setPermissao("ROLE_FUNCIONARIO"); // ou ROLE_ADMIN
                admin.setSenha(new BCryptPasswordEncoder().encode("admin"));
                usuarioRepository.save(admin);
                System.out.println("Usuário admin criado com senha 'admin'");
            }
        };
    }
}
