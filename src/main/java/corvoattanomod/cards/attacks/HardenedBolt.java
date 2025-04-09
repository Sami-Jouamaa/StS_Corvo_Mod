package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class HardenedBolt extends BaseCard {
    public static final String ID = makeID("HardenedBolt");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 7;

    public HardenedBolt()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int damageToDeal = damage;
        if (SpecialBonuses.checkRanged())
        {
            damageToDeal = (int)Math.round(1.5*damageToDeal);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
}
