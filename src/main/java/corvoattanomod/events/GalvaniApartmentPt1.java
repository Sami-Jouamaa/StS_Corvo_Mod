package corvoattanomod.events;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;

import static corvoattanomod.CorvoAttanoMod.imagePath;
import static corvoattanomod.CorvoAttanoMod.makeID;

public class GalvaniApartmentPt1 extends PhasedEvent {
    public static final String ID = makeID("GalvaniApartment1");

    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private static final String IMG = imagePath("events/GalvaniApartment1.jpg");

    public GalvaniApartmentPt1()
    {
        super(ID, NAME, IMG);

        registerPhase("start", new TextPhase(DESCRIPTIONS[0])
                .addOption(new TextPhase.OptionInfo(OPTIONS[0]).setOptionResult((i)->{
                    AbstractDungeon.player.gainGold(60);
                    transitionKey("middle");
                })));

        registerPhase("middle", new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[1], (i)->transitionKey("fight"))
                .addOption(OPTIONS[2], (i)->openMap()));

        // Will have to replace MonsterHelper.CULTIST_ENC by a City Watch guard encounter (needs to be implemented)
        registerPhase("fight", new CombatPhase(MonsterHelper.CULTIST_ENC)
                .addRewards(true, (room)->room.addGoldToRewards(25))
                .setNextKey("end"));

        registerPhase("end", new TextPhase(DESCRIPTIONS[2])
                .addOption(OPTIONS[2], (i)->openMap()));

        transitionKey("start");
    }
}
