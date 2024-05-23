package com.kar.book.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesDTD {

	   private Long favoriteId;
	
	    private UsersDTO user;
	    private PostsDTO post;
	    private LocalDateTime createdAt;
	
}
