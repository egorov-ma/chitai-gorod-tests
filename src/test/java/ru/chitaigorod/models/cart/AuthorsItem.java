package ru.chitaigorod.models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorsItem{
	@JsonProperty("isForeignAgent")
	private boolean isForeignAgent;
	private String firstName;
	private String lastName;
	private String middleName;
	private int id;
	private String url;
}