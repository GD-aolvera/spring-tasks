package com.gd.clinic.builders;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public interface TestCollection<T> {

    Set<T> GenerateObjects(int requestedQuantity);

    int randomInt();

}
