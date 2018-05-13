package srpt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class cLogFile {
	static String vsPath;
	File vfFileName;
	Integer viLineNumber;
	Boolean vbAppend,vbNew;
	String vsHeader;
	public cLogFile(String vsP,String vsFN, Integer viLN, Boolean vbA, Boolean vbN, String vsH) {
		cLogFile.vsPath=vsP;
		this.vfFileName=new File(vsP+vsFN);
		this.viLineNumber=viLN;
		this.vbAppend=vbA;
		this.vbNew=vbN;
		this.vsHeader=vsH;
	}
	public void mReadFile() {
		showFullContents(this.vfFileName);
	}
	public void mReadFile(Integer x, Integer y) {
		showPartialContents(this.vfFileName,x,y);
	}
	public void mCreateFile(String FileName,Integer x,Integer y) throws IOException {
		extractPartialContents(this.vfFileName,x,y,FileName);
	}
	public void mDeleteLines(Integer x, Integer y) throws IOException {
		delPartialContents(this.vfFileName,x,y);
	}
	public void findRecord(String KEY,String NFile) throws IOException {
		File aFile =this.vfFileName;StringBuilder contents = new StringBuilder();
        Integer LineNumber=0;
        Boolean FirstRecord = true, Head=false, FoundKEY = false;
        File NewFile = new File(cLogFile.vsPath+NFile);BufferedWriter writerNewFile;writerNewFile = new BufferedWriter(new FileWriter(NewFile));writerNewFile.close();
        File tempFile = new File(cLogFile.vsPath+"TFile");BufferedWriter writer;writer = new BufferedWriter(new FileWriter(tempFile));        
        try {BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {String line = null;	
	        while (( line = input.readLine()) != null)
	        	{
	        	Head=false;++LineNumber;contents.append(line);contents.append(System.getProperty("line.separator"));
	        	if(line.contains(this.vsHeader)) 
	        		{
	        		if(FirstRecord) {Head=true;FirstRecord=false;writer.close();writer = new BufferedWriter(new FileWriter(tempFile));}
	        		if(FoundKEY) {writer.close();joinFiles(NewFile,tempFile);FoundKEY=false;Head=true;writer = new BufferedWriter(new FileWriter(tempFile));}
		        		else {writer.close();tempFile.delete();writer = new BufferedWriter(new FileWriter(tempFile));}
	        		}
	        	if(Head) {}
	        	if(line.contains(KEY)) {FoundKEY=true;tempFile.delete();}
	        	writer.write(line.toString()+System.getProperty("line.separator"));
	        	}
	        if(FoundKEY) {writer.close();joinFiles(NewFile,tempFile);FoundKEY=false;tempFile.delete();}
	        else {writer.close();tempFile.delete();}
	      }
	      finally {input.close();writer.close();}
	    }
	    catch (IOException ex){ex.printStackTrace();}
        
    }
	public static boolean betweenExclusive(int x, int min, int max)
	   {
	    if(max==0)max=x+1;   
		return x>min-1 && x<max+1;    
	   }
	public static void extractPartialContents(File aFile, Integer StartLine, Integer EndLine, String NewFile) throws IOException {
	    StringBuilder contents = new StringBuilder();
	    File tempFile = new File(vsPath+NewFile);
	    BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(tempFile));
		try {
		      BufferedReader input =  new BufferedReader(new FileReader(aFile));
		      try {
		        String line = null;
		        Integer LineNumber=0;
		        while (( line = input.readLine()) != null)
		        	{
		        	++LineNumber;
		        	contents.append(line);contents.append(System.getProperty("line.separator"));
		        	if(betweenExclusive(LineNumber,StartLine,EndLine)) {
		        		writer.write(line.toString()+System.getProperty("line.separator"));
		        	}
		        	else {
		        		
		        		}
		        	}
		      }
		      finally {
		    	  input.close();writer.close();
		    	  }
		    }
		    catch (IOException ex){ex.printStackTrace();}
	}
	static public void delPartialContents(File aFile, Integer StartLine, Integer EndLine) throws IOException {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    File tempFile = new File(vsPath+"myTempFile.txt");
	    BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(tempFile));
		try {
		      BufferedReader input =  new BufferedReader(new FileReader(aFile));//use buffering, reading one line at a time FileReader always assumes default encoding is OK!
		      try {
		        String line = null; //not declared within while loop
		        //readLine is a bit quirky :it returns the content of a line MINUS the newline. it returns null only for the END of the stream. it returns an empty String if two newlines appear in a row.
		        Integer LineNumber=0;
		        while (( line = input.readLine()) != null)
		        	{
		        	++LineNumber;
		        	contents.append(line);contents.append(System.getProperty("line.separator"));
		        	if(betweenExclusive(LineNumber,StartLine,EndLine)) {}
		        	else {
		        		writer.write(line.toString()+System.getProperty("line.separator"));
		        		//System.out.println(LineNumber+ " . " + line.length() + " . " + line.toString());
		        		}
		        	}
		      }
		      finally {
		    	  input.close();writer.close();
		    	  renamefile(aFile,tempFile);tempFile.delete();	    	  
		    	  }
		    }
		    catch (IOException ex){ex.printStackTrace();}
	}
	    
	static public void showPartialContents(File aFile, Integer StartLine, Integer EndLine) {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    
	    try {
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));//use buffering, reading one line at a time FileReader always assumes default encoding is OK!
	      try {
	        String line = null; //not declared within while loop
	        //readLine is a bit quirky :it returns the content of a line MINUS the newline. it returns null only for the END of the stream. it returns an empty String if two newlines appear in a row.
	        Integer LineNumber=0;
	        while (( line = input.readLine()) != null)
	        	{
	        	++LineNumber;
	        	contents.append(line);contents.append(System.getProperty("line.separator"));
	        	if(betweenExclusive(LineNumber,StartLine,EndLine)) {
	        		System.out.println(LineNumber+ " . " + line.length() + " . " + line.toString());
	        		}
	        	}
	      }
	      finally {input.close();}
	    }
	    catch (IOException ex){ex.printStackTrace();}
	    }
	static public void showFullContents(File aFile) {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    
	    try {
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));//use buffering, reading one line at a time FileReader always assumes default encoding is OK!
	      try {
	        String line = null;
	        while (( line = input.readLine()) != null)
	        	{
	        	contents.append(line);contents.append(System.getProperty("line.separator"));
	        	System.out.println(line.length() + " . " + line.toString());}
	      }
	      finally {input.close();}
	    }
	    catch (IOException ex){ex.printStackTrace();}
	    }
	static public void joinFiles(File FileX, File FileY) throws IOException {
	    File FileT = new File(vsPath+"myTempFile");
	    FileT.createNewFile();BufferedWriter writer;writer = new BufferedWriter(new FileWriter(FileT));
	    StringBuilder contents = new StringBuilder();BufferedReader readerX =  new BufferedReader(new FileReader(FileX));
	    writer.write(System.getProperty("line.separator"));
	    try {String line = null; while (( line = readerX.readLine()) != null){contents.append(line);contents.append(System.getProperty("line.separator"));writer.write(line.toString()+System.getProperty("line.separator"));}}
   	  	finally {readerX.close();}
   	  	BufferedReader readerY =  new BufferedReader(new FileReader(FileY));
   	  	writer.write(System.getProperty("line.separator"));
   	  	try {String line = null; while (( line = readerY.readLine()) != null){contents.append(line);contents.append(System.getProperty("line.separator"));writer.write(line.toString()+System.getProperty("line.separator"));}}
	  	finally {readerY.close();}
   	  	writer.close();renamefile(FileX,FileT);FileT.delete();
	}
	static public void renamefile(File aFile, File tempFile) throws IOException {
	    BufferedWriter writer;aFile.delete();aFile.createNewFile();
  	  	StringBuilder contents = new StringBuilder();BufferedReader reader =  new BufferedReader(new FileReader(tempFile));
  	  	writer = new BufferedWriter(new FileWriter(aFile));
  	  	try {String line = null;while (( line = reader.readLine()) != null){contents.append(line);contents.append(System.getProperty("line.separator"));writer.write(line.toString()+System.getProperty("line.separator"));}}
  	  finally {reader.close();writer.close();}
  	  
	}
	
}
