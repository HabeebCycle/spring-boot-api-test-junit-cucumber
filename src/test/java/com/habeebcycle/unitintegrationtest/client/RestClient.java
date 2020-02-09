package com.habeebcycle.unitintegrationtest.client;

import com.habeebcycle.unitintegrationtest.model.Box;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class RestClient {

    private final String SERVER_URL = "http://localhost";
    private final String BOX_ENDPOINT = "/treasures";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String boxEndPoint(String endpoint){
        return SERVER_URL + ":" + port + BOX_ENDPOINT + endpoint;
    }

    public Box getCurrentBox() {
        return restTemplate.getForEntity(boxEndPoint("/box"), Box.class).getBody();
    }

    public List<String> getBoxContents(){
        return restTemplate.getForEntity(boxEndPoint("/box/all"), List.class).getBody();
    }

    public int getTreasureNum(final String treasure){
        return restTemplate.getForEntity(boxEndPoint("/box/" + treasure), Integer.class).getBody();
    }

    public int putTreasure(final String treasure) {
        return restTemplate.postForEntity(boxEndPoint("/box"), treasure, Void.class).getStatusCodeValue();
    }

    public boolean removeTreasure(final String treasure){
        return restTemplate.getForEntity(boxEndPoint("/box/" + treasure), Boolean.class).getBody();
    }

    public boolean removeSameTreasure(final String treasure){
        return restTemplate.getForEntity(boxEndPoint("/box/all/" + treasure), Boolean.class).getBody();
    }

    public int emptyTreasureBox(){
        restTemplate.delete(boxEndPoint("/box/all"));
        return HttpStatus.OK.value();
    }

}
