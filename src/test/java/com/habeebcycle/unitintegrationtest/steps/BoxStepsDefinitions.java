package com.habeebcycle.unitintegrationtest.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.habeebcycle.unitintegrationtest.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoxStepsDefinitions {
    private final Logger log = LoggerFactory.getLogger(BoxStepsDefinitions.class);

    @Autowired
    private RestClient restClient;

    @Given("^the box is empty$")
    public void the_box_is_empty() {
        assertThat(restClient.getCurrentBox().getBoxContents().isEmpty()).isTrue();
    }

    @Given("^the box is not empty and add (\\d+) (\\w+)$")
    public void the_box_is_not_empty_with_n_treasure(final int quantity, final String treasure){
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting {} {} in the box", quantity, treasure))
                .map(ignore -> restClient.putTreasure(treasure))
                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
        assertThat(restClient.getCurrentBox().getBoxContents().isEmpty()).isFalse();
    }

    @When("^(\\d+) (\\w+) is put into the box$")
    public void treasure_is_put_into_the_box(final int quantity, final String treasure){
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting a {} in the box, number {}", treasure, quantity))
                .map(ignore -> restClient.putTreasure(treasure))
                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
    }

    @Then("^the box should contain only (\\d+) (\\w+)$")
    public void the_bag_should_contain_only_treasure(final int quantity, final String treasure) {
        assertNumberOfTimes(quantity, treasure, true);
    }

    @When("^the box is emptied")
    public void the_box_is_emptied(){
        assertThat(restClient.emptyTreasureBox()).isEqualTo(HttpStatus.OK.value());
        assertThat(restClient.getCurrentBox().getBoxContents().isEmpty()).isTrue();
    }

    @When("^(\\d+) treasure (\\w+) is put into the box$")
    public void n_treasure_is_put_into_the_box(final int quantity, final String treasure){
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting a {} in the box, number {}", treasure, quantity))
                .map(ignore -> restClient.putTreasure(treasure))
                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
        assertNumberOfTimes(quantity, treasure, false);
    }
/*
    @And("^(\\d+) treasure (\\w+) is put into the box$")
    public void n_treasure_is_put_add_the_box(final int quantity, final String treasure){
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting a {} in the box, number {}", treasure, quantity))
                .map(ignore -> restClient.putTreasure(treasure))
                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
        assertNumberOfTimes(quantity, treasure, false);
    }
*/
    @Then("^the box should contain only (\\d+) (\\w+) treasure$")
    public void box_should_contain_treasure_a(final int quantity, final String treasure){
        assertNumberOfTimes(quantity, treasure, false);
    }
/*
    @And("^the box should contain only (\\d+) (\\w+) treasure$")
    public void the_box_should_contain_treasure_b(final int quantity, final String treasure){
        assertNumberOfTimes(quantity, treasure, true);
    }
*/
    private void assertNumberOfTimes(final int quantity, final String treasure, boolean total) {
        int timesInList = 0;
        final List<String> contents = restClient.getCurrentBox().getBoxContents();
        log.info("Expecting {} times {}. The box contains {}", quantity, treasure, contents);
        if(total)
            timesInList = contents.size();
        else
            timesInList = Collections.frequency(contents, treasure);
        assertThat(timesInList).isEqualTo(quantity);
    }

}
