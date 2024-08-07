package dev.yashkalia.groceries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class GroceriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceriesApplication.class, args);

//		String file = "src/main/Data/grocery-api-db.animals.json";
//		try{
//			String json = readFileAsString(file);
//			System.out.println(json);
//		}
//		catch(Exception e){
//			System.out.println(e.toString());
//		}
//
//

//		ObjectMapper mapper = new ObjectMapper();
	}

//	public static String readFileAsString(String file) throws Exception
//	{
//		return new String(Files.readAllBytes(Paths.get(file)));
//	}
}
