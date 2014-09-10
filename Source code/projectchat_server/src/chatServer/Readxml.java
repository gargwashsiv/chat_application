
package chatServer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Readxml {

        public boolean checkidOnSignin(String loginid){
           try {

		File fXmlFile = new File("test.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("client");
                System.out.println(nList.getLength());
		for (int temp = 0; temp < nList.getLength(); temp++) {
                   Node nNode = nList.item(temp);
                   Element eElement = (Element) nNode;

		      if(getTagValue("loginid", eElement).equals(loginid)){
		         return false;
                    }
                }


	  } catch (Exception e) {
		e.printStackTrace();
	  }
          return true;

        }


	public String  checkidOnlogin(String loginid, String password) {

	  try {

		File fXmlFile = new File("test.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("client");
                System.out.println(nList.getLength());
		for (int temp = 0; temp < nList.getLength(); temp++) {
                   Node nNode = nList.item(temp);
                   Element eElement = (Element) nNode;

		      if(getTagValue("loginid", eElement).equals(loginid)){
		         if(getTagValue("password", eElement).equals(password))
                               return getTagValue("name", eElement);
                         return null;
                    }
                }


	  } catch (Exception e) {
		e.printStackTrace();
	  }
          return null;
  }

  private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

	return nValue.getNodeValue();
  }

}
