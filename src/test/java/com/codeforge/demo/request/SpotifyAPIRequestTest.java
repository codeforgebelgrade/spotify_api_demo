package com.codeforge.demo.request;

import com.codeforge.demo.mappers.ShellEventMapper;
import com.codeforge.demo.model.ShellEventModel;
import com.codeforge.demo.model.WebmotifShellObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class SpotifyAPIRequestTest {

    @Test
    public void getArtisInfoTest() throws IOException {

        String responseContent = SpotifyAPIRequestHandler.executeHttpRequest("Nickelback",
                "Bearer BQASdWGDWXoU6uCy5pVrPwsAZLwczxHaaeaPXA-K8S2iFIiAzy_KzNZFhoym0SeGQRphi28iLbELmWsxJR6ecvKVPIdO7zH076aVZdEpLiridpsbY2GJESblYm4ECUMa2ctPM36IkYRz");

        Assert.assertNotNull(responseContent);
    }

    @Test
    public void parseShellObjectXMLFeedTest() {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("http://media.tmeu.eupdev1.websys.tmcs/finland/partnerfeed/partnerfeed.xml");

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList nListEvents = doc.getElementsByTagName("event");
            //NodeList nListVenues = doc.getElementsByTagName("venue");

            processShellObjects(nListEvents, "event");
            //processShellObjects(nListVenues);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processShellObjects(NodeList nList, String objectType) {

        List<WebmotifShellObject> shellObjectList = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                //Initialize shell object
                //if(objectType.equalsIgnoreCase("event")) {
                ShellEventModel shellEvent = new ShellEventModel();
                shellEvent.setEvent(new ShellEventModel.Event());
                shellEvent.getEvent().setAttractionIds(new ArrayList<Integer>());
                shellEvent.getEvent().setCategoryIds(new ArrayList<Integer>());

                Element eElement = (Element) nNode;
                sortOutShit(eElement, shellEvent);

                shellObjectList.add(shellEvent);
                System.out.println("----------------------------");
            }
        }

        System.out.println("Number of elements in shell object list: " + shellObjectList.size());
    }

    private void sortOutShit(Node nNode, WebmotifShellObject shellObject) {

        Node index = nNode.getFirstChild();
        while(index != nNode.getLastChild()) {
            if(!index.getNodeName().equalsIgnoreCase("#text")) {
                System.out.println("Child : " + index.getNodeName());
                System.out.println("Child value : " + index.getFirstChild().getNodeValue());
                // Depending on the type of the objecrt, we can call different mappers
                if(shellObject instanceof ShellEventModel) {
                    ShellEventMapper.map(index.getNodeName(), index.getFirstChild().getNodeValue(), (ShellEventModel) shellObject);
                } else {
                    // Call some other logic
                }
                if(index.hasChildNodes()) {
                    sortOutShit(index, shellObject);
                }
            }
            index = index.getNextSibling();
        }
    }
}
