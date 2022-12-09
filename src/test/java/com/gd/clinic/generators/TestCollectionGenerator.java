package com.gd.clinic.generators;

import java.util.Set;

public interface TestCollectionGenerator<T> {

    Set<T> generateObjects(int requestedQuantity);

    int randomInt();

}
