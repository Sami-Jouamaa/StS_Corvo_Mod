package corvoattanomod.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class InstantSleepDart  extends BaseCard {
    public static final String ID = makeID("InstantSleepDart");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public InstantSleepDart()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
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
        addToBot((new StunMonsterAction(m, p, 1)));
    }
}
