import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SingleAuditingLog {

		   private static SingleAuditingLog instance = new SingleAuditingLog();

		   private SingleAuditingLog(){}

		   public static SingleAuditingLog getInstance(){
		      return instance;
		   }

		   public void WriteLog(String text){
			    
			   LocalDateTime dateTime = LocalDateTime.now();
			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			   
			    try {
			    BufferedWriter writer = new BufferedWriter(new FileWriter("logSistemaElettorale.txt", true));
			    writer.append("\n"+ text + " " + dateTime.format(formatter) );
			    
			    writer.close();
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
			  /*
			   System.out.println(" devo scrivere "+text);
			   //FileWriter writer = null;
			   BufferedWriter writer = null;
			    try {
			    	writer = new BufferedWriter(new FileWriter("logSistemaElettorale.txt"));
			    	//writer = new FileWriter("logSistemaElettorale.txt", true);
			    	System.out.println("ok");
			    }
				   catch (Exception e) { 
					   try {
						  File LogFile = new File("logSistemaElettorale.txt");
						  writer = new BufferedWriter(new FileWriter("logSistemaElettorale.txt"));
						  //writer = new FileWriter("logSistemaElettorale.txt", true);
					      if (LogFile.createNewFile()) {
					        System.out.println("File created: " + LogFile.getName());
					      } else {
					        System.out.println("File already exists.");
					      }
					    } catch (IOException ex) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
					   
				   }
			    
			     try {
			    	 writer.append(text);
			    	 System.out.println("nessun errore");
			     }catch(IOException e) {
			    	e.printStackTrace();
			     }
			     */
		   }
		
}
