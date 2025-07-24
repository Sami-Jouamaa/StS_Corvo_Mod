package corvoattanomod.cards.attacks;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;

public class SummonBloodflyAttack extends BaseCard {
    public static final String ID = makeID("SummonBloodflyAttack");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int NB_REPEAT = 4;

    public SummonBloodflyAttack()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setCostUpgrade(2);
        setExhaust(true);
        setMagic(NB_REPEAT);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot((AbstractGameAction)new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY)));
        addToBot((AbstractGameAction)new VFXAction(new DieDieDieEffect(), 0.7F));
        int numberOfGoreTrigger = 0;
        for (int i = 0; i < NB_REPEAT; i++)
        {
            addToBot(new DamageAllEnemiesAction(p, damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot(new SFXAction("INTIMIDATE"));
        addToBot(new VFXAction(p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
        {
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo,6, false), 3, true, AbstractGameAction.AttackEffect.NONE));
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 6, false), 3, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
