package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class CoffinNails extends BaseCard {
    public static final String ID = makeID("CoffinNails");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    public static final int DAMAGE = 6;
    public static final int UPG_DAMAGE = 2;
    public static final int VULNERABLE = 1;
    public static final int UPG_VULNERABLE = 1;

    public CoffinNails()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(VULNERABLE, UPG_VULNERABLE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (int repeat = 0; repeat < 2; repeat++)
        {
            int damageToDeal = damage;
            if(SpecialBonuses.checkRanged())
            {
                damageToDeal = (int)Math.round(1.5*damageToDeal);
            }
            addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }
}
