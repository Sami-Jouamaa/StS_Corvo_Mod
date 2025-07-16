package corvoattanomod.util;

import java.util.Random;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;

public class SpecialBonuses {

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

    public static boolean checkGore()
    {
        Random r = new Random();
        int low = 0;
        int high = 100;
        int result = r.nextInt(high - low) + low;
        return result <= 19;
    }
}
