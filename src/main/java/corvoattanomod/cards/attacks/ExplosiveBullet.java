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

public class ExplosiveBullet extends BaseCard {
    public static final String ID = makeID("ExplosiveBullet");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 4;

    public ExplosiveBullet()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractMonster previousEnemy = null;
        int mainDamageToDeal = damage;
        if(SpecialBonuses.checkRanged())
        {
            mainDamageToDeal = (int)Math.round(1.5*mainDamageToDeal);
        }
        ArrayList<AbstractMonster> allMonsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        if(allMonsters.size() == 1)
        {
            addToBot(new DamageAction(m, new DamageInfo(p, mainDamageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new DamageAction(m, new DamageInfo(p, mainDamageToDeal/2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        else
        {
            addToBot(new DamageAction(m, new DamageInfo(p, mainDamageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            for (AbstractMonster mo: (AbstractDungeon.getCurrRoom()).monsters.monsters)
            {
                if (previousEnemy == m)
                {
                    addToBot(new DamageAction(mo, new DamageInfo(p, mainDamageToDeal/2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    addToBot(new DamageAction(m, new DamageInfo(p, mainDamageToDeal/2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    break;
                }
                previousEnemy = mo;
            }
        }
    }
}
