package sampoko.vaadinspringdemo.model;

import org.junit.Test;

import static sampoko.vaadinspringdemo.model.Gender.FEMALE;
import static sampoko.vaadinspringdemo.model.Gender.MALE;
import static sampoko.vaadinspringdemo.model.Gender.getGender;
import static org.junit.Assert.*;


public class GenderTest {

    @Test
    public void testGetValue() throws Exception {
        assertEquals(10, FEMALE.getValue());
    }

    @Test
    public void testGetGender() throws Exception {
        assertEquals(MALE, getGender(0));
    }

    @Test
    public void testInvalidValue() throws Exception {
        try {
            getGender(3);
        } catch (IllegalArgumentException e) {
            return; // success
        }

        throw new Exception("Test failed");
    }

}