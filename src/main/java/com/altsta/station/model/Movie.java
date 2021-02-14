package com.altsta.station.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//{ Item: {year=1989, name=Bill & Ted's Excellent Adventure, rating=****, fans=[James, Sara]} }

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Movie {
	
	private String year;
	private String name;
	private String rating;
	private List<String> fans;

}
