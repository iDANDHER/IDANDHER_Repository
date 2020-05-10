package javamapping;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


import com.sap.aii.mapping.api.AbstractTransformation; //AbstractTransformation class needs to be imported, the associated jar file is: com.sap.xpi.ib.mapping.lib.jar
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;


/**
 * @author iDANDHER 
 *
 */
public class javamapping extends AbstractTransformation {

	/**
	 * CONSTRUCTOR
	 */
	public javamapping() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override // this is to override the method abstract method AbstractTransformation
	
	/*
	 Since SAP PO execute Transform() method instead of Execute() method, we will pass control from Transform() to Execute() method
	 so we can add all the logic in execute() method
	*/
	public void transform(TransformationInput arg0, TransformationOutput arg1) throws StreamTransformationException{
	
	/*
	 In order to pass the control we got the payload and the stream of each argument (arg0 and arg1)
	 Due to we are throwing an exception with "StreamTransformationException" we will add "Try and Catch" as best practice
	 This Try and Catch exception can be copied and pasted into the Execute() method since we also are throwing an exception
	*/
			
	try {
		
		execute( arg0.getInputPayload().getInputStream(), arg1.getOutputPayload().getOutputStream() );
		
	}catch(Exception ee){
		
		throw new StreamTransformationException(ee.toString());
		
	}
		
	
	
	}
	
	

	// Execute() method is to have a backward compatibility with XI, mean,you run this code on PO and can be executed on XI also 
	// This method is executed by XI instead of transform method
	public void execute(InputStream in, OutputStream out) throws StreamTransformationException{
		
		
	try {
		
	// Main Logic of SAP PO Java Mapping
	
	// With this Looping we are just reading the input with read() method and writting out with write() method	
		int buffer;
		while((buffer = in.read()) != -1 ) {
			
			out.write(buffer);
		}
		
		out.flush();
		
	}catch(Exception ee){
		
		throw new StreamTransformationException(ee.toString());
		
	}// End of the Main Logic
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// We will implement the call of Transform() method through a Try and Catch
		// using an object from FileInputStream and FileOutputStream classes as input and output parameters
		try {
			
			FileInputStream objFileInput = new FileInputStream("inputfile.txt");
			FileOutputStream objFileOutput = new FileOutputStream("outputfile.txt");
			
		// Creation of instance of the class javamapping to work we the parameters we just received above
			
			javamapping objJavaMapping = new javamapping();
			
			objJavaMapping.execute(objFileInput, objFileOutput);
			
			System.out.println("SAP CPI Consultant");
			
		}catch(Exception ee){
			ee.printStackTrace();
		}
		
		
	}

}