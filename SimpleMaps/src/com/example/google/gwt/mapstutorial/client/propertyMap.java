package com.example.google.gwt.mapstutorial.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class propertyMap {
	MapWidget map;
	
	public propertyMap()
	{
		map = new MapWidget();
		map.setSize("100%", "100%");
	}
	
	public void buildUi() {
	  
	    // Open a map centered on vancouver
	    LatLng vancouver = LatLng.newInstance(49.264448, -123.185844);
		map.setCenter(vancouver, 10);
	   
	    // Add some controls for the zoom level
	    map.addControl(new LargeMapControl());
	    
	     // can remove later, just for testing**************
	     // Add a marker
	    map.addOverlay(new Marker(vancouver));
	    // Add an info window to highlight a point of interest
	    map.getInfoWindow().open(map.getCenter(),
	        new InfoWindowContent("Vancouver"));
	    // end test*****************************************
	    
//	    
//	    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
//	    dock.addNorth(map, 500);
//
//	    // Add the map to the HTML host page
//	    RootLayoutPanel.get().add(dock);
	  }
	
	  /*
	   * Finds the location and plots it on the map!
	   * 
	   */  
	  public void findLocation(final String location)
	  {
		  LatLngCallback callback = new LatLngCallback() {

		 	   public void onFailure() {
		 	   //address was not found. do nothing
		 		   // insert some error handling here
		 		   // message pop-up
		 	   }

		 	   public void onSuccess(LatLng point) {
		 	   // add the location onto the map
		 	   Marker marker = new Marker(point);
		 	   map.addOverlay(marker);
		 	   map.setCenter(point);
		 	   VerticalPanel panel = new VerticalPanel();
		 	   InfoWindowContent content = new InfoWindowContent(panel);
		 	   panel.add(new Label(location));
		 	   map.getInfoWindow().open(marker, content);
		 	   }
		 	   };
		 	   Geocoder geocoder = new Geocoder();
		 	   geocoder.getLatLng(location, callback);
	  }
		/**
		 * Returns reference to the MapWidget
		 */
		public MapWidget getMap() {
			return this.map;
		}
	
}
