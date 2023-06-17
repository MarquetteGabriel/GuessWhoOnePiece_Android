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
                return getBountyUnknown(bountyTypeSearched);
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
    private static String getBountyUnknown(BountyType bountyType)
    {

        switch (bountyType)
        {
            case UNKNOWN:
                return "Both are unknown";
            case NO_RESEARCHED:
                return "The character is not wanted";
            case BOUNTY_ON:
                return "The character is wanted";
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
                return "The character is not wanted";
            case NO_RESEARCHED:
                return "Both are not wanted";
            default :
                return null;
        }
    }
    private static String getBountyOn(BountyType bountyType, Characters characters, Characters characterSearched)
    {
        switch (bountyType)
        {
            case UNKNOWN:
                return "Bounty is Unknown";
            case NO_RESEARCHED:
                return "The character is not wanted";
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
            return "Equal";
        }
        else if(diff <= 50)
        {
            return "Less than 50M gap";
        } else if (diff <= 200) {
            return "Between 50 and 200M gap";
        } else if(diff <= 500)
        {
            return "Between 200 and 500M gap";
        }
        else {
            return "More than 500M gap";
        }
    }
}
