package in.ashokit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;

import in.ashokit.request.Passenger;
import in.ashokit.response.Ticket;
@Service
public class MakeMyTripServiceImpl implements MakeMyTripService {
	
	private String BOOK_TICKET_URL = "http://54.163.133.56:8080/ticket";
	
	private String GET_TICKET_URL = "http://54.163.133.56:8080/ticket/{ticketNum}";

	@Override
	public Ticket bookTicket(Passenger passenger) {
		
			WebClient webClient = WebClient.create();
			
			Ticket ticket = webClient.post()
					 .uri(BOOK_TICKET_URL)	
					 .header("Accept","application/json")
					 .bodyValue(passenger)
					 //.body(BodyInserters.fromObject(passenger))
					 .retrieve()
					 .bodyToMono(Ticket.class)
					 .block();
			return ticket;
	}
		
		/*
		RestTemplate rt =new RestTemplate();
		//it is used to send post request
		 ResponseEntity<Ticket> respEntity = 
				 rt.postForEntity(BOOK_TICKET_URL,passenger, Ticket.class);
		 Ticket ticket = respEntity.getBody();
		
		return ticket;
		*/
	
	
	

	@Override
	public Ticket getTicket(Integer ticketNum) {
		
		//WebClient is a interface
				//it is introduced in 5.0
				//get the instance of webclient(impl class)
				WebClient webClient = WebClient.create();
				
				//send get request and map response to Ticket Obj
				//it is used to send the get request
				Ticket ticket = webClient.get()
						.uri(GET_TICKET_URL, ticketNum)
						//retrieve the response from the body
						.retrieve()
						//map the response to Ticket class obj
						.bodyToMono(Ticket.class)
						//you wait till u get the response from IRCTC API
						.block();//sync call
				
				return ticket;
		/*
		RestTemplate rt =new RestTemplate();
		//it is used to send get request
		ResponseEntity<Ticket> responseEntity = 
				rt.getForEntity(GET_TICKET_URL,Ticket.class,ticketNum);
		Ticket ticket = responseEntity.getBody();
		
		return ticket;
		*/
		//return null;
	
	}


}