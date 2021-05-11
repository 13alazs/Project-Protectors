package protectors;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CharacterTest {
    // Test Character with
    // 10 max health
    // 10 resource
    // 10 armor
    Character testChar = new Character(0, 0, 0, 0, null, "testChar", 10, "", 10, 10.0, null, null, null, 10);

    // General ability
    Ability ab = new Ability("testAb", 3, 0, 0.0, "", "", 0, "", "");

    @Test
    public void shouldNotBeCasted() {
        Ability ab = new Ability("testAb", 11, 0, 0.0, "", "", 0, "", "");
        assertFalse(testChar.castAbility(ab, null));
    }

    @Test
    public void shouldBeCorrectlyCastedWithExtremeValue() {
        ArrayList<Character> target = new ArrayList();
        target.add(new Character(0, 0, 0, 0, null, "targetChar", 10, "", 10, 10.0, null, null, null, 10));

        Ability abMax = new Ability("testAbMax", testChar.getCurrResource(), 0, 0.0, "", "", 0, "attack", "");
        assertTrue(testChar.castAbility(abMax, target));

        Ability abZero = new Ability("testAb", 0, 0, 0.0, "", "", 0, "attack", "");
        assertTrue(testChar.castAbility(abZero, target));
    }

    @Test
    public void shouldSubtractResource() {
        ArrayList<Character> target = new ArrayList();
        target.add(new Character(0, 0, 0, 0, null, "targetChar", 10, "", 10, 10.0, null, null, null, 10));

        testChar.castAbility(ab, target);
        assertTrue(testChar.getCurrResource() == 2);
    }

    @Test
    public void attackShouldSubtractFromHealth() {
        ArrayList<Character> target = new ArrayList();
        target.add(new Character(0, 0, 0, 0, null, "targetChar", 10, "", 10, 2.0, null, null, null, 10));

        Ability attack = new Ability("attackAb", 3, 0, 3.0, "", "", 0, "attack", "");

        testChar.castAbility(attack, target);
        assertTrue(target.get(0).getCurrHealth() == 9);
    }

    @Test
    public void healShouldChangeHealth() {
        ArrayList<Character> target = new ArrayList();
        target.add(new Character(0, 0, 0, 0, null, "targetChar", 10, "", 10, 2.0, null, null, null, 10));

        Ability heal = new Ability("healAb", 3, 0, -3.0, "", "", 0, "heal", "");

        testChar.castAbility(heal, target);
        assertTrue(target.get(0).getCurrHealth() == 13);
    }

    @Test
    public void resurrectShouldMakeAlive() {
        ArrayList<Character> target = new ArrayList();
        target.add(new Character(0, 0, 0, 0, null, "targetChar", 10, "", 10, 0.0, null, null, null, 10));

        Ability res = new Ability("resAb", 0, 0, 0, "", "", 0, "resurrect", "");
        Ability attack = new Ability("attackAb", 3, 0, 11.0, "", "", 0, "attack", "");

        testChar.castAbility(attack, target);
        assertFalse(target.get(0).isAlive());

        testChar.castAbility(res, target);
        assertTrue(target.get(0).isAlive());
    }

}
