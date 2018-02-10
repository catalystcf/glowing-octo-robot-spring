package com.aaroshka.atopical.model;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkModel extends ResourceSupport {
	
	private final String href;
	private final Long linkId;

	@JsonCreator
	public LinkModel( @JsonProperty( "href") String href, 
				      @JsonProperty( "href") Long linkId
			) 
	{
		this.href     = href;
		this.linkId   = linkId;
	}
	
	public String getHref() {
		return this.href;
	}
	
	public Long getLinkId() {
		return this.linkId;
	}
	
}
