package corvoattanomod.util;

import java.util.Random;

import corvoattanomod.CorvoAttanoMod;
import corvoattanomod.character.CorvoCharacter;

public class MeleeRanged {

    public boolean checkMelee()
    {
        Random r = new Random();
        int low = 1;
        // The high is exclusive, so it goes from 1 to 100.
        int high = 101;
        int result = r.nextInt(high - low) + low;

        if (CorvoCharacter.neutralStance || CorvoCharacter.stealthStance)
        {
            return result <= 25;
        }
        else if (CorvoCharacter.combatStance)
        {
            return result <= 50;
        }
        return false;
    }

    // The chances of each stance are different than for melee
    public boolean checkRanged()
    {
        Random r = new Random();
        int low = 1;
        // The high is exclusive, so it goes from 1 to 100.
        int high = 101;
        int result = r.nextInt(high - low) + low;

        if (CorvoCharacter.neutralStance || CorvoCharacter.combatStance)
        {
            return result <= 25;
        }
        else if (CorvoCharacter.stealthStance)
        {
            return result <= 50;
        }
        return false;
    }
}
