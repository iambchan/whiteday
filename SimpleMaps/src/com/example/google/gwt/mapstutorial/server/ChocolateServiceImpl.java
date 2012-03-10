package com.example.google.gwt.mapstutorial.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.example.google.gwt.mapstutorial.client.ChocolateReview;
import com.example.google.gwt.mapstutorial.client.chocolateService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ChocolateServiceImpl extends RemoteServiceServlet implements
chocolateService {

  private static final Logger LOG = Logger.getLogger(ChocolateServiceImpl.class.getName());
  private static final PersistenceManagerFactory PMF =
      JDOHelper.getPersistenceManagerFactory("transactions-optional");

  public void addChocolateReview(String review, int price, int rating,
			 String storeName, String location, String reviewer) {
    PersistenceManager pm = getPersistenceManager();
    try {
      pm.makePersistent(new ChocolateReview(review,price, rating,
 			 storeName, location, reviewer));
    } finally {
      pm.close();
    }
  }


@Override
public String[] getChocolateReview() {
	// TODO Auto-generated method stub
//    PersistenceManager pm = getPersistenceManager();
//    List<String> symbols = new ArrayList<String>();
//    try {
//      Query q = pm.newQuery(Stock.class, "user == u");
//      q.declareParameters("com.google.appengine.api.users.User u");
//      q.setOrdering("createDate");
//      List<Stock> stocks = (List<Stock>) q.execute(getUser());
//      for (Stock stock : stocks) {
//        symbols.add(stock.getSymbol());
//      }
//    } finally {
//      pm.close();
//    }
//    return (String[]) symbols.toArray(new String[0]);
	return null;
}

@Override
public void removeChocolateReview(String symbol) {
	// TODO Auto-generated method stub
//	 PersistenceManager pm = getPersistenceManager();
//	    try {
//	      long deleteCount = 0;
//	      Query q = pm.newQuery(Stock.class, "user == u");
//	      q.declareParameters("com.google.appengine.api.users.User u");
//	      List<Stock> stocks = (List<Stock>) q.execute(getUser());
//	      for (Stock stock : stocks) {
//	        if (symbol.equals(stock.getSymbol())) {
//	          deleteCount++;
//	          pm.deletePersistent(stock);
//	        }
//	      }
//	      if (deleteCount != 1) {
//	        LOG.log(Level.WARNING, "removeStock deleted "+deleteCount+" Stocks");
//	      }
//	    } finally {
//	      pm.close();
//	    }
	
}

private PersistenceManager getPersistenceManager() {
    return PMF.getPersistenceManager();
  }
}