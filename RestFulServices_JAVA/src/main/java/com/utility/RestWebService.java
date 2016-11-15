package com.utility;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;

import com.constants.Constants;
import com.logger.SuiteLogger;

/**
 * @author asingla
 *
 */

public class RestWebService extends AbstractTestNGSpringContextTests {


	private static String plainCreds = Constants.LOGIN_CREDENTIALS;
	private static byte[] plainCredsBytes = plainCreds.getBytes();
	private static byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	private static String base64Creds = new String(base64CredsBytes);
	private static HttpHeaders headers = new HttpHeaders();
	private static RestTemplate restTemplate;

/**
 * 
 * @param url
 * @param method
 * @param T
 * @param classname
 * @return
 */
	public static < T > T exchange(String url, String method, T T, Class < T > classname){

		headers.add("Authorization", "Basic " + base64Creds);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		restTemplate = new RestTemplate();

		if(method.equals(Constants.REQUEST_METHOD_POST)){

			return post(url, T, classname);
		}
		else if(method.equals(Constants.REQUEST_METHOD_GET)){

			return get(url, T, classname);

		}
		else if(method.equals(Constants.REQUEST_METHOD_PUT)){

			return put(url, T, classname);

		}
		else if(method.equals(Constants.REQUEST_METHOD_DELETE)){

			return (T)delete(url, T);

		}	
		return null;
	}
	/**
	 * This method posts data to application.
	 * 
	 * @return created object.
	 */

	public static < T > T post(String url, T T, Class < T > classname){


		HttpEntity < T > request = new HttpEntity< T >(T, headers);
		ResponseEntity< T > result = restTemplate.exchange(url, HttpMethod.POST, request, classname);
		SuiteLogger.log.info("Post result :: "+result.getBody());
		return result.getBody();
	}

	/**
	 * This method get data from application.
	 * 
	 * @return object received in response.
	 */
	public static < T > T get(String url, T T, Class < T > classname){


		HttpEntity<T> request = new HttpEntity<T>(headers);
		ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.GET, request, classname);
		SuiteLogger.log.info("Get result :: "+result.getBody());
		return result.getBody();
	}

	/**
	 * This method updates existing record in application.
	 * 
	 * @return updated object.
	 */
	public static < T > T put(String url, T T, Class < T > classname){


		HttpEntity<T> request = new HttpEntity<T>(T, headers);
		ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.PUT, request, classname);
		SuiteLogger.log.info("Put result :: "+result.getBody());
		return result.getBody();
	}

	/**
	 * This method deletes record in application.
	 * 
	 * @return status response.
	 */
	public static < T > String delete(String url, T T){


		HttpEntity<T> request = new HttpEntity<T>(headers);
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
		SuiteLogger.log.info("Delete result :: "+result);
		return result.getStatusCode().value()+"";
	}



}
