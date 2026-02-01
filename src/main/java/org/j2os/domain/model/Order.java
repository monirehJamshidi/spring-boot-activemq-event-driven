package org.j2os.domain.model;

public record Order(
        String orderId,
        String product,
        int quantity
) {
}
