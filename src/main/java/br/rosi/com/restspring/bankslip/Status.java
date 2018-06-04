package br.rosi.com.restspring.bankslip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.rosi.com.restspring.utils.EnumUtils;

public enum Status {
	
	PENDING,
	PAID,
	CANCELED;
	
	@JsonCreator
	public static Status fromValue(String value){
		return EnumUtils.getEnumFromString(Status.class, value);
	}
	
	@JsonValue
    public String toJson() {
        return name().toUpperCase();
    }

}
