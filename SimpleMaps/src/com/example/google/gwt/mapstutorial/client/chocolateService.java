package com.example.google.gwt.mapstutorial.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("chocolateReview")
public interface chocolateService extends RemoteService {
  public void addChocolateReview(String review, int price, int rating,
			 String storeName, String location, String reviewer);
  public void removeChocolateReview(String symbol);
  public String[] getChocolateReview();
}