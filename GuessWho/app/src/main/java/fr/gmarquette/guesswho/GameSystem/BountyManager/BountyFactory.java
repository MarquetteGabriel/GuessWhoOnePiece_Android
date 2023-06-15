/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem.BountyManager;

import static java.lang.Integer.parseInt;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class BountyFactory {
    private static final String NoBounty = "Unknown";

    public static String whatBounty(Characters characters, Characters characterSearched)
    {
        BountyType bountyType = getTypeOfBounty(characters);
        BountyType bountyTypeSearched = getTypeOfBounty(characterSearched);
        switch (bountyType)
        {
            case UNKNOWN:
                return getBountyUknown(bountyTypeSearched);
            case NO_RESEARCHED:
                return getNoBounty(bountyTypeSearched);
            case BOUNTY_ON:
                return getBountyOn(bountyTypeSearched, characters, characterSearched);
            default :
                return null;
        }
    }
    private static BountyType getTypeOfBounty(Characters characters)
    {
        switch (characters.getBounty())
        {
            case NoBounty :
                return BountyType.UNKNOWN;
            case "0" :
                return BountyType.NO_RESEARCHED;
            default :
                return BountyType.BOUNTY_ON;
        }
    }
    private static String getBountyUknown(BountyType bountyType)
    {

        switch (bountyType)
        {
            case UNKNOWN:
                return "Les 2 sont inconnus";
            case NO_RESEARCHED:
                return "Le personnage n'est pas recherché";
            case BOUNTY_ON:
                return "Le personnage est recherché";
            default :
                return null;
        }
    }
    private static String getNoBounty(BountyType bountyType)
    {
        switch (bountyType)
        {
            case UNKNOWN:
            case BOUNTY_ON:
                return "Le personnage n'est pas recherché";
            case NO_RESEARCHED:
                return "Le 2 ne sont pas recheché";
            default :
                return null;
        }
    }
    private static String getBountyOn(BountyType bountyType, Characters characters, Characters characterSearched)
    {
        switch (bountyType)
        {
            case UNKNOWN:
                return "La prime est inconnue";
            case NO_RESEARCHED:
                return "Le personnage n'est pas recherché";
            case BOUNTY_ON:
                return getBountyFork(characters, characterSearched);
            default :
                return null;
        }
    }
    private static String getBountyFork(Characters characters, Characters characterSearched)
    {
        int diff = Math.abs(parseInt(characters.getBounty().substring(0, characters.getBounty().length() - 3)) -
                parseInt(characterSearched.getBounty().substring(0, characterSearched.getBounty().length() - 3)));
        if (diff == 0)
        {
            return "Pareil";
        }
        else if(diff <= 50)
        {
            return "-50M d'écart";
        } else if (diff <= 200) {
            return "Entre 50 et 200M d'écart";
        } else if(diff <= 500)
        {
            return "Entre 200 et 500M d'écart";
        }
        else {
            return "Plus de 500 d'écart";
        }
    }
}
