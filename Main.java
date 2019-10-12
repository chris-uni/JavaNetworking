package org;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String args[]) {
		
		// To-do: comm with server.
		
		String serverName = "https://172.217.10.52:8080";
		
		System.out.println("Trying to connect to: " + serverName);
		
		try {
			URL url = new URL(serverName);
			
			Map<String, String> params = new HashMap<>();
			params.put("name", "chris");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json");
			
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(params));
			out.flush();
			out.close();
			
			conn.setRequestMethod("GET");
			
			String contentType = conn.getHeaderField("Content-Type");
			
			System.out.println(contentType);
			
			int status = conn.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			
			conn.disconnect();
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {

			   HttpURLConnection con = (HttpURLConnection) new URL(serverName).openConnection();
			   con.setRequestMethod("HEAD");
			   con.setConnectTimeout(5000); //set timeout to 5 seconds
			  // return (con.getResponseCode() == HttpURLConnection.HTTP_OK);

			} catch (java.net.SocketTimeoutException e) {
			   e.printStackTrace();
			} catch (java.io.IOException e) {
			   e.printStackTrace();
			}*/
		
	}
	
}
