package com.mercado.mercadosegundamano;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mercado.mercadosegundamano.modelo.Producto;
import com.mercado.mercadosegundamano.modelo.Usuario;
import com.mercado.mercadosegundamano.servicios.ProductoServicio;
import com.mercado.mercadosegundamano.servicios.UsuarioServicio;
import com.mercado.mercadosegundamano.upload.StorageService;

@SpringBootApplication
public class MercadosegundamanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadosegundamanoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
		return args -> {

			Usuario usuario = new Usuario("Rodrigo", "Valdés García", null, "rodri.valdes@gmail.com", "rodrivg");
			usuario = usuarioServicio.registrar(usuario);

			Usuario usuario2 = new Usuario("Alfredo", "Valdés García", null, "alfredo.valdes@gmail.com", "alfredovg");
			usuario2 = usuarioServicio.registrar(usuario2);

			
			List<Producto> listado = Arrays.asList(
				    new Producto("Bicicleta de montaña", 100.0f,"https://m.media-amazon.com/images/I/81AqSbddf2L._AC_SX466_.jpg", usuario),
					new Producto("Golf GTI Serie 2", 2500.0f,"https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg",usuario),
					new Producto("Raqueta de tenis", 10.5f, "https://contents.mediadecathlon.com/p2165250/k$b53c557931dbedf2a3322d75cea6ccbb/sq/raqueta-de-tenis-babolat-boost-rafa-nadal-260-gr.jpg?format=auto&f=800x0", usuario),
					new Producto("PlayStation 5", 525.0f, "https://m.media-amazon.com/images/I/5183aMmrVdL._AC_SX466_.jpg", usuario2),
					new Producto("Teclado RGB", 35.0f, "https://m.media-amazon.com/images/I/71YIAgmeoZL._AC_SY450_.jpg", usuario2),
					new Producto("Iphone XR 128 GB", 850.0f, "https://m.media-amazon.com/images/I/51D51Wn3uzL._AC_SX522_.jpg", usuario2));
			
			listado.forEach(productoServicio::insertar);
			

		};
	}
	
	/**
	 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el almacenamiento
	 * secundario del proyecto
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
}
