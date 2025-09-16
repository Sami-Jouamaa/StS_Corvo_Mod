package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import corvoattanomod.CorvoAttanoMod;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;

public class Clairvoyance extends BaseCard {
    public static final String ID = makeID("Clairvoyance");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    public Clairvoyance()
    {
        super(ID, cardInfo);
        setExhaust(true);
        setSelfRetain(true);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int nextMove = m.nextMove;
        String intent = "";
        // Based on monster name, could check its move history and with pattern (wiki), determine the next move.
        // Will have to create a new file in utils that checks that
        // Here, will just call the corresponding function based on the monster's name.
        // Might be slightly inefficient...
        // Will need to check ascension level
        // If there is no pattern for an enemy, tell the next intent based on probability only
        // For example: Will most likely attack if there is a higher than 50% chance
//        switch(m.id)
//        {
//
//        }
        AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, "Enemy will " + intent, true));
    }
}
