/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem.BountyManager;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class BountyFactory {
    private static final String NoBounty = "Unknown";

    public static BountyType whatBounty(Characters characters, Characters characterSearched)
    {

        BountyType bountyType = getTypeOfBounty(characters);
        BountyType bountyTypeSearched = getTypeOfBounty(characterSearched);
        boolean typeOfResearched = (bountyType == bountyTypeSearched);

        if(typeOfResearched && getTypeOfBounty(characters) == BountyType.BOUNTY_ON)
        {
            float bounty = Float.parseFloat(characters.getBounty().replaceAll("[^\\d.]", ""));
            float bountySearched = Float.parseFloat(characterSearched.getBounty().replaceAll("[^\\d.]", ""));

            if(characters.getBounty().contains("Md") && characterSearched.getBounty().contains("Mi"))
            {
                bounty *= 1000;
            }
            else if (characters.getBounty().contains("Mi") && characterSearched.getBounty().contains("Md"))
            {
                bountySearched *= 1000;
            }

            float diff = bounty - bountySearched;

            if(diff < 0)
            {
                // Arrow Up
                return BountyType.LOWER;
            }
            else if( diff > 0)
            {
                // Arrow down
                return BountyType.UPPER;
            }
            else
            {
                return BountyType.EQUAL;
            }
        }
        else if(typeOfResearched && bountyType == BountyType.NO_RESEARCHED)
        {
            return BountyType.CORRECT_NO_RESEARCHED;
        }
        else if (!typeOfResearched && bountyType == BountyType.BOUNTY_ON)
        {
            return BountyType.NOT_EQUAL;
        }
        else if(typeOfResearched && bountyType == BountyType.UNKNOWN)
        {
            return BountyType.CORRECT_UNKNOWN;
        }
        else
        {
            return bountyType;
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
}
