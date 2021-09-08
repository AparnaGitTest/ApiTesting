package pojoForRegres;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//InputStream ins = new FileInputStream("D:\\student.ser");
		//Class<regresMain> student = (regresMain.class) ((Object) ins).readObject();
        Gson gson = new Gson();

        // convert java object to JSON format,
       // and returned as JSON formatted string
      // String json = gson.toJson(student );

     try {
        //write converted json data to a file named "file.json"
        FileWriter writer = new FileWriter("c:\\file.json");
      //  writer.write(json);
        writer.close();
        } catch (IOException e) {
           e.printStackTrace();
      }
	}

}
