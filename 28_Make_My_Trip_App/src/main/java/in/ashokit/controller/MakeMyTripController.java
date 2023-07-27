package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.request.Passenger;
import in.ashokit.response.Ticket;
import in.ashokit.service.MakeMyTripService;

@Controller
public class MakeMyTripController {
	
	@Autowired
	private MakeMyTripService service;
	
	
	@PostMapping("/book-ticket")
	public String bookTicket(@ModelAttribute("passenger")Passenger passenger,Model model) {
		
		System.out.println(passenger);
		//TODO::service layer
		Ticket bookTicket = service.bookTicket(passenger);
		//ticket object is giving to msg key
		model.addAttribute("msg","Your Ticket Booked with Id:"+bookTicket.getTicketNum());
		
		return "index";
	}
	
	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("passenger", new Passenger());
		return "index";
	}
	
	@GetMapping("/ticket")
	public String getTicketForm(Model model) {
		//ticket object is giving to ticket key
		model.addAttribute("ticket", new Ticket());
		return "ticket-form";
	}
	
	@GetMapping("/get-ticket")
	public String getTicket(@RequestParam Integer ticketNum,Model model) {
		
		Ticket ticketByNum = service.getTicket(ticketNum);
		//ticket object is giving to ticket key
		model.addAttribute("ticket", ticketByNum);
		return "ticket-form";
		
	}
	
	

}
