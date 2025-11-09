package br.csi.Dormez;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Dormez Hoteis API",
		version = "1.0",
		description = "API para gerenciamento de hóspedes, reservas e funcionários do Hotel Dormez.",
		contact = @Contact(name = "João Alisson", email = "joaoalissonflores@hotmail.com")
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
