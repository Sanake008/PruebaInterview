package com.prueba.Prueba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.Prueba.dto.ClientDTO;
import com.prueba.Prueba.dto.OrderDTO;
import com.prueba.Prueba.dto.OrderDetailDTO;
import com.prueba.Prueba.dto.ProductDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.service.impl.ClientServiceImpl;
import com.prueba.Prueba.service.impl.OrderDetailServiceImpl;
import com.prueba.Prueba.service.impl.OrderServiceImpl;
import com.prueba.Prueba.service.impl.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(OrderServiceImpl orderService, ClientServiceImpl clientService, ProductServiceImpl productService, OrderDetailServiceImpl orderDetailService) {
		return args -> {
			// read JSON and load json
			System.out.println("***************Saving data from JSON***************");
			ObjectMapper mapper = new ObjectMapper();

			TypeReference<List<OrderDTO>> typeReference1 = new TypeReference<List<OrderDTO>>(){};
			InputStream inputStream1 = TypeReference.class.getResourceAsStream("/orders.json");

			TypeReference<List<ClientDTO>> typeReference2 = new TypeReference<List<ClientDTO>>(){};
			InputStream inputStream2 = TypeReference.class.getResourceAsStream("/clients.json");

			TypeReference<List<ProductDTO>> typeReference3 = new TypeReference<List<ProductDTO>>(){};
			InputStream inputStream3 = TypeReference.class.getResourceAsStream("/products.json");

			TypeReference<List<OrderDetailDTO>> typeReference4 = new TypeReference<List<OrderDetailDTO>>(){};
			InputStream inputStream4 = TypeReference.class.getResourceAsStream("/orderdetail.json");

			try {
				List<OrderDTO> orders = mapper.readValue(inputStream1,typeReference1);
				List<ClientDTO> clients = mapper.readValue(inputStream2,typeReference2);
				List<ProductDTO> products = mapper.readValue(inputStream3,typeReference3);
				List<OrderDetailDTO> orderDetailDTOList = mapper.readValue(inputStream4,typeReference4);
				System.out.println("Clients");
				clients.forEach(c-> {
					try {
						clientService.save(c);
					} catch (BadRequestException e) {
						throw new RuntimeException(e);
					}
				});
				System.out.println("Products");
				products.forEach(p-> {
					try {
						productService.save(p);
					} catch (BadRequestException e) {
						throw new RuntimeException(e);
					}
				});
				System.out.println("Orders");
				orders.forEach(o-> {
                    try {
                        orderService.save(o);
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                });
				System.out.println("Order Details");
				orderDetailDTOList.forEach(od-> {
					try {
						orderDetailService.save(od);
					} catch (BadRequestException e) {
						throw new RuntimeException(e);
					}
				});

				System.out.println("***************Data Saved!***************");
			} catch (IOException e){
				System.out.println("Unable to save data: " + e.getMessage());
			}
		};
	}

}
