package repo;

import com.google.api.server.spi.response.BadRequestException;

class EntityNotFoundException extends BadRequestException {
    final private static String NOT_FOUND_MSG = "Given entity not found";

    EntityNotFoundException() {
        super(NOT_FOUND_MSG);
    }
}
