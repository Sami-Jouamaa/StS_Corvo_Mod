package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.SpringrazorEffect;
import corvoattanomod.util.CardStats;

import javax.swing.*;

public class Springrazor extends BaseCard {
    public static final String ID = makeID("Springrazor");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int DAMAGE = 3;
    private static final int REPEAT = 4;
    private static final int MAX_FEAR = 2;

    public Springrazor()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE);
        setCostUpgrade(1);
        setMagic(REPEAT);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(p, p, new SpringrazorEffect(p, 1, damage, REPEAT, MAX_FEAR), 1));
    }
}
