package com.example.google.gwt.mapstutorial.client;

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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



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
	private Button displayButton = new Button("Display");
	private Button removeDisplayButton = new Button("Remove Display");
	private Button reviewPageButton = new Button("Review Page");
	private Button removeReviewPageButton = new Button("Remove Review Page");
	
	private Button postReviewButton = new Button("Post Review");
	private Button removeReviewButton = new Button("Remove Review");
	
	private FlexTable table = new FlexTable();
	private FlexTable postReviewTable = new FlexTable();
  // GWT module entry point method.
  public void onModuleLoad() {

	    HorizontalPanel linkPanel = new HorizontalPanel();
	    linkPanel.add(displayButton);
	    linkPanel.add(removeDisplayButton);
	    linkPanel.add(reviewPageButton);
	    linkPanel.add(postReviewButton);
	    linkPanel.add(removeReviewButton);
	    
	    RootPanel.get().add(linkPanel);
	    
	    panel = new HorizontalPanel();
	    displayButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	panel.clear();
	        	
	        	initializeMaps();
	        	panel.add(panorama);
	    	    panel.add(map);
	    	    RootPanel.get().add(panel);
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
	        	
	        	initializeReviewPage();
	        	panel.add(table);
	        	RootPanel.get().add(panel);
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
	        	RootPanel.get().add(panel);
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
 
  public void initializeReviewPage() {
	  table.setText(0, 0, "Symbol");
	  table.setText(0, 1, "Price");
	  table.setText(0, 2, "Change");
	  table.setText(0, 3, "Remove");
	    
  }
  
  public void initializePostReviewPanel() {
	  postReviewPanel = new HorizontalPanel();
	  TextBox titleTextBox = new TextBox();
	  
	  postReviewTable.setText(0, 0, "Title");
	  postReviewTable.setWidget(0, 1, titleTextBox);
	  
	  postReviewPanel.add(postReviewTable);
	  
	  
  }
  
  
}