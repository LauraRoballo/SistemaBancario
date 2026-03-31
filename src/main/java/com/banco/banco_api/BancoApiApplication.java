package com.banco.banco_api;

//import com.banco.banco_api.entity.Cliente;
//import com.banco.banco_api.repository.ClienteRepository;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BancoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApiApplication.class, args);
	}
}
// Se comenta la prueba realizada para que no se cree un cliente nuevo cada que se ejecute
/*
	@Bean
	public CommandLineRunner testDatabase(ClienteRepository repository) {
		return args -> {
			// 1. Creamos un objeto Cliente
			Cliente nuevoCliente = new Cliente();
			nuevoCliente.setNombre("Laura");
			nuevoCliente.setDocumento("12345678");
			nuevoCliente.setEmail("lauradaniela@prueba.com");


			// 2. Le pedimos al repositorio que lo guarde en MySQL
			repository.save(nuevoCliente);

			// 3. Imprimimos en consola para confirmar
			System.out.println("El cliente se guardó en la base de datos.");
		};
	}
*/
