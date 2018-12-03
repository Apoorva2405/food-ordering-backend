package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Item;
import org.upgrad.models.Restaurant;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

public interface ItemService {
    List<Item> getItemByPopularity(int restaurantId);
}
