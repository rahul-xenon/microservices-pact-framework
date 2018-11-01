package se.ff.demo;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TodoTest {
    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("TodoService", "localhost", 8112, this);

    @Pact(consumer = "TodoClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        System.out.println("createPact");
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");


        DslPart todoResults = new PactDslJsonBody()
                .integerType("userId",1)
                .integerType("id",1)
                .stringType("title","delectus aut autem")
                .booleanType("completed",false)
                .asBody();

        return builder
                .given("There is a todo task with id 1")
                .uponReceiving("A request for todo with id 1")
                .path("/todos/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(todoResults).toPact();

    }

    @Test
    @PactVerification()
    public void doTest() {
        System.setProperty("pact.rootDir","../pacts");  // Change output dir for generated pact-files
        Todo t = new TodoClient("localhost",provider.getPort()).call(1);
        System.out.println("According to test:"+
                "\n----------TOTO-----------\n"+
                t
                +"\n------------------------");
        assertNotNull(t);
        assertTrue(t.getId() >= 0);
    }

}

