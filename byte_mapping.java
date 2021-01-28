import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;
import java.io.*;

/**
 * @author iDANDHER
 * 28 ENERO 2021
 */
public class byte_mapping extends AbstractTransformation{

	/**
	 * @param args
	 */
	public void transform(TransformationInput transformationInput, TransformationOutput TransformationOutput) throws StreamTransformationException{
		
		try {
			InputStream inputstream = transformationInput.getInputPayload().getInputStream();
			OutputStream outputstream = TransformationOutput.getOutputPayload().getOutputStream();
			
			
			byte[] payload = new byte[inputstream.available()];
			
			// no mapping
			// Sending Input content to Output Content as it is
			
			inputstream.read(payload);
			outputstream.write(payload);
			
		}catch(Exception exception){
			getTrace().addDebugMessage(exception.getMessage());
			throw new StreamTransformationException(exception.toString());
			
		}
	}

}
