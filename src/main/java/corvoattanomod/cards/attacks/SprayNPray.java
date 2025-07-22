package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

import java.util.ArrayList;
import java.util.Random;

public class SprayNPray extends BaseCard {
    public static final String ID = makeID("SprayNPray");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.NONE,
            1
    );

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 3;

    public SprayNPray()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<AbstractMonster> allMonsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        if(allMonsters.size() == 1)
        {
            for (int repeat = 0; repeat < magicNumber; repeat++)
            {
                int damageToDeal = damage;
                if(SpecialBonuses.checkRanged())
                {
                    damageToDeal = (int)Math.round(1.5*damageToDeal);
                }
                addToBot(new DamageAction(allMonsters.get(0), new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
        else
        {
            for (int repeat = 0; repeat < magicNumber; repeat++)
            {
                int damageToDeal = damage;
                if(SpecialBonuses.checkRanged())
                {
                    damageToDeal = (int)Math.round(1.5*damageToDeal);
                }
                Random r = new Random();
                int low = 0;
                int high = allMonsters.size();
                int result = r.nextInt(high - low) + low;
                addToBot(new DamageAction(allMonsters.get(result), new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }
}
