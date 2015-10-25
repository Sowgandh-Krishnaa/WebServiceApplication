/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import org.w3c.dom.Document;
/**
 *
 * @author sowgandh
 */
public class MashUp {

    /**
     * @param args the command line arguments
     */
    static MongoClient mongo;
    static DB db1;
        static DB db2;

    static DBCollection table1;static DBCollection table2;
    
    public MashUp(){
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MashUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        db1 = mongo.getDB("API");
        db2 = mongo.getDB("MashUp");
        table1 = db1.getCollection("APIS");
          table2 = db2.getCollection("MashUps");
    }
    HashMap<String,String> api = new HashMap<String,String>() ;
    HashMap<String,String> mashup = new HashMap<String,String>();
   // StringBuilder apistring = new StringBuilder();
    //StringBuilder mashupstring = new StringBuilder();
   // String abc = "";
   
    
    public void readApi() throws Exception{
        
       // File f1 = new File("C:\\Users\\sowgandh\\Desktop\\API_&_MASHUP Text\\api.txt");
BufferedReader br = new BufferedReader(new FileReader("/Users/Pranav/Desktop/api.txt"));
       BufferedReader br2 = new BufferedReader(new FileReader("/Users/Pranav/Desktop/mashup.txt"));
        try {
       
        String line1 = "";
       // String line2 = br2.readLine();
        int count=0;
          
       
         
         
         
         
        
         
         
         while ((line1 = br.readLine()) != null) {
         
           ++count;
            String[] token1 = line1.split("\\$\\#\\$");
             BasicDBObject document = new BasicDBObject();
            for(int i=0;i<token1.length;i++){
             
               // System.out.println( i + " " +token1[i]);
                //System.out.println();
                 switch(i){
               case 0 :{
                   //System.out.println(token1[i]);
                   document.put("id", token1[i]);
                    api.put("id",token1[i]);
                   break;
               }
               case 1 :{
                   //System.out.println(token1[i]);
                   api.put("title",token1[i]);
                   document.put("title", token1[i]);
                   break;
               }
               case 2 :{
                   //System.out.println(token1[i]);
                   document.put("summary", token1[i]);
                   api.put("summary",token1[i]);
                   break;
               }
               case 3 :{
                   if(token1[i].equals("")){
                        document.put("rating",0.0);
                   }else{
                       DecimalFormat df = new DecimalFormat("#.#");
                       
                   document.put("rating", Double.parseDouble(df.format(Double.parseDouble(token1[i]))));
                   api.put("rating",token1[i]);
                   }
                   break;
               }
                case 4 :{
                   
                   document.put("name", token1[i]);
                   api.put("name",token1[i]);
                   break;
               }
               case 7 :{
                   document.put("description", token1[i]);
                   api.put("description",token1[i]);
                   break;
               }
               
                case 17 :{
                   String tags[] = token1[i].split("\\#\\#\\#");
                  //  System.out.println("Tags lenght "+tags.length);
                   String tag="";
                    for(int j=0;j<tags.length;j++){
                        tag=tag+tags[j]+" ";
                    }
                   // System.out.println("Tags are "+tag);
                    document.put("Tags", tag);
                    api.put("Tags",tag);
                   break;
               }
                case 18 :{
                    document.put("category", token1[i]);
                   api.put("category",token1[i]);
                   break;
               }
                case 19 :{
                    document.put("protocols", token1[i]);
                   api.put("protocols",token1[i]);
                   break;
               }
                
               case 45 :{
                    document.put("updated", token1[i]);
                   api.put("updated",token1[i]);
                   break;
               }
               default :{
                   //System.out.println("Error");
                   break;
               }
           
           }
         
            }
           

           
          table1.insert(document);
        
     
          
        }
          //  line = br.readLine();
        
          
    
        /*
        second
        */
        
        System.out.println();System.out.println();System.out.println();
        
        
        String line2="";
        
        int count2=0;
        while ((line2 = br2.readLine()) != null) {
         
           ++count2;
            String[] token1 = line2.split("\\$\\#\\$");
             BasicDBObject document = new BasicDBObject();
            for(int i=0;i<token1.length;i++){
             
                //System.out.println( i + " " +token1[i]);
                //System.out.println();
                switch(i){
               case 0 :{
                   //System.out.println(token1[i]);
                   document.put("id", token1[i]);
                    api.put("id",token1[i]);
                   break;
               }
               case 1 :{
                   //System.out.println(token1[i]);
                   api.put("title",token1[i]);
                   document.put("title", token1[i]);
                   break;
               }
               case 2 :{
                   //System.out.println(token1[i]);
                   document.put("summary", token1[i]);
                   api.put("summary",token1[i]);
                   break;
               }
               case 3 :{
                   document.put("rating", token1[i]);
                   api.put("rating",token1[i]);
                   break;
               }
               
               case 4:{
                    document.put("name", token1[i]);
                   api.put("name",token1[i]);
                   break;
               }
               
               case 7 :{
                   document.put("description", token1[i]);
                   api.put("description",token1[i]);
                   break;
               }
               
                case 15 :{
                   String tags[] = token1[i].split("\\#\\#\\#");
                  //  System.out.println("Tags lenght "+tags.length);
                   String tag="";
                    for(int j=0;j<tags.length;j++){
                        tag=tag+tags[j]+" ";
                    }
                   // System.out.println("Tags are "+tag);
                    document.put("Tags", tag);
                    api.put("Tags",tag);
                   break;
               }
                case 16 :{
                    String temp[] = token1[i].split("\\#\\#\\#");
                   String tag="";
                    for(int j=0;j<temp.length;j++){
                     // System.out.println("Tags are "+temp[j]);
                       String g[] = temp[j].split("\\$\\$\\$");
                       
                        for(int t=0;t<g.length;t++){
                        if(t%2==0){
                          
                            tag=tag+"OO"+g[t]+" ";
                          // tag=tag+temp[j]+"(";
                          // System.out.println("1 "+tag);
                        
                        }
                        }
                       }
                    //System.out.println("Tags are "+tag);
                    document.put("APIs", tag);
                   api.put("Apis",token1[i]);
                   break;
               }
                case 17 :{
                    document.put("updated", token1[i]);
                   api.put("updated",token1[i]);
                   break;
               }
                
               
               default :{
                   //System.out.println("Error");
                   break;
               }
           
           }
         
            }
           
            
           
          table2.insert(document);
        
          
        }
           
       
       
        
    } finally {
        
        br.close();
        br2.close();
    }     
    }
    
    
   
    
    public ArrayList<String> getApibasedonProtocols(String protocol){
        ArrayList<String> api = new ArrayList<String>();
        BasicDBObject whereQuery = new BasicDBObject();
	whereQuery.put("protocols", protocol);
	DBCursor cursor = table1.find(whereQuery);
	while(cursor.hasNext()) {
	    String temp =  cursor.next().get("id").toString();
            api.add(temp);
	}
        
        return api;
        
    }
    
    public ArrayList<String> getApibasedonCategory(String category){
        ArrayList<String> api = new ArrayList<String>();
        BasicDBObject whereQuery = new BasicDBObject();
	
        whereQuery.put("category", category);
	DBCursor cursor = table1.find(whereQuery);
	while(cursor.hasNext()) {
	    String temp =  cursor.next().get("id").toString();
            api.add(temp);
	}
        
        return api;
        
    }
    
     public ArrayList<String> getApibasedonTags(String tags){
        DBCursor cursor = table1.find();
        
        ArrayList<String> api = new ArrayList<String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
          String tags_1[] = tags.split(" ");
           
          for(int i=0;i<tags_1.length;i++){
          if(temp.get("Tags").toString().contains(tags_1[i])){
               
               api.add(temp.get("id").toString());
           }
          }
	}
        return api;
    }
    
     public HashMap<String,String> getApibasedonRating(float rating){
         HashMap<String,String> api = new HashMap<String,String>();
         DBCursor cursor = table1.find();
       
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
           //System.out.println(temp.get("id").toString()+ " "+temp.get("rating").toString());
           if(temp.get("rating").toString()==""){
               temp.put("rating", "0.0");
           }
           if(Float.parseFloat(temp.get("rating").toString())>rating){
              if(api.get("Higher")==null){
                  
                  api.put("Higher", temp.get("id").toString());
                  
              }else{
                  if(!api.get("Higher").contains(temp.get("id").toString())){
                  String temp1 = api.get("Higher")+" "+temp.get("id").toString();
                  api.put("Higher",temp1);
                  }
              }
           }else if(Float.parseFloat(temp.get("rating").toString())<rating){
               if(api.get("Lower")==null){
                  api.put("Lower", temp.get("id").toString());
              }else{
                  if(!api.get("Lower").contains(temp.get("id").toString())){
                  String temp1 = api.get("Lower")+" "+temp.get("id").toString();
                  api.put("Lower",temp1);
                  }
              }
           }else{
               if(api.get("Equals")==null){
                  api.put("Equals", temp.get("id").toString());
              }else{
                  if(!api.get("Equals").contains(temp.get("id").toString())){
                  String temp1 = api.get("Equals")+" "+temp.get("id").toString();
                  api.put("Equals",temp1);
                  }
              }
           }
	}
        return api;
         
     }
    
    public ArrayList<String> getapibyYear(String year){
       DBCursor cursor = table1.find();
        
        ArrayList<String> api = new ArrayList<String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
            
           if(temp.get("updated").toString().contains(year)){
               
               api.add(temp.get("id").toString());
           }
	}
        return api;
    }
     
    
     public HashMap<String,String> getInformation(String keywords ){
          DBCursor cursor = table1.find();
        
       HashMap<String,String> api = new HashMap<String,String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
           String keywords_list[] = keywords.split(" ");
           for(int i=0;i<keywords_list.length;i++){ 
           if(temp.get("title").toString().contains(keywords_list[i])){
               if(api.get("title")==null){
                   
                   api.put("title","APINAME"+ temp.get("id")+"$$"+temp.get("title").toString());
               }else{
                  if(!api.get("title").contains("APINAME"+ temp.get("id")+"$$"+temp.get("title").toString())){
                   api.put("title",api.get("title")+" "+"APINAME"+ temp.get("id")+"$$"+temp.get("title").toString());
                   }
                   }
               
           }if(temp.get("summary").toString().contains(keywords_list[i])){
               if(api.get("summary")==null){
                   System.out.println("Entering");
                   api.put("summary","APINAME"+ temp.get("id")+"$$"+temp.get("summary").toString());
               }else{
                   if(!api.get("summary").contains("APINAME"+ temp.get("id")+"$$"+temp.get("summary").toString())){
                   api.put("summary",api.get("summary")+" "+"APINAME"+ temp.get("id")+"$$"+temp.get("summary").toString());
                   }
               }
               
           }if(temp.get("description").toString().contains(keywords_list[i])){
               if(api.get("description")==null){
                   System.out.println("Entering");
                   api.put("description","APINAME"+ temp.get("id")+"$$"+temp.get("description").toString());
               }else{
                   if(!api.get("description").contains("APINAME"+ temp.get("id")+"$$"+temp.get("description").toString())){
                   api.put("description",api.get("description")+" "+"APINAME"+ temp.get("id")+"$$"+temp.get("description").toString());
                   }
               }
               
           }
           }
	}
        return api;
    }
    
    
    public ArrayList<String> getMashUpbyYear(String year){
       DBCursor cursor = table2.find();
        
        ArrayList<String> api = new ArrayList<String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
            
           if(temp.get("updated").toString().contains(year)){
               
               api.add(temp.get("id").toString());
           }
	}
        return api;
    }
    public ArrayList<String> getMashUpbyUsedApis(String Api){
       DBCursor cursor = table2.find();
        
        ArrayList<String> api = new ArrayList<String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
            
           if(temp.get("APIs").toString().contains(Api)){
               
               api.add(temp.get("APIs").toString());
           }
	}
        return api;
    }
    
    
    public ArrayList<String> getMashUpbasedonTags(String tags){
        DBCursor cursor = table2.find();
        
        ArrayList<String> api = new ArrayList<String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
          String tags_1[] = tags.split(" ");
           
          for(int i=0;i<tags_1.length;i++){
          if(temp.get("Tags").toString().contains(tags_1[i])){
               
               api.add(temp.get("id").toString());
           }
          }
	}
        return api;
    }
    
    public ArrayList<String>getMashUpVisual(){
        DBCursor cursor = table1.find();
        
        ArrayList<String> api = new ArrayList<String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
         
           
          
          if(temp.get("updated").toString().contains("2013")){
               
               api.add(temp.get("name").toString());
           }
          
	}
        return api;
    }
    
    
      public HashMap<String,String> getMashUpInformation(String keywords ){
       DBCursor cursor = table2.find();
        
       HashMap<String,String> api = new HashMap<String,String>();
	while(cursor.hasNext()) {
	   DBObject temp = cursor.next();
           String keywords_list[] = keywords.split(" ");
           for(int i=0;i<keywords_list.length;i++){ 
           if(temp.get("title").toString().contains(keywords_list[i])){
               if(api.get("title")==null){
                   
                   api.put("title","APINAME"+ temp.get("id")+"$$"+temp.get("title").toString());
               }else{
                  if(!api.get("title").contains("APINAME"+ temp.get("id")+"$$"+temp.get("title").toString())){
                   api.put("title",api.get("title")+" "+"APINAME"+ temp.get("id")+"$$"+temp.get("title").toString());
                   }
                   }
               
           }if(temp.get("summary").toString().contains(keywords_list[i])){
               if(api.get("summary")==null){
                   System.out.println("Entering");
                   api.put("summary","APINAME"+ temp.get("id")+"$$"+temp.get("summary").toString());
               }else{
                   if(!api.get("summary").contains("APINAME"+ temp.get("id")+"$$"+temp.get("summary").toString())){
                   api.put("summary",api.get("summary")+" "+"APINAME"+ temp.get("id")+"$$"+temp.get("summary").toString());
                   }
               }
               
           }if(temp.get("description").toString().contains(keywords_list[i])){
               if(api.get("description")==null){
                   System.out.println("Entering");
                   api.put("description","APINAME"+ temp.get("id")+"$$"+temp.get("description").toString());
               }else{
                   if(!api.get("description").contains("APINAME"+ temp.get("id")+"$$"+temp.get("description").toString())){
                   api.put("description",api.get("description")+" "+"APINAME"+ temp.get("id")+"$$"+temp.get("description").toString());
                   }
               }
               
           }
           }
	}
        return api;
    }
    
    
    public String queryDatabaseApi(String Year,String Protocol,String Category,String Tags,String Keywords,Double Rating){
        
        String temp="";
        BasicDBObject gtQuery = new BasicDBObject();
        BasicDBObject ltQuery = new BasicDBObject();
        BasicDBObject eqQuery = new BasicDBObject();
	
        if(!Rating.equals(0.0)){
             
            
            /*
            greater than
            */
            
            
            
            temp=temp+"\n";
            temp=temp+"Greater than "+Rating+"\n"+"\n";
            temp=temp+"\n";
            
            if(!Year.equals("")){
            gtQuery.put("updated",new BasicDBObject("$regex",".*"+Year+".*"));
        }
        if(!Protocol.equals("")){
            gtQuery.put("protocols",Protocol);
        }
       
        if(!Category.equals("")){
            gtQuery.put("category",Category);
        }
        if(!Tags.equals("")){
            gtQuery.put("Tags",new BasicDBObject("$regex",".*"+Tags+".*") );
        }
         if(!Keywords.equals("")){
            DBObject clause1 = new BasicDBObject("summary", new BasicDBObject("$regex",".*"+Keywords+".*"));  
            DBObject clause2 = new BasicDBObject("description", new BasicDBObject("$regex",".*"+Keywords+".*"));
            DBObject clause3 = new BasicDBObject("title", new BasicDBObject("$regex",".*"+Keywords+".*")); 
            BasicDBList or = new BasicDBList();
            or.add(clause1);or.add(clause3);
            or.add(clause2);
            gtQuery.put("$or", or);
            //DBObject query = new BasicDBObject("$or", or);
//System.out.println(query);

        }
         
           gtQuery.put("rating",new BasicDBObject("$gt",Rating));
           DBCursor cursor = table1.find(gtQuery);
           while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 if(!temp.contains(temp1.get("name").toString()))
                 temp=temp+" "+temp1.get("name")+"\n";
	}
           
           
           /*
           lower than
           */
           
           temp=temp+"\n";
            temp=temp+"Lower than "+Rating+"\n"+"\n";
            temp=temp+"\n";
            
            if(!Year.equals("")){
            ltQuery.put("updated",new BasicDBObject("$regex",".*"+Year+".*"));
        }
        if(!Protocol.equals("")){
            ltQuery.put("protocols",Protocol);
        }
       
        if(!Category.equals("")){
            ltQuery.put("category",Category);
        }
        if(!Tags.equals("")){
            ltQuery.put("Tags",new BasicDBObject("$regex",".*"+Tags+".*") );
        }
         if(!Keywords.equals("")){
            DBObject clause1 = new BasicDBObject("summary", new BasicDBObject("$regex",".*"+Keywords+".*"));  
            DBObject clause2 = new BasicDBObject("description", new BasicDBObject("$regex",".*"+Keywords+".*"));
            DBObject clause3 = new BasicDBObject("title", new BasicDBObject("$regex",".*"+Keywords+".*")); 
            BasicDBList or = new BasicDBList();
            or.add(clause1);or.add(clause3);
            or.add(clause2);
            ltQuery.put("$or", or);
            //DBObject query = new BasicDBObject("$or", or);
//System.out.println(query);

        }
         
           ltQuery.put("rating",new BasicDBObject("$lt",Rating));
           DBCursor cursor2 = table1.find(ltQuery);
           while(cursor2.hasNext()) {
		 DBObject temp1 = cursor2.next();
                 if(!temp.contains(temp1.get("name").toString()))
                 temp=temp+" "+temp1.get("name")+"\n";
	}
           
           
           
           
           
           
           /*
           
           equal to 
           */
           
           
           temp=temp+"\n";
            temp=temp+"Equal to "+Rating+"\n"+"\n";
            temp=temp+"\n";
            
            if(!Year.equals("")){
            eqQuery.put("updated",new BasicDBObject("$regex",".*"+Year+".*"));
        }
        if(!Protocol.equals("")){
            eqQuery.put("protocols",Protocol);
        }
       
        if(!Category.equals("")){
            eqQuery.put("category",Category);
        }
        if(!Tags.equals("")){
            eqQuery.put("Tags",new BasicDBObject("$regex",".*"+Tags+".*") );
        }
         if(!Keywords.equals("")){
            DBObject clause1 = new BasicDBObject("summary", new BasicDBObject("$regex",".*"+Keywords+".*"));  
            DBObject clause2 = new BasicDBObject("description", new BasicDBObject("$regex",".*"+Keywords+".*"));
            DBObject clause3 = new BasicDBObject("title", new BasicDBObject("$regex",".*"+Keywords+".*")); 
            BasicDBList or = new BasicDBList();
            or.add(clause1);or.add(clause3);
            or.add(clause2);
            eqQuery.put("$or", or);
            //DBObject query = new BasicDBObject("$or", or);
//System.out.println(query);

        }
         
           eqQuery.put("rating",new BasicDBObject("$eq",Rating));
           DBCursor cursor3 = table1.find(eqQuery);
           while(cursor3.hasNext()) {
		 DBObject temp1 = cursor3.next();
                 if(!temp.contains(temp1.get("name").toString()))
                 temp=temp+" "+temp1.get("name")+"\n";
	}
           
           
           
           
           
           
           
           
           
        }else{
            
            BasicDBObject test =  new BasicDBObject();
            if(!Year.equals("")){
            test.put("updated",new BasicDBObject("$regex",".*"+Year+".*"));
        }
        if(!Protocol.equals("")){
            test.put("protocols",Protocol);
        }
       
        if(!Category.equals("")){
            test.put("category",Category);
        }
        if(!Tags.equals("")){
            test.put("Tags",new BasicDBObject("$regex",".*"+Tags+".*") );
        }
         if(!Keywords.equals("")){
            DBObject clause1 = new BasicDBObject("summary", new BasicDBObject("$regex",".*"+Keywords+".*"));  
            DBObject clause2 = new BasicDBObject("description", new BasicDBObject("$regex",".*"+Keywords+".*"));
            DBObject clause3 = new BasicDBObject("title", new BasicDBObject("$regex",".*"+Keywords+".*")); 
            BasicDBList or = new BasicDBList();
            or.add(clause1);or.add(clause3);
            or.add(clause2);
            test.put("$or", or);
            //DBObject query = new BasicDBObject("$or", or);
//System.out.println(query);

        }
            
            
            
            
            DBCursor cursor = table1.find(test);
             while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 if(!temp.contains(temp1.get("name").toString()))
                 temp=temp+" "+temp1.get("name")+"\n";
	}
        }
        
        
        
        
        
        
        
        /*
        if(!Year.equals("")){
            //gtQuery.put("rating", new BasicDBObject("$gt", "4"));
            gtQuery.put("updated",new BasicDBObject("$regex",".*"+Year+".*"));
        }
        if(!Protocol.equals("")){
            gtQuery.put("protocols",Protocol);
        }
       
        if(!Category.equals("")){
            gtQuery.put("category",Category);
        }
        if(!Tags.equals("")){
            gtQuery.put("Tags",new BasicDBObject("$regex",".*"+Tags+".*") );
        }
         if(!Keywords.equals("")){
            DBObject clause1 = new BasicDBObject("summary", new BasicDBObject("$regex",".*"+Keywords+".*"));  
            DBObject clause2 = new BasicDBObject("description", new BasicDBObject("$regex",".*"+Keywords+".*"));
            DBObject clause3 = new BasicDBObject("title", new BasicDBObject("$regex",".*"+Keywords+".*")); 
            BasicDBList or = new BasicDBList();
            or.add(clause1);or.add(clause3);
            or.add(clause2);
            gtQuery.put("$or", or);
            //DBObject query = new BasicDBObject("$or", or);
//System.out.println(query);

        }
         


//gtQuery.put("rating", new BasicDBObject("$gt", "4").append("$lt", "5"));
      //  gtQuery.put("updated", "2012-12-17T09:51:40Z");
	DBCursor cursor = table1.find(gtQuery);
	String temp="";
       
        if(!Rating.equals(0.0)){
             gtQuery.put("rating",new BasicDBObject("$gt",Rating));
             temp="Greater than "+Rating+"\n"+"\n";
              while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 temp=temp+" "+temp1.get("name")+"\n";
          }
              
              gtQuery.remove("rating");
              temp=temp+"\n";
        gtQuery.put("rating",new BasicDBObject("$lt",Rating));
             temp= temp+"Lesser than "+Rating+"\n"+"\n";
              while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 temp=temp+" "+temp1.get("name")+"\n";
	}
        
        gtQuery.remove("rating");
        temp=temp+"\n";
        gtQuery.put("rating",new BasicDBObject("$eq",Rating));
             temp=temp+"Equal to "+Rating+"\n"+"\n";
              while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 temp=temp+" "+temp1.get("name")+"\n";
	}
        
        }else{
            while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 temp=temp+" "+temp1.get("name")+"\n";
	}
        }

        */
       
        
        
        
        return temp;
    }
      
  
     public String queryDatabaseMashUp(String Year,String UsedApi,String Tags,String Keywords){
         BasicDBObject gtQuery = new BasicDBObject();
        
	if(!Year.equals("")){
            //gtQuery.put("rating", new BasicDBObject("$gt", "4"));
            gtQuery.put("updated",new BasicDBObject("$regex",".*"+Year+".*"));
        }
        if(!UsedApi.equals("")){
            System.out.println("UsedApi "+UsedApi);
           // DBObject clause1 = new BasicDBObject("APIS", new BasicDBObject("$regex",".*"+UsedApi+".*"));  
           // gtQuery.put("APIs",new BasicDBObject("APIs", new BasicDBObject("$regex",".*"+UsedApi+".*")));
         gtQuery.put("APIs",new BasicDBObject("$regex",".*"+UsedApi+".*"));
        }
        
        if(!Tags.equals("")){
            gtQuery.put("Tags",new BasicDBObject("$regex",".*"+Tags+".*") );
        }
         if(!Keywords.equals("")){
            DBObject clause1 = new BasicDBObject("summary", new BasicDBObject("$regex",".*"+Keywords+".*"));  
            DBObject clause2 = new BasicDBObject("description", new BasicDBObject("$regex",".*"+Keywords+".*"));
            DBObject clause3 = new BasicDBObject("title", new BasicDBObject("$regex",".*"+Keywords+".*")); 
            BasicDBList or = new BasicDBList();
            or.add(clause1);or.add(clause3);
            or.add(clause2);
            gtQuery.put("$or", or);
            //DBObject query = new BasicDBObject("$or", or);
//System.out.println(query);

        }


//gtQuery.put("rating", new BasicDBObject("$gt", "4").append("$lt", "5"));
      //  gtQuery.put("updated", "2012-12-17T09:51:40Z");
	DBCursor cursor = table2.find(gtQuery);
	String temp="";
        while(cursor.hasNext()) {
		 DBObject temp1 = cursor.next();
                 temp=temp+" "+temp1.get("name")+"\n";
	}
        return temp;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
      fetching data
      
      */
    
      
    
     
    public static void main(String[] args) {
       
       
        
        MashUp t = new MashUp();
        try {
            t.readApi();
        } catch (Exception ex) {
            Logger.getLogger(MashUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        
  
        
        
        /* BasicDBObject gtQuery = new BasicDBObject();
	gtQuery.put("rating", new BasicDBObject("$gt", "4").append("$lt", "5"));
      //  gtQuery.put("updated", "2012-12-17T09:51:40Z");
	DBCursor cursor = table1.find(gtQuery);
	
        while(cursor.hasNext()) {
		System.out.println(cursor.next());
                System.out.println();
	}
        */
        
        
        
        
        
        
        //db1.getCollection("table1").f
        //FindIterable<Document> iterable = db1.getCollection("restaurants").find(new Document("cuisine", "Italian").append("address.zipcode", "10075"));
        
        /*  try {
        
       mongo = new MongoClient("localhost", 27017);
        db1 = mongo.getDB("API");
        db2 = mongo.getDB("MashUp");
        
       MashUp first = new MashUp();
            first.readApi();
         HashMap<String,String> map = first.getMashUpInformation("Shopzilla.com");
         Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        System.out.println(pair.getKey() + " = " + pair.getValue());
        
    }
          
            
           } catch (Exception ex) {
            ex.printStackTrace();
        }
*/
    }
}
    