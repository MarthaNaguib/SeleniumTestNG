package providers;

import constants.CommonConstants;
import models.Dataset;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static constants.CommonConstants.XML_PATH;

public class XmlDatasetProvider
{
    @DataProvider(name = CommonConstants.DATA_PROVIDER_NAME)
    private Iterator<Dataset> getData()
    {
        try
        {
            // Load the XML file
//            File xmlFile = new File(
//                    "/home/martha/Downloads/Self Study/exam/src/test/java/loginTestData.xml");
//            File xmlFile = new File(
//                    "src/test/java/loginTestData.xml");
            File xmlFile = new File(XML_PATH);
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(xmlFile);

            // Find the <testData> section.
            NodeList testDataList = document.getElementsByTagName("testData");

            HashMap<String, String> attributeMap = null;
            List<Dataset> data = new ArrayList<Dataset>();
            Element testDataElement = null;

            for (int i = 0; i < testDataList.getLength(); i++)
            {
                Node testDataNode = testDataList.item(i);
                if (testDataNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    testDataElement = (Element) testDataNode;
                    String id = testDataElement.getAttribute("id");
                    System.out.println("ID: " + id);

                    attributeMap = new HashMap<String, String>();

                    NodeList childNodes = testDataElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++)
                    {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            String tagName = childNode.getNodeName();
                            String textContent = childNode.getTextContent();
                            System.out.println("Tag Name: " + tagName);
                            System.out.println("Value: " + textContent);
                            attributeMap.put(tagName, textContent);
                        }
                    }
                    data.add(new Dataset(id, attributeMap));
                }
            }
            System.out.println(data.size());
            return data.iterator();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
