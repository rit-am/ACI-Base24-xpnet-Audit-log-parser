package srpt;

import java.io.IOException;
import java.time.Instant;
public class TestManager {public static void main(String[] args) throws IOException {String File,Path1,Path2,Path,User,RecordIdentifier;Instant instant;
		Path1 ="C:\\Users\\";Path2="\\Downloads\\data\\txt\\";User=System.getProperty("user.name");File = "DataSet1";RecordIdentifier="BOR";
		Path=Path1+User+Path2; cLogFile x =new cLogFile(Path,File, 0 , true, true, RecordIdentifier);
		instant = Instant.now();long varlongStartTime = instant.getEpochSecond();System.out.println("Seconds since epoch: " + varlongStartTime + " | " + "Years since epoch: " + (double)varlongStartTime/31536000);
		x.ExtractLoad("ognizan","File1key");
		instant = Instant.now();long varlongStopTime = instant.getEpochSecond();System.out.println("Seconds since epoch: " + varlongStopTime + " | " + "Years since epoch: " + (double)varlongStopTime/31536000);}}
		