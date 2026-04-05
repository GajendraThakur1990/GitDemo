package stepDefination;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        stepDefinationPlace m = new stepDefinationPlace();
        if (stepDefinationPlace.place_id==null) {
            m.i_have_a_valid_place_payload_with("Sheela", "Hindi", "Noida Sector 62");
            m.i_send_a_request_to_the("POST", "addPlaceAPI");
            m.verify_place_id_created_maps_to_using("Sheela", "getPlaceAPI");
        }

    }
}
