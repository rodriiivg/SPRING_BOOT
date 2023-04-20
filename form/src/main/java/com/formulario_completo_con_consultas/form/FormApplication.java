package com.formulario_completo_con_consultas.form;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.formulario_completo_con_consultas.form.repositorios.EmpleadoRepository;
import com.formulario_completo_con_consultas.form.modelo.Empleado;
import com.formulario_completo_con_consultas.form.upload.storage.StorageService;
@SpringBootApplication
public class FormApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormApplication.class, args);
	}

	/**
	 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el
	 * almacenamiento secundario del proyecto
	 * 
	 * @param storageService Almacenamiento secundario del proyecto
	 * @return
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	CommandLineRunner initData(EmpleadoRepository repositorio) {
		return (args) -> {

			Empleado empleado = new Empleado("Rodrigo Valdés", "rodri.valdes@gmail.com", "985600000");
			Empleado empleado2 = new Empleado("José García", "jose.garcia@gmail.com", "985600000");
		
			repositorio.save(empleado);
			repositorio.save(empleado2);
			
			repositorio.findAll().forEach(System.out::println);

			repositorio.saveAll(
					Arrays.asList(new Empleado(1, "Rodrigo Valdés", "rodri.valdes@gmail.com", "985600000"),
							new Empleado(2, "María López", "maria.lopez@gmail.com", "985600000"),
							new Empleado(3, "Ángel Antúnez", "angel.antunez@gmail.com", "985600000")));

			List<String> nombres = Arrays.asList("Lucas", "Hugo", "Martín", "Daniel", "Pablo", "Alejandro", "Mateo",
					"Adrián", "Álvaro", "Manuel", "Leo", "David", "Mario", "Diego", "Javier", "Luis", "Marcos", "Juan",
					"José", "Gonzalo", "Lucía", "Sofía", "María", "Martina", "Paula", "Julia", "Daniela", "Valeria",
					"Alba", "Emma", "Carla", "Sara", "Noa", "Carmen", "Claudia", "Valentina", "Alma", "Ana", "Luisa",
					"Marta");

			List<String> apellidos = Arrays.asList("García", "González", "López", "Rodríguez", "Martínez", "Sánchez",
					"Pérez", "Gómez", "Martín", "Saez", "Velasco", "Moya", "Soler", "Parra", "Bravo", "Rojas", "Romero",
					"Sosa", "Torres", "Álvarez", "Flores", "Molina", "Ortiz", "Silva", "Torres");


			
			Collections.shuffle(nombres);

			repositorio.saveAll(IntStream.rangeClosed(1, nombres.size()).mapToObj((i) -> {
				String nombre = nombres.get(i-1);
				String apellido1 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				String apellido2 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				return new Empleado(String.format("%s %s %s", nombre, apellido1, apellido2), 
						String.format("%s.%s@gmail.com", nombre.toLowerCase(), apellido1.toLowerCase()), "985600000");
			}).collect(Collectors.toList()));

		};
	}

}
