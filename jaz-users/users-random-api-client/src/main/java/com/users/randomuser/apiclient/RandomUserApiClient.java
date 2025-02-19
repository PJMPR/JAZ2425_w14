package com.users.randomuser.apiclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.users.randomuser.contract.UserResultsDto;

@Component
public class RandomUserApiClient implements IRandomUsersApiClient{

    private final RestTemplate restTemplate;

    public RandomUserApiClient() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public UserResultsDto getRandomUsers(int howMany) {
        var url = "https://randomuser.me/api/?results=%d".formatted(howMany);

        return restTemplate.getForObject(url, UserResultsDto.class);
    }
}
