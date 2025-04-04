package corvoattanomod.util;

import java.util.Random;

import corvoattanomod.CorvoAttanoMod;
import corvoattanomod.character.CorvoCharacter;

public class MeleeRanged {

    public static boolean checkMelee()
    {
        Random r = new Random();
        int low = 0;
        int high = 100;
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
    public static boolean checkRanged()
    {
        Random r = new Random();
        int low = 0;
        int high = 100;
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
