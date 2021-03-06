package org.example.components.cart;

import java.util.concurrent.*;

import org.example.api.cart.*;
import org.osgi.service.component.annotations.Component;

@Component
public class CartManagerComponent implements CartManager {

	private final ConcurrentMap<String, Cart> carts
		= new ConcurrentHashMap<>();

	@Override
	public Cart getCart(String userId) throws Exception {
		Cart cart = new CartImpl();
		Cart existing = carts.putIfAbsent(userId, cart);
		if (existing != null) cart = existing;
		
		return cart;
	}
}
