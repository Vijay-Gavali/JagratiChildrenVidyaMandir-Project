package com.jagratichildrenvidyamandir.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class userDTO {

	@Id
	int userId;
	String name;
}
