package com.example.google.gwt.mapstutorial.client;

import com.google.gwt.cell.client.TextButtonCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.StreetviewInitializedHandler;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.streetview.Pov;
import com.google.gwt.maps.client.streetview.StreetviewClient;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaOptions;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;
import com.google.gwt.maps.client.streetview.LatLngStreetviewCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widget.client.TextButton;



/*
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChocolateApp implements EntryPoint {

	private HorizontalPanel panel;
	private HorizontalPanel postReviewPanel;
	private MapWidget map;
	private StreetviewPanoramaWidget panorama;
	private StreetviewClient svClient;
	private final LatLng tenthStreet = LatLng.newInstance(33.78148, -84.38713);
	private LatLng currentLatLng = tenthStreet;
	private Pov currentPov = Pov.newInstance();
	private Polygon viewPolygon;
	private TextButton displayButton = new TextButton("Display");
	private TextButton removeDisplayButton = new TextButton("Remove Display");
	private TextButton reviewPageButton = new TextButton("Review Page");
	private TextButton removeReviewPageButton = new TextButton("Remove Review Page");
	
	private TextButton postReviewButton = new TextButton("Post Review");
	private TextButton removeReviewButton = new TextButton("Remove Review");
	
	
	private FlexTable postReviewTable = new FlexTable();
  // GWT module entry point method.
  public void onModuleLoad() {

	    HorizontalPanel linkPanel = new HorizontalPanel();
	    linkPanel.add(displayButton);
	    linkPanel.add(removeDisplayButton);
	    linkPanel.add(reviewPageButton);
	    linkPanel.add(postReviewButton);
	    linkPanel.add(removeReviewButton);
	    
	    
	    
	    RootPanel.get("links").add(linkPanel);
	    
	    panel = new HorizontalPanel();
	    displayButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	panel.clear();
	        	
	        	initializeMaps();
	        	panel.add(panorama);
	    	    panel.add(map);
	    	    RootPanel.get("maps").add(panel);
	        }
	      });
  
	    removeDisplayButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	panel.clear();
	        	//panel.remove(panorama);
	        	//panel.remove(map);
	        }
	      });
	    
	    reviewPageButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {	    
	        	panel.clear();
	        	
	        	panel.add(initializeReviewPage());
	        	RootPanel.get("reviewPage").add(panel);
	        }
	      });
	    
	    removeReviewPageButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {	     
	        	panel.clear();
	        	//panel.remove(table);
	        }
	      });
	    
	    postReviewButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	panel.clear();
	        	
	        	initializePostReviewPanel();
	        	panel.add(postReviewPanel);
	        	RootPanel.get("postReview").add(panel);
	        }
	      });
	    
	    removeReviewButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	panel.clear();
	        	//panel.remove(postReviewPanel);
	        }
	      });
  }
  
  public void initializeMaps() {
	  StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
	    options.setLatLng(tenthStreet);
	    svClient = new StreetviewClient();
	    panorama = new StreetviewPanoramaWidget(options);
	    panorama.setSize("500px", "300px");

	    map = new MapWidget(tenthStreet, 16);
	    map.setSize("500px", "300px");
	    map.addMapClickHandler(new MapClickHandler() {
	      public void onClick(MapClickEvent event) {
	        LatLng point = event.getLatLng() == null ? event.getOverlayLatLng()
	            : event.getLatLng();
	        if (point != null) {
	          svClient.getNearestPanoramaLatLng(point,
	              new LatLngStreetviewCallback() {
	                @Override
	                public void onFailure() {
	                }

	                @Override
	                public void onSuccess(LatLng point) {
	                  panorama.setLocationAndPov(point, Pov.newInstance());
	                }
	              });
	        }
	      }
	    });
	  
  }
  
  public VerticalPanel initializeReviewPage() {
	  VerticalPanel reviewPagePanel = new VerticalPanel();
	  Label label = new Label("There is no review here!");
	  
	  reviewPagePanel.add(label);
	  
	  return reviewPagePanel;
	  
	  
  }
 
  public VerticalPanel initializeReviewPage(ChocolateReview cReview) {
	  VerticalPanel reviewPagePanel = new VerticalPanel();
	  String title = "<h2>" + cReview.getStoreName() + "</h2><br/><br/><b>" +cReview.getLocation() + "</b>";
	  HTMLPanel html = new HTMLPanel(title);
	  //Label label = new Label(cReview.getStoreName());
	  //Label locationLabel = new Label(cReview.getLocation());
	  
	  FlexTable table = new FlexTable();
	  
	  table.setText(0, 0, "Review's Name:");
	  table.setText(0, 1, cReview.getReviewer());
	  table.setText(1, 0, "Rating:");
	  table.setText(1, 1, Integer.toString(cReview.getRating()));
	  table.setText(2, 0, "Price:");
	  table.setText(2, 1, Integer.toString(cReview.getPrice()));
	  table.setText(3, 0, "Review:");
	  table.setText(3, 1, cReview.getReview());
	  
	  reviewPagePanel.add(html);
	  //reviewPagePanel.add(label);
	  //reviewPagePanel.add(locationLabel);
	  reviewPagePanel.add(table);
	  return reviewPagePanel;
	    
  }
  
  public void initializePostReviewPanel() {
	  postReviewPanel = new HorizontalPanel();
	  final TextBox titleTextBox = new TextBox();
	  final TextBox locationTextBox = new TextBox();
	  
	  final TextBox pricingTextBox = new TextBox();
	  final TextBox nameTextBox = new TextBox();
	  final TextArea reviewTextArea = new TextArea();
	  
	  final ListBox ratingListBox = new ListBox();
	    ratingListBox.addItem("0");
	    ratingListBox.addItem("1");
	    ratingListBox.addItem("2");
	    ratingListBox.addItem("3");
	    ratingListBox.addItem("4");
	    ratingListBox.setVisibleItemCount(5);
	  Button submit = new Button("Submit");
	  
	  postReviewTable.setText(0, 0, "Store Name:");
	  postReviewTable.setWidget(0, 1, titleTextBox);
	  postReviewTable.setText(1, 0, "Location:");
	  postReviewTable.setWidget(1, 1, locationTextBox);
	  postReviewTable.setText(2, 0, "Rating:");
	  postReviewTable.setWidget(2, 1, ratingListBox);
	  postReviewTable.setText(3, 0, "Pricing:");
	  postReviewTable.setWidget(3, 1, pricingTextBox);
	  postReviewTable.setText(4, 0, "Reviewer's Name:");
	  postReviewTable.setWidget(4, 1, nameTextBox);
	  postReviewTable.setText(5, 0, "Review:");
	  postReviewTable.setWidget(5, 1, reviewTextArea);
	  postReviewTable.setWidget(6, 0, submit);
	  
	  postReviewPanel.add(postReviewTable);


	  submit.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	
	        	ChocolateReview cReview = new ChocolateReview(reviewTextArea.getText(), Integer.parseInt(pricingTextBox.getText()), ratingListBox.getSelectedIndex(),
	   		 titleTextBox.getText(), locationTextBox.getText(), nameTextBox.getText());

	        	panel.clear();
	        	panel.add(initializeReviewPage(cReview));
	        	RootPanel.get("reviewPage").add(panel);
	        }
	  });
	  
	  
  }
  
  
}