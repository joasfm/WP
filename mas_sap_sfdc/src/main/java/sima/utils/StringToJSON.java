package sima.utils;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.*;

public class StringToJSON extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		// TODO Auto-generated method stub
		String Cadena = (String) message.getPayload();
		ArrayList _Payload = new ArrayList();
		JSONArray array = new JSONArray(Cadena);
		Map<String, String> retMap = new HashMap<String, String>();


		    for(int i=0; i<array.length(); i++){
		        JSONObject jsonObj  = array.getJSONObject(i);
		        
		        if(jsonObj != JSONObject.NULL) {
		            retMap = toMap(jsonObj);
		            _Payload.add(retMap);
		        }
		        
		       
		    }
		    System.out.println(_Payload.toString());
		
		
		// JSONObject jsonObj = new JSONObject(Cadena);
		 
		
		return _Payload;
	}
	public static Map<String, String> toMap(JSONObject object) throws JSONException {
	    Map<String, String> map = new HashMap<String, String>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value.toString());
	    }
	    return map;
	}

	public static ArrayList<Object> toList(JSONArray array) throws JSONException {
	    ArrayList<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}

}
