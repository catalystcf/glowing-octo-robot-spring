package com.aaroshka.atopical.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aaroshka.atopical.model.Greeting;
import com.aaroshka.atopical.model.LinkModel;
import com.aaroshka.atopical.service.LinkService;

@RestController
@RequestMapping( "/links")
public class LinkController {
	
	private final LinkService  linkService;
	
	public LinkController( LinkService linkService )
	{
		this.linkService = linkService;
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LinkModel> getAllLinks() {
	    List<LinkModel> allLinks = this.linkService.allLinks();
	    		
	    for ( LinkModel linkModel: allLinks ) {
	        Link selfLink = linkTo( LinkController.class).slash( linkModel.getLinkId()).withSelfRel();
	        linkModel.add(selfLink);
	        
//	        
//	         
//	        if (orderService.getAllOrdersForCustomer(customer.getCustomerId()).size() > 0) {
//	            List<Order> methodLinkBuilder = 
//	              methodOn(CustomerController.class).getOrdersForCustomer(customer.getCustomerId());
//	            Link ordersLink = linkTo(methodLinkBuilder).withRel("allOrders");
//	            customer.add(ordersLink);
//	        }
	    }
	    return allLinks;
	}
		
	@RequestMapping("")
	public HttpEntity<LinkModel> link( @RequestParam( value="linkId", required = false ) Long linkId )
	{
		
		LinkModel lm = new LinkModel( null, linkId );
		

        // lm.add(  linkTo( methodOn(LinkController.class).link( linkId ) ) .withSelfRel());
        
        lm.add( linkTo(LinkController.class).slash( lm.getLinkId() ).withSelfRel() );
		
		return new ResponseEntity<LinkModel>( lm, HttpStatus.OK );
		
		
	}

	private static final String TEMPLATE = "Hello, %s!";
	
    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(LinkController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
	
    
    /**
     * http://www.baeldung.com/spring-hateoas-tutorial
     * At this point, the CustomerController controller can be extended with a method that return all orders of a particular customer:

@RequestMapping(value = "/{customerId}/orders", method = RequestMethod.GET)
public List getOrdersForCustomer(@PathVariable String customerId) {
    return orderService.getAllOrdersForCustomer(customerId);
}*/
}
