package com.wholegroup.dbtest;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.googlecode.objectify.ObjectifyService.ofy;

@WebServlet(urlPatterns = "/index.html")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        final PrintWriter out = resp.getWriter();

        try {
            TestCounter testCounter = ofy().load().key(Key.create(TestCounter.class, 1L)).now();
            if (testCounter == null) {
                testCounter = new TestCounter();
                testCounter.id = 1L;
            }
            testCounter.counter++;
            ofy().save().entity(testCounter).now();

            out.println("Calls Counter: " + testCounter.counter);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Entity
    public static class TestCounter {

        @Id
        Long id;

        int counter;
    }
}
