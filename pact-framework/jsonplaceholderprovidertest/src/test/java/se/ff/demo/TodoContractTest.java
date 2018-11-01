package se.ff.demo;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("TodoService") // Set up name of tested provider
@PactFolder("../pacts") // Point where to find pacts (See also section Pacts source in documentation)


public class TodoContractTest {

    URL url;
    {
        try {
            url = new URL("https://jsonplaceholder.typicode.com");
            System.out.println("URL="+url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @State("There is a todo task with id 1") // Method will be run before testing interactions that require "with-data" state
    public void setUpState() {
        System.out.println("There is a todo task with id 1" );
    }



    @TestTarget // Annotation denotes Target that will be used for tests
    public final Target target = new HttpTarget(url); // Out-of-the-box implementation of Target (for more information take a look at Test Target section)
}
