/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saxsys.dojo.skillprofile.business;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco.dierenfeldt
 */
public class ProfileTest {

    ProfileImpl instance = new ProfileImpl();
    int employeeNumber = 666;
    String employeename = "hellchicken metalrocker";
    String[] skills = {"B Java", "B stricken", "B bloggen"};
    String[][] projekts = {{"Microsoft", "Windoof9", "01/05 - 04/11", "Java, bloggen"}, {"Blizzard", "WorldOfWarcraft", "01/05 - 04/11", "bloggen, stricken"}, {"Saxonia", "Dojo", "01/05 - 04/11", "stricken"}};

    public ProfileTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance.setEmployeeNumber(employeeNumber);
        instance.setEmployeeName(employeename);
        instance.setSkills(skills);
        instance.setProjects(projekts);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEmployeeNumber method, of class Profile.
     */
    @Test
    public void testGetterAndSetter() {
        System.out.println("getEmployeeNumber");

        
        int resultEmployeeNumber = instance.getEmployeeNumber();
        String resultEmployeename = instance.getEmployeeName();
        String[] resultSkills = instance.getSkills();
        String[][] resultProjekts = instance.getProjects();

        assertEquals(employeeNumber, resultEmployeeNumber);
        assertEquals(employeename, resultEmployeename);
        for (int i = 0; i < skills.length; i++) {
            String skill = skills[i];
            String resSkill = resultSkills[i];

            assertEquals(skill, resSkill);
        }
        for (int i = 0; i < projekts.length; i++) {
            String[] projekt = projekts[i];
            String[] resultProjekt = resultProjekts[i];
            for (int j = 0; j < projekt.length; j++) {
                String line = projekt[j];
                String resultLine = resultProjekt[j];

                assertEquals(line, resultLine);
            }
        }
    }

    /**
     * Test of toString method, of class Profile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        String expResult = "";
        String result = instance.toString();
//        rudimentary implementation
        assertTrue(result.contains(skills[0]));
        assertTrue(result.contains(projekts[0][2]));
    }
}
