package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;

public class TimeStop extends BaseCard {
    public static final String ID = makeID("TimeStop");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    private static final int INTANGIBLE = 2;
    private static final int UPG_INTANGIBLE = 1;

    public TimeStop()
    {
        super(ID, cardInfo);
        setMagic(INTANGIBLE, UPG_INTANGIBLE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new SkipEnemiesTurnAction());
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, magicNumber)));
    }
}
