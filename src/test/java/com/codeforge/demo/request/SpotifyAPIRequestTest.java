package com.codeforge.demo.request;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;

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
            NodeList nList = doc.getElementsByTagName("event");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    sortOutShit(eElement);
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortOutShit(Node nNode) {

        Node index = nNode.getFirstChild();
        while(index != nNode.getLastChild()) {
            if(!index.getNodeName().equalsIgnoreCase("#text")) {
                System.out.println("Child : " + index.getNodeName());
                System.out.println("Child value : " + index.getFirstChild().getNodeValue());
                if(index.hasChildNodes()) {
                    sortOutShit(index);
                }
            }
            index = index.getNextSibling();
        }
    }
}
