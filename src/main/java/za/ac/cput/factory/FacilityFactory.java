package za.ac.cput.factory;

import za.ac.cput.domain.Facility;

public class FacilityFactory {

    public static Facility createFacility(String name, String type, String address, String contactPerson,String contactPhone, Double latitude, Double longitude){
        if (name == null || type == null || address == null || contactPerson == null || contactPhone == null
                || latitude == null || longitude == null) {
            throw new IllegalStateException("All fields must be provided");
        }return new Facility.Builder()
                .setName(name)
                .setType(type)
                .setAddress(address)
                .setContactPerson(contactPerson)
                .setContactPhone(contactPhone)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
    }
}
