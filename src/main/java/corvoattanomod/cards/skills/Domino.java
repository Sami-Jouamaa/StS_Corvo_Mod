package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.DominoEffect;
import corvoattanomod.util.CardStats;

import java.util.ArrayList;

public class Domino extends BaseCard {
    public static final String ID = makeID("Domino");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int DMG_REFLECT = 50;
    private static final int DMG_REFLECT_UPG = 25;

    public Domino()
    {
        super(ID, cardInfo);
        setMagic(DMG_REFLECT, DMG_REFLECT_UPG);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<AbstractMonster> roomMonsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        for (AbstractMonster mo : roomMonsters)
        {
            addToBot(new ApplyPowerAction(mo, p, new DominoEffect(mo, magicNumber, roomMonsters, p)));
        }
    }
}
