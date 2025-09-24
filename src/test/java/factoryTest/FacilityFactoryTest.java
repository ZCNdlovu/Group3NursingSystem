package factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Facility;
import za.ac.cput.factory.FacilityFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacilityFactoryTest {

    @Test
    void createFacility() {
        Facility facility = FacilityFactory.createFacility(
                "CPUT Heath Center",
                "Healthcare",
                "125 Long Street, Cape Town",
                "Nurse Smith",
                "0217434567",
                22.1,
                22.6
        );

        assertNotNull(facility);
        System.out.println(facility);
    }
}
