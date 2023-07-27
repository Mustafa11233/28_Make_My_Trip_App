package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {

	
		
		static String url = "http://54.163.133.56:8080/ticket/{ticketNum}";

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
			
			WebClient webClient = WebClient.create();
			System.out.println("request sending start.......");
			                  webClient.get()
					                   .uri(url,6)
					                   .retrieve()
					                   .bodyToMono(String.class)
					                   //for block() method their is return type
					                   //for subscribe() method no return type
					                   //.block();  Sync
					                   //it is a network call(AWS cloud),it will take 2 to 3 seconds
					                   //when u get the request don't wait for the response
					                   //continue your execution,after getting the response 
					                   //give to the handlerResponse method,this method will handle
					                   //the response
					                   .subscribe(Application::handlerResponse);//ASync
					                  
			System.out.println("request sending end .......");
					 
		}
		public static void handlerResponse(String response) {
			System.out.println(response);
		}
		
	}


