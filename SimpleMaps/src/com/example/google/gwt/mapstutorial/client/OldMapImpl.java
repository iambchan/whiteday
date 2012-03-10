package com.example.google.gwt.mapstutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.streetview.Pov;
import com.google.gwt.maps.client.streetview.StreetviewClient;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;



/*
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OldMapImpl implements EntryPoint {



	
  // GWT module entry point method.
  public void onModuleLoad() {
   /*
    * Asynchronously loads the Maps API.
    *
    * The first parameter should be a valid Maps API Key to deploy this
    * application on a public server, but a blank key will work for an
    * application served from localhost.
   */
//   Maps.loadMapsApi("AIzaSyBjDoqh_8ZFoxVvvW4ETGdJvADLzTX6-sI", "2", false, new Runnable() {
//      public void run() {
//        buildUi();
//      }
//    });
//   
   
//   Maps.loadMapsApi("", "2", false, new Runnable() {
//	      public void run() {
//	        buildUi();
//	        findLocation(map, "whistler");
//	      }
//	    });
	  
	  propertyMap theMap = new propertyMap();
	  theMap.buildUi();
	  // Add map to the container
	  //mapContainerPanel.add(theMap.getMap());
	  
	  // build streetview map
	  
	  
	  
	  // Add street view map to the container
	  // streetViewContainerPanel.add(streetMap.getMap());
	  
	  
	  // assemble map panel
		//mapsPanel.add(mapContainerPanel);	  	
		//mapsPanel.add(streetViewContainerPanel);
  
	    Button theButton = new Button();
	    theButton.setText("test");
	    HorizontalPanel panel = new HorizontalPanel();
	    //panel.setSize("100%","500px");
	    //SimplePanel sp = new SimplePanel(); 
	    //sp.setSize("100%", "100%");
	    panel.add(theMap.getMap());
	    //panel.add(sp);
	    panel.add(theButton);
	    panel.setCellWidth(theMap.getMap(), "500px");
	    panel.setCellHeight(theMap.getMap(), "500px");
	    panel.setCellHorizontalAlignment(theMap.getMap(), HorizontalPanel.ALIGN_LEFT);
	    panel.setCellHorizontalAlignment(theButton, HorizontalPanel.ALIGN_RIGHT);
	    
	    panel.setWidth("100%");
	    RootPanel.get().add(panel);
	    
	    
  }
  
}