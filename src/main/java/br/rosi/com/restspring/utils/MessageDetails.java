package br.rosi.com.restspring.utils;

import lombok.Data;

@Data
public class MessageDetails {
	  private String message;

	  public MessageDetails(String message) {
	    super();
	    this.message = message;
	  }
}
