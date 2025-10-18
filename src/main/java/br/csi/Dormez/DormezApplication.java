package br.csi.Dormez;

import br.csi.Dormez.model.CargoEnum;
import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(info = @Info(
		title = "API Exemplo",
		version = "1.0",
		description = "Documentação API Exemplo",
		contact = @Contact(name = "Suporte", email = "suporte@exemplo.com")
))

@SpringBootApplication
public class DormezApplication {

	public static void main(String[] args) {
		SpringApplication.run(DormezApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(FuncionarioRepository repository) {
		return args -> {
			Funcionario func = new Funcionario("Maria Silva", "maria@hotel.com", "11999999999", CargoEnum.RECEPCIONISTA);
			repository.save(func);

			System.out.println("Funcionário salvo com sucesso! " + func);
		};
	} */

}
