package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.Burn;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class IncendiaryBolt extends BaseCard {
    public static final String ID = makeID("IncendiaryBolt");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 4;
    private static final int BURN = 4;
    private static final int UPG_BURN = 2;

    public IncendiaryBolt()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE);
        setMagic(BURN, UPG_BURN);
        setCostUpgrade(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int damageToDeal = damage;
        if (SpecialBonuses.checkRanged())
        {
            damageToDeal = (int)Math.round(1.5*damageToDeal);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(m, p, new Burn(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.FIRE));

    }

}
