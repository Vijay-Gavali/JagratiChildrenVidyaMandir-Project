package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class user {

	@Id
	int userId;

	String name;
}
