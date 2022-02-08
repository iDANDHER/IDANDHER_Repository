import java.io.*;
import com.sap.aii.mapping.api.*;
public class Test_JavaMapping extends AbstractTransformation {
    @Override
    public void transform(TransformationInput transformationInput, TransformationOutput transformationOutput) throws StreamTransformationException {
        try {
            InputStream inputstream = transformationInput.getInputPayload().getInputStream();
            OutputStream outputstream = transformationOutput.getOutputPayload().getOutputStream();
            // Copy Input content to Output content
            byte[] b = new byte[inputstream.available()];
            inputstream.read(b);
       
            outputstream.write("Prefixing this line to input. Test_JavaMapping. \r\n".getBytes());
            outputstream.write(b);
        } catch (Exception exception) {
            getTrace().addDebugMessage(exception.getMessage());
            throw new StreamTransformationException(exception.toString());
        }
    }
}