package com.aaroshka.atopical.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aaroshka.atopical.model.LinkModel;

@Service
public class LinkService {

	public LinkModel linkModelMockFactory( Long linkId) {
		LinkModel lm = new LinkModel( "https://to.nowhere.com", linkId );
		return lm;
	}
	

	public List<LinkModel> allLinks() {
		// TODO: how to createa a stream of numbers
		
		LinkModel lm1 = this.linkModelMockFactory(1L);
		LinkModel lm2 = this.linkModelMockFactory(2L);
		LinkModel lm3 = this.linkModelMockFactory(3L);
		
		List<LinkModel> allLinks = new ArrayList<LinkModel>(3);
		allLinks.add( lm1 );
		allLinks.add( lm2 );
		allLinks.add( lm3 );
		return allLinks;
	}
	
	
	
	

}
