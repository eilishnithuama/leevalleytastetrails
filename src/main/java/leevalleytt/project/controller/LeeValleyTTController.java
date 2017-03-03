package leevalleytt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import leevalleytt.project.entity.CustomerDetails;
import leevalleytt.project.entity.Tour;
import leevalleytt.project.repository.CustomerDetailsRepository;
import leevalleytt.project.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LeeValleyTTController {
	
	@Autowired
	private TourRepository tours;
	@Autowired
	private MailController mailController;
	@Autowired
	private CustomerDetailsRepository customerDetails;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("customerDetails",new CustomerDetails());
		return "index";
	}
	
	@RequestMapping(value="/booking",method=RequestMethod.GET)
	public String booking(Model model){
		List<Tour> toursList = tours.findByCompleted(0);
		model.addAttribute("tours",toursList);
		if(toursList.size() == 0)
			return "/";
		else
		{
			toursList = tours.findByCompleted(1);
			return "booking";
		}
	}
	
	@RequestMapping(value="/payment{tour_id}")
	public String payment(Model model,@RequestParam String tour_id){
		CustomerDetails cd = new CustomerDetails();
		List<Tour> tourList = tours.findAll();
		if(Integer.parseInt(tour_id) > tourList.size()) {
			return "booking";
		}
		else
		{
			cd.setTour(tourList.get(Integer.parseInt(tour_id)-1));
			model.addAttribute("customerDetails", cd);
			return "payment";
		}
	}
	
	@RequestMapping(value="/submitPayment",method=RequestMethod.POST)
	public String submitPayment(@ModelAttribute CustomerDetails cd,BindingResult br, Model model)
	{
		if(cd.getName().isEmpty() || cd.getExtraInfo().isEmpty() || cd.getEmail().isEmpty()){
			return "booking";
		}
		System.out.println(cd);
		//System.out.println(cd.getTour().getName());
		Stripe.apiKey = "sk_test_pib5M6qQNE3CX7xmnyatojA6";
		String token = cd.getStripeToken();

		// Charge the user's card:
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", (cd.getAmount()*100));
		params.put("currency", "eur");
		params.put("description", "Payment for tour");
		params.put("source", token);

		try {
			Charge charge = Charge.create(params);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException
				| APIException e) {
			e.printStackTrace();
		}
		
		try {
			customerDetails.save(cd);
			tours.updateAmountBooked(((cd.getTour().getAmountOfPeopleBooked())+(cd.getNoOfTickets())), cd.getId());
			mailController.sendBookingConfirmation(cd);
			mailController.sendBookingToDorothy(cd);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping("/faq")
	public String faq(){
		return "faq";
	}
	@RequestMapping("/about")
	public String about(){
		return "about";
	}
	
	@RequestMapping("/gallery")
	public String gallery(){
		return "gallery";
	}
	
	@RequestMapping("/contact")
	public String contactMe(@ModelAttribute CustomerDetails cd)
	{
		if(cd.getName().isEmpty() || cd.getExtraInfo().isEmpty() || cd.getEmail().isEmpty()){
			return "index";
		}
		try {
			mailController.sendContactQuestionToDorothy(cd);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "about";
	}

}
