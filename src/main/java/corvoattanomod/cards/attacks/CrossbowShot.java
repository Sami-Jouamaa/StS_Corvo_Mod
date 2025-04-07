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
import corvoattanomod.util.MeleeRanged;

public class CrossbowShot extends BaseCard {
    public static  final String ID = makeID("CrossbowShot");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 5;

    public CrossbowShot()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean triggersBonus = MeleeRanged.checkRanged();
        int damageToDeal = damage;
        if (triggersBonus)
        {
            damageToDeal = (int)Math.round(1.5*damageToDeal);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        // Can use this to sleep enemies for a turn, will sleep them the turn the attack is played though
        addToBot((new StunMonsterAction(m, p, 1)));
    }
}
