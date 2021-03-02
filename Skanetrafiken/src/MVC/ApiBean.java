package MVC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public class ApiBean {
	
	
	public String input = "";

	public String URL = "http://www.labs.skanetrafiken.se/v2.2/stationresults.asp?selPointFrKey=80000";

	public String URL2 = "http://www.labs.skanetrafiken.se/v2.2/querystation.asp?inpPointfr=malm%F6";
	
	
	public ArrayList<String> names = new ArrayList<String>();;
	public ArrayList<String> dates = new ArrayList<String>();;
	
	public ArrayList<String> stations = new ArrayList<String>();;
	public ArrayList<String> id = new ArrayList<String>();;
	
	
	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames() {
		try {
			apiGet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getDates() {
		return dates;
	}
	
	public void setDates() {
		try {
			apiGet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	// Getting API for schedule and headings.
	private void apiGet() throws IOException {

		URL line_api_url = new URL(URL);

		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();

		linec.setDoInput(true);

		linec.setDoOutput(true);

		linec.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		String inputLine;

		String ApiResponse = "";

		while ((inputLine = in.readLine()) != null) {

			ApiResponse += inputLine;

		}
		in.close();

		Document doc = convertStringToXMLDocument(ApiResponse);

		doc.getDocumentElement().normalize();

		//System.out.println("Root ele:" + doc.getDocumentElement().getNodeName());

		Node nodeBody = doc.getElementsByTagName("soap:Body").item(0);

		NodeList nodeResult = (NodeList) nodeBody.getFirstChild().getFirstChild();

		Node nodelines = nodeResult.item(2);

		NodeList listOflines = nodelines.getChildNodes();

		for (int i = 0; i < listOflines.getLength(); i++) {

			// System.out.println(listOflines.item(i).getFirstChild().getTextContent());

			NodeList dateLines = listOflines.item(i).getChildNodes();
			NodeList towardsLines = listOflines.item(i).getChildNodes();

			for (int y = 0; y < dateLines.getLength(); y++) {
				// System.out.println(allLine.item(y).getTextContent());

				if (dateLines.item(y).getNodeName().equals("JourneyDateTime")) {

					//System.out.println("Name " + dateLines.item(y).getTextContent());

					dates.add(dateLines.item(y).getTextContent());
				}

			}
			
			for (int y = 0; y < towardsLines.getLength(); y++) {
				// System.out.println(allLine.item(y).getTextContent());

				if (towardsLines.item(y).getNodeName().equals("Towards")) {

					//System.out.println("Name " + towardsLines.item(y).getTextContent());

					names.add(towardsLines.item(y).getTextContent());	
				}

			}
		}

	}
	
	
	public ArrayList<String> getStations() {
		return stations;
	}

	public void setStations() {
		try {
			apiGetStations();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getId() {
		return id;
	}
	
	public void setId() {
		try {
			apiGetStations();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Getting API for stations and id.
	
	private void apiGetStations() throws IOException {

		URL line_api_url = new URL(URL2);

		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();

		linec.setDoInput(true);

		linec.setDoOutput(true);

		linec.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		String inputLine;

		String ApiResponse = "";

		while ((inputLine = in.readLine()) != null) {

			ApiResponse += inputLine;

		}
		in.close();

		Document doc = convertStringToXMLDocument(ApiResponse);

		doc.getDocumentElement().normalize();

		//System.out.println("Root ele:" + doc.getDocumentElement().getNodeName());

		Node nodeBody = doc.getElementsByTagName("soap:Body").item(0);

		NodeList nodeResult = (NodeList) nodeBody.getFirstChild().getFirstChild();

		Node nodelines = nodeResult.item(2);

		NodeList listOflines = nodelines.getChildNodes();

		for (int i = 0; i < listOflines.getLength(); i++) {

			// System.out.println(listOflines.item(i).getFirstChild().getTextContent());

			NodeList dateLines = listOflines.item(i).getChildNodes();
			NodeList towardsLines = listOflines.item(i).getChildNodes();

			for (int y = 0; y < dateLines.getLength(); y++) {
				// System.out.println(allLine.item(y).getTextContent());

				if (dateLines.item(y).getNodeName().equals("Name")) {

					//System.out.println("Name " + dateLines.item(y).getTextContent());

					stations.add(dateLines.item(y).getTextContent());
				}

			}
			
			for (int y = 0; y < towardsLines.getLength(); y++) {
				// System.out.println(allLine.item(y).getTextContent());

				if (towardsLines.item(y).getNodeName().equals("Id")) {

					//System.out.println("Name " + towardsLines.item(y).getTextContent());

					id.add(towardsLines.item(y).getTextContent());	
				}

			}
		}

	}
	

	private Document convertStringToXMLDocument(String xmlString) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;

		try {

			builder = factory.newDocumentBuilder();

			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

			return doc;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

}