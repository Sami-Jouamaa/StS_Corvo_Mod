package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class HowlingBolt extends BaseCard {
    public static final String ID = makeID("HowlingBolt");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 3;
    private static final int WEAK = 1;
    private static final int UPG_WEAK = 1;

    public HowlingBolt()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(WEAK, UPG_WEAK);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int damageToDeal = damage;
        if(SpecialBonuses.checkRanged())
        {
            damageToDeal = (int)Math.round(1.5 * damageToDeal);
        }
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
}
