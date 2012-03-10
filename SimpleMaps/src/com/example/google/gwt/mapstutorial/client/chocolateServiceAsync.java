package com.example.google.gwt.mapstutorial.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface chocolateServiceAsync {

	void addChocolateReview(String review, int price, int rating,
			 String storeName, String location, String reviewer, AsyncCallback<Void> callback);

	void getChocolateReview(AsyncCallback<String[]> callback);

	void removeChocolateReview(String symbol, AsyncCallback<Void> callback);

}
