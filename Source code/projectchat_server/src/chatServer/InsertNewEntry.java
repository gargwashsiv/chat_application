
package chatServer;


import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.SAXException;

public class InsertNewEntry {


  public InsertNewEntry(String loginid, String name, String password, String email) throws ParserConfigurationException, SAXException, Exception{
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document doc =  factory.newDocumentBuilder().parse(new File("test.xml"));
    Insertingnewentry( doc, loginid , name, password, email);
  }



  public void Insertingnewentry(Document doc,String loginid, String name, String password, String email)throws Exception {

  Element person = doc.createElement("client");

  Element Node = doc.createElement("loginid");
  person.appendChild(Node);
  Text idtextNode = doc.createTextNode(loginid);
  Node.appendChild(idtextNode);

  Element root = doc.getDocumentElement();


  Element Name = doc.createElement("name");
  person.appendChild(Name);
  Text nametextNode = doc.createTextNode(name);
  Name.appendChild(nametextNode);

  Element Paswordnode = doc.createElement("password");
  person.appendChild(Paswordnode);
  Text passwordtextNode = doc.createTextNode(password);
  Paswordnode.appendChild(passwordtextNode);

  Element emailNode = doc.createElement("email");
  person.appendChild(emailNode);
  Text emailtextNode = doc.createTextNode(email);
  emailNode.appendChild(emailtextNode);

  root.appendChild(person);

  Node firstNode = root.getFirstChild();
  root.insertBefore(person, firstNode);

  TransformerFactory factory =
  TransformerFactory.newInstance();
  Transformer transformer = factory.newTransformer();

  transformer.setOutputProperty(OutputKeys.INDENT, "yes");


  StreamResult result = new StreamResult(new File("test.xml"));
  DOMSource source = new DOMSource(doc);
  transformer.transform(source, result);

  System.out.println("file saved");


  }
}

