package com.example.demo.collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("books")
public class Books {
	private ObjectId _id;
	private String name;
	private Integer price;

}
