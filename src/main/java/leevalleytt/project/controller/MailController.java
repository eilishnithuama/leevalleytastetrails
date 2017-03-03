package leevalleytt.project.controller;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import leevalleytt.project.entity.CustomerDetails;

@Component
public class MailController {

	@Autowired
    private JavaMailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendConfirmationMail(CustomerDetails customer) {
        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(customer.getEmail());
        msg.setText(
            "Dear " + customer.getName()     
                + ",\n Thank you for your booking with Lee Valley Taste Trails!." +
                "You are now booked for the "
                + customer.getTour().getName() + " on the " + customer.getTour().getDate());
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void sendBookingConfirmation(CustomerDetails cd) throws MessagingException{
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper;
    	helper = new MimeMessageHelper(message,true);
    	
    	String emailBody = "<h3>Tour Booked!</h3>" +
    			"Hi " + cd.getName() + "," +
				"<p>You have been booked for the <b>\"" + cd.getTour().getName() +
				"\"</b> tour with us on <b>" + new SimpleDateFormat("EEEE, MMMM d",Locale.ENGLISH).format(cd.getTour().getDate()) +
				"</b> and have successfully booked " + cd.getNoOfTickets() + " ticket(s). </p>" +
				"<p> Thank you and we look forward to seeing you on the tour! </p>" +
				"Dorothy O'Tuama <br/> LeeValleyTasteTrails";
				
		helper.setSubject("Tour Booking Confirmation");
		helper.setTo("dotuama@live.com");
		helper.setText(emailBody,true);
		
		mailSender.send(message);
    }
    
    public void sendBookingToDorothy(CustomerDetails cd) throws MessagingException{
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper;
    	helper = new MimeMessageHelper(message,true);
    	
    	String emailBody = "<h3>New booking!</h3>" +
    						"<p>" + cd.getName() + " has booked for " + cd.getTour().getName() +
    						" on the <br/>" + new SimpleDateFormat("EEE, MMM d",Locale.ENGLISH).format(cd.getTour().getDate()) +
    						"<br/> and has booked " + cd.getNoOfTickets() + " tickets </p>" +
    						"<p> They provided the following extra information: <br/>" + cd.getExtraInfo() + "</p>" +
    						"<p> This tour now has " + cd.getTour().getAmountOfPeopleBooked() +
    						" people booked. </p>";
    						
    	helper.setSubject("New Tour Booking");
    	helper.setTo("dotuama@live.com");
    	helper.setText(emailBody,true);
    	
    	mailSender.send(message);	
    }
    
    public void sendContactQuestionToDorothy(CustomerDetails cd) throws MessagingException{
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper;
    	helper = new MimeMessageHelper(message,true);
    	
    	String emailBody = "<h3>Contact Request</h3>" +
    						"<p><b>Name:</b> " + cd.getName() + "</br> " +
    						"<b>Email:</b> " + cd.getEmail() + "</br> " +
    						"<b>Phone Number:</b> " + cd.getPhoneNo() + "</br> " +
    						"<b>Information:</b>" + "</br> " + cd.getExtraInfo() + "</p>";
    						
    	helper.setSubject("Contact Me Request");
    	helper.setTo("dotuama@live.com");
    	helper.setFrom(cd.getEmail());
    	helper.setText(emailBody,true);
    	
    	mailSender.send(message);
    	
    }
    
    public void send() throws MessagingException{
    	
    }
}


