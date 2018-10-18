//Made by Cody Susta
//Converted to method by Zachary Clark

package parserXML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import engine.Room;

import org.w3c.dom.Node;
import java.io.File;

public class readxml {

  public static Room[] create_world(String xmlfile) {
	  
	Room[] world = new Room[0];

	// without try-catch block this will not compile
    try {

    // variable names are the tag names to grab from xml input file
	String name = "";
	String description = "";
	String detail = "";
	String north = "";
	String northeast = "";
	String east = "";
	String southeast = "";
	String south = "";
	String southwest = "";
	String west = "";
	String northwest = "";
	String up = "";
	String down = "";
	//String item = "";
	//String observe = "";

	/*Scanner input = new Scanner(System.in);
	System.out.print("please enter xml input file: ");
	String xmlfile = input.nextLine();*/
	
	/* using dom parser, takes in xml file and creates a tree structure 
	* with all tags and data, we then parse the tree structure not 
	* the actual file itself  
	*/
	
	File fXmlFile = new File(xmlfile);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	doc.getDocumentElement().normalize();
	
	//list of the number of room tags
	NodeList nList = doc.getElementsByTagName("room");
	
	world = new Room[nList.getLength()];
			
	//loop through list of room tags
	for (int temp = 0; temp < nList.getLength(); temp++) {	
		Node nNode = nList.item(temp);
				
//		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			

			//Element eElement = (Element) nNode;

			
			/* this is going to each tag name and grabbing the contents for each
			* iteration of rooms, feeds cardinal directions into an array connection.
			* creates room object from each iteration.
			*/
//			System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
			name = doc.getElementsByTagName("name").item(temp).getTextContent();
//			System.out.println("\ndescription: " + eElement.getElementsByTagName("description").item(0).getTextContent());
			description = doc.getElementsByTagName("description").item(temp).getTextContent();
//			System.out.println("\ndetail: " + eElement.getElementsByTagName("detail").item(0).getTextContent());
			detail = doc.getElementsByTagName("detail").item(temp).getTextContent();
//			System.out.println("\nconnections : " + eElement.getElementsByTagName("connections").item(0).getTextContent());
			north = doc.getElementsByTagName("north").item(temp).getTextContent();
			northeast = doc.getElementsByTagName("northeast").item(temp).getTextContent();
			east = doc.getElementsByTagName("east").item(temp).getTextContent();
			southeast = doc.getElementsByTagName("southeast").item(temp).getTextContent();
			south = doc.getElementsByTagName("south").item(temp).getTextContent();
			southwest = doc.getElementsByTagName("southwest").item(temp).getTextContent();
			west = doc.getElementsByTagName("west").item(temp).getTextContent();
			northwest = doc.getElementsByTagName("northwest").item(temp).getTextContent();
			up = doc.getElementsByTagName("up").item(temp).getTextContent();
			down = doc.getElementsByTagName("down").item(temp).getTextContent();
//			System.out.println("\ninteract: " +eElement.getElementsByTagName("interact").item(0).getTextContent());
			/*item = doc.getElementsByTagName("item").item(temp).getTextContent();
			observe = doc.getElementsByTagName("observe").item(temp).getTextContent();*/
			String[] connections = {north,northeast,east,southeast,south,southwest,west,northwest, up, down}; 
			
			world[temp] = new Room(name , description , detail , connections);
		}
		
	}
	
	//input.close();


    } catch (Exception e) {
	e.printStackTrace();
    }
    
	return(world);  
  }
}
