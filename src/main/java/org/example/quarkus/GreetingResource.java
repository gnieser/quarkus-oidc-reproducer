package org.example.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("/greeting")
public class GreetingResource {

    private List<Greeting> greetings = new ArrayList<>();

    public GreetingResource() {
        Greeting g1 = new Greeting();
        g1.uuid = UUID.fromString("861c8c20-aa2e-4d00-b6e1-312b4977e17a");
        g1.message = "Hello World";

        Greeting g2 = new Greeting();
        g2.uuid = UUID.fromString("a7cc6bad-e06c-4b24-90dd-86e66d55cc9e");
        g2.message = "Bonjour le Monde";

        greetings.add(g1);
        greetings.add(g2);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response list() {
        return Response.ok(greetings).build();
    }
}
