package com.galasd.testAPI;

import com.galasd.testAPI.entities.Mapbox;
import com.galasd.testAPI.entities.Nasa;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;


@RunWith(SpringRunner.class)
@SpringBootTest
class TestApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllAsteroids() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/nasa",
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAllCities() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/mapbox",
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAstreroidById() {
        Nasa nasa = restTemplate.getForObject(getRootUrl() + "/nasa/1", Nasa.class);
        System.out.println(nasa.getName());
        Assert.assertNotNull(nasa);
    }

    @Test
    public void testGetCityById() {
        Mapbox mapbox = restTemplate.getForObject(getRootUrl() + "/mapbox/1", Mapbox.class);
        System.out.println(mapbox.getName());
        Assert.assertNotNull(mapbox);
    }

    @Test
    public void testCreateAsteroid() {
        Nasa asteroid = new Nasa();
        asteroid.setName("test");
        asteroid.setEarthDistance(5555);
        ResponseEntity<Nasa> postResponse = restTemplate.postForEntity(getRootUrl() + "/nasa", asteroid, Nasa.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testCreateCity() {
        Mapbox city = new Mapbox();
        city.setName("test");
        city.setLatitude(20.05d);
        city.setLongitude(40.1d);
        ResponseEntity<Mapbox> postResponse = restTemplate.postForEntity(getRootUrl() + "/mapbox", city, Mapbox.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateAsteroid() {
        int id = 1;
        Nasa asteroid = restTemplate.getForObject(getRootUrl() + "/nasa/" + id, Nasa.class);
        asteroid.setName("Supertest");
        restTemplate.put(getRootUrl() + "/nasa/" + id, asteroid);
        Nasa updatedAsteroid = restTemplate.getForObject(getRootUrl() + "/nasa/" + id, Nasa.class);
        Assert.assertNotNull(updatedAsteroid);
    }

    @Test
    public void testUpdateCity() {
        int id = 1;
        Mapbox city = restTemplate.getForObject(getRootUrl() + "/mapbox/" + id, Mapbox.class);
        city.setName("Supercity");
        restTemplate.put(getRootUrl() + "/mapbox/" + id, city);
        Mapbox updatedCity = restTemplate.getForObject(getRootUrl() + "/mapbox/" + id, Mapbox.class);
        Assert.assertNotNull(updatedCity);
    }

    @Test
    public void testDeleteAsteroid() {
        int id = 2;
        Nasa asteroid = restTemplate.getForObject(getRootUrl() + "/nasa/" + id, Nasa.class);
        Assert.assertNotNull(asteroid);
        restTemplate.delete(getRootUrl() + "/nasa/" + id);
		try {
			asteroid = restTemplate.getForObject(getRootUrl() + "/nasa/" + id, Nasa.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
    }

    @Test
    public void testDeleteCity() {
        int id = 2;
        Mapbox city = restTemplate.getForObject(getRootUrl() + "/mapbox/" + id, Mapbox.class);
        Assert.assertNotNull(city);
        restTemplate.delete(getRootUrl() + "/mapbox/" + id);
		try {
			city = restTemplate.getForObject(getRootUrl() + "/mapbox/" + id, Mapbox.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
    }
}