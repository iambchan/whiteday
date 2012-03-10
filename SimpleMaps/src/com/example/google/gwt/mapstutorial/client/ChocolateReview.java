package com.example.google.gwt.mapstutorial.client;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ChocolateReview {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;
//  @Persistent
//  private User user;
  @Persistent
  private Date createDate; 
  @Persistent
  private String review; 
  @Persistent
  private int price;
  @Persistent
  private int rating; 
  @Persistent
  private String storeName;
  @Persistent
  private String location;
  @Persistent
  private String reviewer;

  public ChocolateReview() {
    this.createDate = new Date();   
  }
//
//  public Chocolate(User user, String symbol) {
//    this();
//    this.user = user;
//    this.symbol = symbol;
//  }
  
//public User getUser() {
//return this.user;
//}

//public void setUser(User user) {
//this.user = user;
//}
  
 public ChocolateReview(String review, int price, int rating,
		 String storeName, String location, String reviewer) {
	    this();
	    this.review = review;
	    this.price = price;
	    this.rating = rating;
	    this.storeName = storeName;
	    this.location = location;
	    this.reviewer = reviewer;
	  }
  
  public Long getId() {
    return this.id;
  }

  public String getReview() {
    return this.review;
  }
  
  public int getPrice() {
	    return this.price;
	  }
  
  public int getRating() {
	    return this.rating;
	  }
  
  public String getLocation() {
	    return this.location;
	  }
  
  public String getStoreName() {
	    return this.storeName;
	  }

  public Date getCreateDate() {
    return this.createDate;
  }
  
  public String getReviewer() {
	  return this.reviewer;
  }
 

	  public void setReview(String review) {
	    this.review = review;
	  }
	  
	  public void setPrice(int price) {
		    this.price = price;
		  }
	  
	  public void setRating(int rating) {
		    this.rating = rating;
		  }
	  
	  public void setLocation(String location) {
		    this.location = location;
		  }
	  
	  public void setStoreName(String storeName) {
		    this.storeName = storeName;
		  }
	  
	  public void setReviewer(String reviewer){
		  this.reviewer = reviewer;
	  }
}