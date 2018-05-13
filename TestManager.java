package srpt;

import java.io.IOException;
import java.time.Instant;
import java.util.Random;
public class TestManager {public static void main(String[] args) throws IOException {String File,Path1,Path2,Path,User,RecordIdentifier,SearchString,OutFile;Instant instant;
		Path1 ="C:\\Users\\";Path2="\\Downloads\\data\\txt\\";User=System.getProperty("user.name");File = "DataSet1";
		RecordIdentifier="BOR";SearchString="ognizan";OutFile=randSTR();Path=Path1+User+Path2; cLogFile x =new cLogFile(Path,File);
		instant = Instant.now();long varlongStartTime = instant.getEpochSecond();System.out.println("Seconds since epoch: " + varlongStartTime + " | " + "Years since epoch: " + (double)varlongStartTime/31536000);
		x.ExtractLoad(RecordIdentifier,SearchString,OutFile);
		instant = Instant.now();long varlongStopTime = instant.getEpochSecond();System.out.println("Seconds since epoch: " + varlongStopTime + " | " + "Years since epoch: " + (double)varlongStopTime/31536000);}
public static String randSTR() {int leftLimit = 97;int rightLimit = 122;int targetStringLength = 10;Random random = new Random();StringBuilder buffer = new StringBuilder(targetStringLength);for (int i = 0; i < targetStringLength; i++) {int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));buffer.append((char) randomLimitedInt);}return buffer.toString();}
}

		